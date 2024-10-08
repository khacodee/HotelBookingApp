package com.khacv.hotelbookingapp.service.guest;

import com.khacv.hotelbookingapp.dto.guest.GuestDTO;
import com.khacv.hotelbookingapp.entity.booking.Booking;
import com.khacv.hotelbookingapp.entity.guest.Guest;
import com.khacv.hotelbookingapp.exception.NotFoundException;
import com.khacv.hotelbookingapp.repository.booking.BookingRepository;
import com.khacv.hotelbookingapp.repository.guest.GuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.khacv.hotelbookingapp.util.Messages.*;

@Service
public class GuestService implements IGuestService {
    @Autowired
    private GuestRepository guestRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Override
    public List<Guest> getAllGuest(){
        return guestRepository.findAll();
    }

    @Override
    public Guest findGuestByBookingId(int id) {
        Booking booking = bookingRepository.findById(id);

        Guest guest = guestRepository.findById(booking.getGuest().getId());

        return guest;
    }

    @Override
    public Guest createGuest(GuestDTO guestDTO){
        Guest newGuest = new Guest();
        newGuest.setFullName(guestDTO.getFullName());
        newGuest.setAddress(guestDTO.getAddress());
        newGuest.setEmail(guestDTO.getEmail());
        newGuest.setPhoneNumber(guestDTO.getPhoneNumber());
        guestRepository.save(newGuest);
        return newGuest;
    }

    @Override
    public Guest deleteGuest(int id) {
        Guest guest = guestRepository.findById(id);
        if(guest != null){
            guestRepository.delete(guest);
        }
        return guest;
    }

    @Override
    public Guest getGuestById(int id){
        Guest guest = guestRepository.findById(id);
        if(guest == null){
            throw new NotFoundException(NOT_FOUND);
        }

        return guest;
    }

    @Override
    public Guest updateGuestProfile(int guestId, GuestDTO guestDTO){
        Guest existingGuest = guestRepository.findById(guestId);
        if(existingGuest == null){
           throw new NotFoundException(NOT_FOUND);
        }
        existingGuest.setFullName(guestDTO.getFullName());
        existingGuest.setAddress(guestDTO.getAddress());
        existingGuest.setEmail(guestDTO.getEmail());
        existingGuest.setPhoneNumber(guestDTO.getPhoneNumber());

        guestRepository.save(existingGuest);


             return existingGuest;


    }

}
