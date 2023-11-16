package com.khacv.hotelbookingapp.service.user;

import com.khacv.hotelbookingapp.dto.user.UserDTO;
import com.khacv.hotelbookingapp.dto.user.UserInformationDTO;
import com.khacv.hotelbookingapp.entity.user.Role;
import com.khacv.hotelbookingapp.entity.user.UserInfo;
import com.khacv.hotelbookingapp.exception.UserNotFoundException;
import com.khacv.hotelbookingapp.repository.role.RoleRepository;
import com.khacv.hotelbookingapp.repository.user.UserInfoRepository;
import com.khacv.hotelbookingapp.util.Constants;
import com.khacv.hotelbookingapp.util.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.khacv.hotelbookingapp.util.Constants.*;
import static com.khacv.hotelbookingapp.util.Messages.*;


@Service
public class UserService implements IUserService{

    @Autowired
    private UserInfoRepository userRepository;
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public UserInformationDTO loadInfoUserByName(String username){

        Optional<UserInfo> userDetail = userRepository.findByUsername(username);

        if(userDetail.isEmpty()){
            throw new UserNotFoundException(NOT_FOUND);
        }
        UserInfo userInfo = userDetail.get();
        UserInformationDTO userInformationDTO = new UserInformationDTO();
        userInformationDTO.setName(userInfo.getUsername());
        userInformationDTO.setEmail(userInfo.getEmail());

        return userInformationDTO;


    }

    @Override
    public String SignUp(UserDTO userDTO) {
        if (userRepository.existsByUsernameOrEmail(userDTO.getUsername(), userDTO.getEmail())) {
            throw new UserNotFoundException(USERNAME_OR_EMAIL_EXISTS);
        }

        UserInfo userEntity = new UserInfo();
        userEntity.setUsername(userDTO.getUsername());
        userEntity.setPassword(encoder.encode(userDTO.getPassword()));
        userEntity.setEmail(userDTO.getEmail());

        Role role = roleRepository.findByName(USER_ROLE);

        if (role == null) {
            role = new Role();
            role.setName(USER_ROLE);
            roleRepository.save(role);
        }

        userEntity.setRoles(Collections.singleton(role));

        userRepository.save(userEntity);

        return ADDED_SUCCESSFULLY;
    }

    @Override
    public String updateUser(int id, UserInfo userInfo){
        UserInfo userInf =userRepository.findById(id);
        if(userInf == null){
            throw new UserNotFoundException(NOT_FOUND);
        }
        boolean existingUser = userRepository.existsByUsernameOrEmail(userInfo.getUsername(), userInfo.getEmail());
        if (existingUser && !userInf.getUsername().equals(userInfo.getUsername()) && !userInf.getEmail().equals(userInfo.getEmail())) {
            throw new UserNotFoundException(USERNAME_OR_EMAIL_EXISTS);
        }
        userInf.setUsername(userInfo.getUsername());
        if(!userInfo.getPassword().isEmpty()){
            userInf.setPassword(encoder.encode(userInfo.getPassword()));
        }
        userInf.setEmail(userInfo.getEmail());
        Set<Role> updateRole = new HashSet<>();
        if(userInfo.getRoles() != null && !userInfo.getRoles().isEmpty()){
            for (Role role :
                    userInfo.getRoles()) {
                Role existingRole = roleRepository.findByName(role.getName());
                if (existingRole == null) {
                    roleRepository.save(role);
                    updateRole.add(role);
                } else {
                    updateRole.add(existingRole);
                }
            }
        }
        else {
            updateRole =userInf.getRoles();
        }
        userInf.setRoles(updateRole);


        userRepository.save(userInf);
        return UPDATE_SUCCESSFUL;
    }


    @Override
    public UserInfo getUserById(int id){

        UserInfo userInfo = userRepository.findById(id);

        if (userInfo == null) {
            // Tạo thông báo khi người dùng không tồn tại
            throw new UserNotFoundException(NOT_FOUND);
        }
        return userInfo;
    }

    @Override
    public List<UserInfo> getListUser(){

        List<UserInfo> userInfoList = userRepository.findAll();

        List<UserInfo> result = new ArrayList<>();

        for (UserInfo userInfo : userInfoList){
            boolean isAdmin = false;
            for (Role role : userInfo.getRoles()){
                if(role.getName().contains(ADMIN_ROLE)){
                    isAdmin = true;
                    break;
                }
            }
            if(!isAdmin){
                result.add(userInfo);
            }
        }
        return result;
    }

    @Override
    public String deleteUser(int userId) {
        UserInfo userInf =userRepository.findById(userId);

        if (userInf == null) {
            throw new UserNotFoundException(NOT_FOUND);
        }

        // Xóa mối quan hệ người dùng trong bảng user_role
        userInf.getRoles().clear();
        userRepository.save(userInf);

        // Xóa người dùng
        userRepository.delete(userInf);

        return DELETE_SUCCESSFUL;
    }


    @Override
    public String addUser(UserInfo userInfo) {
        if (userRepository.existsByUsernameOrEmail(userInfo.getUsername(), userInfo.getEmail())) {
            throw new UserNotFoundException(USERNAME_OR_EMAIL_EXISTS);
        }

        userInfo.setPassword(encoder.encode(userInfo.getPassword()));
        Set<Role> userRoles = new HashSet<>();
        for (Role role : userInfo.getRoles()) {
            Role existingRole = roleRepository.findByName(role.getName());
            if (existingRole == null) {
                roleRepository.save(role);
                userRoles.add(role);
            }else {
                userRoles.add(existingRole);
            }

        }
        userInfo.setRoles(userRoles);

        userRepository.save(userInfo);

        return ADDED_SUCCESSFULLY;
    }
}
