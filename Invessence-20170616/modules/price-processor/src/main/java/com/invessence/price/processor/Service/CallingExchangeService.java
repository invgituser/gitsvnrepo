package com.invessence.price.processor.Service;

import java.util.List;

import com.invessence.price.FIS.bean.*;

/**
 * Created by bhaveshy on 3/16/2016.
 */
public interface CallingExchangeService
{
   public HistoricalData getDailyExchangeRate(String URL, String symbol,String exchangeDate)throws Exception;
   public List<HistoricalDataRates>  getHistoricalExchangeRate(String URL, String symbol,String exchangeDate)throws Exception;

}
