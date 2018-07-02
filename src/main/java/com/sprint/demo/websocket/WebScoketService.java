package com.sprint.demo.websocket;

import java.security.Principal;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import com.google.gson.Gson;
import com.sprint.demo.models.ChatMessage;
import com.sprint.demo.models.OrderDetails;
import com.sprint.demo.utils.Utils;

@RestController
public class WebScoketService {
	
	@Autowired
    private SimpMessagingTemplate messagingTemplate;

	@MessageMapping("/confirm-order")
	@SendTo("order-details-queue")
	public ChatMessage submitOrder( @Payload ChatMessage chatObj , SimpMessageHeaderAccessor headerAccessor) {
		
;		System.out.println("User name : "+chatObj.getSender());
//		String map = new Gson().toJson(oderDetailsObj);
//		OrderDetails orderDetailsObj = new Gson().fromJson(oderDetailsObj.getContent(), OrderDetails.class);
		OrderDetails orderDetailsObj = chatObj.getOrderDetails();
		orderDetailsObj.setOrderId(Utils.generateString());
		return chatObj;

	}
	
	
	@MessageMapping("/chat.addUser")
    @SendTo("/topic/hello")
    public ChatMessage addUser(@Payload ChatMessage chatMessage,
                               SimpMessageHeaderAccessor headerAccessor) {
        // Add username in web socket session
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        return chatMessage;
    }
	
	
}
