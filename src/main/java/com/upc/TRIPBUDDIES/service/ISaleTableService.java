package com.upc.TRIPBUDDIES.service;

import com.upc.TRIPBUDDIES.entities.Places;
import com.upc.TRIPBUDDIES.entities.SaleTable;
import com.upc.TRIPBUDDIES.entities.User;

import java.util.List;

public interface ISaleTableService extends CrudService<SaleTable> {

    SaleTable save(User user, Places places) throws Exception;

    List<SaleTable> findByUser_Id(Long user_id);
    List<SaleTable> findByPlaces_Id(Long places_id);
}
