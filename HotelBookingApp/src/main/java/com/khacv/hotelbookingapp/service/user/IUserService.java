package com.khacv.hotelbookingapp.service.user;

import com.khacv.hotelbookingapp.dto.user.UserDTO;
import com.khacv.hotelbookingapp.dto.user.UserInformationDTO;
import com.khacv.hotelbookingapp.entity.user.UserInfo;

import java.util.List;

public interface IUserService {

    UserInformationDTO loadInfoUserByName(String username);

    UserInfo SignUp(UserDTO userDTO);

    UserInfo updateUser(int id, UserInfo userInfo);

    UserInfo getUserById(int id);

    List<UserInfo> getListUser();

    UserInfo deleteUser(int userId);

    UserInfo addUser(UserInfo userInfo);
}
