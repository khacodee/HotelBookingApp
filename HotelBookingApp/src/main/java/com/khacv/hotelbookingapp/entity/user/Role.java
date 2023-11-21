package com.khacv.hotelbookingapp.entity.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.HashSet;
import java.util.Set;

import static com.khacv.hotelbookingapp.util.Constants.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name =ROLE)
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = ROLE_ID)
    private int id;
    private String name;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = ROLE_PERMISSION,
            joinColumns = @JoinColumn(name = ROLE_ID),
            inverseJoinColumns = @JoinColumn(name =PERMISSION_ID)
    )
    private Set<Permission> permissions =new HashSet<>();
}