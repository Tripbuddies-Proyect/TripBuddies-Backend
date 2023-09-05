package com.upc.TRIPBUDDIES.service.impl;

import com.upc.TRIPBUDDIES.entities.Places;
import com.upc.TRIPBUDDIES.repository.IBussinessRepository;
import com.upc.TRIPBUDDIES.repository.IPlacesRepository;
import com.upc.TRIPBUDDIES.service.IPlacesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PlacesServiceImpl implements IPlacesService {

    private final IPlacesRepository placesRepository;

    public PlacesServiceImpl(IPlacesRepository placesService) {
        this.placesRepository = placesService;
    }

    @Override
    @Transactional
    public Places save(Places places) throws Exception {
        return placesRepository.save(places);
    }

    @Override
    @Transactional
    public void delete(Long id) throws Exception {
        placesRepository.deleteById(id);
    }

    @Override
    @Transactional
    public List<Places> getAll() throws Exception {
        return placesRepository.findAll();
    }

    @Override
    @Transactional
    public Optional<Places> getById(Long id) throws Exception {
        return placesRepository.findById(id);
    }

    @Override
    @Transactional
    public List<Places> findByBussiness_Id(Long business_id) throws Exception {
        return placesRepository.findByBussiness_Id(business_id);
    }

    @Override
    public List<Places> findByLocation(String location) throws Exception {
        return placesRepository.findByLocation(location);
    }
}
