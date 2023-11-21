package com.khacv.hotelbookingapp.entity.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

import static com.khacv.hotelbookingapp.util.Constants.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = USER)
public class UserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = USER_ID)
    private int id;
    private String username;
    private String email;
    private String password;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = USER_ROLE,
            joinColumns = @JoinColumn(name = USER_ID),
            inverseJoinColumns = @JoinColumn(name = ROLE_ID)
    )
    private Set<Role> roles = new HashSet<>();

}
