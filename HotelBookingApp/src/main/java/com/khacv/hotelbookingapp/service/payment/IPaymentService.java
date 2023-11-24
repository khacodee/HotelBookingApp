package com.khacv.hotelbookingapp.service.payment;

import com.khacv.hotelbookingapp.dto.payment.PaymentDTO;
import com.khacv.hotelbookingapp.entity.payment.Payment;

import java.util.List;

public interface IPaymentService {
    List<Payment> getListPayment();

    Payment getPaymentById(int id);

    String createPayment(PaymentDTO paymentDTO);

    String updatePayment(int id, PaymentDTO paymentDTO);
    String deletePayment(int id);

}
