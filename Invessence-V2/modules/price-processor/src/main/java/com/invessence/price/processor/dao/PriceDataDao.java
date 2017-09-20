package com.invessence.price.processor.dao;

import java.sql.SQLException;
import java.util.List;

import com.invessence.price.processor.bean.PriceData;

public interface PriceDataDao {

	public void delete()throws SQLException;
	public void insert(PriceData priceData) throws SQLException;
	public void insertBatch(final List<PriceData> priceDataLst,String destCurrency)throws SQLException;
	public List <PriceData> findByTicker(String ticker);
	public List<PriceData> findByDate(String date);
	public PriceData findByTickerNDate(String ticker, String date);
	public String callProcedure(String process, String businessDate, String ticker,String dest_currency)throws SQLException;
	public void callEodProcedure(String process, String businessDate)throws SQLException;
	public void callHolidayProcedure(String startDate, String endDate) throws SQLException;
	public void GetDailyMissingData(String startDate, String ticker,String destcurrency) throws SQLException;
	public void GetExchangePriceData(String ticker,String destcurrency)throws SQLException;

}
