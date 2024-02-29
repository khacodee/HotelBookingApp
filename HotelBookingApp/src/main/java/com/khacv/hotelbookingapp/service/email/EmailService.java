package com.khacv.hotelbookingapp.service.email;

import com.khacv.hotelbookingapp.entity.booking.Booking;
import com.khacv.hotelbookingapp.entity.payment.Payment;
import com.khacv.hotelbookingapp.exception.NotFoundException;
import com.khacv.hotelbookingapp.repository.booking.BookingRepository;
import com.khacv.hotelbookingapp.repository.payment.PaymentRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import static com.khacv.hotelbookingapp.util.Constants.*;
import static com.khacv.hotelbookingapp.util.Messages.*;
@Service
public class EmailService implements IEmailService{
    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private PaymentRepository paymentRepository;
    public void sendApprovedEmail(int bookingId) {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        try {
            Booking booking = bookingRepository.findById(bookingId);
            if(booking == null){
                throw new NotFoundException(NOT_FOUND);
            }
            String emailContent = APPROVE_BOOK +
                    DATE_CHECK_IN + booking.getCheckInDate() +BR +
                    DATE_CHECK_OUT + booking.getCheckOutDate() + BR+
                    ROOM_NUMBER_BOOK + booking.getRoom().getRoomNumber() + BR +
                    HOTEL_NAME + booking.getRoom().getHotel().getName() +BR + BR +
                    "Booker information:<br>" +
                    "Full Name: " + booking.getGuest().getFullName() + BR +
                    "Email: " + booking.getGuest().getEmail() + "<br>" +
                    "Phone Number: " + booking.getGuest().getPhoneNumber();
            helper.setTo(booking.getGuest().getEmail());
            helper.setSubject(RESERVATION_APPROVED);
            helper.setText(emailContent, true);
            mailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }

        @Override
        public void sendPaymetsEmail(int bookingId, String paymemtUrl) {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);
            try {
                Booking booking = bookingRepository.findById(bookingId);
                if(booking == null){
                    throw new NotFoundException(NOT_FOUND);
                }
                String emailContent =
                        "Booker information:<br>" +
                        "Full Name: " + booking.getGuest().getFullName() + BR +
                        "Email: " + booking.getGuest().getEmail() + BR +
                        "Phone Number: " + booking.getGuest().getPhoneNumber() + BR +
                                "Room information:<br>" + "Room number: " + booking.getRoom().getRoomNumber() + BR +
                                "Hotel name: " + booking.getRoom().getHotel().getName() +BR + BR +
                                "Payment information:<br>" +
                                DATE_CHECK_IN + booking.getCheckInDate() +BR +
                                DATE_CHECK_OUT + booking.getCheckOutDate() + BR+
                                ROOM_NUMBER_BOOK + booking.getRoom().getRoomNumber() + BR + BR

                                + "Phí thuê phòng: " + booking.getTotalPrice()
                                + BR + BR +
                                "Thông tin thanh toán: " + paymemtUrl;

                helper.setTo(booking.getGuest().getEmail());
                helper.setSubject("Thông báo thanh toán");
                helper.setText(emailContent, true);
                mailSender.send(message);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
    }


}
