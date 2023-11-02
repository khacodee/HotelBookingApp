package com.khacv.hotelbookingapp.repository;

import com.khacv.hotelbookingapp.user.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Integer> {
    Optional<UserInfo> findByUsername(String username);

    boolean existsByUsernameOrEmail(String username, String email);

    UserInfo findById(int id);
}
