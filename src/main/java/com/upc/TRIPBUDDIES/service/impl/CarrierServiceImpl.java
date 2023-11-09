package com.upc.TRIPBUDDIES.service.impl;

import com.upc.TRIPBUDDIES.entities.carrier;

import com.upc.TRIPBUDDIES.repository.ICarrierRepository;


import com.upc.TRIPBUDDIES.service.ICarrierService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class CarrierServiceImpl implements ICarrierService {

    private ICarrierRepository CarrierRepository;
    public CarrierServiceImpl(ICarrierRepository CarrierRepository){
        this.CarrierRepository = CarrierRepository;
    }
    @Override
    @Transactional
    public carrier save(carrier carrier) throws Exception {
        return CarrierRepository.save(carrier);
    }


    @Override
    @Transactional
    public void delete(Long id) throws Exception {
        CarrierRepository.deleteById(id);
    }

    @Override
    @Transactional
    public List<carrier> getAll() throws Exception {
        return CarrierRepository.findAll();
    }

    @Override
    @Transactional
    public Optional<carrier> getById(Long id) throws Exception {
        return CarrierRepository.findById(id);
    }

    @Override
    public carrier existsByEmailAndPassword(String email, String password) {
        return CarrierRepository.findByEmailAndPassword(email, password);
    }
}
