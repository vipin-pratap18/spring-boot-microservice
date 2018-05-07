package com.hello.manager;

import com.hello.model.HelloMessage;

import java.util.List;

/**
 * Service abstraction for possible Vessel operations.
 * @author VipinK
 */
public interface HelloServiceManager {

	HelloMessage updateMessage(HelloMessage message);

	HelloMessage saveMessage(HelloMessage message);

	HelloMessage deleteMessage(String messageId);

	List<HelloMessage> getAllMessages();

	HelloMessage getMessageById(String messageId);
}
