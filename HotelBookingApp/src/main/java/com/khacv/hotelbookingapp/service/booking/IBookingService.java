package com.khacv.hotelbookingapp.service.booking;

import com.khacv.hotelbookingapp.dto.booking.BookingRoomDTO;
import com.khacv.hotelbookingapp.dto.booking.BookingWithGuestDTO;
import com.khacv.hotelbookingapp.dto.guest.GuestDTO;
import com.khacv.hotelbookingapp.entity.booking.Booking;
import com.khacv.hotelbookingapp.entity.guest.Guest;

import java.util.List;

public interface IBookingService {


    List<Booking> getListBooking();
    Booking getBookingById(int id);
   Booking createBooking(BookingRoomDTO bookingRoomDTO);

    Guest createBookingWithGuest(BookingWithGuestDTO bookingWithGuestDTO);

    Booking updateBooking(int id, BookingRoomDTO bookingRoomDTO);
    Booking approveBookRoom(int id);
    Booking rejectedBookRoom(int id);
    List<Booking> getListBookingApprove();

    Booking deleteBooking(int id);
}
