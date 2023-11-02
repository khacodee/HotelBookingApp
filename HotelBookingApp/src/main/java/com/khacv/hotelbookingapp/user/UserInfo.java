package com.khacv.hotelbookingapp.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
public class UserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int id;
    private String username;
    private String email;
    private String password;
    @Column(name = "full_name")
    private String fullName;
    private String address;
    @Column(name = "phone_number")
    private String phoneNumber;



    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

}
