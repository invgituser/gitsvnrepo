package com.invessence.price.processor.DAO;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.invessence.price.processor.bean.DBParameters;
public interface DBParametersDao {

	public Map<String, DBParameters> getDBParametres() throws SQLException;
}
