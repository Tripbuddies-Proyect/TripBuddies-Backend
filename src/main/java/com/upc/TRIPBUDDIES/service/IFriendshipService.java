package com.upc.TRIPBUDDIES.service;

import com.upc.TRIPBUDDIES.entities.Friendship;

import java.util.List;

public interface IFriendshipService extends CrudService<Friendship>{
    List<Friendship> findByUserId(Long id) throws Exception;
}
