package com.khacv.hotelbookingapp.service.guest;

import com.khacv.hotelbookingapp.dto.guest.GuestDTO;
import com.khacv.hotelbookingapp.entity.guest.Guest;

import java.util.List;

public interface IGuestService {
   Guest updateGuestProfile(int guestId, GuestDTO guestDTO);

    Guest getGuestById(int id);

    List<Guest> getAllGuest();

    Guest findGuestByBookingId(int id);

   Guest createGuest(GuestDTO guestDTO);

    Guest deleteGuest(int id);
}
