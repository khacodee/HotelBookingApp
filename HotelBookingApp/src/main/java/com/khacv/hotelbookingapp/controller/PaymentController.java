package com.khacv.hotelbookingapp.controller;

import com.khacv.hotelbookingapp.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/app")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;


    @GetMapping("/payment")
    public ResponseEntity<?> getListPayment(){
        return ResponseEntity.ok(paymentService.getListPayment());
    }

    @GetMapping("/payment/{id}")
    public ResponseEntity<?> getPaymentById(@PathVariable int id){
        return ResponseEntity.ok(paymentService.getPaymentById(id));
    }
}
