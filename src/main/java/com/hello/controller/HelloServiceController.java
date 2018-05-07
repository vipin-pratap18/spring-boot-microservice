package com.hello.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hello.manager.HelloServiceManager;
import com.hello.model.HelloMessage;
import org.apache.commons.lang.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
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

	@Autowired
	private DiscoveryClient discoveryClient;

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

    @RequestMapping(value = "/sayhello", method = RequestMethod.GET)
    public List<HelloMessage> getAllMessagesByServiceDiscovery(){

        List<HelloMessage> helloMessages = new ArrayList<>();
	    String registeredServiceName = "HELLO-SERVICE";
        List<ServiceInstance> serviceInstance = discoveryClient.getInstances(registeredServiceName);
        if(serviceInstance == null || serviceInstance.isEmpty()) {
            LOG.debug("No running instance found for service {} ", registeredServiceName);
            throw new RuntimeException("No running instance found for the service : " + registeredServiceName);
        }
        String baseURL = serviceInstance.get(0).getUri().toString();
        LOG.debug("Fetched : {} instance is {} ", registeredServiceName, baseURL);

        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper objectMapper = new ObjectMapper();

        ResponseEntity<String> response = restTemplate.exchange(baseURL + "/hello/messages", HttpMethod.GET, null, String.class);
        JSONArray responseJsonObj = new JSONArray(response.getBody());
        TypeReference<List<HelloMessage>> mapType = new TypeReference<List<HelloMessage>>() {};
        try {
            helloMessages = objectMapper.readValue(responseJsonObj.toString(), mapType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return helloMessages;

        //TODO : Load balancer and multiple instances
        //TODO : Request interceptor and Error handler
    }

}
