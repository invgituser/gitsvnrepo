package com.invessence.data;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import com.invessence.dao.AccountDAO;
import com.invessence.dao.MsgDAO;

@ManagedBean
@RequestScoped
public class EmailData {
	
	List<MsgData> emailMsgList = null;
	
	
	@ManagedProperty(value="#{msgDAO}")
	private MsgDAO msgDAO;
	
	
	public MsgDAO getMsgDAO() {
		return msgDAO;
	}
	
	
	
	public List<MsgData> getEmailMsgList() {
		return emailMsgList;
	}



	public void setEmailMsgList(List<MsgData> emailMsgList) {
		this.emailMsgList = emailMsgList;
	}



	public void setMsgDAO(MsgDAO msgDAO) {
		this.msgDAO = msgDAO;
	}
	
	public void getEmailMessages() {
		emailMsgList = msgDAO.getEmailMsgList();
	}
	
	

}
