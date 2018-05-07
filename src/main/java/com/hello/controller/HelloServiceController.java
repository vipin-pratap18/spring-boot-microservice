package com.hello.controller;

import com.hello.manager.HelloServiceManager;
import com.hello.model.HelloMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This class is responsible for handling all the vessel information requests
 * based on vessel selection criteria.
 *
 * @author VipinK
 */

@RestController
@RequestMapping("/hello/messages")
public class HelloServiceController {

	private final Logger LOG = LoggerFactory.getLogger(HelloServiceController.class);

	@Autowired
	private HelloServiceManager messageManager;

	@RequestMapping(value = "", method = RequestMethod.POST)
	public HelloMessage saveMessage(@RequestBody HelloMessage message){
		return messageManager.saveMessage(message);
	}

	@RequestMapping(value = "", method = RequestMethod.PUT)
	public HelloMessage updateMessage(@RequestBody HelloMessage message){
		return messageManager.updateMessage(message);
	}

    @RequestMapping(value = "/{messageId}", method = RequestMethod.DELETE)
    public HelloMessage deleteMessage(@PathVariable String messageId){
        return messageManager.deleteMessage(messageId);
    }
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public List<HelloMessage> getAllMessages(){
		return messageManager.getAllMessages();
	}

	@RequestMapping(value = "/{messageId}", method = RequestMethod.GET)
	public HelloMessage getMessageById(@PathVariable String messageId){
		return messageManager.getMessageById(messageId);
	}

}
