package com.khacv.hotelbookingapp.service.payment;

import com.khacv.hotelbookingapp.dto.payment.PaymentDTO;
import com.khacv.hotelbookingapp.entity.booking.Booking;
import com.khacv.hotelbookingapp.entity.payment.Payment;
import com.khacv.hotelbookingapp.exception.NotFoundException;
import com.khacv.hotelbookingapp.repository.booking.BookingRepository;
import com.khacv.hotelbookingapp.repository.payment.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.khacv.hotelbookingapp.util.Messages.*;

@Service
public class PaymentService implements IPaymentService {
    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private  BookingRepository bookingRepository;

    @Override
    public List<Payment> getListPayment(){
        return paymentRepository.findAll();
    }

    @Override
    public Payment getPaymentById(int id){
        return paymentRepository.findById(id);
    }

    @Override
    public String createPayment(PaymentDTO paymentDTO){

        Payment payment = new Payment();

        Booking booking = bookingRepository.findById(paymentDTO.getBookingId());

        if(booking == null){
            throw new NotFoundException("NOT FOUND");
        }
        payment.setPaymentDate(paymentDTO.getPaymentDate());
        payment.setAmount(paymentDTO.getAmount());
        payment.setBooking(booking);
        payment.setStatus(paymentDTO.getStatus());

        paymentRepository.save(payment);

        return ADDED_SUCCESSFULLY;
    }

    @Override
    public String updatePayment(int id, PaymentDTO paymentDTO) {
        Payment payment = getPaymentById(id);
        payment.setPaymentDate(paymentDTO.getPaymentDate());
        payment.setAmount(paymentDTO.getAmount());
        payment.setStatus(paymentDTO.getStatus());
        paymentRepository.save(payment);
        return UPDATE_SUCCESSFUL;
    }

    @Override
    public String deletePayment(int id) {
        Payment payment = getPaymentById(id);
        paymentRepository.delete(payment);
        return DELETE_SUCCESSFUL;
    }


}
