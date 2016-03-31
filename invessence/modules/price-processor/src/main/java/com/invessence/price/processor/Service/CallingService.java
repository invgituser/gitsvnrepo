package com.invessence.price.processor.Service;

import java.util.List;

import com.invessence.price.processor.bean.*;

/**
 * Created by bhaveshy on 3/16/2016.
 */
public interface CallingService
{
   public List<PriceData> getDailyPriceData(String businessDate, List<SecMaster> tickerList)throws Exception;
   public  List<PriceData> getHistoricalPriceData(String businessDate, String ticker)throws  Exception;
}
