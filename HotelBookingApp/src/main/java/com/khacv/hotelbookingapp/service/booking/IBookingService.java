package com.khacv.hotelbookingapp.service.booking;

import com.khacv.hotelbookingapp.dto.booking.BookingRoomDTO;
import com.khacv.hotelbookingapp.entity.booking.Booking;

import java.util.List;

public interface IBookingService {


    List<Booking> getListBooking();
    Booking getBookingById(int id);
    String createBooking(BookingRoomDTO bookingRoomDTO);

    String updateBooking(int id, BookingRoomDTO bookingRoomDTO);
    String approveBookRoom(int id);
    String rejectedBookRoom(int id);
    List<Booking> getListBookingApprove();

    String deleteBooking(int id);
}
