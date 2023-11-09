package com.upc.TRIPBUDDIES.service;

import com.upc.TRIPBUDDIES.entities.Adquisicions;


import java.util.List;

public interface IAdquisicionsService extends CrudService<Adquisicions>{
    List<Adquisicions> findByTravellerId(Long user_id) throws Exception;
    boolean existsByTravellerIdAndPlacesId(Long user_id, Long place_id) throws Exception;

}
