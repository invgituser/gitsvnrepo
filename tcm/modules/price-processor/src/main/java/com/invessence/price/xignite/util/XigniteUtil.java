package com.invessence.price.xignite.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

import com.invessence.price.util.PriceProcessConst;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.invessence.price.xignite.bo.XigniteArgs;
import com.invessence.price.xignite.bo.XigniteKeyValue;
import org.springframework.beans.factory.annotation.*;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

/**
 * @author Hiten Patidar
 */
@Component
public class XigniteUtil
{
   //@Value(value="${UTIL.XIGNITE_SWITCH}")
   //String xigniteSwitch;

   @Autowired
   protected MessageSource resource;


   protected String getMessage(String code, Object[] object, Locale locale)
   {
      return resource.getMessage(code, object, locale);
   }


   //use it here

	/*public XigniteUtil(){
      xigniteSwitch ="hello";
	}*/

   /**
    * This method used to check if xignite layer is currently active or not
    *
    * @return boolean
    * @throws FileNotFoundException
    * @throws IOException
    */
   /*public Boolean xigniteSwitch() throws FileNotFoundException, IOException {


		boolean switchValue = false;
		System.out.println("Entered Xignite switch check");

		Properties configFile = new Properties();
		InputStream input = XigniteUtil.class.getClassLoader().getResourceAsStream("XigniteConfig.properties");
		configFile.load(input);
		// Get switch value from configuration
		//System.out.println("xigniteSwitch: "+xigniteSwitch);
		if (configFile.getProperty("UTIL.XIGNITE_SWITCH").equalsIgnoreCase("ON")) {

			switchValue = true;
		} else {
			switchValue = false;
		}
		return switchValue;
	}*/

   /**
    * This method serves as a single point of contact to connect with xignite
    * it takes operation name and object in input and returns json Array
    *
    * @param xigniteArgs
    * @return json object
    */
   public JSONArray xigniteMe(XigniteArgs xigniteArgs)
   {


      JSONObject jsonObject = null;
      JSONArray jsonArray = new JSONArray();
      String operationName = PriceProcessConst.operationName;
      HttpURLConnection conn = null;
      List<XigniteKeyValue> keyValues = new ArrayList<XigniteKeyValue>();
      if (xigniteArgs.getOperationName() == null)
      {
         jsonArray = xigniteErrorJson("Operation name is detected as null", "");
         return jsonArray;
      }
      System.out.println("Entering into ignite layer");
      Properties configFile = new Properties();
      try
      {

         operationName = xigniteArgs.getOperationName();
         InputStream input = XigniteUtil.class.getClassLoader().getResourceAsStream("XigniteConfig.properties");
         //InputStream input = new FileInputStream("/price-processor/src/main/resources/XigniteConfig.properties");
         configFile.load(input);
         // Get authentication token from property
         String api_token = configFile.getProperty("API_TOKEN");
         System.out.println(api_token);

         // get operation url part from property file
         String api_url = configFile.getProperty("API_URL_" + operationName);
         System.out.println("api_url" + api_url);
         if (api_url == null)
         {
            throw new Exception("Incorrect or empty operation name detected");
         }

         keyValues = xigniteArgs.getInput().getKeyValues();
         // prepare url with inputs
         if (keyValues.size() > 0)
         {
            api_url = api_url.concat("?");
         }
         for (XigniteKeyValue keyValue : keyValues)
         {
            if (keyValue.getKey() == null)
            {
               throw new Exception("one of the input key is detected as null");
            }
            if (keyValue.getValue() == null)
            {
               throw new Exception("one of the input value is detected as null");
            }
            api_url = api_url.concat(keyValue.getKey());

            api_url = api_url.concat("=");

            api_url = api_url.concat(keyValue.getValue());
            api_url = api_url.concat("&");
         }
         // append authentication token at the of the url
         System.out.println("api_url: " + api_url);
         URL url = null;
         String xigRes = "";
         url = new URL(api_url + "_token=" + api_token);
         String outputType = configFile.getProperty("OUTPUT_TYPE_" + operationName);
         System.out.println("outputType: " + outputType);

         conn = (HttpURLConnection) url.openConnection();
         conn.setRequestMethod("GET");
         if (conn.getResponseCode() != 200)
         {
            System.out.println("Error Code: " + conn.getResponseCode());
            throw new Exception(conn.getResponseCode() + ": " + conn.getResponseMessage());
         }
         BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

         String fuls = "";
         while ((fuls = br.readLine()) != null)
         {
            xigRes = xigRes + fuls;
         }

         System.out.println("xigRes: " + xigRes);
         if (xigRes.startsWith("<"))
         {
            throw new Exception("something went wrong with api URL");
         }

         // perform check if api output is an object or array
         if (outputType.equals("ARRAY"))
         {
            jsonArray = new JSONArray(xigRes);
            System.out.println("jsonarray length: " + jsonArray.length());
         }
         else
         {
            jsonObject = new JSONObject(xigRes);
            jsonArray.put(jsonObject);
         }
         System.out.println("jsonarray: " + jsonArray);
         // System.out.println("jsonObject: " + jsonObject);
         System.out.println(operationName + " :: End of XigniteMe: Returning json object back to caller");
         return jsonArray;
      }
      catch (Exception e)
      {
         e.printStackTrace();
         jsonArray = xigniteErrorJson(e.getMessage(),operationName);
         return jsonArray;
      }
      finally
      {
         conn.disconnect();
      }
   }

   /**
    * Generic function t prepare json, can be used when there is any error and
    * program wants to return error in json format
    *
    * @param errorDesc
    * @param operationName
    * @return
    */
   public JSONArray xigniteErrorJson(String errorDesc, String operationName)
   {
      System.out.println("Entering in to xigniteErrorJson");
      JSONObject obj = new JSONObject();
      JSONArray jsonArray = new JSONArray();
      try
      {
         obj.put("Outcome", "Failed");
         obj.put("Message", operationName + " :: " + errorDesc);
         obj.put("Identity", "Request");
         jsonArray.put(obj);

      }
      catch (JSONException e)
      {
         e.printStackTrace();
      }
      // System.out.println("xigniteErrorJson:: jsonArray: "+jsonArray);
      return jsonArray;
   }

   public void setResource(MessageSource resource)
   {
      this.resource = resource;
   }
}