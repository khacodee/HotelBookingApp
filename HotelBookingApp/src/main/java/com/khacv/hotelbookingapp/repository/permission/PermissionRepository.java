package com.khacv.hotelbookingapp.repository.permission;

import com.khacv.hotelbookingapp.entity.user.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Integer> {
    Permission findByName(String name);
}
