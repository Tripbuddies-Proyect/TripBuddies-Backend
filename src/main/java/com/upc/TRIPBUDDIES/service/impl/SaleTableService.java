package com.upc.TRIPBUDDIES.service.impl;

import com.upc.TRIPBUDDIES.entities.Places;
import com.upc.TRIPBUDDIES.entities.SaleTable;
import com.upc.TRIPBUDDIES.entities.User;
import com.upc.TRIPBUDDIES.repository.ISaleTableRepository;
import com.upc.TRIPBUDDIES.service.ISaleTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class SaleTableService implements ISaleTableService {

    @Autowired
    private ISaleTableRepository saleTableRepository;

    @Override
    @Transactional
    public SaleTable save(SaleTable saleTable) throws Exception {
        try {
            // Utiliza el repositorio para guardar la entidad SaleTable en la base de datos
            return saleTableRepository.save(saleTable);
        } catch (Exception e) {
            throw new Exception("Error al guardar la entidad SaleTable", e);
        }
    }

    @Override
    @Transactional
    public SaleTable save(User user, Places places) throws Exception {
        SaleTable saleTable = new SaleTable();
        saleTable.setUser(user);
        saleTable.setPlaces(places);
        // Puedes establecer la fecha de venta aquí si es necesario
        // saleTable.setSaleDate(new Date());
        return saleTableRepository.save(saleTable);
    }


    @Override
    @Transactional
    public void delete(Long id) throws Exception {

    }

    @Override
    @Transactional
    public List<SaleTable> getAll() throws Exception {
        try {
            // Utiliza el método findAll() proporcionado por JpaRepository para obtener todas las filas de SaleTable.
            List<SaleTable> saleTables = saleTableRepository.findAll();
            return saleTables;
        } catch (Exception e) {
            // Maneja las excepciones aquí si es necesario.
            throw new Exception("No se pudieron obtener todas las SaleTables", e);
        }
    }

    @Override
    @Transactional
    public Optional<SaleTable> getById(Long id) throws Exception {
        return Optional.empty();
    }

    @Override
    @Transactional
    public List<SaleTable> findByUser_Id(Long user_id) {
        return null;
    }

    @Override
    @Transactional
    public List<SaleTable> findByPlaces_Id(Long places_id) {
        return null;
    }
}
