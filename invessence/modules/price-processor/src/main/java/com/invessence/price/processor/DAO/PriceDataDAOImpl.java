package com.invessence.price.processor.DAO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.invessence.price.processor.bean.PriceData;

@Repository
@Transactional
public class PriceDataDAOImpl implements PriceDataDao {
	@Autowired
	DataSource dataSource;
	private JdbcTemplate jdbcTemplate;

	public void delete() {
		try {
			
			String sql = "DELETE from tmp_rbsa_daily";
			jdbcTemplate = new JdbcTemplate(dataSource);
			jdbcTemplate.execute(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void callProcedure(String process, String businessDate, String ticker){
		try{
	       System.out.println("******************************");
	       jdbcTemplate = new JdbcTemplate(dataSource);			
	        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
			.withProcedureName("price_processor");
			Map<String, Object> inParamMap = new HashMap<String, Object>();
			inParamMap.put("p_process",process);
			inParamMap.put("p_businessDate",businessDate);
			inParamMap.put("p_ticker",ticker);
			SqlParameterSource in = new MapSqlParameterSource(inParamMap);
			Map<String, Object> simpleJdbcCallResult = simpleJdbcCall.execute(in);
			System.out.println(simpleJdbcCallResult);
			System.out.println("******************************");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public void insert(PriceData priceData) throws SQLException {

		

			String sql = "INSERT INTO tmp_rbsa_daily "
					+ "(TICKER, BUSINESSDATE, OPEN_PRICE, CLOSE_PRICE, HIGH_PRICE, LOW_PRICE, PREV_CLOSE_PRICE,VOLUME/*,INSERTED_BY,INSERTED_ON*/) VALUES (?, ?, ?, ?,?,?,?,?/*, ?, ?*/)";

			jdbcTemplate = new JdbcTemplate(dataSource);

			jdbcTemplate.update(sql,
					new Object[] { priceData.getTicker(), priceData.getBusinessDate(), priceData.getOpenPrice(),
							priceData.getClosePrice(), priceData.getHighPrice(), priceData.getLowPrice(), priceData
									.getPrevClosePrice(),
					priceData.getVolume()/* ,new Long (63), new Date() */
			});
			
			
		
	}

	public void log(String error)
	{
		
	}
	public void insertBatch(final List<PriceData> customers){
		
		String sql = "INSERT INTO tmp_rbsa_daily "
				+ "(TICKER, BUSINESSDATE, OPEN_PRICE, CLOSE_PRICE, HIGH_PRICE, LOW_PRICE, PREV_CLOSE_PRICE,VOLUME/*,INSERTED_BY,INSERTED_ON*/) VALUES (?, ?, ?, ?,?,?,?,?/*, ?, ?*/)";
			
		  jdbcTemplate.batchUpdate (sql, new BatchPreparedStatementSetter() {
							
			public int getBatchSize() {
				return customers.size();
			}

			public void setValues(PreparedStatement ps, int i) throws SQLException {
				
				PriceData priceData = customers.get(i);
				
				ps.setString(1,priceData.getTicker()); 
				//ps.setDate(2,  new java.sql.Date(priceData.getBusinessDate()));
				ps.setString(2, priceData.getBusinessDate());
				ps.setDouble(3,priceData.getOpenPrice()); 
				ps.setDouble(4,priceData.getClosePrice());  
				ps.setDouble(5,priceData.getHighPrice());  
				ps.setDouble(6,priceData.getLowPrice());  
				ps.setDouble(7,priceData.getPrevClosePrice()); 
				ps.setDouble(8,priceData.getVolume());				
			}
		  });
		  
//			System.out.println("******************************");
//			jdbcTemplate = new JdbcTemplate(dataSource);			
//			SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
//					.withProcedureName("price_processor");
//					Map<String, Object> inParamMap = new HashMap<String, Object>();
//					inParamMap.put("process", "DAILY");
//					SqlParameterSource in = new MapSqlParameterSource(inParamMap);
//					Map<String, Object> simpleJdbcCallResult = simpleJdbcCall.execute(in);
//					System.out.println(simpleJdbcCallResult);
//					System.out.println("******************************");
		}
	
	public List<PriceData> findByTicker(String ticker) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<PriceData> findByDate(String date) {
		// TODO Auto-generated method stub
		return null;
	}

	public PriceData findByTickerNDate(String ticker, String date) {
		// TODO Auto-generated method stub
		return null;
	}

}
