package com.khacv.hotelbookingapp.repository.reviews;

import com.khacv.hotelbookingapp.entity.reviews.Reviews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewsRepository extends JpaRepository<Reviews, Integer> {

    Reviews findById(int id);
}
