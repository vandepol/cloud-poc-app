package com.sprint.demo.services;

import java.util.Map;

import javax.jms.DeliveryMode;
import javax.jms.Message;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.sprint.demo.jms.JmsConfig;
import com.sprint.demo.models.ConfirmOrderLVOResponse;
import com.sprint.demo.models.OrderDetails;
import com.sprint.demo.mule.ConfirmOrderServiceImpl;
import com.sprint.demo.utils.Utils;


@RestController
public class ConfirmOrderService {
	
	 @Autowired
	  private JmsConfig jmsConfig;
	 
	 @Autowired
		private  OrderRepository repository;
	 
	 @Autowired
	 ConfirmOrderServiceImpl confirmOrderServiceImpl;

	@RequestMapping(value = "/confirm-order" ,  method = RequestMethod.POST)
	public ResponseEntity placeAnOrderAsynchronously(@RequestBody OrderDetails reqBody){
		
		String orderId = Utils.generateString();
		System.out.println("Received request ");
		reqBody.setOrderId(orderId);
		//Writing to persisted QUEUE1
		JmsTemplate jmsTemplate = jmsConfig.jmsTemplate();
		String map = new Gson().toJson(reqBody);
		 jmsTemplate.convertAndSend("outbound.queue", map, m -> {

	            m.setJMSCorrelationID(orderId);
	            m.setJMSExpiration(1000);
	            m.setJMSMessageID("message-id that comes from client in the header.");
	            m.setJMSDeliveryMode(DeliveryMode.PERSISTENT);
	            m.setJMSPriority(Message.DEFAULT_PRIORITY);
	            m.setJMSTimestamp(System.nanoTime());
	            m.setJMSType("type");
	            m.setStringProperty("jms-custom-header", "this is a custom jms property");
	            m.setBooleanProperty("jms-custom-property", true);
	            m.setDoubleProperty("jms-custom-property-price", 0.0);

	            return m;
	        });
		 
		 
		 
		 
		 
		 
//		 ConfirmOrderLVOResponse confirmOrderLVOResponse = null; 
//		 
//		 //CALLING MULE API 
//		 try {
//			 confirmOrderLVOResponse = confirmOrderServiceImpl.confirmOrder(reqBody);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		 
//		 //IF SUCCESS
//		 if(confirmOrderLVOResponse.getStatus() == 200){
//			 confirmOrderLVOResponse.setOrderId(orderId);
//			 //DELETE FROM PERSISTED QUEUE1
////			 jmsTemplate.convertAndSend("success.queue", map, m -> {
////
////		            m.setJMSCorrelationID(orderId);
////		            m.setJMSExpiration(1000);
////		            m.setJMSMessageID("message-id that comes from client in the header.");
////		            m.setJMSDeliveryMode(DeliveryMode.NON_PERSISTENT);
////		            m.setJMSPriority(Message.DEFAULT_PRIORITY);
////		            m.setJMSTimestamp(System.nanoTime());
////		            m.setJMSType("type");
////		            m.setStringProperty("jms-custom-header", "this is a custom jms property");
////		            m.setBooleanProperty("jms-custom-property", true);
////		            m.setDoubleProperty("jms-custom-property-price", 0.0);
////
////		            return m;
////		        });
//			//RETURN SUCCESS RESPONSE TO CLIENT
//			 return new ResponseEntity<>(confirmOrderLVOResponse, HttpStatus.OK);
//		 }
		 
		 
		 
		 
		 //IF FAILURE INSERT INTO MONGO DB
//		 else{
//			confirmOrderLVOResponse.setOrderId(orderId);
//		 	reqBody.setHttpStatus(confirmOrderLVOResponse.getStatus());
//		 	reqBody.setErrorCode(confirmOrderLVOResponse.getErrorCode());
//		 	reqBody.setErrorCategory(confirmOrderLVOResponse.getCategory());
//		 	reqBody.setErrorMessage(confirmOrderLVOResponse.getErrorMessage());
//		 	//Saving into MONGO DB
//		 
//		 	map = new Gson().toJson(reqBody);
//		 	jmsTemplate.convertAndSend("outbound.queue", map, m -> {
//
//	            m.setJMSCorrelationID(orderId);
//	            m.setJMSExpiration(10000);
//	            m.setJMSMessageID("message-id that comes from client in the header.");
//	            m.setJMSDeliveryMode(DeliveryMode.PERSISTENT);
//	            m.setJMSPriority(Message.DEFAULT_PRIORITY);
//	            m.setJMSTimestamp(System.nanoTime());
//	            m.setJMSType("type");
//	            m.setStringProperty("jms-custom-header", "this is a custom jms property");
//	            m.setBooleanProperty("jms-custom-property", true);
//	            m.setDoubleProperty("jms-custom-property-price", 0.0);
//
//	            return m;
//	        });
//		 	
//		 	//GETTING UPDATED DETAILS FROM MONGO DB , NOT NEEDED BUT DOING JUST FOR CHECKING
//		 	reqBody = repository.findByOrderId(orderId);
//		 	
//		 }
		 reqBody = repository.findByOrderId(orderId);
		 	//AND ALSO RETURN ERROR OR SOME MESSAGE TO CLIENT
		
		return new ResponseEntity<>(reqBody, HttpStatus.OK);
	}
}
