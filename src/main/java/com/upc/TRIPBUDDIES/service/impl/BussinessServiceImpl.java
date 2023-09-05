package com.upc.TRIPBUDDIES.service.impl;

import com.upc.TRIPBUDDIES.entities.Bussiness;
import com.upc.TRIPBUDDIES.entities.Traveller;
import com.upc.TRIPBUDDIES.repository.IBussinessRepository;
import com.upc.TRIPBUDDIES.repository.ITravellerRepository;
import com.upc.TRIPBUDDIES.service.IBussinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BussinessServiceImpl implements IBussinessService {

    private IBussinessRepository bussinessRepository;
    public BussinessServiceImpl(IBussinessRepository bussinessRepository){
        this.bussinessRepository = bussinessRepository;
    }
    @Override
    @Transactional
    public Bussiness save(Bussiness bussiness) throws Exception {
        return bussinessRepository.save(bussiness);
    }


    @Override
    @Transactional
    public void delete(Long id) throws Exception {
        bussinessRepository.deleteById(id);
    }

    @Override
    @Transactional
    public List<Bussiness> getAll() throws Exception {
        return bussinessRepository.findAll();
    }

    @Override
    @Transactional
    public Optional<Bussiness> getById(Long id) throws Exception {
        return bussinessRepository.findById(id);
    }
}
