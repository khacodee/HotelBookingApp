package com.khacv.hotelbookingapp.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name ="permission")
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "permission_id")
    private int id;

    private String name;

}
