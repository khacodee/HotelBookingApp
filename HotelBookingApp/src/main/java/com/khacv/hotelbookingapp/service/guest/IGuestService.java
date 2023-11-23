package com.khacv.hotelbookingapp.service.guest;

import com.khacv.hotelbookingapp.dto.guest.GuestDTO;
import com.khacv.hotelbookingapp.entity.guest.Guest;

import java.util.List;

public interface IGuestService {
    String updateGuestProfile(int guestId, GuestDTO guestDTO);

    Guest getGuestById(int id);

    List<Guest> getAllGuest();

    Guest findGuestByBookingId(int id);

    String createGuest(GuestDTO guestDTO);
}
