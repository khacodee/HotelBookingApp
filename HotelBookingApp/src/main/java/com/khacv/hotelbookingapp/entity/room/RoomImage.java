package com.khacv.hotelbookingapp.entity.room;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.khacv.hotelbookingapp.entity.room.Room;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

import static com.khacv.hotelbookingapp.util.Constants.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name =ROOM_IMAGES)
public class RoomImage implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = IMAGE_ID)
    private int imageId;
    @ManyToOne
    @JoinColumn(name = ROOM_ID)
    @JsonBackReference
    private Room room;
    private String imageUrl;
}
