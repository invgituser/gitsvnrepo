package com.invessence.price.xignite;

import java.util.List;

import com.invessence.price.processor.Service.CallingService;
import com.invessence.price.processor.bean.*;
import com.invessence.price.util.PriceProcessConst;
import com.invessence.price.xignite.bo.*;
import com.invessence.price.xignite.dao.*;
import com.invessence.price.xignite.util.*;
import org.json.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;

public class CallingServiceXignite implements CallingService
{
   @Value(value = "${UTIL.XIGNITE_SWITCH}")
   String xigniteSwitch;

   public static void main(String[] args)
   {
      // TODO Auto-generated method stub
      pricing(PriceProcessConst.operationName);
   }

	/*
    * @Autowired private ProcessDailyPricingDao dailyPricingDao;
	 *
	 * @Autowired private IdenfiersDao idenfiersDao;
	 * Properties configFile = new Properties();
		InputStream input = XigniteUtil.class.getClassLoader().getResourceAsStream("XigniteConfig.properties");
		configFile.load(input);
	 */

   public static void pricing(String operationName)
   {


   }

   @Override
   public List<PriceData> getDailyPriceData(String priceDate, List<SecMaster> tickerList)
   {
      List<PriceData> pdList = null;
      try
      {

         // Code to call xignite switch and check if xignite layer available
         //boolean xigniteSwitch = false;
         XigniteUtil xigniteUtil = new XigniteUtil();
         //xigniteSwitch = xigniteUtil.xigniteSwitch();

         JsonToObject jsonToObject = new JsonToObject();
         // if xignte available call api


         XigniteArgs xigniteArgs = new XigniteArgs();
         // calling dao layer to fetch api identifiers

         IdenfiersDao idenfiersDao = new IdenfiersDao();
         ProcessDailyPricingDao dailyPricingDao = new ProcessDailyPricingDao();
         // TODO code to be written for below dao
         //String operationName= "DAILY_PRICING";
         xigniteArgs = idenfiersDao.fetchApiIdetfiers(PriceProcessConst.operationName);
         // now we have xigniteArgs object populated with operation name
         // and inputs, we are ready to call xignite util
         JSONArray jsonArray = new JSONArray();

         jsonArray = xigniteUtil.xigniteMe(xigniteArgs);
         // This code is for getting json array from json file(offline process)
         /*	try
            {
					//JSONParser parser = new JSONParser();
					//Object obj	 =  parser.parse(new FileReader(
					//"/C:/Users/bhaveshy/Desktop/json_object.txt"));
					JSONTokener jsonTokener = new JSONTokener(new FileReader(
						"/C:/Users/bhaveshy/Desktop/json_object.txt"));
					JSONArray jsonArray1 = new JSONArray(jsonTokener);
					//jsonArray=jsonArray1;
					//System.out.println("*************json file***************" + jsonArray1.length());

					//jsonObject=jsonArray.getJSONObject(0);


				}catch (Exception e)
				{
					e.printStackTrace();
				}
*/
         // we have received json array form xignite util, we can check
         // for errors and proceed
         if (jsonArray == null || jsonArray.equals("") || jsonArray.length() == 0)
         {
            System.out.println("jsonArray values 0");
         }
         else
         {
            System.out.println("Callling Program:: jsonArray: " + jsonArray);
            JSONObject jsonObject = new JSONObject();
            jsonObject = jsonArray.getJSONObject(0);

            String outcome = jsonObject.getString("Outcome");
            System.out.println("Callling Program:: outcome: " + outcome);

            if (outcome.equals("Failed"))
            {
               // TODO Raise an exception to support team, 'Failed" is an
               // error caught at xignite layer while calling api or
               // manipulating xignite layer
               // mailAlertMsg
               System.out.println("Callling Program:: Failed errror");
               //mailAlertMsg.append("Callling Program:: Failed errror");
            }
            else
            {
               // Call parsing program here parsing here
               EndOfDayQuotes endOfDayQuotes = new EndOfDayQuotes();
               // call jsonToObject program for conversion from json to
               // object
               endOfDayQuotes = jsonToObject.parseToEndOfDayQuote(jsonArray);


               pdList = jsonToObject.objectConversion(endOfDayQuotes, priceDate);

               System.out.println("securities" + pdList.size());

               // endOfDayQuotes is populated with data and is available
               // for insertion to database
               System.out
                  .println("Calling program:: open: " + endOfDayQuotes.getEndOfDayQuotes().get(0).getOpen());
//					System.out.println("Calling program:: cik: "
               //	+ endOfDayQuotes.getEndOfDayQuotes().get(0).getSecurity().getCik());
               // DB call to insert data
               dailyPricingDao.insertDailyPricingData(endOfDayQuotes);
            }
         }

         // TODO write code, //xignite switch is not available

      }
      catch (JSONException e)
      {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }

      return pdList;
   }

   @Override
   public List<PriceData> getHistoricalPriceData(String priceDate, String ticker)
   {
      System.out.println("Not Support historical price process for Xignite");
      return null;
   }
}