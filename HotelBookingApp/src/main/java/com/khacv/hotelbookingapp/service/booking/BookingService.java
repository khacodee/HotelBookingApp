package com.khacv.hotelbookingapp.service.booking;

import com.khacv.hotelbookingapp.dto.booking.BookingRoomDTO;
import com.khacv.hotelbookingapp.dto.booking.BookingWithGuestDTO;
import com.khacv.hotelbookingapp.dto.guest.GuestDTO;
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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

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
    public Booking createBooking(BookingRoomDTO bookingRoomDTO){
        Booking booking = new Booking();

        Guest guest = guestRepository.findById(bookingRoomDTO.getGuestId());

        if (guest == null) {
            throw new NotFoundException(NOT_FOUND);
        }
        booking.setGuest(guest);

        Room room = roomRepository.findById(bookingRoomDTO.getRoomId());
        if (room == null) {
            throw new NotFoundException(NOT_FOUND);
        }
        if(room.isBooked()){
            throw new NotFoundException("Room booked");
        }
        booking.setRoom(room);
        booking.setPaymentMethod(bookingRoomDTO.getPaymentMethod());
        booking.setCheckInDate(bookingRoomDTO.getCheckInDate());
        booking.setCheckOutDate(bookingRoomDTO.getCheckOutDate());

        BigDecimal numberOfDays = new BigDecimal(calculateStayDuration(bookingRoomDTO.getCheckInDate(), bookingRoomDTO.getCheckOutDate()));

        BigDecimal totalPrice = numberOfDays.multiply(room.getPrice());
        booking.setTotalPrice(totalPrice);
        booking.setBookingStatus(PENDING);

        bookingRepository.save(booking);

        return booking;
    }

    @Override
    public Guest createBookingWithGuest(BookingWithGuestDTO bookingRoomDTO) {
        Booking booking = new Booking();
        Guest guest = new Guest();
        guest.setFullName(bookingRoomDTO.getGuestFullName());
        guest.setAddress(bookingRoomDTO.getGuestAddress());
        guest.setEmail(bookingRoomDTO.getGuestEmail());
        guest.setPhoneNumber(bookingRoomDTO.getGuestPhoneNumber());

        // Lưu thông tin Guest vào cơ sở dữ liệu
        Guest savedGuest = guestRepository.save(guest);
        if(savedGuest == null){
            throw new NotFoundException("Guest not saved");
        }
        booking.setGuest(savedGuest);
        Room room = roomRepository.findById(bookingRoomDTO.getRoomId());
        if (room == null) {
            throw new NotFoundException("Room not found");
        }

        if(room.isBooked()){
            throw new NotFoundException("Room booked");
        }
        booking.setRoom(room);
        booking.setCheckInDate(bookingRoomDTO.getCheckInDate());
        booking.setCheckOutDate(bookingRoomDTO.getCheckOutDate());

        BigDecimal numberOfDays = new BigDecimal(calculateStayDuration(bookingRoomDTO.getCheckInDate(), bookingRoomDTO.getCheckOutDate()));
        booking.setPaymentMethod(bookingRoomDTO.getPaymentMethod());
        BigDecimal totalPrice = numberOfDays.multiply(room.getPrice());
        booking.setTotalPrice(totalPrice);
        booking.setBookingStatus(PENDING);

        Booking  savedBooking = bookingRepository.save(booking);
        List<Booking> result = new ArrayList<>();
        result.add(savedBooking);
        guest.setBookings(result);

        return guest;
    }

    private long calculateStayDuration(Date checkInDate, Date checkOutDate) {
        long diffInMillies = Math.abs(checkOutDate.getTime() - checkInDate.getTime());
        return TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
    }

    @Override
    public Booking updateBooking(int id, BookingRoomDTO updateBooking) {
        Booking booking = bookingRepository.findById(id);

        booking.setCheckInDate(updateBooking.getCheckInDate());
        booking.setCheckOutDate(updateBooking.getCheckOutDate());
        booking.setTotalPrice(updateBooking.getTotalPrice());
        booking.setBookingStatus(updateBooking.getBookingStatus());
        booking.setPaymentMethod(updateBooking.getPaymentMethod());
        Guest guest = guestRepository.findById(updateBooking.getGuestId());
        if (guest == null) {
            throw new NotFoundException(NOT_FOUND);
        }
        booking.setGuest(guest);
        Room room = roomRepository.findById(updateBooking.getRoomId());
        if (room == null) {

            throw new NotFoundException(NOT_FOUND);
        }
        booking.setRoom(room);

        bookingRepository.save(booking);

        return booking;
    }


    @Override
    public Booking approveBookRoom(int id){
        Booking booking = bookingRepository.findById(id);

        if(booking == null){
            throw new NotFoundException(NOT_FOUND);
        }
        booking.setBookingStatus(CONFIRMED);

        booking.getRoom().setBooked(true);

        bookingRepository.save(booking);

        return booking;

    }

    @Override
    public Booking rejectedBookRoom(int id) {
        Booking booking = bookingRepository.findById(id);

        if(booking == null){
            throw new NotFoundException(NOT_FOUND);
        }

        booking.setBookingStatus(REJECTED);
        booking.getRoom().setBooked(false);
        bookingRepository.save(booking);

        return booking;
    }

    @Override
    public List<Booking> getListBookingApprove(){

        List<Booking> result = bookingRepository.findByBookingStatus(PENDING);

        if(result.isEmpty()){
            throw new NotFoundException(NOT_FOUND);
        }

        return result;
    }

    @Override
    public Booking deleteBooking(int id) {
        Booking booking = bookingRepository.findById(id);
        if(booking == null){
            throw new NotFoundException(NOT_FOUND);
        }
        bookingRepository.delete(booking);

        return booking;
    }
}
