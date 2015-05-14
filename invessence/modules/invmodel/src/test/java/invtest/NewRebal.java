package invtest;

import java.io.*;
import java.util.ArrayList;

import com.invmodel.Const.InvConst;
import com.invmodel.dao.*;
import com.invmodel.rebalance.*;
import com.invmodel.rebalance.data.*;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 11/18/14
 * Time: 1:00 PM
 * To change this template use File | Settings | File Templates.
 */
public class NewRebal
{
   private static String datadir = "C:/Users/Jigar/Work Related/RiverFrontAdvisors/Clients/";

   public static void main(String[] args) throws Exception
   {
      // Intialize the Instance and Load prerequired data.
      PortfolioOptimizer  portfolioOptimizer = PortfolioOptimizer.getInstance();
      portfolioOptimizer.refreshDataFromDB();

      RebalanceProcess rbal = RebalanceProcess.getInstance();
      TLHSecurityCollection tlhsecurityCollection =  TLHSecurityCollection.getInstance();

      /*AssetDBCollection assetDAO = AssetDBCollection.getInstance();
      DailyReturns dailyReturnDAO = DailyReturns.getInstance();*/

      SecurityDBCollection secDao = new SecurityDBCollection();
      secDao.loadDataFromDB(InvConst.DEFAULT_THEME);
      rbal.setPortfolioOptimizer(portfolioOptimizer);
      rbal.setSecurityDAO(secDao);
      rbal.setTlhSecurityCollection(tlhsecurityCollection);

      // Now we can do rebalaning on account(s).
      ArrayList<TradeData> tradeList = rbal.process(null, 115L);
      printTradeFile(tradeList);
   }

   public static PrintWriter getFileHandle(String fileName)
   {
      File file;
      PrintWriter writer = null;

      try
      {
         file = new File(datadir + "Rebal/" + fileName);

         //file = new RandomAccessFile ("filename.ext","rw");
         file.getParentFile().mkdirs();
         writer = new PrintWriter(file);
      }
      catch (FileNotFoundException e)
      {
         e.printStackTrace();
      }
      return writer;
   }

   public static void printTradeFile (ArrayList<TradeData> tList){

      if(tList != null) {
         PrintWriter writer = getFileHandle("TradeFile.csv");
         writer.println("IB Acct#" +
                           "," + "Acctnum" +
                           "," + "Ticker" +
                           "," + "Qty" +
                           "," + "CurPrice" +
                           "," + "Money" +
                           "," + "holdingTicker" +
                           "," + "holdingQty" +
                           "," + "holdingPrice" +
                           "," + "holdingMoney" +
                           "," + "holdingWeight" +
                           "," + "costBasis" +
                           "," + "allocTicker" +
                           "," + "allocQty" +
                           "," + "allocPrice" +
                           "," + "allocValue" +
                           "," + "allocWeight" +
                           "," + "tradeType");

         for (TradeData tData : tList) {
            writer.println(tData.getClientAccountID() +
                              "," + tData.getAcctnum() +
                              "," + tData.getTicker() +
                              "," + tData.getQty() +
                              "," + tData.getCurPrice() +
                              "," + tData.getMoney() +
                              "," + tData.getHoldingTicker() +
                              "," + tData.getHoldingQty() +
                              "," + tData.getHoldingPrice() +
                              "," + tData.getHoldingValue() +
                              "," + tData.getHoldingWeight() +
                              "," + tData.getCostBasisValue() +
                              "," + tData.getAllocTicker() +
                              "," + tData.getAllocQty() +
                              "," + tData.getAllocPrice() +
                              "," + tData.getAllocValue() +
                              "," + tData.getAllocWeight() +
                              "," + tData.getTradeType());

         }
         writer.println();
         writer.close();

      }
   }

}
