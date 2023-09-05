package com.upc.TRIPBUDDIES.service.impl;

import com.upc.TRIPBUDDIES.entities.User;
import com.upc.TRIPBUDDIES.repository.IUsersRepository;
import com.upc.TRIPBUDDIES.service.IUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class UsersServiceImpl implements IUsersService {
    private final IUsersRepository usersRepository;
    public UsersServiceImpl(IUsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    @Transactional
    public User save(User users) throws Exception {
        return usersRepository.save(users);

    }

    @Override
    @Transactional
    public void delete(Long id) throws Exception {
        usersRepository.deleteById(id);
    }

    @Override
    public List<User> getAll() throws Exception {
        return usersRepository.findAll();
    }

    @Override
    public Optional<User> getById(Long id) throws Exception {
        return usersRepository.findById(id);
    }

    @Override
    public User findByEmail(String email) throws Exception {
        return usersRepository.findByEmail(email);
    }

    @Override
    public List<User> findByFirstName(String firstName) throws Exception {
        return usersRepository.findByFirstName(firstName);
    }

    @Override
    public List<User> findByRole(String role) throws Exception {
        return usersRepository.findByRole(role);
    }

}
