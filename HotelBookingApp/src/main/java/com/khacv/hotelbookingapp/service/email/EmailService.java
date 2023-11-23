package com.khacv.hotelbookingapp.service.email;

import com.khacv.hotelbookingapp.entity.booking.Booking;
import com.khacv.hotelbookingapp.repository.booking.BookingRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import static com.khacv.hotelbookingapp.util.Constants.*;
import static com.khacv.hotelbookingapp.util.Messages.*;

@Service
public class EmailService implements IEmailService{
    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private BookingRepository bookingRepository;
    public void sendApprovedEmail(int bookingId, String recipientEmail) {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        try {
            Booking booking = bookingRepository.findById(bookingId);
            String emailContent = APPROVE_BOOK +
                    DATE_CHECK_IN + booking.getCheckInDate() +BR +
                    DATE_CHECK_OUT + booking.getCheckOutDate() + BR+
                    ROOM_NUMBER_BOOK + booking.getRoom().getRoomNumber() + BR +
                    HOTEL_NAME + booking.getRoom().getHotel().getName() + BR + BR +
                    "Booker information:<br>" +
                    "Full Name: " + booking.getGuest().getFullName() + BR +
                    "Email: " + booking.getGuest().getEmail() + "<br>" +
                    "Phone Number: " + booking.getGuest().getPhoneNumber();
            helper.setTo(recipientEmail);
            helper.setSubject(RESERVATION_APPROVED);
            helper.setText(emailContent, true);
            mailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }
}
