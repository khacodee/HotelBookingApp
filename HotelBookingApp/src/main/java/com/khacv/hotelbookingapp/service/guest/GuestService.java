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
    public String createGuest(GuestDTO guestDTO){
        Guest newGuest = new Guest();
        newGuest.setFullName(guestDTO.getFullName());
        newGuest.setAddress(guestDTO.getAddress());
        newGuest.setPhoneNumber(guestDTO.getPhoneNumber());
        guestRepository.save(newGuest);
        return ADDED_SUCCESSFULLY;
    }

    @Override
    public String deleteGuest(int id) {
        Guest guest = guestRepository.findById(id);
        if(guest == null){
            throw new NotFoundException("Not Found");
        }
        guestRepository.delete(guest);
        return DELETE_SUCCESSFUL;
    }

    @Override
    public Guest getGuestById(int id){
        Guest guest = guestRepository.findById(id);
        if(guest == null){
            throw new NotFoundException("Not Found");
        }
        return guest;
    }

    @Override
    public String updateGuestProfile(int guestId, GuestDTO guestDTO){
        Guest existingGuest = guestRepository.findById(guestId);
        if(existingGuest == null){
            throw new NotFoundException("Not Found");
        }
            existingGuest.setFullName(guestDTO.getFullName());
            existingGuest.setAddress(guestDTO.getAddress());
            existingGuest.setPhoneNumber(guestDTO.getPhoneNumber());

             guestRepository.save(existingGuest);

             return UPDATE_SUCCESSFUL;


    }

}
