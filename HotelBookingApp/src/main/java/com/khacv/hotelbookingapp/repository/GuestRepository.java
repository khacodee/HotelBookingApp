package com.khacv.hotelbookingapp.repository;

import com.khacv.hotelbookingapp.entity.Guest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuestRepository extends JpaRepository<Guest, Integer> {
    Guest findById(int id);
}
