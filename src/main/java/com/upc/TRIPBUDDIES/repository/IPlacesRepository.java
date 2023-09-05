package com.upc.TRIPBUDDIES.repository;

import com.upc.TRIPBUDDIES.entities.Places;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IPlacesRepository extends JpaRepository<Places, Long>{
    List<Places> findByBussiness_Id(Long business_id);
    List<Places> findByLocation(String location);
}
