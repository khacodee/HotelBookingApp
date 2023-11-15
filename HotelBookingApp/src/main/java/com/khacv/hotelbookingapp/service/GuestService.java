package com.khacv.hotelbookingapp.service;

import com.khacv.hotelbookingapp.dto.GuestDTO;
import com.khacv.hotelbookingapp.entity.Guest;
import com.khacv.hotelbookingapp.repository.GuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuestService  {
    @Autowired
    private GuestRepository guestRepository;

    public List<Guest> getAllGuest(){
        return guestRepository.findAll();
    }

    public Guest getGuestById(int id){
        return guestRepository.findById(id);
    }

    public String updateGuestProfile(int guestId, GuestDTO guestDTO){
        Guest existingGuest = guestRepository.findById(guestId);
        if(existingGuest != null){
            existingGuest.setFullName(guestDTO.getFullName());
            existingGuest.setAddress(guestDTO.getAddress());
            existingGuest.setPhoneNumber(guestDTO.getPhoneNumber());

             guestRepository.save(existingGuest);

             return "Update Successfull";
        }
        return null;
    }

}
