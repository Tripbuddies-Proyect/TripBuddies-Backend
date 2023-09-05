package com.upc.TRIPBUDDIES.service.impl;

import com.upc.TRIPBUDDIES.entities.Favorite;
import com.upc.TRIPBUDDIES.repository.IFavoriteRepository;
import com.upc.TRIPBUDDIES.service.IFavoriteService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class FavoriteServiceImpl implements IFavoriteService {
    private final IFavoriteRepository favoriteRepository;

    public FavoriteServiceImpl(IFavoriteRepository favoriteRepository) {
        this.favoriteRepository = favoriteRepository;
    }

    @Override
    @Transactional
    public Favorite save(Favorite favorite) throws Exception {
        return favoriteRepository.save(favorite);
    }

    @Override
    @Transactional
    public void delete(Long id) throws Exception {
        favoriteRepository.deleteById(id);
    }

    @Override
    @Transactional
    public List<Favorite> getAll() throws Exception {
        return favoriteRepository.findAll();
    }

    @Override
    @Transactional
    public Optional<Favorite> getById(Long id) throws Exception {
        return favoriteRepository.findById(id);
    }

    @Override
    @Transactional
    public List<Favorite> findByTravellerId(Long traveller_id) {
        return favoriteRepository.findByTravellerId(traveller_id);
    }

    @Override
    @Transactional
    public List<Favorite> findByPlacesId(Long places_id) {
        return favoriteRepository.findByPlacesId(places_id);
    }
}
