package com.juan.dogs.repositions;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.juan.dogs.models.Dog;

@Repository
public interface DogRepository extends CrudRepository<Dog, Long> {
	List<Dog> findAll(); // SELECT * FROM dogs
}
