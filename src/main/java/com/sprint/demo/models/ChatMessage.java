package com.sprint.demo.models;

public class ChatMessage {
	
	private String sender;
	private OrderDetails orderDetails;
	private MessageType type;
	private String content;
	
	
	public enum MessageType {
        CHAT,
        JOIN,
        LEAVE
    }

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public OrderDetails getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(OrderDetails orderDetails) {
		this.orderDetails = orderDetails;
	}

	

	public String getContent() {
		return content;
	}

	public MessageType getType() {
		return type;
	}

	public void setType(MessageType type) {
		this.type = type;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	

}
