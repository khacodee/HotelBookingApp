package com.khacv.hotelbookingapp.controller.user;

import com.khacv.hotelbookingapp.dto.user.UserDTO;
import com.khacv.hotelbookingapp.entity.user.AuthRequest;
import com.khacv.hotelbookingapp.entity.user.UserInfo;
import com.khacv.hotelbookingapp.exception.AccessDeniedException;
import com.khacv.hotelbookingapp.exception.UnauthorizedException;
import com.khacv.hotelbookingapp.repository.user.UserInfoRepository;
import com.khacv.hotelbookingapp.service.user.JwtService;
import com.khacv.hotelbookingapp.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import static com.khacv.hotelbookingapp.util.Messages.ERROR;
import static com.khacv.hotelbookingapp.util.Messages.INVALID_USERNAME_PASSWORD;

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
    public ResponseEntity<?> getUserById(@PathVariable int id){
        try {
        return ResponseEntity.ok(service.getUserById(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ERROR + e.getMessage());
        }
    }

    @GetMapping("/users")
    @PreAuthorize("hasAuthority('VIEW_LIST_USER')")
    public ResponseEntity<?> getListUser(){
        try {
        // Code xử lý khi có quyền truy cập
        return ResponseEntity.ok(service.getListUser());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ERROR + e.getMessage());
        }
    }
    @PostMapping("/users")
    @PreAuthorize("hasAuthority('CREATE_USER')")
    public ResponseEntity<?> addNewUser(@RequestBody UserInfo userInfo) {

        try {
        return ResponseEntity.ok(service.addUser(userInfo));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ERROR + e.getMessage());
        }
    }
    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody UserDTO userDTO) {
        try {
        return ResponseEntity.ok(service.SignUp(userDTO));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ERROR + e.getMessage());
        }
    }

    @PutMapping("/users/{id}")
    @PreAuthorize("hasAuthority('UPDATE_USER')")
    public ResponseEntity<?> updateUser(@PathVariable int id, @RequestBody UserInfo userInfo){
        try {

        return ResponseEntity.ok(service.updateUser(id, userInfo));
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ERROR + e.getMessage());
        }
    }



    @DeleteMapping("/users/{id}")
    @PreAuthorize("hasAuthority('DELETE_USER')")
    public ResponseEntity<?> deleteUser(@PathVariable int id){
        try {
        return ResponseEntity.ok(service.deleteUser(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ERROR + e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateAndGetToken(@RequestBody AuthRequest authRequest) {

        try{
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
            if (authentication.isAuthenticated()) {
                return ResponseEntity.ok("token: "+jwtService.generateToken(authRequest.getUsername()));
            }
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        catch (Exception ex) {
            throw new UnauthorizedException(INVALID_USERNAME_PASSWORD);
        }
    }

}