package com.khacv.hotelbookingapp.repository.user;

import com.khacv.hotelbookingapp.entity.user.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Integer> {
    Optional<UserInfo> findByUsername(String username);

    boolean existsByUsername(String username);

    UserInfo findById(int id);
}
