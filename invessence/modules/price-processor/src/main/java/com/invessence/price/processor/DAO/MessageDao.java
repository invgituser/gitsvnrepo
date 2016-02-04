package com.invessence.price.processor.DAO;

import java.sql.SQLException;
import java.util.List;

import com.invessence.price.processor.bean.meassage_data;

public interface MessageDao {
	

	public void insert(meassage_data md) throws SQLException ;

}
