package com.khacv.hotelbookingapp.controller.payment;

import com.khacv.hotelbookingapp.dto.payment.PaymentDTO;
import com.khacv.hotelbookingapp.entity.hotel.Hotel;
import com.khacv.hotelbookingapp.entity.payment.Payment;
import com.khacv.hotelbookingapp.entity.response.ApiResponse;
import com.khacv.hotelbookingapp.entity.response.ErrorResponse;
import com.khacv.hotelbookingapp.exception.IllegalArgumentException;
import com.khacv.hotelbookingapp.service.payment.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static com.khacv.hotelbookingapp.util.Messages.*;

@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"})
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
        ApiResponse<List<Payment>> response = new ApiResponse<>();
        try {
            List<Payment> payments = paymentService.getListPayment();
            response.setData(payments);
        }catch (Exception e){
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setCode(404); // Not Found
            errorResponse.setMessage("ERROR_PAYMENT");
            List<String> errorMessages = new ArrayList<>();
            errorMessages.add(e.getMessage());
            errorResponse.setErrors(errorMessages);
            response.setError(errorResponse);
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/payment/{id}")
    public ResponseEntity<?> getPaymentById(@PathVariable int id){
        ApiResponse<Payment> response = new ApiResponse<>();
        try {
            Payment payment = paymentService.getPaymentById(id);
            response.setData(payment);
        }catch (Exception e){
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setCode(404); // Not Found
            errorResponse.setMessage("ERROR_PAYMENT");
            List<String> errorMessages = new ArrayList<>();
            errorMessages.add(e.getMessage());
            errorResponse.setErrors(errorMessages);
            response.setError(errorResponse);
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.ok(response);
    }

    @PutMapping("/payment/{id}")
    public ResponseEntity<?> updatePayment(@PathVariable int id, @RequestBody PaymentDTO paymentDTO){
        ApiResponse<Payment> response = new ApiResponse<>();
        try {
            Payment payment = paymentService.updatePayment(id, paymentDTO);
            response.setData(payment);
        }catch (Exception e){
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setCode(404); // Not Found
            errorResponse.setMessage("ERROR_PAYMENT");
            List<String> errorMessages = new ArrayList<>();
            errorMessages.add(e.getMessage());
            errorResponse.setErrors(errorMessages);
            response.setError(errorResponse);
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/payment/{id}")
    public ResponseEntity<?> deletePayment(@PathVariable int id){
        ApiResponse<Payment> response = new ApiResponse<>();
        try {
            Payment payment = paymentService.deletePayment(id);
            response.setData(payment);
        }catch (Exception e){
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setCode(404); // Not Found
            errorResponse.setMessage("ERROR_PAYMENT");
            List<String> errorMessages = new ArrayList<>();
            errorMessages.add(e.getMessage());
            errorResponse.setErrors(errorMessages);
            response.setError(errorResponse);
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.ok(response);
    }
}
