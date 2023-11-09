package com.upc.TRIPBUDDIES.service.impl;

import com.upc.TRIPBUDDIES.entities.Friendship;
import com.upc.TRIPBUDDIES.repository.IFriendRepository;
import com.upc.TRIPBUDDIES.service.IFriendshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Service
public class FriendshipServiceImpl implements IFriendshipService {
    private  final IFriendRepository friendRepository;

    public FriendshipServiceImpl(IFriendRepository friendRepository) {
        this.friendRepository = friendRepository;
    }

    @Override
    @Transactional
    public Friendship save(Friendship friendship) throws Exception {
        return  friendRepository.save(friendship);
    }

    @Override
    @Transactional
    public void delete(Long id) throws Exception {
        friendRepository.deleteById(id);
    }

    @Override
    @Transactional
    public List<Friendship> getAll() throws Exception {
        return friendRepository.findAll();
    }

    @Override
    @Transactional
    public Optional<Friendship> getById(Long id) throws Exception {
        return friendRepository.findById(id);
    }

    @Override
    @Transactional
    public List<Friendship> findByUserId(Long id) throws Exception {
        return friendRepository.findByUserId(id);
    }
}
