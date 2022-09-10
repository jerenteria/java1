package com.juan.dogs.repositions;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.juan.dogs.models.Rating;

@Repository
public interface RatingRepository extends CrudRepository <Rating, Long>{

}
