package com.khacv.hotelbookingapp.controller.payment;

import com.khacv.hotelbookingapp.exception.IllegalArgumentException;
import com.khacv.hotelbookingapp.service.payment.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.khacv.hotelbookingapp.util.Messages.*;

@RestController
@RequestMapping("/app")
public class PaymentController {
    @Autowired
    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }


    @GetMapping("/payment")
    public ResponseEntity<?> getListPayment(){
        try {
        return ResponseEntity.ok(paymentService.getListPayment());
        }catch (Exception e){
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    @GetMapping("/payment/{id}")
    public ResponseEntity<?> getPaymentById(@PathVariable int id){
        try {
        return ResponseEntity.ok(paymentService.getPaymentById(id));
        }catch (Exception e){
            throw new IllegalArgumentException(e.getMessage());
        }
    }
}
