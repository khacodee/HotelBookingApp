package com.khacv.hotelbookingapp.controller.user;

import com.khacv.hotelbookingapp.dto.user.UserDTO;
import com.khacv.hotelbookingapp.entity.response.ApiResponse;
import com.khacv.hotelbookingapp.entity.response.ErrorResponse;
import com.khacv.hotelbookingapp.entity.room.Room;
import com.khacv.hotelbookingapp.entity.user.AuthRequest;
import com.khacv.hotelbookingapp.entity.user.UserInfo;
import com.khacv.hotelbookingapp.exception.AccessDeniedException;
import com.khacv.hotelbookingapp.exception.ForbiddenException;
import com.khacv.hotelbookingapp.exception.IllegalArgumentException;
import com.khacv.hotelbookingapp.exception.UnauthorizedException;
import com.khacv.hotelbookingapp.repository.user.UserInfoRepository;
import com.khacv.hotelbookingapp.service.user.JwtService;
import com.khacv.hotelbookingapp.service.user.UserService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.khacv.hotelbookingapp.util.Messages.*;

@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"})
@RestController
@RequestMapping("/auth")
public class UserController {
    @Autowired
    private final UserService service;

    @Autowired
    private final JwtService jwtService;

    @Autowired
    private final UserInfoRepository userInfoRepository;
    @Autowired
    private final AuthenticationManager authenticationManager;

    public UserController(UserService service, JwtService jwtService, UserInfoRepository userInfoRepository, AuthenticationManager authenticationManager) {
        this.service = service;
        this.jwtService = jwtService;
        this.userInfoRepository = userInfoRepository;
        this.authenticationManager = authenticationManager;
    }

    @GetMapping("/welcome")
    public ResponseEntity<?> welcome() {
        return ResponseEntity.ok("Welcome this endpoint is not secure");
    }

    @GetMapping("/users/{id}")
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    @PreAuthorize("hasAuthority('VIEW_DETAIL_USER')")
    public ResponseEntity<?> getUserById(@PathVariable int id) {
        ApiResponse<UserInfo> response = new ApiResponse<>();
        try {
            UserInfo userInfo = service.getUserById(id);
            response.setData(userInfo);
        }
        catch (Exception e){
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setCode(400);
            errorResponse.setMessage("ERROR_USER_NOT_FOUND");
            List<String> errorMessages = new ArrayList<>();
            errorMessages.add(e.getMessage());
            errorResponse.setErrors(errorMessages);
            response.setError(errorResponse);
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.ok(response);
    }


    @GetMapping("/users")
    @PreAuthorize("hasAuthority('VIEW_LIST_USER')")
    public ResponseEntity<?> getListUser() {
        ApiResponse<List<UserInfo>> response = new ApiResponse<>();
        try {
            List<UserInfo> userInfo = service.getListUser();
            response.setData(userInfo);
        }
        catch (Exception e){
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setCode(400);
            errorResponse.setMessage("ERROR_USER_NOT_FOUND");
            List<String> errorMessages = new ArrayList<>();
            errorMessages.add(e.getMessage());
            errorResponse.setErrors(errorMessages);
            response.setError(errorResponse);
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping("/users")
    @PreAuthorize("hasAuthority('CREATE_USER')")
    public ResponseEntity<?> addNewUser(@RequestBody UserInfo userInf){
        ApiResponse<UserInfo> response = new ApiResponse<>();
        ErrorResponse errorResponse = new ErrorResponse();
        try {
            UserInfo userInfo = service.addUser(userInf);
            response.setData(userInfo);
        } catch (Exception e) {
            errorResponse.setCode(400);
            errorResponse.setMessage("ERROR_USER_EXISTS_ALREADY");
            List<String> errorMessages = new ArrayList<>();
            errorMessages.add(e.getMessage());
            errorResponse.setErrors(errorMessages);
            response.setError(errorResponse);
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.ok(response);
    }
    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody UserDTO userDTO) {

        ApiResponse<UserInfo> response = new ApiResponse<>();
        ErrorResponse errorResponse = new ErrorResponse();
        try {
            UserInfo userInfo = service.SignUp(userDTO);
            response.setData(userInfo);
        }catch (Exception e){
            errorResponse.setCode(400);
            errorResponse.setMessage("ERROR_USER");
            List<String> errorMessages = new ArrayList<>();
            errorMessages.add(e.getMessage());
            errorResponse.setErrors(errorMessages);
            response.setError(errorResponse);
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.ok(response);
    }

    @PutMapping("/users/{id}")
    @PreAuthorize("hasAuthority('UPDATE_USER')")
    public ResponseEntity<?> updateUser(@PathVariable int id, @RequestBody UserInfo userInfo){
        ApiResponse<UserInfo> response = new ApiResponse<>();
        try {
            UserInfo userInfo1 = service.updateUser(id, userInfo);
            response.setData(userInfo1);
        }catch (Exception e){
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setCode(400);
            errorResponse.setMessage("ERROR_USER_NOT_FOUND");
            List<String> errorMessages = new ArrayList<>();
            errorMessages.add(e.getMessage());
            errorResponse.setErrors(errorMessages);
            response.setError(errorResponse);
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.ok(response);
    }



    @DeleteMapping("/users/{id}")
    @PreAuthorize("hasAuthority('DELETE_USER')")
    public ResponseEntity<?> deleteUser(@PathVariable int id){
        ApiResponse<UserInfo> response = new ApiResponse<>();
        try {
            UserInfo userInfo = service.deleteUser(id);
            response.setData(userInfo);
        }catch (Exception e){
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setCode(400);
            errorResponse.setMessage("ERROR_USER_NOT_FOUND");
            List<String> errorMessages = new ArrayList<>();
            errorMessages.add(e.getMessage());
            errorResponse.setErrors(errorMessages);
            response.setError(errorResponse);
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        ApiResponse<String> response = new ApiResponse<>();
        try {
                Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
                if (authentication.isAuthenticated()) {
                    response.setData(jwtService.generateToken(authRequest.getUsername()));
                    return ResponseEntity.ok(response);
                }
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            } catch (Exception ex) {
                ErrorResponse errorResponse = new ErrorResponse();
                errorResponse.setCode(401);
                errorResponse.setMessage("Error_invalid_credentials");
                List<String> errorMessages = new ArrayList<>();
                errorMessages.add("INVALID_PASSWORD_OR_USERNAME");
                errorResponse.setErrors(errorMessages);
                response.setError(errorResponse);// Custom message for invalid credentials
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
            }

    }

}