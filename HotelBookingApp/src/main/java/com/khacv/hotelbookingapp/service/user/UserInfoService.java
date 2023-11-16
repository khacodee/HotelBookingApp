package com.khacv.hotelbookingapp.service.user;


import com.khacv.hotelbookingapp.exception.UserNotFoundException;
import com.khacv.hotelbookingapp.repository.role.RoleRepository;
import com.khacv.hotelbookingapp.repository.user.UserInfoRepository;
import com.khacv.hotelbookingapp.entity.user.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserInfoService implements UserDetailsService {
    @Autowired
    private UserInfoRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<UserInfo> userDetail = userRepository.findByUsername(username);
        if(userDetail.isEmpty()){
            throw new UserNotFoundException("Not Found!!");
        }
        // Converting userDetail to UserDetails
        return userDetail.map(UserInfoDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found " + username));
    }

}
