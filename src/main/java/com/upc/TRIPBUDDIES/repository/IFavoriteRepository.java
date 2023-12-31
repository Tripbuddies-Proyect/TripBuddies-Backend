package com.upc.TRIPBUDDIES.repository;

import com.upc.TRIPBUDDIES.entities.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IFavoriteRepository extends JpaRepository<Favorite, Long> {
    List<Favorite> findByTravellerId(Long travellerId);
    List<Favorite> findByPlacesId(Long places_id);

}
