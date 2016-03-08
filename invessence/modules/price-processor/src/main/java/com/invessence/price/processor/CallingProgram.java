package com.invessence.price.processor;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.invessence.price.xignite.bo.EndOfDayQuotes;
import com.invessence.price.xignite.bo.XigniteArgs;
import com.invessence.price.xignite.dao.IdenfiersDao;
import com.invessence.price.xignite.dao.ProcessDailyPricingDao;
import com.invessence.price.xignite.util.JsonToObject;
import com.invessence.price.xignite.util.XigniteUtil;
import org.springframework.beans.factory.annotation.Value;

public class CallingProgram {
	@Value(value="${UTIL.XIGNITE_SWITCH}")
	String xigniteSwitch;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		pricing("DAILY_PRICING");
	}

	/*
	 * @Autowired private ProcessDailyPricingDao dailyPricingDao;
	 * 
	 * @Autowired private IdenfiersDao idenfiersDao;
	 * Properties configFile = new Properties();
		InputStream input = XigniteUtil.class.getClassLoader().getResourceAsStream("XigniteConfig.properties");
		configFile.load(input);
	 */

	public static void pricing(String operationName) {

		try {
			// Code to call xignite switch and check if xignite layer available
			boolean xigniteSwitch = false;
			XigniteUtil xigniteUtil = new XigniteUtil();
			xigniteSwitch = xigniteUtil.xigniteSwitch();

			JsonToObject jsonToObject = new JsonToObject();
			// if xignte available call api
			if (xigniteSwitch) {
				XigniteArgs xigniteArgs = new XigniteArgs();
				// calling dao layer to fetch api identifiers
				IdenfiersDao idenfiersDao = new IdenfiersDao();
				ProcessDailyPricingDao dailyPricingDao = new ProcessDailyPricingDao();
				// TODO code to be written for below dao
				xigniteArgs = idenfiersDao.fetchApiIdetfiers(operationName);
				// now we have xigniteArgs object populated with operation name
				// and inputs, we are ready to call xignite util
				JSONArray jsonArray = new JSONArray();
				jsonArray = xigniteUtil.xigniteMe(xigniteArgs);

				// we have received json array form xignite util, we can check
				// for errors and proceed
				System.out.println("Callling Program:: jsonArray: " + jsonArray);
				JSONObject jsonObject = new JSONObject();
				jsonObject = jsonArray.getJSONObject(0);
				String outcome = jsonObject.getString("Outcome");
				System.out.println("Callling Program:: outcome: " + outcome);
				if (outcome.equals("Failed")) {
					// TODO Raise an exception to support team, 'Failed" is an
					// error caught at xignite layer while calling api or
					// manipulating xignite layer
					System.out.println("Callling Program:: Failed errror");
				} else {
					// Call parsing program here parsing here
					EndOfDayQuotes endOfDayQuotes = new EndOfDayQuotes();
					// call jsonToObject program for conversion from json to
					// object
					endOfDayQuotes = jsonToObject.parseToEndOfDayQuote(jsonArray);

					// endOfDayQuotes is populated with data and is available
					// for insertion to database
					System.out
							.println("Calling program:: open: " + endOfDayQuotes.getEndOfDayQuotes().get(0).getOpen());
					System.out.println("Calling program:: cik: "
							+ endOfDayQuotes.getEndOfDayQuotes().get(0).getSecurity().getCik());
					// DB call to insert data
					dailyPricingDao.insertDailyPricingData(endOfDayQuotes);
				}
			} else {

			}
			// TODO write code, //xignite switch is not available

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}