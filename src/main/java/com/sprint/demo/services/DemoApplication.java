package com.sprint.demo.services;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableJms
@ImportResource("classpath:applicationContext.xml")
public class DemoApplication {
	
    public static void main(String[] args) {
    	System.out.println("hi .......");
        SpringApplication.run(DemoApplication.class, args);
    		
        
    	}
//    @Override implements CommandLineRunner
//    public void run(String... args) throws Exception {
//    	repository.save(new OrderDetails("orderId", "httpStatus","errorCode","cvv","expDate",
//    			"nameOnCard","cardNumber"));
//    	
//    	OrderDetails obj = repository.findByOrderId("orderId");
//    	
//    	System.out.println("From db  : "+obj.getCardNumber());
//    }
    
}