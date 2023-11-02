package com.khacv.hotelbookingapp.repository;


import com.khacv.hotelbookingapp.user.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByName(String name);
}