package com.khacv.hotelbookingapp.service.payment;

import com.khacv.hotelbookingapp.entity.payment.Payment;
import com.khacv.hotelbookingapp.repository.payment.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService implements IPaymentService {
    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public List<Payment> getListPayment(){
        return paymentRepository.findAll();
    }

    @Override
    public Payment getPaymentById(int id){
        return paymentRepository.findById(id);
    }
}
