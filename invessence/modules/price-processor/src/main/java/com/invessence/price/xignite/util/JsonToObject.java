package com.invessence.price.xignite.util;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.invessence.price.xignite.bo.EndOfDayQuote;
import com.invessence.price.xignite.bo.EndOfDayQuotes;
import com.invessence.price.xignite.bo.Securities;

/**
 * This calls can be used as utility class, contains methods for conversion of
 * json to different objects or business objects
 * 
 * @author Hiten patidar
 *
 */
public class JsonToObject {
	/**
	 * this method take Json array and and object of list type of End of the day
	 * quotes upon parsing json array object is populated as a list
	 * 
	 * @param jsonArray
	 * @return
	 */
	public EndOfDayQuotes parseToEndOfDayQuote(JSONArray jsonArray) {

		EndOfDayQuotes endOfDayQuotes = new EndOfDayQuotes();
		// iterate json array and store each iteration in a list element
		String outcome = "";
		System.out.println("jsonArray.length(): " + jsonArray.length());
		List<EndOfDayQuote> endOfDayQuotesLst = new ArrayList<EndOfDayQuote>();
		try {
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonObject = new JSONObject();
				jsonObject = (JSONObject) jsonArray.get(i);
				// TODO now you have json object instance with you, each
				// instance
				// contains data for a ticker, while parsing this you need to
				// check
				// for api errors
				outcome = jsonObject.get("Outcome").toString();
				if (outcome.equals("RegistrationError")) {
					// code to handle registration errors , collect all the
					// errror details here
					System.out.println("RegistrationError");
					continue;
				} else if (outcome.equals("RequestError")) {
					// code to handle request errors , collect all the errror
					// details here
					System.out.println("RequestError");
					continue;
				} else if (outcome.equals("SystemError")) {
					// code to handle system errors , collect all the errror
					// details here
					System.out.println("SystemError");
					continue;
				} else { // Success
					System.out.println("Object Parsing ");
					EndOfDayQuote endOfDayQuote = new EndOfDayQuote();
					endOfDayQuote.setDate(jsonObject.get("Date") == null ? null : jsonObject.getString("Date"));
					endOfDayQuote.setLast(jsonObject.getDouble("Last"));
					endOfDayQuote.setOpen(jsonObject.getDouble("Open"));
					endOfDayQuote.setHigh(jsonObject.getDouble("High"));
					endOfDayQuote.setLow(jsonObject.getDouble("Low"));
					endOfDayQuote.setVolume(jsonObject.getDouble("Volume"));
					endOfDayQuote.setLastClose(jsonObject.getDouble("LastClose"));
					endOfDayQuote.setChangeFromOpen(jsonObject.getDouble("ChangeFromOpen"));
					endOfDayQuote.setPercentChangeFromOpen(jsonObject.getDouble("PercentChangeFromOpen"));
					endOfDayQuote.setChangeFromLastClose(jsonObject.getDouble("ChangeFromLastClose"));
					endOfDayQuote.setPercentChangeFromLastClose(jsonObject.getDouble("PercentChangeFromLastClose"));
					endOfDayQuote.setSplitRatio(jsonObject.getDouble("SplitRatio"));
					endOfDayQuote.setCummulativeCashDividend(jsonObject.getDouble("CummulativeCashDividend"));
					endOfDayQuote.setCummulativeStockDividend(jsonObject.getDouble("CummulativeStockDividend"));
					endOfDayQuote
							.setCummulativeStockDividendRatio(jsonObject.getDouble("CummulativeStockDividendRatio"));
					endOfDayQuote
							.setCurrency(jsonObject.get("Currency") == null ? null : jsonObject.getString("Currency"));
					endOfDayQuote.setAdjustmentMethods(jsonObject.get("AdjustmentMethodUsed") == null ? null
							: jsonObject.getString("AdjustmentMethodUsed"));
					endOfDayQuote.setDataConfidence(
							jsonObject.get("DataConfidence") == null ? null : jsonObject.getString("DataConfidence"));
					endOfDayQuote.setLastPriceSource(
							jsonObject.get("LastPriceSource") == null ? null : jsonObject.getString("LastPriceSource"));
					endOfDayQuote.setLastPriceType(
							jsonObject.get("LastPriceType") == null ? null : jsonObject.getString("LastPriceType"));
					endOfDayQuote.setEndOfDayPrice(jsonObject.getDouble("Open"));
					endOfDayQuote.setEndOfDayPriceDate(jsonObject.get("EndOfDayPriceDate") == null ? null
							: jsonObject.getString("EndOfDayPriceDate"));
					endOfDayQuote.setEndOfDayPriceMethodUsed(jsonObject.get("EndOfDayPriceMethodUsed") == null ? null
							: jsonObject.getString("EndOfDayPriceMethodUsed"));
					endOfDayQuote.setEndOfDayPriceTimings(jsonObject.get("EndOfDayPriceTiming") == null ? null
							: jsonObject.getString("EndOfDayPriceTiming"));
					endOfDayQuote.setEndOfDayPriceSource(jsonObject.get("EndOfDayPriceSource") == null ? null
							: jsonObject.getString("EndOfDayPriceSource"));
					endOfDayQuote.setExchangeClose(jsonObject.getDouble("ExchangeClose"));
					endOfDayQuote.setValuation(jsonObject.getDouble("Valuation"));
					endOfDayQuote.setLastTradeDate(
							jsonObject.get("LastTradeDate") == null ? null : jsonObject.getString("LastTradeDate"));
					endOfDayQuote.setExchangeCloseDate(jsonObject.get("ExchangeCloseDate") == null ? null
							: jsonObject.getString("ExchangeCloseDate"));
					endOfDayQuote.setValuationDate(
							jsonObject.get("ValuationDate") == null ? null : jsonObject.getString("ValuationDate"));

					// setting security object
					JSONObject sec = (JSONObject) jsonObject.get("Security");
					Securities securities = new Securities();
					if (sec != null) {
						securities.setCik(sec.get("CIK") == null ? null : sec.getString("CIK"));
						securities.setCusip(sec.get("CUSIP") == null ? null : sec.getString("CUSIP"));
						securities.setSymbol(sec.get("Symbol") == null ? null : sec.getString("Symbol"));
						securities.setIsin(sec.get("ISIN") == null ? null : sec.getString("ISIN"));
						securities.setValoren(sec.get("Valoren") == null ? null : sec.getString("Valoren"));
						securities.setName(sec.get("Name") == null ? null : sec.getString("Name"));
						securities.setMarket(sec.get("Market") == null ? null : sec.getString("Market"));
						securities.setCategoryOrIndustry(
								sec.get("CategoryOrIndustry") == null ? null : sec.getString("CategoryOrIndustry"));
						// securities.setSymbol(sec.get("Symbol") == null ? null
						// : sec.getString("Symbol"));
						// securities.setSymbol(sec.get("Symbol") == null ? null
						// : sec.getString("Symbol"));
						// securities.setSymbol(sec.get("Symbol") == null ? null
						// : sec.getString("Symbol"));
						// securities.setSymbol(sec.get("Symbol") == null ? null
						// : sec.getString("Symbol"));

					}

					endOfDayQuote.setSecurity(securities);
					// Adding object to list for each iteration
					endOfDayQuotesLst.add(endOfDayQuote);
				}
				endOfDayQuotes.setEndOfDayQuotes(endOfDayQuotesLst);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO call email sending program to send collected errors
		return endOfDayQuotes;
	}
}