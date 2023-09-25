package com.upc.TRIPBUDDIES.service;

import com.upc.TRIPBUDDIES.entities.Places;

import java.util.List;


public interface IPlacesService extends CrudService<Places>{
    List<Places> findByBussiness_Id(Long business_id) throws Exception;

    List<Places> findByDestino(String destino) throws Exception;

}
