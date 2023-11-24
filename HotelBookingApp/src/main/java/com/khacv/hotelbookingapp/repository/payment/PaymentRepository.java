package com.khacv.hotelbookingapp.repository.payment;

import com.khacv.hotelbookingapp.entity.payment.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {

    Payment findById(int id);

    Payment findByBookingId(int bookingId);
}
