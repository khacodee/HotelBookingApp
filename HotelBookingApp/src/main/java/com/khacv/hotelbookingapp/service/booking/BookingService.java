package com.khacv.hotelbookingapp.service.booking;

import com.khacv.hotelbookingapp.dto.booking.BookingRoomDTO;
import com.khacv.hotelbookingapp.entity.booking.Booking;
import com.khacv.hotelbookingapp.entity.guest.Guest;
import com.khacv.hotelbookingapp.entity.room.Room;
import com.khacv.hotelbookingapp.exception.IllegalArgumentException;
import com.khacv.hotelbookingapp.exception.NotFoundException;
import com.khacv.hotelbookingapp.repository.booking.BookingRepository;
import com.khacv.hotelbookingapp.repository.guest.GuestRepository;
import com.khacv.hotelbookingapp.repository.room.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.khacv.hotelbookingapp.util.Constants.*;
import static com.khacv.hotelbookingapp.util.Messages.*;

@Service
public class BookingService implements IBookingService{

    @Autowired
    private BookingRepository bookingRepository;


    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private GuestRepository guestRepository;

    @Override
    public List<Booking> getListBooking(){
        return bookingRepository.findAll();
    }

    @Override
    public Booking getBookingById(int id){

        Booking booking = bookingRepository.findById(id);

        if (booking == null){
            throw new NotFoundException(NOT_FOUND);
        }

        return  booking;
    }

    @Override
    public String createBooking(BookingRoomDTO bookingRoomDTO){
        Booking booking = new Booking();

        booking.setCheckInDate(bookingRoomDTO.getCheckInDate());
        booking.setCheckOutDate(bookingRoomDTO.getCheckOutDate());
        booking.setTotalPrice(bookingRoomDTO.getTotalPrice());
        booking.setBookingStatus(PENDING);

        Guest guest = guestRepository.findById(bookingRoomDTO.getGuestId());
        if(guest == null){
            throw new NotFoundException(NOT_FOUND);
        }
        booking.setGuest(guest);
        Room room = roomRepository.findById(bookingRoomDTO.getRoomId());
        if (room == null) {

            throw new NotFoundException(NOT_FOUND);
        }
        booking.setRoom(room);

        bookingRepository.save(booking);

        return ADDED_SUCCESSFULLY;

    }

    @Override
    public String approveBookRoom(int id, String status){
        Booking booking = bookingRepository.findById(id);

        if(booking == null){
            throw new NotFoundException(NOT_FOUND);
        }

        switch (status){
            case "confirmed":
                booking.setBookingStatus(CONFIRMED);
                break;
            case "rejected":
                booking.setBookingStatus(REJECTED);
                break;
            default:
                throw new IllegalArgumentException(INVALID);
        }
        bookingRepository.save(booking);

        return UPDATE_SUCCESSFUL;
    }

    @Override
    public List<Booking> getListBookingApprove(){

        List<Booking> result = bookingRepository.findByBookingStatus(PENDING);

        if(result.isEmpty()){
            throw new NotFoundException(NOT_FOUND);
        }

        return result;
    }
}
