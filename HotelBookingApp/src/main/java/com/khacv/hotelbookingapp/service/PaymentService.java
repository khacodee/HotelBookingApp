package com.khacv.hotelbookingapp.service;

import com.khacv.hotelbookingapp.entity.Payment;
import com.khacv.hotelbookingapp.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;

    public List<Payment> getListPayment(){
        return paymentRepository.findAll();
    }
    public Payment getPaymentById(int id){
        return paymentRepository.findById(id);
    }
}
