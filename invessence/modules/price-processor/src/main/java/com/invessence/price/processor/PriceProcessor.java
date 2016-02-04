package com.invessence.price.processor;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import com.invessence.rbsa.RBSA2;
import com.invessence.rbsa.dao.data.RBSAData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.*;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Component;

import com.invessence.price.processor.DAO.DBParametersDao;
import com.invessence.price.processor.DAO.MessageDao;
import com.invessence.price.processor.DAO.PriceDataDAOImpl;
import com.invessence.price.processor.DAO.PriceDataDao;
import com.invessence.price.processor.DAO.SecMasterDao;
import com.invessence.price.processor.bean.DBParameters;
import com.invessence.price.processor.bean.PriceData;
import com.invessence.price.processor.bean.SecMaster;
import com.invessence.price.processor.bean.meassage_data;
import com.invessence.price.util.CommonUtil;
import com.invessence.price.yahoo.Stock;
import com.invessence.price.yahoo.YahooFinance;
import com.invessence.price.yahoo.histquotes.HistoricalQuote;
import com.invessence.price.yahoo.histquotes.Interval;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

@Component
public class PriceProcessor {

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
 
	
	
	SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");


	public void process() {
			

		StringBuilder mailAlertMsg = null;
            
		try {
			mailAlertMsg = new StringBuilder();
			
			Map<String, DBParameters> dbParamMap = dbParametersDao.getDBParametres();
			if (dbParamMap == null && dbParamMap.size()== 0) {
				mailAlertMsg.append("parameters not available");
				System.out.println("parameters not available");
			} else {
				System.out.println("LAST_BDATE_OF_MONTH :" + dbParamMap.get("LAST_BDATE_OF_MONTH").getValue());
				List<SecMaster> lst = secMasterDao.findByWhere("status = 'A'");
				if (lst != null && lst.size() > 0) {

					if (CommonUtil.dateCompare(dbParamMap.get("LAST_BDATE_OF_MONTH").getValue().toString()) == false) {

						System.out.println("PriceProcessor.process() executing Daily Process");
						Iterator<SecMaster> sec = lst.iterator();
                         priceDataDao.delete();
						int i = 0;
						while (sec.hasNext()) {
							SecMaster secMaster = (SecMaster) sec.next();
							System.out.println(secMaster.toString());
							try {
								dailyProcess(secMaster.getTicker(), price_provider);								
							} catch (Exception e) {

								System.out.println("Ticker Exception :" + secMaster.getTicker());
								mailAlertMsg.append("Ticker Exception :" + secMaster.getTicker());
								exceptionHandler(e, mailAlertMsg, "Ticker Exception :");
								
							}
						}
						priceDataDao.callProcedure("DAILY","","");
					} else if (CommonUtil.dateCompare(dbParamMap.get("LAST_BDATE_OF_MONTH").getValue().toString()) == true) {
						System.out.println("PriceProcessor.process() executing Monthly Process");
						Iterator<SecMaster> sec = lst.iterator();

						int i = 0;
						while (sec.hasNext()) {
							SecMaster secMaster = (SecMaster) sec.next();
							System.out.println(secMaster.toString());
							try {
								priceDataDao.delete();
								monthlyProcess(secMaster.getTicker(),price_provider);
								try {
									priceDataDao.callProcedure("MONTHLY",sdf.format(new Date()),secMaster.getTicker());

									try {
										rbsaCall(secMaster.getTicker());
									} catch (Exception e) {
										//e.printStackTrace();
										System.out.println("rbsa call:" + secMaster.getTicker());
										mailAlertMsg.append("rbsa call:" + secMaster.getTicker());
										exceptionHandler(e, mailAlertMsg, "RBSA Process :");
									}
								} catch (Exception e) {
									//e.printStackTrace();
									System.out.println("callProcedure:" + secMaster.getTicker());
									mailAlertMsg.append("callProcedure:" + secMaster.getTicker());
									exceptionHandler(e, mailAlertMsg, "callProcedure :");
								}
								// Call Procedure
							} catch (Exception e) {
								System.out.println("Ticker Exception:" + secMaster.getTicker());
								mailAlertMsg.append("Ticker Exception:" + secMaster.getTicker());
								exceptionHandler(e, mailAlertMsg, "Ticker Exception :");
							}
						}
					}
				} else {
					mailAlertMsg.append("list  not available from  yahoo:");
					System.out.println("list  not available from  yahoo:");
				}
			}
		} catch (Exception e) {
			System.out.println("PriceProcessor.process() WE R HERE..");
			exceptionHandler(e, mailAlertMsg, "main process");
		}
              
	
		finally {
			if (mailAlertMsg.length() > 0) {
				System.out.println("MailAlertMsg IS :" + mailAlertMsg);
			} else {
				System.out.println("MailAlertMsg is empty");
			}
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

	public void dailyProcess(String ticker, String provider)throws Exception{
		if(provider.equalsIgnoreCase("yahoo")){
			
			Stock stk = YahooFinance.get(ticker);
			PriceData pd = new PriceData(stk.getQuote().getSymbol(),
					sdf.format(stk.getQuote().getLastTradeTime().getTime()),
					Double.valueOf("" + stk.getQuote().getOpen()), Double.valueOf("" + stk.getQuote().getPrice()),
					Double.valueOf("" + stk.getQuote().getDayHigh()), Double.valueOf("" + stk.getQuote().getDayLow()),
					Long.valueOf(stk.getQuote().getVolume()), new Date(),
					Double.valueOf("" + stk.getQuote().getPreviousClose()), new Long(2), new Date());

//			System.out.println("Open   :" + stk.getQuote().getOpen());
//			System.out.println("LastTradePriceOnly   :" + stk.getQuote().getPrice());
//			System.out.println("Volume   :" + stk.getQuote().getVolume());
//			System.out.println("DayHigh   :" + stk.getQuote().getDayHigh());
//			System.out.println("DayLow   :" + stk.getQuote().getDayLow());
//			System.out.println("PreviousClose   :" + stk.getQuote().getPreviousClose());
			priceDataDao.insert(pd);
		}else if(provider.equalsIgnoreCase("xignite")){
			
		
		}else{
			
		}
			

	}

	public void monthlyProcess(String ticker,String provider) throws Exception {
		System.out.println("***********"+price_provider+"********");
		if(provider.equalsIgnoreCase("yahoo")){

			try {

				List<PriceData> pdl = new ArrayList<PriceData>();
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
				PriceData pd = new PriceData(stk.getQuote().getSymbol(),
													  sdf.format(stk.getQuote().getLastTradeTime().getTime()),
													  Double.valueOf("" + stk.getQuote().getOpen()), Double.valueOf("" + stk.getQuote().getPrice()),
													  Double.valueOf("" + stk.getQuote().getDayHigh()), Double.valueOf("" + stk.getQuote().getDayLow()),
													  Long.valueOf(stk.getQuote().getVolume()), new Date(),
													  Double.valueOf("" + stk.getQuote().getPreviousClose()), new Long(2), new Date());

				//pdl.add(pd);
				Date d = new Date();
//				Calendar from = new GregorianCalendar(2015, 9, 5);// Calendar.getInstance();
//				Calendar to = new GregorianCalendar(2016, 1, 29);// Calendar.getInstance();//2007-05-30
				Calendar from = Calendar.getInstance();
				from.setTime(new Date());
				Calendar to = Calendar.getInstance();//2007-05-30
				to.setTime(new Date());
				from.add(Calendar.YEAR, -20); // from 5 years ago
				List<HistoricalQuote> hstLst = stk.getHistory(from, to, Interval.DAILY);

				Iterator<HistoricalQuote> itr = hstLst.iterator();
				System.out.println("*********************Historical Data************************");

				while (itr.hasNext()) {

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
					pdl.add(hpd);
				}

				priceDataDao.insertBatch(pdl);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(provider.equalsIgnoreCase("xignite")){


		}else{

		}

	}

	public void exceptionHandler(Exception ex, StringBuilder mailAlertMsg, String process) {
		try {
			System.out.println("EXCEPTION CLASS:" + ex.getClass());
			ex.printStackTrace();
			if (ex instanceof MySQLIntegrityConstraintViolationException) {
				mailAlertMsg.append(process + "MySQLViolationException: " + ex.getMessage() + "\n");
				System.out.println(process + " MySQLViolationException: " + ex.getMessage());
			} else if (ex instanceof BadSqlGrammarException) {
				mailAlertMsg.append(process + "SqlGrammarException : " + ex.getMessage() + "\n");
				System.out.println(process + "SqlGrammarException : " + ex.getMessage());
			} else if (ex instanceof CannotGetJdbcConnectionException) {
				mailAlertMsg.append(process + "JDBC ConnectionException : " + ex.getMessage() + "\n");
				System.out.println(process + "ConnectionException : " + ex.getMessage());
			} else if (ex instanceof DuplicateKeyException) {
				mailAlertMsg.append(process + "DUPLICATE KEY : " + ex.getMessage() + "\n");
				System.out.println(process + "DUPLICATE KEY : " + ex.getMessage());
			}
			else if (ex instanceof DataIntegrityViolationException) {
				mailAlertMsg.append(process + "DATA TRUNCATION : " + ex.getMessage() + "\n");
				System.out.println(process + "DATA TRUNCATION : " + ex.getMessage());
			}
			 else if (ex instanceof NullPointerException) {
					mailAlertMsg.append(process +  "NULL POINTER EXCEPTION:" + ex.getMessage() + "\n");
					System.out.println(process + "NULL POINTER EXCEPTION:" + ex.getMessage());
				}
			 else if (ex instanceof SQLException) {
					mailAlertMsg.append(process +  "sql exception:"  + ex.getMessage() + "\n");
					System.out.println(process +    "sql exception:"  + ex.getMessage());
				}
			else if (ex instanceof InvalidDataAccessApiUsageException) {
				mailAlertMsg.append(process +  "Required input parameter  is missing:"  + ex.getMessage() + "\n");
				System.out.println(process +    "Required input parameter  is missing:"  + ex.getMessage());
			}
			else {
				mailAlertMsg.append(process + "NO Exception:" + ex.getMessage() + "\n");
				System.out.println(process + " : " + ex.getMessage());
			}
			
			
			meassage_data md = new meassage_data();
			md.setMsg(mailAlertMsg);		
            messageDao.insert(md);
			
		    } catch (Exception e) {
			mailAlertMsg.append(process + " QAZ: " + ex.getMessage() + "\n");
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
