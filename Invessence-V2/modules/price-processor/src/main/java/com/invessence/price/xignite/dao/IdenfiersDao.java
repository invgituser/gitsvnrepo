package com.invessence.price.xignite.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.invessence.price.xignite.bo.XigniteArgs;
import com.invessence.price.xignite.bo.XigniteInputs;
import com.invessence.price.xignite.bo.XigniteKeyValue;

@Repository
public class IdenfiersDao {
	/**
	 * takes string as input and returns xigniteArgs object its a generic method
	 * which fetches idefiers for a input string from db and sets to XigniteArgs
	 * object
	 * 
	 * @param
	 * @return
	 */
	public XigniteArgs fetchApiIdetfiers(String operationName,String ticker) {
		XigniteArgs xigniteArgs = new XigniteArgs();
		XigniteInputs inputs = new XigniteInputs();
		XigniteKeyValue keyValue = null;

		List<XigniteKeyValue> keyValueLst = new ArrayList<XigniteKeyValue>();

		// Pick up correct value of operation nsame and set it to bo
		xigniteArgs.setOperationName(operationName);

		// TODO write code for database call to fetch identifiers
//		String ticker = "";
		// set tickers
		/*
		 * For loop keyValue = new XigniteKeyValue();
		 * keyValue.setKey("IdentifierType"); keyValue.setValue("Symbol");
		 * keyValueLst.add(keyValue);
		 * 
		 */

		// TODO remove below block once database call in place
		if (operationName.equalsIgnoreCase("DAILY_PRICING")) {
			keyValue = new XigniteKeyValue();
			keyValue.setKey("IdentifierType");
			keyValue.setValue("Symbol");
			keyValueLst.add(keyValue);

			keyValue = new XigniteKeyValue();
			keyValue.setKey("Identifiers");
			keyValue.setValue(ticker);
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
		return xigniteArgs;
	}
}