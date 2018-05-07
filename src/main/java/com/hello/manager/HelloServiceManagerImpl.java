package com.hello.manager;

import com.hello.dal.HelloServiceDAL;
import com.hello.model.HelloMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author VipinK
 *
 */
@Service
public class HelloServiceManagerImpl implements HelloServiceManager {

	private final Logger LOG = LoggerFactory.getLogger(HelloServiceManagerImpl.class);
	@Autowired
	private HelloServiceDAL messageDAL;

    @Override
    public HelloMessage updateMessage(HelloMessage message) {
        return messageDAL.updateMessage(message);
    }

    @Override
    public HelloMessage saveMessage(HelloMessage message) {
        return messageDAL.saveMessage(message);
    }

    @Override
    public HelloMessage deleteMessage(String messageId) {
        return messageDAL.deleteMessage(messageId);
    }

    @Override
    public List<HelloMessage> getAllMessages() {
        return messageDAL.getAllMessages();
    }

    @Override
    public HelloMessage getMessageById(String messageId) {
        return messageDAL.getMessageById(messageId);
    }
}
