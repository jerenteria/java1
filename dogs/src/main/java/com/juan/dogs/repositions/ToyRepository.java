package com.juan.dogs.repositions;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.juan.dogs.models.Toy;

@Repository
public interface ToyRepository extends CrudRepository<Toy, Long> {

}
