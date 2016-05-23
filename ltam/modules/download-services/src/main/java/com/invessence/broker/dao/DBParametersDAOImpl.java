package com.invessence.broker.dao;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import com.invessence.broker.bean.DBParameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.SqlReturnType;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Component;

@Component
public class DBParametersDAOImpl implements DBParametersDao {
	@Autowired
	DataSource dataSource;
	private JdbcTemplate jdbcTemplate;

	public Map<String, DBParameters> getDBParametres() {
		List<DBParameters> dbParamsLst = null;
		Map<String, DBParameters> dbParamsMap = null;
		try {
			System.out.println("SecMasterDAOImpl.findByWhere()");
			String sql = "SELECT name, value, format, description FROM invessence_switch where name in('LAST_BDATE_OF_MONTH','PRICE_DATE')";
			jdbcTemplate = new JdbcTemplate(dataSource);
			dbParamsLst = jdbcTemplate.query(sql, ParameterizedBeanPropertyRowMapper.newInstance(DBParameters.class));
			if(dbParamsLst.size()>0){
				dbParamsMap=new HashMap<String, DBParameters>();
				Iterator<DBParameters> itr=dbParamsLst.iterator();
				while (itr.hasNext()) {
					DBParameters dbParameters = (DBParameters) itr.next();
					dbParamsMap.put(dbParameters.getName(), dbParameters);					
				}
				
			}
			
//			System.out.println("******************************");
//			SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
//					.withProcedureName("getDB_Parameters");
//					Map<String, Object> inParamMap = new HashMap<String, Object>();
//					inParamMap.put("process", "MONTHLY");
//					SqlParameterSource in = new MapSqlParameterSource(inParamMap);
//					Map<String, Object> simpleJdbcCallResult = simpleJdbcCall.execute(in);
//					System.out.println(simpleJdbcCallResult);
//					System.out.println("******************************");
			
			return dbParamsMap;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}
}
