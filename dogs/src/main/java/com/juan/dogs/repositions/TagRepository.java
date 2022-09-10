package com.juan.dogs.repositions;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.juan.dogs.models.Tag;

@Repository
public interface TagRepository extends CrudRepository<Tag, Long>{

}
