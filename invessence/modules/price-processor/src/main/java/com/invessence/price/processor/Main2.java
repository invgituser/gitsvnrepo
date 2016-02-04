//package com.invessence.price.processor;
//
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.GregorianCalendar;
//import java.util.Iterator;
//import java.util.List;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ConfigurableApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//import com.invessence.price.processor.DAO.PriceDataDao;
//import com.invessence.price.processor.DAO.SecMasterDao;
//import com.invessence.price.processor.bean.PriceData;
//import com.invessence.price.processor.bean.SecMaster;
//import com.invessence.price.yahoo.Stock;
//import com.invessence.price.yahoo.YahooFinance;
//import com.invessence.price.yahoo.histquotes.HistoricalQuote;
//import com.invessence.price.yahoo.histquotes.Interval;
//
//public class Main2 {
//	@Autowired
//	SecMasterDao secMasterDao;
//	
//	@Autowired
//	PriceDataDao priceDataDao;
//	
//	SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
//
//	public Main2() {
//		// TODO Auto-generated constructor stub
//		System.out.println("Main.Main()");
//		System.out.println("AMHI ITHE AHOT!");
//		//secMasterDao.findByWhere("");
//		// JDBCEmployeeDAO jdbcEmployeeDAO = (JDBCEmployeeDAO)
//		// context.getBean("jdbcEmployeeDAO");
//		// Employee employee3 = new Employee(456, "javacodegeeks", 34);
//		// jdbcEmployeeDAO.insert(employee3);
//		//
//		// Employee employee4 = jdbcEmployeeDAO.findById(456);
//		// System.out.println(employee4);
//	}
//
//	public static void main(String[] args) {
//		try {
//			
//			ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("priceBeanConf.xml");
//			SecMasterDao secDao = context.getBean(SecMasterDao.class);
//			List<SecMaster> lst = secDao.findByWhere("status = 'A'");
//			
//			Iterator<SecMaster> sec = lst.iterator();
//			PriceDataDao priceDataDao = context.getBean(PriceDataDao.class);
//			//app1.delete();
//			int i = 0;
//			while (sec.hasNext()) {
//				SecMaster secMaster = (SecMaster) sec.next();
//				System.out.println(secMaster.toString());
//				
//				Stock stk = YahooFinance.get(secMaster.getTicker());
//				try {
//					
//					new Main2().dailyProcess(secMaster.getTicker(), priceDataDao);
//					
////
////					PriceData pd = new PriceData(stk.getQuote().getSymbol(), new Date(),
////							Double.valueOf("" + stk.getQuote().getOpen()),
////							Double.valueOf("" + stk.getQuote().getPrice()),
////							Double.valueOf("" + stk.getQuote().getDayHigh()),
////							Double.valueOf("" + stk.getQuote().getDayLow()), Long.valueOf(stk.getQuote().getVolume()),
////							new Date(), Double.valueOf("" + stk.getQuote().getPreviousClose()), new Long(2),
////							new Date());
//					
////					Date d=new Date();
////					Calendar from = new GregorianCalendar(2015,9,5);// Calendar.getInstance();
////					Calendar to = new GregorianCalendar(2015,12,20);// Calendar.getInstance();//2007-05-30
////				
//////					Calendar from = new GregorianCalendar(d.getYear(),d.getMonth(),d.getDay());// Calendar.getInstance();
//////					Calendar to = new GregorianCalendar(d.getYear(),d.getMonth(),d.getDay()-1);// Calendar.getInstance();//2007-05-30
////					from.add(Calendar.YEAR, -20); // from 5 years ago
////					List<HistoricalQuote> hstLst= stk.getHistory(from,to, Interval.DAILY);
////					//List<HistoricalQuote> lst= stock1.getHistory(from,to, Interval.DAILY);
////					Iterator<HistoricalQuote> itr=hstLst.iterator();
////					System.out.println("*********************Historical Data************************");
////					List<PriceData> pdl=new ArrayList<PriceData>();
////					while (itr.hasNext()) {
////						
////						HistoricalQuote historicalQuote = (HistoricalQuote) itr.next();
//////						System.out.println("Ticker   :" + historicalQuote.getSymbol());
//////						System.out.println("LastTradeDate : " + sdf.format(historicalQuote.getDate().getTime()));
//////						// System.out.println("LastTradeDate
//////						// :"+stk.getQuote().getLastTradeDateStr());
//////						System.out.println("Open   :" + historicalQuote.getOpen());
//////						System.out.println("LastTradePriceOnly   :" + historicalQuote.getClose());
//////						System.out.println("Volume   :" + historicalQuote.getVolume());
//////						System.out.println("DayHigh   :" + historicalQuote.getHigh());
//////						System.out.println("DayLow   :" + historicalQuote.getLow());
//////						System.out.println("PreviousClose   :" + historicalQuote.getAdjClose());
////						
////						PriceData hpd = new PriceData(historicalQuote.getSymbol(), historicalQuote.getDate().getTime(),
////								Double.valueOf("" + historicalQuote.getOpen()),
////								Double.valueOf("" + historicalQuote.getClose()),
////								Double.valueOf("" + historicalQuote.getHigh()),
////								Double.valueOf("" + historicalQuote.getLow()), Long.valueOf(historicalQuote.getVolume()),
////								new Date(), Double.valueOf("" + historicalQuote.getAdjClose()), new Long(2),
////								new Date());
////						//pdl.add(hpd);
////						//app1.insert(hpd);
////					}
////					
////					app1.insertBatch(pdl);
////					
////					priceDao.insert(pd);
////					System.out.println("*********************Daily Data************************");					
////					System.out.println("Ticker   :" + stk.getQuote().getSymbol());
////					System.out.println("LastTradeDate : " + sdf.format(stk.getQuote().getLastTradeTime().getTime()));
////					// System.out.println("LastTradeDate
////					// :"+stk.getQuote().getLastTradeDateStr());
////					System.out.println("Open   :" + stk.getQuote().getOpen());
////					System.out.println("LastTradePriceOnly   :" + stk.getQuote().getPrice());
////					System.out.println("Volume   :" + stk.getQuote().getVolume());
////					System.out.println("DayHigh   :" + stk.getQuote().getDayHigh());
////					System.out.println("DayLow   :" + stk.getQuote().getDayLow());
////					System.out.println("PreviousClose   :" + stk.getQuote().getPreviousClose());
////					// System.out.println("PreviousClose :"+stk.get);
////
////					// stk.getQuote().getLastTradeTime()
////					i++;
////					 //if(i==5)break;
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//			context.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		// new Main();
//
//	}
//	
//	public void dailyProcess(String ticker, PriceDataDao priceDataDao){
//		
//		try {
//
//			Stock stk = YahooFinance.get(ticker);
//			PriceData pd = new PriceData(stk.getQuote().getSymbol(), new Date(),
//					Double.valueOf("" + stk.getQuote().getOpen()),
//					Double.valueOf("" + stk.getQuote().getPrice()),
//					Double.valueOf("" + stk.getQuote().getDayHigh()),
//					Double.valueOf("" + stk.getQuote().getDayLow()), Long.valueOf(stk.getQuote().getVolume()),
//					new Date(), Double.valueOf("" + stk.getQuote().getPreviousClose()), new Long(2),
//					new Date());
//			
//			System.out.println("Open   :" + stk.getQuote().getOpen());
//			System.out.println("LastTradePriceOnly   :" + stk.getQuote().getPrice());
//			System.out.println("Volume   :" + stk.getQuote().getVolume());
//			System.out.println("DayHigh   :" + stk.getQuote().getDayHigh());
//			System.out.println("DayLow   :" + stk.getQuote().getDayLow());
//			System.out.println("PreviousClose   :" + stk.getQuote().getPreviousClose());
//			priceDataDao.insert(pd);
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//			
//
//	}
//	
//	
//	public void monthlyProcess(String ticker){
//		
//		
//		try {
//			Stock stk = YahooFinance.get(ticker);
//			PriceData pd = new PriceData(stk.getQuote().getSymbol(), new Date(),
//					Double.valueOf("" + stk.getQuote().getOpen()),
//					Double.valueOf("" + stk.getQuote().getPrice()),
//					Double.valueOf("" + stk.getQuote().getDayHigh()),
//					Double.valueOf("" + stk.getQuote().getDayLow()), Long.valueOf(stk.getQuote().getVolume()),
//					new Date(), Double.valueOf("" + stk.getQuote().getPreviousClose()), new Long(2),
//					new Date());
//			
//			Date d=new Date();
//			Calendar from = new GregorianCalendar(2015,9,5);// Calendar.getInstance();
//			Calendar to = new GregorianCalendar(2015,12,20);// Calendar.getInstance();//2007-05-30
//		
////			Calendar from = new GregorianCalendar(d.getYear(),d.getMonth(),d.getDay());// Calendar.getInstance();
////			Calendar to = new GregorianCalendar(d.getYear(),d.getMonth(),d.getDay()-1);// Calendar.getInstance();//2007-05-30
//			from.add(Calendar.YEAR, -20); // from 5 years ago
//			List<HistoricalQuote> hstLst= stk.getHistory(from,to, Interval.DAILY);
//			//List<HistoricalQuote> lst= stock1.getHistory(from,to, Interval.DAILY);
//			Iterator<HistoricalQuote> itr=hstLst.iterator();
//			System.out.println("*********************Historical Data************************");
//			List<PriceData> pdl=new ArrayList<PriceData>();
//			while (itr.hasNext()) {
//				
//				HistoricalQuote historicalQuote = (HistoricalQuote) itr.next();
////				System.out.println("Ticker   :" + historicalQuote.getSymbol());
////				System.out.println("LastTradeDate : " + sdf.format(historicalQuote.getDate().getTime()));
////				// System.out.println("LastTradeDate
////				// :"+stk.getQuote().getLastTradeDateStr());
////				System.out.println("Open   :" + historicalQuote.getOpen());
////				System.out.println("LastTradePriceOnly   :" + historicalQuote.getClose());
////				System.out.println("Volume   :" + historicalQuote.getVolume());
////				System.out.println("DayHigh   :" + historicalQuote.getHigh());
////				System.out.println("DayLow   :" + historicalQuote.getLow());
////				System.out.println("PreviousClose   :" + historicalQuote.getAdjClose());
//				
//				PriceData hpd = new PriceData(historicalQuote.getSymbol(), historicalQuote.getDate().getTime(),
//						Double.valueOf("" + historicalQuote.getOpen()),
//						Double.valueOf("" + historicalQuote.getClose()),
//						Double.valueOf("" + historicalQuote.getHigh()),
//						Double.valueOf("" + historicalQuote.getLow()), Long.valueOf(historicalQuote.getVolume()),
//						new Date(), Double.valueOf("" + historicalQuote.getAdjClose()), new Long(2),
//						new Date());
//				//pdl.add(hpd);
//				//app1.insert(hpd);
//			}
//			
//			//priceDataDao.insertBatch(pdl);
//			
//			priceDataDao.insert(pd);
//			System.out.println("*********************Daily Data************************");					
//			System.out.println("Ticker   :" + stk.getQuote().getSymbol());
//			System.out.println("LastTradeDate : " + sdf.format(stk.getQuote().getLastTradeTime().getTime()));
//			// System.out.println("LastTradeDate
//			// :"+stk.getQuote().getLastTradeDateStr());
//			System.out.println("Open   :" + stk.getQuote().getOpen());
//			System.out.println("LastTradePriceOnly   :" + stk.getQuote().getPrice());
//			System.out.println("Volume   :" + stk.getQuote().getVolume());
//			System.out.println("DayHigh   :" + stk.getQuote().getDayHigh());
//			System.out.println("DayLow   :" + stk.getQuote().getDayLow());
//			System.out.println("PreviousClose   :" + stk.getQuote().getPreviousClose());
//			// System.out.println("PreviousClose :"+stk.get);
//
//			// stk.getQuote().getLastTradeTime()
//			 //if(i==5)break;
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//			
//	}
//	
//		
//
//	public PriceDataDao getPriceDataDao() {
//		return priceDataDao;
//	}
//
//	public void setPriceDataDao(PriceDataDao priceDataDao) {
//		this.priceDataDao = priceDataDao;
//	}
//
//	public SecMasterDao getSecMasterDao() {
//		return secMasterDao;
//	}
//
//	public void setSecMasterDao(SecMasterDao secMasterDao) {
//		this.secMasterDao = secMasterDao;
//	}
//
//}
