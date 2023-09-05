package com.upc.TRIPBUDDIES.service.impl;

import com.upc.TRIPBUDDIES.entities.Message;
import com.upc.TRIPBUDDIES.repository.IMessageRepository;
import com.upc.TRIPBUDDIES.service.IMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Service
public class MessageServiceImpl implements IMessageService {
    private final IMessageRepository messageRepository;

    public MessageServiceImpl(IMessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }
    @Override
    @Transactional
    public Message save(Message message) throws Exception {
        return messageRepository.save(message);
    }
    @Override
    @Transactional
    public void delete(Long id) throws Exception {
        messageRepository.deleteById(id);
    }
    @Override
    @Transactional
    public List<Message> getAll() throws Exception {
        return messageRepository.findAll();
    }
    @Override
    @Transactional
    public Optional<Message> getById(Long id) throws Exception {
        return messageRepository.findById(id);
    }
    @Override
    @Transactional
    public List<Message> findLastMessageTraveller(long id) throws Exception {
        return messageRepository.findLastMessageTraveller(id);
    }

    @Override
    @Transactional
    public List<Message> findLastMessageBussiness(long id) throws Exception {
        return messageRepository.findLastMessageBussiness(id);
    }

}
