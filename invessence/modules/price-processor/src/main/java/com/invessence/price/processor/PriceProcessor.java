package com.invessence.price.processor;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import com.invessence.price.processor.bean.*;
import com.invessence.price.util.*;
import com.invessence.rbsa.RBSA2;
import com.invessence.rbsa.dao.data.RBSAData;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.*;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.invessence.price.processor.DAO.DBParametersDao;
import com.invessence.price.processor.DAO.MessageDao;
import com.invessence.price.processor.DAO.PriceDataDao;
import com.invessence.price.processor.DAO.SecMasterDao;
import com.invessence.price.yahoo.Stock;
import com.invessence.price.yahoo.YahooFinance;
import com.invessence.price.yahoo.histquotes.HistoricalQuote;
import com.invessence.price.yahoo.histquotes.Interval;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

@Component
public class PriceProcessor {
	private static final Logger logger = Logger.getLogger(PriceProcessor.class);
	@Autowired
	DBParametersDao dbParametersDao;
	@Autowired
	SecMasterDao secMasterDao;
	@Autowired
	PriceDataDao priceDataDao;

	@Autowired
	private
	MessageDao messageDao;

	@Value(value="${securities.provider}")
	String price_provider;

	@Autowired
	DataSource dataSource;
	private JdbcTemplate jdbcTemplate;

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	SimpleDateFormat switchFormat = new SimpleDateFormat("yyyyMMdd");

	public void process() throws SQLException
	{

		StringBuilder mailAlertMsg = null;

		try {
			mailAlertMsg = new StringBuilder();

			Map<String, DBParameters> dbParamMap = dbParametersDao.getDBParametres();
			if (dbParamMap == null && dbParamMap.size()== 0) {
				mailAlertMsg.append("DB parameters are not available");
				System.out.println("DB parameters are not available");
			} else {
				System.out.println("LAST_BDATE_OF_MONTH :" + dbParamMap.get("LAST_BDATE_OF_MONTH").getValue());
				List<SecMaster> lst = secMasterDao.findByWhere("status = 'A'");
				if (lst != null && lst.size() > 0) {
					String businessDate=sdf.format(switchFormat.parse(dbParamMap.get("BUSINESS_DATE").getValue().toString()));
					System.out.println("Business Date :"+businessDate);
					if(CommonUtil.dateCompare(dbParamMap.get("BUSINESS_DATE").getValue().toString(),dbParamMap.get("LAST_BDATE_OF_MONTH").getValue().toString())==false)
					{
						// if (CommonUtil.dateCompare(dbParamMap.get("LAST_BDATE_OF_MONTH").getValue().toString())==false)
						sdf.format(switchFormat.parse(dbParamMap.get("BUSINESS_DATE").getValue().toString()));
						dailyProcess(businessDate,lst,mailAlertMsg);
					} else if(CommonUtil.dateCompare(dbParamMap.get("BUSINESS_DATE").getValue().toString(),dbParamMap.get("LAST_BDATE_OF_MONTH").getValue().toString())==true){
						monthlyProcess(businessDate,lst,mailAlertMsg);
					}
				} else {
					mailAlertMsg.append("Ticker list not available for process");
					System.out.println("Ticker list not available for process");
				}
			}
		} catch (Exception e) {
			System.out.println("PriceProcessor.process() WE R HERE..");
			exceptionHandler(e, mailAlertMsg, "main process");
		}


		finally {
			if (mailAlertMsg.length() > 0) {
				System.out.println("MailAlertMsg IS :" + mailAlertMsg);
				EmailMsg md = new EmailMsg();
				md.setMsg(mailAlertMsg);
				try
				{
					messageDao.insert(md);
				}
				catch (SQLException e)
				{
					e.printStackTrace();
				}

			} else {
				System.out.println("MailAlertMsg is empty");
			}
		}

	}

