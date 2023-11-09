package com.upc.TRIPBUDDIES.service;

import com.upc.TRIPBUDDIES.entities.Message;

import java.util.List;

public interface IMessageService extends CrudService<Message>{
    List<Message> findLastMessageTraveller(long id) throws Exception;
    List<Message> findLastMessageBussiness(long id) throws Exception;

}
