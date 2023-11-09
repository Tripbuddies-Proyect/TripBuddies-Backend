package com.upc.TRIPBUDDIES.repository;

import com.upc.TRIPBUDDIES.entities.carrier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICarrierRepository extends JpaRepository<carrier, Long> {
    carrier findByEmailAndPassword(String email, String password);
}
