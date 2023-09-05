package com.upc.TRIPBUDDIES.service.impl;

import com.upc.TRIPBUDDIES.entities.Traveller;
import com.upc.TRIPBUDDIES.repository.ITravellerRepository;
import com.upc.TRIPBUDDIES.service.ITravellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class TravellerServiceImpl implements ITravellerService {

    private ITravellerRepository travellerRepository;
    public TravellerServiceImpl(ITravellerRepository travellerRepository) {
        this.travellerRepository = travellerRepository;
    }
    @Override
    @Transactional

    public Traveller save(Traveller traveller) throws Exception {
        return travellerRepository.save(traveller);
    }

    @Override
    @Transactional

    public void delete(Long id) throws Exception {
        travellerRepository.deleteById(id);
    }

    @Override
    @Transactional

    public List<Traveller> getAll() throws Exception {
        return travellerRepository.findAll();
    }

    @Override
    @Transactional

    public Optional<Traveller> getById(Long id) throws Exception {
        return travellerRepository.findById(id);
    }
}