	public void dailyProcess(String businessDate, List<SecMaster> tickerList,StringBuilder mailAlertMsg)
	{
		System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
		System.out.println("PriceProcessor.process() executing Daily Process");
		if(price_provider.equalsIgnoreCase("yahoo"))
		{
			List<PriceData> pdList = null;
			try
			{
				priceDataDao.delete();

				pdList = getDailyPriceData(businessDate, tickerList, mailAlertMsg);
				if (pdList == null || pdList.size() == 0)
				{
					mailAlertMsg.append("Daily price data not available for upload");
				}
				else
				{
					try
					{
						priceDataDao.insertBatch(pdList);
						if (mailAlertMsg.length() == 0)
						{
							try
							{
								priceDataDao.callProcedure(PriceProcessConst.DAILY, businessDate, "");
								try
								{
									priceDataDao.callEodProcedure(PriceProcessConst.DAILY, businessDate);
								}
								catch (Exception e)
								{
									mailAlertMsg.append("Daily price eod process issue " + e.getMessage());
								}
							}
							catch (Exception e)
							{
								mailAlertMsg.append("Daily price data operation issue " + e.getMessage());
							}
						}
					}
					catch (Exception e)
					{
						mailAlertMsg.append("Daily price data upload issue"+e.getMessage());
					}
				}
			}
			catch (Exception e)
			{
				mailAlertMsg.append("Daily price data upload issue"+e.getMessage());
			}
		}
		else if(price_provider.equalsIgnoreCase("xignite"))
		{

		}
		}

	public void monthlyProcess(String businessDate, List<SecMaster> tickerList,StringBuilder mailAlertMsg)
	{
		System.out.println("PriceProcessor.process() executing Monthly Process");
		if(price_provider.equalsIgnoreCase("yahoo"))
		{
			List<PriceData> pdList = null;
			Iterator<SecMaster> sec = tickerList.iterator();
			int i = 0;
			while (sec.hasNext())
			{
				SecMaster secMaster = (SecMaster) sec.next();
				System.out.println(secMaster.toString());
				try
				{
					priceDataDao.delete();
					pdList = getHistoricalPriceData(businessDate, secMaster.getTicker(), mailAlertMsg);
					if (pdList == null || pdList.size() == 0)
					{
						mailAlertMsg.append("Historical price data not available for ticker " + secMaster.getTicker() + "\n");
					}
					else
					{
						try
						{
							priceDataDao.insertBatch(pdList);
							try
							{
								priceDataDao.callProcedure(PriceProcessConst.MONTHLY, businessDate, secMaster.getTicker());
								if (secMaster.getRbsaFlag().equalsIgnoreCase("Y"))
								{
									try
									{
										rbsaCall(secMaster.getTicker());
									}
									catch (Exception e)
									{
										mailAlertMsg.append("RBSA process call issue for ticker " + secMaster.getTicker() + "\n" + e.getMessage() + "\n");
									}
								}

							}
							catch (Exception e)
							{
								e.printStackTrace();
								mailAlertMsg.append("Historical price data operation issue for ticker " + secMaster.getTicker() + "\n" + e.getMessage() + "\n");
							}
						}
						catch (Exception e)
						{
							mailAlertMsg.append("Historical price data upload issue " + secMaster.getTicker() + "\n" + e.getMessage() + "\n");
						}
					}
				}
				catch (Exception e)
				{
					System.out.println("Ticker Exception:" + secMaster.getTicker());
					mailAlertMsg.append("Ticker Exception:" + secMaster.getTicker() + "\n");
					exceptionHandler(e, mailAlertMsg, "Ticker Exception :");
				}
			}
			if (mailAlertMsg.length() > 0)
			{
				System.out.println("MailAlertMsg IS :" + mailAlertMsg);
			}
			else
			{
				System.out.println("MailAlertMsg is empty");
				try
				{

					priceDataDao.callEodProcedure(PriceProcessConst.MONTHLY, businessDate);
				}
				catch (Exception e)
				{
					mailAlertMsg.append("Historical price eod process issue " + e.getMessage());
				}
			}
		}
		else if(price_provider.equalsIgnoreCase("xignite"))
		{
		}
	}



