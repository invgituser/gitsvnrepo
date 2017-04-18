package com.invessence.price.processor.dao;
import java.sql.SQLException;
import java.util.List;

import com.invessence.price.processor.bean.*;

public interface SecMasterDao  {

	public List<SecMaster> findByWhere(String where)throws SQLException;
	public SecMaster findByTicker(String ticker)throws SQLException;
	public List<APIDetails> getSwitch(String companyName,String service_operation) throws SQLException;
	public List<SecMaster> getTicker(String priceDate)throws SQLException;


}
