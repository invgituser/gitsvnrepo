package com.invessence.broker.dao;

import java.util.*;
import javax.sql.DataSource;

import com.invessence.broker.bean.DownloadFileDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Component;

@Component
public class FileDetailsDAOImpl implements FileDetailsDao {

	@Autowired
	DataSource dataSource;
	private JdbcTemplate jdbcTemplate;

	public Map<String, DownloadFileDetails> getFileDetails() {
		List<DownloadFileDetails> dbParamsLst = null;
		Map<String, DownloadFileDetails> dbParamsMap = null;
		try {
			System.out.println("SecMasterDAOImpl.findByWhere()");
			String sql = "SELECT name, value, format, description FROM invessence_switch where name in('LAST_BDATE_OF_MONTH','PRICE_DATE')";
			jdbcTemplate = new JdbcTemplate(dataSource);
			dbParamsLst = jdbcTemplate.query(sql, ParameterizedBeanPropertyRowMapper.newInstance(DownloadFileDetails.class));
			if(dbParamsLst.size()>0){
				dbParamsMap=new HashMap<String, DownloadFileDetails>();
				Iterator<DownloadFileDetails> itr=dbParamsLst.iterator();
				while (itr.hasNext()) {
					DownloadFileDetails dbParameters = (DownloadFileDetails) itr.next();
					//dbParamsMap.put(dbParameters.getName(), dbParameters);
				}
				
			}
			return dbParamsMap;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}
	public List<DownloadFileDetails> findByWhere(String where) {
		List<DownloadFileDetails> lst = null;
		try {
			System.out.println("SecMasterDAOImpl.findByWhere()");
			String sql = "SELECT vendor, filename, active, tmp_tableName, available, sourcepath, downloaddir, format, required, canbeempty, postProcess, postInstruction FROM download_files where "+where;
			jdbcTemplate = new JdbcTemplate(dataSource);
			lst = jdbcTemplate.query(sql, ParameterizedBeanPropertyRowMapper.newInstance(DownloadFileDetails.class));
			System.out.println("lst size :" + lst.size());
			return lst;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lst;
	}

}
