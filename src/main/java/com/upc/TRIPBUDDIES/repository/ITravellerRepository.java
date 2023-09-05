package com.upc.TRIPBUDDIES.repository;

import com.upc.TRIPBUDDIES.entities.Traveller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITravellerRepository extends JpaRepository<Traveller, Long> {
    boolean existsTravellerByEmail(String email);
}
