package com.invessence.price.processor.Service;

import java.io.IOException;
import java.util.*;

import com.invessence.price.processor.bean.*;
import com.invessence.service.bean.ServiceRequest;


/**
 * Created by bhaveshy on 3/14/2016.
 */

public interface PriceService
{
   public List<PriceData> getPrice(List<APIDetails> apidetails, String operation, String priceDate, List<SecMaster> tickerList) throws Exception;
   public List<PriceData> getPrice(List<APIDetails> apidetails, String operation, String priceDate, String ticker) throws Exception;
   public HashMap<String, Object> getPrice(List<APIDetails> apidetails, String operation, String priceDate, String ticker, String tickerSource,ServiceRequest serviceRequest) throws Exception;
}


