package com.invessence.xignite.util;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.invessence.xignite.bean.XigniteArgs;
import com.invessence.xignite.bean.XigniteInputs;
import com.invessence.xignite.bean.XigniteKeyValue;

public class CallingProgram {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		XigniteUtil xigniteUtil = new XigniteUtil();
		XigniteArgs xigniteArgs = new XigniteArgs();

		XigniteInputs inputs = new XigniteInputs();

		XigniteKeyValue keyValue = null;
		List<XigniteKeyValue> keyValueLst = new ArrayList<XigniteKeyValue>();
		
		xigniteArgs.setOperationName("DAILY_PRICING");
		//xigniteArgs.setOperationName("FUNDAMENTAL");
		//xigniteArgs.setOperationName("GLOBAL_HISTORICAL_RANGE");
		//xigniteArgs.setOperationName("");
		if (xigniteArgs.getOperationName().equalsIgnoreCase("DAILY_PRICING")) {
			keyValue = new XigniteKeyValue();
			keyValue.setKey("IdentifierType");
			keyValue.setValue("Symbol");
			keyValueLst.add(keyValue);

			keyValue = new XigniteKeyValue();
			keyValue.setKey("Identifiers");
			keyValue.setValue("Cash,SHY,PRWBX,BSV");
			keyValueLst.add(keyValue);

			keyValue = new XigniteKeyValue();
			keyValue.setKey("AdjustmentMethod");
			keyValue.setValue("SplitOnly");
			keyValueLst.add(keyValue);

			keyValue = new XigniteKeyValue();
			keyValue.setKey("EndOfDayPriceMethod");
			keyValue.setValue("LastTrade");
			keyValueLst.add(keyValue);

			keyValue = new XigniteKeyValue();
			keyValue.setKey("AsOfDate");
			keyValue.setValue("02/26/2016");
			keyValueLst.add(keyValue);
		} else if (xigniteArgs.getOperationName().equalsIgnoreCase("FUNDAMENTAL")) {

			keyValue = new XigniteKeyValue();
			keyValue.setKey("IdentifierType");
			keyValue.setValue("Symbol");
			keyValueLst.add(keyValue);

			keyValue = new XigniteKeyValue();
			keyValue.setKey("Identifiers");
			keyValue.setValue("BMW.XETR,MIK");
			keyValueLst.add(keyValue);

			keyValue = new XigniteKeyValue();
			keyValue.setKey("UpdatedSince");
			keyValue.setValue("07/01/2012");
			keyValueLst.add(keyValue);
		} else if (xigniteArgs.getOperationName().equals("GLOBAL_HISTORICAL_RANGE")) {
			keyValue = new XigniteKeyValue();
			keyValue.setKey("IdentifierType");
			keyValue.setValue("Symbol");
			keyValueLst.add(keyValue);

			keyValue = new XigniteKeyValue();
			keyValue.setKey("Identifier");
			keyValue.setValue("Cash");
			keyValueLst.add(keyValue);

			keyValue = new XigniteKeyValue();
			keyValue.setKey("AdjustmentMethod");
			keyValue.setValue("SplitOnly");
			keyValueLst.add(keyValue);

			keyValue = new XigniteKeyValue();
			keyValue.setKey("StartDate");
			keyValue.setValue("02/22/2015");
			keyValueLst.add(keyValue);

			keyValue = new XigniteKeyValue();
			keyValue.setKey("EndDate");
			keyValue.setValue("02/19/2016");
			keyValueLst.add(keyValue);

		}
		inputs.setKeyValues(keyValueLst);

		xigniteArgs.setInput(inputs);

		JSONArray jsonArray = xigniteUtil.xigniteMe(xigniteArgs);
		try {
			System.out.println("Caller:: json object length: " + jsonArray.length());
			System.out.println("Caller:: json object: " + jsonArray);
			JSONObject jsonObject = new JSONObject();
			jsonObject = jsonArray.getJSONObject(0);
			System.out.println("Caller:: json object Outcome: " + jsonObject.get("Outcome"));			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}