package com.khacv.hotelbookingapp.service.email;

public interface IEmailService {

   String sendApprovedEmail(int bookingId, String recipientEmail);
}
