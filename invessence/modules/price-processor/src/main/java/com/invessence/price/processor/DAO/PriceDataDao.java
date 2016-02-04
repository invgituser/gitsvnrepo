package com.invessence.price.processor.DAO;

import java.sql.SQLException;
import java.util.List;

import com.invessence.price.processor.bean.PriceData;

public interface PriceDataDao {

	public void delete();
	public void insert(PriceData priceData) throws SQLException;
	
	public void insertBatch(final List<PriceData> priceDataLst)throws SQLException;
	public List <PriceData> findByTicker(String ticker);
	public List<PriceData> findByDate(String date);
	public PriceData findByTickerNDate(String ticker, String date);
	public void callProcedure(String process, String businessDate, String ticker);
	
		
	
}
