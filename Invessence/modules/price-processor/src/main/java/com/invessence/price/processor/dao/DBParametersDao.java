package com.invessence.price.processor.dao;

import java.sql.SQLException;
import java.util.Map;

import com.invessence.price.processor.bean.DBParameters;
public interface DBParametersDao {

	public Map<String, DBParameters> getDBParametres() throws SQLException;
	public Map<String, DBParameters> getPrevBusinessDate(String businessDate) throws SQLException;
}
