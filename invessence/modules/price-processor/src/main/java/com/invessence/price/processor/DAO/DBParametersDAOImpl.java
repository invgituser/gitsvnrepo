package com.invessence.price.processor.DAO;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.SqlReturnType;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.invessence.price.processor.bean.DBParameters;
import com.invessence.price.processor.bean.SecMaster;

@Repository
public class DBParametersDAOImpl implements DBParametersDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public Map<String, DBParameters> getDBParametres() throws SQLException {
		List<DBParameters> dbParamsLst = null;
		Map<String, DBParameters> dbParamsMap = null;
		try {
			System.out.println("SecMasterDAOImpl.findByWhere()");
			String sql = "SELECT name, value, format, description FROM invdb.invessence_switch where name in('BUSINESS_DATE','LAST_BDATE_OF_MONTH','PRICE_DATE')";
			dbParamsLst = jdbcTemplate.query(sql, ParameterizedBeanPropertyRowMapper.newInstance(DBParameters.class));
			if(dbParamsLst.size()>0){
				dbParamsMap=new HashMap<String, DBParameters>();
				Iterator<DBParameters> itr=dbParamsLst.iterator();
				while (itr.hasNext()) {
					DBParameters dbParameters = (DBParameters) itr.next();
					dbParamsMap.put(dbParameters.getName(), dbParameters);					
				}
				
			}
			
			
			
			
			
			return dbParamsMap;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}
}
