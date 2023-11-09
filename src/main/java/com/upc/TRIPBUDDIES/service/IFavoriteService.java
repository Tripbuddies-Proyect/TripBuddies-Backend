package com.upc.TRIPBUDDIES.service;

import com.upc.TRIPBUDDIES.entities.Favorite;

import java.util.List;

public interface IFavoriteService extends CrudService<Favorite>{
    List<Favorite> findByTravellerId(Long traveller_id);
    List<Favorite> findByPlacesId(Long places_id);
}
