package com.invessence.price.FIS.dao;

import java.sql.SQLException;
import java.util.List;

import com.invessence.price.FIS.bean.*;

/**
 * Created by sagar on 4/10/2017.
 */
public interface  SecExchangeDao
{
   public void delete(String symbol,String exchangeDate) throws SQLException;
   public List<SecExchangeMaster> getSymbol() throws SQLException;
   public void insertBatch(List<HistoricalDataRates> TimeSeriesPoint,String symbol ) throws SQLException;
   public void insert(HistoricalData objDailyRates,String symbol ) throws SQLException;

}
