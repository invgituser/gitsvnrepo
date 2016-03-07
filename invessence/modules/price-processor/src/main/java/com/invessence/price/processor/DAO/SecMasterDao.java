package com.invessence.price.processor.DAO;
import java.sql.SQLException;
import java.util.List;
import com.invessence.price.processor.bean.SecMaster;

public interface SecMasterDao  {

	public List<SecMaster> findByWhere(String where)throws SQLException;;
	public SecMaster findByTicker(String ticker)throws SQLException;
}
