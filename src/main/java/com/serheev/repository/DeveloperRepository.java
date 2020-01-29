package com.serheev.repository;

import com.serheev.model.DeveloperEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeveloperRepository extends CrudRepository<DeveloperEntity, Long>{
}
