package com.upc.TRIPBUDDIES.service;

import com.upc.TRIPBUDDIES.entities.Traveller;

public interface ITravellerService extends CrudService<Traveller> {

    Traveller  existsByEmailAndPassword(String email, String password);
}
