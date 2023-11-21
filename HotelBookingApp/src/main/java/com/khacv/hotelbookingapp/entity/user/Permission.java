package com.khacv.hotelbookingapp.entity.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.khacv.hotelbookingapp.util.Constants.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name =PERMISSION)
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = PERMISSION_ID)
    private int id;

    private String name;

}
