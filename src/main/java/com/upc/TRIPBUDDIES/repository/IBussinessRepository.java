package com.upc.TRIPBUDDIES.repository;

import com.upc.TRIPBUDDIES.entities.Bussiness;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBussinessRepository extends JpaRepository<Bussiness, Long> {
}
