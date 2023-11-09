package com.upc.TRIPBUDDIES.repository;

import com.upc.TRIPBUDDIES.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface IUsersRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

    List<User> findByFirstName(String firstName);

    List<User> findByRole(String role);

}
