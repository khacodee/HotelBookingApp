package com.khacv.hotelbookingapp.repository;

import com.khacv.hotelbookingapp.user.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Integer> {
    Permission findByName(String name);
}
