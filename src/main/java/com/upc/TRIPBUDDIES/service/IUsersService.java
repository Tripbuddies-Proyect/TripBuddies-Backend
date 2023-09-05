package com.upc.TRIPBUDDIES.service;

import com.upc.TRIPBUDDIES.entities.User;

import java.util.List;

public interface IUsersService extends CrudService<User> {

        User findByEmail(String email) throws Exception;

        List<User> findByFirstName(String firstName) throws Exception;

        List<User> findByRole(String role) throws Exception;

}
