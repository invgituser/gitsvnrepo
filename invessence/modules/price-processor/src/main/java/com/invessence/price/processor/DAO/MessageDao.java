package com.invessence.price.processor.DAO;

import java.sql.SQLException;

import com.invessence.price.processor.bean.EmailMsg;

public interface MessageDao {
	

	public void insert(EmailMsg md) throws SQLException ;

}