	public void onDemandProcess(String ticker)
	{
		StringBuilder mailAlertMsg=null;
		System.out.println("PriceProcessor.process() executing Monthly Process");

		List<PriceData> pdList=null;
		try{
			SecMaster secMaster = secMasterDao.findByTicker(ticker);
			if(secMaster==null){
					//Need to call API for ticker information for stored into DB
				System.out.println("Ticker "+ticker+" not available in our database");
			}else{

				try{
				Map<String, DBParameters> dbParamMap = dbParametersDao.getDBParametres();
				if (dbParamMap == null && dbParamMap.size()== 0) {
					mailAlertMsg.append("DB parameters are not available");
					System.out.println("DB parameters are not available");
				} else
				{
					String businessDate = sdf.format(switchFormat.parse(dbParamMap.get("BUSINESS_DATE").getValue().toString()));
					try
					{

						priceDataDao.delete();
						pdList = getHistoricalPriceData(businessDate, secMaster.getTicker(), mailAlertMsg);
						if (pdList == null || pdList.size() == 0)
						{
							mailAlertMsg.append("OnDemand price data not available for ticker " + secMaster.getTicker() + "\n");
						}
						else
						{
							try
							{
								priceDataDao.insertBatch(pdList);
								try
								{
									priceDataDao.callProcedure(PriceProcessConst.ONDEMAND, businessDate, secMaster.getTicker());
									if (secMaster.getRbsaFlag().equalsIgnoreCase("Y"))
									{
										try
										{
											rbsaCall(secMaster.getTicker());
										}
										catch (Exception e)
										{
											mailAlertMsg.append("RBSA process call issue for ticker " + secMaster.getTicker() + "\n" + e.getMessage() + "\n");
										}
									}

								}
								catch (Exception e)
								{
									e.printStackTrace();
									mailAlertMsg.append("OnDemand price data operation issue for ticker " + secMaster.getTicker() + "\n" + e.getMessage() + "\n");
								}
							}
							catch (Exception e)
							{
								mailAlertMsg.append("OnDemand price data upload issue " + secMaster.getTicker() + "\n" + e.getMessage() + "\n");
							}
						}
					}catch (Exception e) {
						System.out.println("Ticker Exception:" + secMaster.getTicker());
						mailAlertMsg.append("Ticker Exception:" + secMaster.getTicker()+"\n");
						exceptionHandler(e, mailAlertMsg, "Ticker Exception :");
					}
				}
				}catch (Exception e) {
				System.out.println("Ticker Exception:" + secMaster.getTicker());
				mailAlertMsg.append("Ticker Exception:" + secMaster.getTicker()+"\n");
				exceptionHandler(e, mailAlertMsg, "Ticker Exception :");
			}
			}
			} catch (Exception e) {
			System.out.println("Ticker Exception:" + ticker);
			mailAlertMsg.append("Ticker Exception:" + ticker+"\n");
			exceptionHandler(e, mailAlertMsg, "Ticker Exception :");
		}

	}

	public void rbsaCall(String ticker)throws Exception
	{

		RBSAData rbsaData;
		RBSA2 rp = new RBSA2();
		rbsaData = rp.optimizeSecurity(ticker);
		Double val = 0.0;
		Double totalAlloc = 0.0;
		if (rbsaData != null)
		{
			for (String key : rbsaData.getSolution().keySet())
			{
				val = (Math.round(rbsaData.getSolution().get(key) * 10000.0) / 100.0);
				totalAlloc = totalAlloc + val;
				System.out.println("Index (" + key + "): " + rbsaData.getSolution().get(key) + "(" + val + "%)");
			}
			System.out.println("Total Allocated: " + totalAlloc);
			System.out.println("Tracking Error: " + (rbsaData.getTrackingError() * 100.00) + "%");
		}
	}

