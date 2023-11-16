package com.khacv.hotelbookingapp.entity.room;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.khacv.hotelbookingapp.entity.room.Room;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "room_images")
public class RoomImage implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
    private int imageId;
    @ManyToOne
    @JoinColumn(name = "room_id")
    @JsonBackReference
    private Room room;
    private String imageUrl;
}
