package com.upc.TRIPBUDDIES.service.impl;

import com.upc.TRIPBUDDIES.entities.Adquisicions;
import com.upc.TRIPBUDDIES.entities.Places;
import com.upc.TRIPBUDDIES.entities.Traveller;
import com.upc.TRIPBUDDIES.repository.IAdquisicionsRepository;
import com.upc.TRIPBUDDIES.service.IAdquisicionsService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Service
public class AdquisicionsServiceImpl implements IAdquisicionsService {

    @Autowired
    private final IAdquisicionsRepository adquisicionsRepository;

    public AdquisicionsServiceImpl(IAdquisicionsRepository adquisicionsRepository) {
        this.adquisicionsRepository = adquisicionsRepository;
    }


    @Override
    public Adquisicions save(Adquisicions adquisicions) throws Exception {
        return adquisicionsRepository.save(adquisicions);
    }

    @Override
    public void delete(Long id) throws Exception {
        adquisicionsRepository.deleteById(id);
    }

    @Override
    public List<Adquisicions> getAll() throws Exception {
        return adquisicionsRepository.findAll();

    }

    @Override
    public Optional<Adquisicions> getById(Long id) throws Exception {
        return adquisicionsRepository.findById(id);
    }

    @Override
    public List<Adquisicions> findByTravellerId(Long user_id) throws Exception {
        return adquisicionsRepository.findByTravellerId(user_id);
    }

    @Override
    public boolean existsByTravellerIdAndPlacesId(Long user_id, Long place_id) throws Exception {
        return adquisicionsRepository.existsByTravellerIdAndPlacesId(user_id, place_id);
    }
}