	public List<PriceData> getDailyPriceData(String businessDate, List<SecMaster> tickerList, StringBuilder mailAlertMsg){
		List<PriceData> pdList=null;
		PriceData pd=null;

			Iterator<SecMaster> sec = tickerList.iterator();
		try {
			priceDataDao.delete();
			System.out.println("businessDate :"+businessDate);
			Date d=sdf.parse(businessDate);
			Calendar from = Calendar.getInstance();
			from.setTime(d);
			System.out.println("from:"+from+ "    date:"+d);
			int i = 0;
			pdList=new ArrayList<PriceData>();
			while (sec.hasNext()) {
				SecMaster secMaster = (SecMaster) sec.next();
				System.out.println(secMaster.toString());
				try {
					//Stock stk = YahooFinance.get(secMaster.getTicker());

					//Stock stk = YahooFinance.get(secMaster.getTicker(),from,from, Interval.DAILY);
					Stock stk = YahooFinance.get(secMaster.getTicker());
					// stk.print();
					List<HistoricalQuote> hstLst = stk.getHistory(from, from, Interval.DAILY);
					//System.out.println("*******//******"+hstLst.size());
					if(hstLst==null || hstLst.size()==0){
						mailAlertMsg.append("Price not available for ticker:" + secMaster.getTicker() + " for busunessdate :" + businessDate + "\n");
					}else{
						HistoricalQuote historicalQuote = (HistoricalQuote) hstLst.get(0);
						//System.out.println("*******************************"+historicalQuote.getSymbol());
						PriceData hpd = new PriceData(historicalQuote.getSymbol(),
																sdf.format(historicalQuote.getDate().getTime()),
																Double.valueOf("" + historicalQuote.getOpen()), Double.valueOf("" + historicalQuote.getClose()),
																Double.valueOf("" + historicalQuote.getHigh()), Double.valueOf("" + historicalQuote.getLow()),
																Long.valueOf(historicalQuote.getVolume()), new Date(),
																Double.valueOf("" + historicalQuote.getAdjClose()), new Long(2), new Date());


//						pd = new PriceData(stk.getQuote().getSymbol(),
//												 sdf.format(stk.getQuote().get.getTime()),
//												 Double.valueOf("" + stk.getQuote().getOpen()), Double.valueOf("" + stk.getQuote().getPrice()),
//												 Double.valueOf("" + stk.getQuote().getDayHigh()), Double.valueOf("" + stk.getQuote().getDayLow()),
//												 Long.valueOf(stk.getQuote().getVolume()), new Date(),
//												 Double.valueOf("" + stk.getQuote().getPreviousClose()), new Long(2), new Date());
						System.out.println(sdf.format(stk.getQuote().getLastTradeTime().getTime()));

						if (sdf.format(historicalQuote.getDate().getTime()).equals(businessDate))
						{
							if (Double.valueOf("" + stk.getQuote().getPrice()).equals(0))
							{
								mailAlertMsg.append("Price value getting 0 for ticker:" + secMaster.getTicker() + "\n");
							}
							else
							{
								pdList.add(hpd);
							}
						}
						else
						{
							mailAlertMsg.append("Price not available for ticker:" + secMaster.getTicker() + " for busunessdate :" + businessDate + "\n");

						}
					}
				} catch (Exception e) {
					mailAlertMsg.append("Price api exception for ticker:" + secMaster.getTicker()+"\n"+e.getMessage());
				}


			}


//			System.out.println("Open   :" + stk.getQuote().getOpen());
//			System.out.println("LastTradePriceOnly   :" + stk.getQuote().getPrice());
//			System.out.println("Volume   :" + stk.getQuote().getVolume());
//			System.out.println("DayHigh   :" + stk.getQuote().getDayHigh());
//			System.out.println("DayLow   :" + stk.getQuote().getDayLow());
//			System.out.println("PreviousClose   :" + stk.getQuote().getPreviousClose());

		} catch (Exception e) {
			mailAlertMsg.append("Price api exception for ticker: \n"+e.getMessage());
		}

		return pdList;
	}

