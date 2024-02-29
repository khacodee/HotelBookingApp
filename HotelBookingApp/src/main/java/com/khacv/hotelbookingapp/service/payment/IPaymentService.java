package com.khacv.hotelbookingapp.service.payment;

import com.khacv.hotelbookingapp.dto.payment.PaymentDTO;
import com.khacv.hotelbookingapp.entity.payment.Payment;

import java.util.List;

public interface IPaymentService {
    List<Payment> getListPayment();

    Payment getPaymentById(int id);

    Payment createPayment(PaymentDTO paymentDTO);

    Payment updatePayment(int id, PaymentDTO paymentDTO);
    Payment deletePayment(int id);

}
