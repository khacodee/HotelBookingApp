package com.khacv.hotelbookingapp.service.email;

public interface IEmailService {

   void sendApprovedEmail(int bookingId);

   void sendPaymetsEmail(int bookingId, String PaymemtUrl);
}
