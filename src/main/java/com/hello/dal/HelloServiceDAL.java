/**
 * 
 */
package com.hello.dal;

import com.hello.model.HelloMessage;

import java.util.List;

/**
 * @author VipinK
 *
 */
public interface HelloServiceDAL {

    HelloMessage updateMessage(HelloMessage message);

    HelloMessage saveMessage(HelloMessage message);

    HelloMessage deleteMessage(String messageId);

    List<HelloMessage> getAllMessages();

    HelloMessage getMessageById(String messageId);
}
