package com.khacv.hotelbookingapp.repository;

import com.khacv.hotelbookingapp.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {

    Payment findById(int id);
}
