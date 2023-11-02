package com.khacv.hotelbookingapp.service;

import com.khacv.hotelbookingapp.dto.UserDTO;
import com.khacv.hotelbookingapp.dto.UserInformationDTO;
import com.khacv.hotelbookingapp.dto.UserProfileDTO;
import com.khacv.hotelbookingapp.user.Role;
import com.khacv.hotelbookingapp.user.UserInfo;
import com.khacv.hotelbookingapp.exception.UserNotFoundException;
import com.khacv.hotelbookingapp.repository.RoleRepository;
import com.khacv.hotelbookingapp.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {

    @Autowired
    private UserInfoRepository userRepository;
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private RoleRepository roleRepository;
    public UserInformationDTO loadInfoUserByName(String username){

        Optional<UserInfo> userDetail = userRepository.findByUsername(username);

        if(userDetail.isEmpty()){
            throw new UserNotFoundException("Not Fount");
        }
        UserInfo userInfo = userDetail.get();
        UserInformationDTO userInformationDTO = new UserInformationDTO();
        userInformationDTO.setName(userInfo.getUsername());
        userInformationDTO.setEmail(userInfo.getEmail());

        return userInformationDTO;


    }
    public String SignUp(UserDTO userDTO) {
        if (userRepository.existsByUsernameOrEmail(userDTO.getUsername(), userDTO.getEmail())) {
            throw new UserNotFoundException("Username or email already exists. Please choose a different one.");
        }

        UserInfo userEntity = new UserInfo();
        userEntity.setUsername(userDTO.getUsername());
        userEntity.setPassword(encoder.encode(userDTO.getPassword()));
        userEntity.setEmail(userDTO.getEmail());

        Role role = roleRepository.findByName("USER");

        if (role == null) {
            role = new Role();
            role.setName("USER");
            roleRepository.save(role);
        }

        userEntity.setRoles(Collections.singleton(role));

        userRepository.save(userEntity);

        return "User Added Successfully";
    }


    public String updateProfileUser(int id, UserProfileDTO userProfileDTO){
        UserInfo userInfo = userRepository.findById(id);
        if(userInfo == null){
            throw new UserNotFoundException("User with ID " + id + " not found.");
        }
        if(userProfileDTO.getEmail() != null){
                userInfo.setEmail(userProfileDTO.getEmail());
        }
        if(userProfileDTO.getFullName() != null){
            userInfo.setFullName(userProfileDTO.getFullName());
        }
        if(userProfileDTO.getAddress() != null){
            userInfo.setAddress(userProfileDTO.getAddress());
        }
        if(userProfileDTO.getPhoneNumber() != null){
            userInfo.setPhoneNumber(userProfileDTO.getPhoneNumber());
        }
        userRepository.save(userInfo);
        return "Update profile successfully";
    }
    public String updateUser(int id, UserInfo userInfo){
        UserInfo userInf =userRepository.findById(id);
        if(userInf == null){
            throw new UserNotFoundException("User with ID " + id + " not found.");
        }
        boolean existingUser = userRepository.existsByUsernameOrEmail(userInfo.getUsername(), userInfo.getEmail());
        if (existingUser && !userInf.getUsername().equals(userInfo.getUsername()) && !userInf.getEmail().equals(userInfo.getEmail())) {
            throw new UserNotFoundException("Username or email already exists. Please choose a different one.");
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
        return "Update user Successfully";
    }

    public UserInfo getUserById(int id){

        UserInfo userInfo = userRepository.findById(id);

        if (userInfo == null) {
            // Tạo thông báo khi người dùng không tồn tại
            throw new UserNotFoundException("User with ID " + id + " not found.");
        }
        return userInfo;
    }

    public List<UserInfo> getListUser(){

        List<UserInfo> userInfoList = userRepository.findAll();

        List<UserInfo> result = new ArrayList<>();

        for (UserInfo userInfo : userInfoList){
            boolean isAdmin = false;
            for (Role role : userInfo.getRoles()){
                if(role.getName().contains("ADMIN")){
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

    public String deleteUser(int userId) {
        UserInfo userInf =userRepository.findById(userId);

        if (userInf == null) {
            throw new UserNotFoundException("User with ID " + userId + " not found.");
        }

        // Xóa mối quan hệ người dùng trong bảng user_role
        userInf.getRoles().clear();
        userRepository.save(userInf);

        // Xóa người dùng
        userRepository.delete(userInf);

        return "Delete User Successfully";
    }


    public String addUser(UserInfo userInfo) {
        if (userRepository.existsByUsernameOrEmail(userInfo.getUsername(), userInfo.getEmail())) {
            throw new UserNotFoundException("Username or email already exists. Please choose a different one.");
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

        return "User Added Successfully";
    }
}
