package com.khacv.hotelbookingapp.controller.vnpay;

import com.khacv.hotelbookingapp.dto.payment.PaymentDTO;
import com.khacv.hotelbookingapp.dto.payment.PaymentResDTO;
import com.khacv.hotelbookingapp.dto.payment.TransactionDTO;
import com.khacv.hotelbookingapp.entity.booking.Booking;
import com.khacv.hotelbookingapp.entity.guest.Guest;
import com.khacv.hotelbookingapp.service.booking.BookingService;
import com.khacv.hotelbookingapp.service.email.EmailService;
import com.khacv.hotelbookingapp.service.guest.GuestService;
import com.khacv.hotelbookingapp.service.payment.PaymentService;
import com.khacv.hotelbookingapp.config.VNPAYConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"})
@RestController
@RequestMapping("/app")
public class VNPayController {

    @Autowired
    private EmailService emailService;

    @Autowired
    private GuestService guestService;
    @Autowired
    private BookingService bookingService;
    @Autowired
    private PaymentService paymentService;
    @GetMapping("/create-payment")
    public ResponseEntity<?> createPayment(
            @RequestParam(value = "bookingId") int bookingId
    ) throws UnsupportedEncodingException {


        Booking booking = bookingService.getBookingById(bookingId);

        BigDecimal totalPriceDecimal = new BigDecimal(String.valueOf(booking.getTotalPrice()));

        BigDecimal amountDecimal = totalPriceDecimal.multiply(BigDecimal.valueOf(100));

        long amount = amountDecimal.longValue();
        String vnp_TxnRef = VNPAYConfig.getRandomNumber(8);
        String vnp_IpAdr ="127.0.0.1";
        //String bankCode = "NCB";
        String vnp_TmnCode = VNPAYConfig.vnp_TmnCode;

        Map<String, String> vnp_Params = new HashMap<>();
        vnp_Params.put("vnp_Version", VNPAYConfig.vnp_Version);
        vnp_Params.put("vnp_Command", VNPAYConfig.vnp_Command);
        vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
        vnp_Params.put("vnp_Amount", String.valueOf(amount));
        vnp_Params.put("vnp_CurrCode", "VND");
        vnp_Params.put("vnp_BankCode", "");
        vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
        vnp_Params.put("vnp_ReturnUrl", VNPAYConfig.vnp_ReturnUrl);
        vnp_Params.put("vnp_OrderType", VNPAYConfig.orderType);
        vnp_Params.put("vnp_OrderInfo", "Thanh toan don hang:" + vnp_TxnRef+ " | bookingId:" + bookingId);
        vnp_Params.put("vnp_Locale", "vn");
        vnp_Params.put("vnp_IpAddr", vnp_IpAdr);



        Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String vnp_CreateDate = formatter.format(cld.getTime());
        vnp_Params.put("vnp_CreateDate", vnp_CreateDate);

        cld.add(Calendar.MINUTE, 15);
        String vnp_ExpireDate = formatter.format(cld.getTime());
        vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate);

        List fieldNames = new ArrayList(vnp_Params.keySet());
        Collections.sort(fieldNames);
        StringBuilder hashData = new StringBuilder();
        StringBuilder query = new StringBuilder();
        Iterator itr = fieldNames.iterator();
        while (itr.hasNext()) {
            String fieldName = (String) itr.next();
            String fieldValue = (String) vnp_Params.get(fieldName);
            if ((fieldValue != null) && (fieldValue.length() > 0)) {
                //Build hash data
                hashData.append(fieldName);
                hashData.append('=');
                hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                //Build query
                query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()));
                query.append('=');
                query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                if (itr.hasNext()) {
                    query.append('&');
                    hashData.append('&');
                }
            }
        }
        String queryUrl = query.toString();
        String vnp_SecureHash = VNPAYConfig.hmacSHA512(VNPAYConfig.secretKey, hashData.toString());
        queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
        String paymentUrl = VNPAYConfig.vnp_PayUrl + "?" + queryUrl;

        PaymentResDTO paymentResDTO = new PaymentResDTO();
        paymentResDTO.setStatus("Ok");
        paymentResDTO.setMessage("Successfully");
        paymentResDTO.setURL(paymentUrl);


        return ResponseEntity.status(HttpStatus.OK).body(paymentResDTO);
    }

    @GetMapping("/payment-information")
    public ResponseEntity<?> transaction(
            @RequestParam(value = "vnp_Amount") String amount,
            @RequestParam(value = "vnp_BankCode") String bankCode,
            @RequestParam(value = "vnp_OrderInfo") String orderInfo,
            @RequestParam(value = "vnp_ResponseCode") String responseCode,
            @RequestParam(value = "vnp_PayDate") String createDate
    ) throws ParseException {
        TransactionDTO transactionDTO = new TransactionDTO();
        PaymentDTO paymentDTO = new PaymentDTO();
        if(responseCode.equals("00")){
            transactionDTO.setStatus("Ok");
            transactionDTO.setMessage("Successfully");
            transactionDTO.setData("");

            String[] parts = orderInfo.split("\\|");
            int bookingId = -1;
            for (String part : parts) {
                if (part.contains("bookingId")) {
                    String[] keyValue = part.split(":");
                    if (keyValue.length > 1) {
                        bookingId = Integer.parseInt(keyValue[1].trim());
                        break;

                    }
                }
            }
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
            Date paymentDate = formatter.parse(createDate);
            paymentDTO.setPaymentDate(paymentDate);

            BigDecimal value = BigDecimal.valueOf(Long.parseLong(amount));
            BigDecimal divisor = BigDecimal.valueOf(100);
            BigDecimal result = value.divide(divisor);
            paymentDTO.setAmount(result);

            paymentDTO.setBookingId(bookingId);

            paymentDTO.setStatus("SUCCESSFULLY");

            paymentService.createPayment(paymentDTO);

            Guest guestEmail = guestService.findGuestByBookingId(bookingId);

            bookingService.approveBookRoom(bookingId);


            emailService.sendApprovedEmail(bookingId);
        }else {
            transactionDTO.setStatus("No");
            transactionDTO.setMessage("Failed");
            transactionDTO.setData("");
        }
        return ResponseEntity.status(HttpStatus.OK).body(transactionDTO);

    }
}

