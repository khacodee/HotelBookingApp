package com.khacv.hotelbookingapp.service.reviews;

import com.khacv.hotelbookingapp.dto.review.ReviewDTO;
import com.khacv.hotelbookingapp.entity.guest.Guest;
import com.khacv.hotelbookingapp.entity.hotel.Hotel;
import com.khacv.hotelbookingapp.entity.reviews.Reviews;
import com.khacv.hotelbookingapp.exception.NotFoundException;
import com.khacv.hotelbookingapp.repository.guest.GuestRepository;
import com.khacv.hotelbookingapp.repository.hotel.HotelRepository;
import com.khacv.hotelbookingapp.repository.reviews.ReviewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import static com.khacv.hotelbookingapp.util.Messages.*;

@Service
public class ReviewsService implements IReviewsService{
    @Autowired
    private ReviewsRepository reviewsRepository;

    @Autowired
    private GuestRepository guestRepository;

    @Autowired
    private HotelRepository hotelRepository;

    @Override
    public List<Reviews> getListReviews(){
        return reviewsRepository.findAll();
    }

    @Override
    public Reviews getReviewsById(int id){
        return reviewsRepository.findById(id);
    }

    @Override
    public String createReviews(ReviewDTO reviewDTO) {

        Reviews newReviews = new Reviews();
        Hotel hotel = hotelRepository.findById(reviewDTO.getHotelId());

        if(hotel == null){
            throw new NotFoundException(NOT_FOUND);
        }
        Guest guest = guestRepository.findById(reviewDTO.getGuestId());
        if (guest == null){
            throw new NotFoundException(NOT_FOUND);
        }
        newReviews.setRating(reviewDTO.getRating());
        newReviews.setComment(reviewDTO.getComment());
        newReviews.setReviewDate(new Date());
        newReviews.setHotel(hotel);
        newReviews.setGuest(guest);

        reviewsRepository.save(newReviews);
        return ADDED_SUCCESSFULLY;
    }
}
