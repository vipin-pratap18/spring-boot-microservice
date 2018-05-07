/**
 *
 */
package com.hello.dal;

import com.hello.model.HelloMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author VipinK
 *
 * Exception thrown in this class are very generic and with no proper response code. So please ignore that part.
 * You have to create your own exception and throw that
 */
@Repository
public class HelloServiceDALImpl implements HelloServiceDAL {

    private final Logger LOG = LoggerFactory.getLogger(HelloServiceDALImpl.class);

    private Map<String, HelloMessage> messageIdToMessageMap = new HashMap<>();

    @Override
    public HelloMessage updateMessage(HelloMessage message) {
        String messageId = message.getMessageId();
        HelloMessage existingMessage = messageIdToMessageMap.get(messageId);
        if(existingMessage == null){
            throw new RuntimeException("A message with message id : " + messageId + " does not exist, so can not update it.");
        }
        messageIdToMessageMap.remove(messageId);
        messageIdToMessageMap.put(messageId, message);

        return message;
    }

    @Override
    public HelloMessage saveMessage(HelloMessage message) {
        String messageId = message.getMessageId();
        HelloMessage existingMessage = messageIdToMessageMap.get(messageId);
        if(existingMessage != null){
            throw new RuntimeException("A message with message id : " + messageId + " is already exist.");
        }
        messageIdToMessageMap.put(messageId, message);

        return message;
    }

    @Override
    public HelloMessage deleteMessage(String messageId) {
        HelloMessage existingMessage = messageIdToMessageMap.get(messageId);
        if(existingMessage == null){
            throw new RuntimeException("A message with message id : " + messageId + " does not exist, so can not delete it.");
        }
        messageIdToMessageMap.remove(messageId);
        return existingMessage;
    }

    @Override
    public List<HelloMessage> getAllMessages() {
        List<HelloMessage> messageList = new ArrayList<>();
        if(messageIdToMessageMap != null && !messageIdToMessageMap.isEmpty()){
            for(Map.Entry<String, HelloMessage> entry : messageIdToMessageMap.entrySet()){
                messageList.add(entry.getValue());
            }

        }
        return messageList;
    }

    @Override
    public HelloMessage getMessageById(String messageId) {
        return messageIdToMessageMap.get(messageId);
    }
}
