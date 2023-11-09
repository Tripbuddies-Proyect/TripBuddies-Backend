package com.upc.TRIPBUDDIES.repository;

import com.upc.TRIPBUDDIES.entities.Friendship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IFriendRepository extends JpaRepository<Friendship, Long> {
    List<Friendship> findByUserId(Long id) throws Exception;

}
