package com.serheev.repository;

import com.serheev.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {
    @Modifying
    @Query(value = "UPDATE users u SET u.additionalInfo = :addInfo WHERE u.id = :id", nativeQuery = true)
    void updateAdditionalInfo(@Param("id") Long id, @Param("addInfo") Map<String, Object> addInfo);
}
