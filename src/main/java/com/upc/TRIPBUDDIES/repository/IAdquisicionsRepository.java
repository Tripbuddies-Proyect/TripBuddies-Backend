package com.upc.TRIPBUDDIES.repository;


import com.upc.TRIPBUDDIES.entities.Adquisicions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IAdquisicionsRepository extends JpaRepository<Adquisicions, Long> {

    List<Adquisicions> findByTravellerId(Long user_id);

    boolean existsByTravellerIdAndPlacesId(Long user_id, Long place_id);


}
