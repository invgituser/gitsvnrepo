package com.invessence.price.processor.bean;

import java.math.BigInteger;

public class EmailMsg
{
	private BigInteger messageid;
	private StringBuilder msg;
	private String sender;
	private String subject;
	
	
	
	public EmailMsg() {
		// TODO Auto-generated constructor stub
	}



	public EmailMsg(BigInteger messageid, StringBuilder msg, String sender, String subject) {
		super();
		this.messageid = messageid;
		this.msg = msg;
		this.sender = sender;
		this.subject = subject;
	}



	public BigInteger getMessageid() {
		return messageid;
	}



	public void setMessageid(BigInteger messageid) {
		this.messageid = messageid;
	}



	public StringBuilder getMsg() {
		return msg;
	}



	public void setMsg(StringBuilder msg) {
		this.msg = msg;
	}



	public String getSender() {
		return sender;
	}



	public void setSender(String sender) {
		this.sender = sender;
	}



	public String getSubject() {
		return subject;
	}



	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	

}
