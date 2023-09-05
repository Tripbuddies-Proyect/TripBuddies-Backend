package com.upc.TRIPBUDDIES.service;

import com.upc.TRIPBUDDIES.entities.Places;

import java.util.List;
import java.util.Optional;

public interface IPlacesService extends CrudService<Places>{
    List<Places> findByBussiness_Id(Long business_id) throws Exception;
    List<Places> findByLocation(String location) throws Exception;

}
