package com.khacv.hotelbookingapp.service;

import com.khacv.hotelbookingapp.dto.BookingRoomDTO;
import com.khacv.hotelbookingapp.entity.Booking;
import com.khacv.hotelbookingapp.entity.Guest;
import com.khacv.hotelbookingapp.entity.Payment;
import com.khacv.hotelbookingapp.entity.Room;
import com.khacv.hotelbookingapp.exception.IllegalArgumentException;
import com.khacv.hotelbookingapp.exception.NotFoundException;
import com.khacv.hotelbookingapp.repository.BookingRepository;
import com.khacv.hotelbookingapp.repository.GuestRepository;
import com.khacv.hotelbookingapp.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;


    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private GuestRepository guestRepository;
    public List<Booking> getListBooking(){
        return bookingRepository.findAll();
    }

    public Booking getBookingById(int id){

        Booking booking = bookingRepository.findById(id);

//        Set<Payment> payments =booking.getPayments();
//        if(payments.isEmpty()){
//            System.out.println("Danh sách thanh toán của đặt phòng rỗng");
//        }

        return  booking;
    }

    public String createBooking(BookingRoomDTO bookingRoomDTO){
        Booking booking = new Booking();

        booking.setCheckInDate(bookingRoomDTO.getCheckInDate());
        booking.setCheckOutDate(bookingRoomDTO.getCheckOutDate());
        booking.setTotalPrice(bookingRoomDTO.getTotalPrice());
        booking.setBookingStatus("PENDING");

        Guest guest = guestRepository.findById(bookingRoomDTO.getGuestId());
        if(guest == null){
            throw new NotFoundException("Guest not found");
        }
        booking.setGuest(guest);
        Room room = roomRepository.findById(bookingRoomDTO.getRoomId());
        if (room == null) {

            throw new NotFoundException("Room not found");
        }
        booking.setRoom(room);

        bookingRepository.save(booking);

        return "Create Booking Room Successfully!!";

    }

    public String approveBookRoom(int id, String status){
        Booking booking = bookingRepository.findById(id);

        if(booking == null){
            throw new NotFoundException("Booking not found!!");
        }

        switch (status){
            case "confirmed":
                booking.setBookingStatus("CONFIRMED");
                break;
            case "rejected":
                booking.setBookingStatus("REJECTED");
                break;
            default:
                throw new IllegalArgumentException("Invalid status");
        }
        bookingRepository.save(booking);

        return "Booking status updated successfully";
    }
}
