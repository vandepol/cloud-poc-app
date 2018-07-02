package com.sprint.demo.models;

import java.io.Serializable;

public class OrderDetails implements Serializable {
	
	private String orderId ;
	private int httpStatus;
	private String errorCode;
	private String cvv;
	private String expDate;
	private String nameOnCard;
	private String cardNumber;
	private String errorCategory;
	private String errorMessage;
	
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public int getHttpStatus() {
		return httpStatus;
	}
	public void setHttpStatus(int httpStatus) {
		this.httpStatus = httpStatus;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getCvv() {
		return cvv;
	}
	public void setCvv(String cvv) {
		this.cvv = cvv;
	}
	public String getExpDate() {
		return expDate;
	}
	public void setExpDate(String expDate) {
		this.expDate = expDate;
	}
	public String getNameOnCard() {
		return nameOnCard;
	}
	public void setNameOnCard(String nameOnCard) {
		this.nameOnCard = nameOnCard;
	}
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public OrderDetails(String orderId, int httpStatus, String errorCode, String cvv, String expDate,
			String nameOnCard, String cardNumber) {
		super();
		this.orderId = orderId;
		this.httpStatus = httpStatus;
		this.errorCode = errorCode;
		this.cvv = cvv;
		this.expDate = expDate;
		this.nameOnCard = nameOnCard;
		this.cardNumber = cardNumber;
	}
	
	public String getErrorCategory() {
		return errorCategory;
	}
	public void setErrorCategory(String errorCategory) {
		this.errorCategory = errorCategory;
	}
	
	
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public OrderDetails(){}
	 

}
