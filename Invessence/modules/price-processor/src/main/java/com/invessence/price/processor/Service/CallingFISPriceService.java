package com.invessence.price.processor.Service;

import java.util.List;

import com.invessence.price.FIS.bean.*;

/**
 * Created by bhaveshy on 3/16/2016.
 */
public interface CallingFISPriceService
{
   public HistoricalData getDailyPrice(String URL, String symbol,String businessDate)throws Exception;
   public List<HistoricalDataRates>  getHistoricalPrice(String URL, String symbol,String businessDate)throws Exception;

}
