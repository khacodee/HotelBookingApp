package com.khacv.hotelbookingapp.controller.email;

import com.khacv.hotelbookingapp.service.email.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"})
@RestController
@RequestMapping("/app")
public class EmailController {

    @Autowired
    private EmailService emailService; // Thay YourEmailService bằng tên của service của bạn

    @PostMapping("/send-payment-email/{bookingId}")
    public String sendPaymentEmail(@PathVariable int bookingId, @RequestBody String paymentUrl) {
        emailService.sendPaymetsEmail(bookingId, paymentUrl);
        return "Email sent successfully";
    }
}

