package com.upc.TRIPBUDDIES.repository;

import com.upc.TRIPBUDDIES.entities.SaleTable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ISaleTableRepository extends JpaRepository<SaleTable, Long> {
    List<SaleTable> findByUser_Id(Long user_id);
    List<SaleTable> findByPlaces_Id(Long places_id);
}
