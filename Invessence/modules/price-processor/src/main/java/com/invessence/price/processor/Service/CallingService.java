package com.invessence.price.processor.Service;

import java.util.*;

import com.invessence.price.processor.bean.*;
import com.invessence.service.bean.ServiceRequest;

/**
 * Created by bhaveshy on 3/16/2016.
 */
public interface CallingService
{
   public List<PriceData> getDailyPriceData(String priceDate, List<SecMaster> tickerList)throws Exception;
   public  List<PriceData> getHistoricalPriceData(String priceDate, String ticker)throws  Exception;
   public HashMap<String, Object> getDailyPriceData(String priceDate,String ticker,ServiceRequest serviceRequest)throws Exception;
   public HashMap<String, Object> getHistoryPriceData(String priceDate,String ticker,ServiceRequest serviceRequest)throws Exception;
}