		List<PriceData> getHistoricalPriceData(String businessDate, String ticker, StringBuilder mailAlertMsg){
			List<PriceData> pdList=null;
		System.out.println("***********"+price_provider+"********");

			try {

				Stock stk = YahooFinance.get(ticker);
//				System.out.println("*********************Daily Data************************");
//				System.out.println("Ticker :" + stk.getQuote().getSymbol());
//				System.out.println("LastTradeDate : " + sdf.format(stk.getQuote().getLastTradeTime().getTime()));
//				System.out.println("Open :" + stk.getQuote().getOpen());
//				System.out.println("LastTradePriceOnly :" +
//											 stk.getQuote().getPrice());
//				System.out.println("Volume :" + stk.getQuote().getVolume());
//				System.out.println("DayHigh :" + stk.getQuote().getDayHigh());
//				System.out.println("DayLow :" + stk.getQuote().getDayLow());
//				System.out.println("PreviousClose :" + stk.getQuote().getPreviousClose());
				pdList= new ArrayList<PriceData>();
				PriceData pd = new PriceData(stk.getQuote().getSymbol(),
													  sdf.format(stk.getQuote().getLastTradeTime().getTime()),
													  Double.valueOf("" + stk.getQuote().getOpen()), Double.valueOf("" + stk.getQuote().getPrice()),
													  Double.valueOf("" + stk.getQuote().getDayHigh()), Double.valueOf("" + stk.getQuote().getDayLow()),
													  Long.valueOf(stk.getQuote().getVolume()), new Date(),
													  Double.valueOf("" + stk.getQuote().getPreviousClose()), new Long(2), new Date());

				//pdl.add(pd);
				Date d=sdf.parse(businessDate);
//				Calendar from = new GregorianCalendar(2015, 9, 5);// Calendar.getInstance();
//				Calendar to = new GregorianCalendar(2016, 1, 29);// Calendar.getInstance();//2007-05-30
				Calendar from = Calendar.getInstance();
				from.setTime(d);
				Calendar to = Calendar.getInstance();//2007-05-30
				to.setTime(d);
				from.add(Calendar.YEAR, -20); // from 5 years ago
				List<HistoricalQuote> hstLst = stk.getHistory(from, to, Interval.DAILY);

				Iterator<HistoricalQuote> itr = hstLst.iterator();
				System.out.println("*********************Historical Data************************");

				while (itr.hasNext())
				{

					HistoricalQuote historicalQuote = (HistoricalQuote) itr.next();
//					System.out.println("Ticker :" + historicalQuote.getSymbol());
//					System.out.println("LastTradeDate : "+sdf.format(historicalQuote.getDate().getTime()));
//					System.out.println("Open :" + historicalQuote.getOpen());
//					System.out.println("LastTradePriceOnly :" +historicalQuote.getClose());
//					System.out.println("Volume :" + historicalQuote.getVolume());
//					System.out.println("DayHigh :" + historicalQuote.getHigh());
//					System.out.println("DayLow :" + historicalQuote.getLow());
//					System.out.println("PreviousClose :" +
//												 historicalQuote.getAdjClose());

					PriceData hpd = new PriceData(historicalQuote.getSymbol(),
															sdf.format(historicalQuote.getDate().getTime()),
															Double.valueOf("" + historicalQuote.getOpen()), Double.valueOf("" + historicalQuote.getClose()),
															Double.valueOf("" + historicalQuote.getHigh()), Double.valueOf("" + historicalQuote.getLow()),
															Long.valueOf(historicalQuote.getVolume()), new Date(),
															Double.valueOf("" + historicalQuote.getAdjClose()), new Long(2), new Date());


					if (!Double.valueOf("" + historicalQuote.getClose()).equals(0))
					{

						pdList.add(hpd);
					}

				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		return  pdList;
	}

	public void onDemand(String ticker) throws Exception {

		SecMaster secMaster=new SecMaster();
		if(ticker.equals(secMaster.getTicker())){
			System.out.println("TICKER ALREADY PRESENT"+ secMaster.getTicker());
			StringBuilder mailAlertMsg= null;
			mailAlertMsg.append("TICKER ALREADY PRESENT"+ secMaster.getTicker());

		}
		else{
			System.out.println("***********"+price_provider+"********");
			if(price_provider.equalsIgnoreCase("yahoo")){

				try {

					List<PriceData> pdl = new ArrayList<PriceData>();
					Stock stk = YahooFinance.get(ticker);
					System.out.println("*********************Daily Data************************");
					System.out.println("Ticker :" + stk.getQuote().getSymbol());
					System.out.println("LastTradeDate : " + sdf.format(stk.getQuote().getLastTradeTime().getTime()));
					System.out.println("Open :" + stk.getQuote().getOpen());
					System.out.println("LastTradePriceOnly :" +
												 stk.getQuote().getPrice());
					System.out.println("Volume :" + stk.getQuote().getVolume());
					System.out.println("DayHigh :" + stk.getQuote().getDayHigh());
					System.out.println("DayLow :" + stk.getQuote().getDayLow());
					System.out.println("PreviousClose :" + stk.getQuote().getPreviousClose());
					PriceData pd = new PriceData(stk.getQuote().getSymbol(),
														  sdf.format(stk.getQuote().getLastTradeTime().getTime()),
														  Double.valueOf("" + stk.getQuote().getOpen()), Double.valueOf("" + stk.getQuote().getPrice()),
														  Double.valueOf("" + stk.getQuote().getDayHigh()), Double.valueOf("" + stk.getQuote().getDayLow()),
														  Long.valueOf(stk.getQuote().getVolume()), new Date(),
														  Double.valueOf("" + stk.getQuote().getPreviousClose()), new Long(2), new Date());

					//pdl.add(pd);
					Date d = new Date();
					Calendar from = new GregorianCalendar(2015, 9, 5);// Calendar.getInstance();
					Calendar to = new GregorianCalendar(2016, 1, 29);// Calendar.getInstance();//2007-05-30
					from.add(Calendar.YEAR, -20); // from 5 years ago
					List<HistoricalQuote> hstLst = stk.getHistory(from, to, Interval.DAILY);

					Iterator<HistoricalQuote> itr = hstLst.iterator();
					System.out.println("*********************Historical Data************************");

					while (itr.hasNext()) {

						HistoricalQuote historicalQuote = (HistoricalQuote) itr.next();
						System.out.println("Ticker :" + historicalQuote.getSymbol());
						System.out.println("LastTradeDate : "+sdf.format(historicalQuote.getDate().getTime()));
						System.out.println("Open :" + historicalQuote.getOpen());
						System.out.println("LastTradePriceOnly :" +historicalQuote.getClose());
						System.out.println("Volume :" + historicalQuote.getVolume());
						System.out.println("DayHigh :" + historicalQuote.getHigh());
						System.out.println("DayLow :" + historicalQuote.getLow());
						System.out.println("PreviousClose :" +
													 historicalQuote.getAdjClose());

						PriceData hpd = new PriceData(historicalQuote.getSymbol(),
																sdf.format(historicalQuote.getDate().getTime()),
																Double.valueOf("" + historicalQuote.getOpen()), Double.valueOf("" + historicalQuote.getClose()),
																Double.valueOf("" + historicalQuote.getHigh()), Double.valueOf("" + historicalQuote.getLow()),
																Long.valueOf(historicalQuote.getVolume()), new Date(),
																Double.valueOf("" + historicalQuote.getAdjClose()), new Long(2), new Date());
						if(!Double.valueOf(""+historicalQuote.getClose()).equals(0)){
							pdl.add(hpd);
						}

						priceDataDao.insertBatch(pdl);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			else if(price_provider.equalsIgnoreCase("xignite")){


			}else{

			}
		}

	}

	public void exceptionHandler(Exception ex, StringBuilder mailAlertMsg, String process) {
		try {
			System.out.println("EXCEPTION CLASS:" + ex.getClass());
			ex.printStackTrace();
			if (ex instanceof MySQLIntegrityConstraintViolationException) {
				//mailAlertMsg.append(process + "MySQLViolationException: " + ex.getMessage() + "\n");
				System.out.println(process + " MySQLViolationException: " + ex.getMessage());
			} else if (ex instanceof BadSqlGrammarException) {
				//mailAlertMsg.append(process + "SqlGrammarException : " + ex.getMessage() + "\n");
				System.out.println(process + "SqlGrammarException : " + ex.getMessage());
			} else if (ex instanceof CannotGetJdbcConnectionException) {
				//mailAlertMsg.append(process + "JDBC ConnectionException : " + ex.getMessage() + "\n");
				System.out.println(process + "ConnectionException : " + ex.getMessage());
			} else if (ex instanceof DuplicateKeyException) {
				//mailAlertMsg.append(process + "DUPLICATE KEY : " + ex.getMessage() + "\n");
				System.out.println(process + "DUPLICATE KEY : " + ex.getMessage());
			}
			else if (ex instanceof DataIntegrityViolationException) {
				//mailAlertMsg.append(process + "DATA TRUNCATION : " + ex.getMessage() + "\n");
				System.out.println(process + "DATA TRUNCATION : " + ex.getMessage());
			}
			 else if (ex instanceof NullPointerException) {
					//mailAlertMsg.append(process +  "NULL POINTER EXCEPTION:" + ex.getMessage() + "\n");
					System.out.println(process + "NULL POINTER EXCEPTION:" + ex.getMessage());
				}
			 else if (ex instanceof SQLException) {
					//mailAlertMsg.append(process +  "sql exception:"  + ex.getMessage() + "\n");
					System.out.println(process +    "sql exception:"  + ex.getMessage());
				}
			else if (ex instanceof InvalidDataAccessApiUsageException) {
				//mailAlertMsg.append(process +  "Required input parameter  is missing:"  + ex.getMessage() + "\n");
				System.out.println(process +    "Required input parameter  is missing:"  + ex.getMessage());
			}
			else {
				//mailAlertMsg.append(process + "NO Exception:" + ex.getMessage() + "\n");
				System.out.println(process + " : " + ex.getMessage());
			}
			
			
			//meassage_data md = new meassage_data();
			//md.setMsg(mailAlertMsg);
           // messageDao.insert(md);

		    } catch (Exception e) {
			//mailAlertMsg.append(process + " QAZ: " + ex.getMessage() + "\n");
			System.out.println(process + " QAZ: " + ex.getMessage());
			
		}
	}

	public PriceDataDao getPriceDataDao() {
		return priceDataDao;
	}

	public void setPriceDataDao(PriceDataDao priceDataDao) {
		this.priceDataDao = priceDataDao;
	}

	public SecMasterDao getSecMasterDao() {
		return secMasterDao;
	}

	public void setSecMasterDao(SecMasterDao secMasterDao) {
		this.secMasterDao = secMasterDao;
	}

	public DBParametersDao getDbParametersDao() {
		return dbParametersDao;
	}

	public void setDbParametersDao(DBParametersDao dbParametersDao) {
		this.dbParametersDao = dbParametersDao;
	}

	public MessageDao getMessageDao() {
		return messageDao;
	}

	public void setMessageDao(MessageDao messageDao) {
		this.messageDao = messageDao;
	}

}
