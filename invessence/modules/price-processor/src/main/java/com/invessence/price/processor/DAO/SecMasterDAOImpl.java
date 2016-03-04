package com.invessence.price.processor.DAO;

import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import com.invessence.price.processor.bean.SecMaster;

@Repository
public class SecMasterDAOImpl implements SecMasterDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<SecMaster> findByWhere(String where) throws SQLException{
		List<SecMaster> lst = null;

			System.out.println("SecMasterDAOImpl.findByWhere()");
			String sql = "SELECT instrumentid, status, ticker, cusip, isin, name, assetclass, subclass, type, style, expenseRatio, lowerBoundReturn, upperBoundReturn, taxableReturn, nontaxableReturn, issuer, adv3months, aum, beta, securityRiskSTD, lowerbound, upperbound, yield, rbsaFlag FROM invdb.sec_master where "+where;
			lst = jdbcTemplate.query(sql, ParameterizedBeanPropertyRowMapper.newInstance(SecMaster.class));
			System.out.println("lst size :" + lst.size());
			return lst;
	}

	public SecMaster findByTicker(String ticker) throws SQLException{
		List<SecMaster> lst = null;
			System.out.println("SecMasterDAOImpl.findByWhere()");
			String sql = "SELECT instrumentid, status, ticker, cusip, isin, name, assetclass, subclass, type, style, expenseRatio, lowerBoundReturn, upperBoundReturn, taxableReturn, nontaxableReturn, issuer, adv3months, aum, beta, securityRiskSTD, lowerbound, upperbound, yield, rbsaFlag FROM invdb.sec_master where ticker='"+ticker+"'";
			lst = jdbcTemplate.query(sql, ParameterizedBeanPropertyRowMapper.newInstance(SecMaster.class));
			System.out.println("lst size :" + lst.size());
			return lst==null?null:lst.size()==0?null:lst.get(0);
	}

}
