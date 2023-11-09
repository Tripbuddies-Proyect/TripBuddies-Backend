package com.upc.TRIPBUDDIES.service;
import com.upc.TRIPBUDDIES.entities.carrier;

public interface ICarrierService extends CrudService<carrier>{
    carrier existsByEmailAndPassword(String email, String password);
}
