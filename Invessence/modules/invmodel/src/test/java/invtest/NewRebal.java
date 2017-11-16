package invtest;

import java.io.*;
import java.util.ArrayList;

import com.invmodel.Const.InvConst;
import com.invmodel.dao.invdb.*;
import com.invmodel.model.ModelUtil;
import com.invmodel.model.dynamic.PortfolioOptimizer;
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
      ModelUtil modelUtil = ModelUtil.getInstance();
      modelUtil.refreshData();

      RebalanceProcess rbal = RebalanceProcess.getInstance();
      TLHSecurityCollection tlhsecurityCollection =  TLHSecurityCollection.getInstance();

      /*AssetDBCollection assetDAO = AssetDBCollection.getInstance();
      DailyReturns dailyReturnDAO = DailyReturns.getInstance();*/

      SecurityCollection secCollection = new SecurityCollection();
      //secCollection.loadDataFromDB(InvConst.INVESSENCE_ADVISOR, "0.Wealth");
      secCollection.loadDataFromDB("BB", "0.BB");
      rbal.setTlhSecurityCollection(tlhsecurityCollection);

      // Now we can do re-balancing on account(s).
      ArrayList<UserTradePreprocess> UserTradePreprocess = rbal.process(2L, 1L);
      printTradeFile(UserTradePreprocess);
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

   public static void printTradeFile (ArrayList<UserTradePreprocess> tList){

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
                           "," + "newQty" +
                           "," + "newValue" +
                           "," + "tradeType" );

         for (UserTradePreprocess tData : tList) {
            writer.println(tData.getClientAccountID() +
                              "," + tData.getAcctnum() +
                              "," + tData.getHoldingTicker() +
                              "," + tData.getCurQty() +
                              "," + tData.getCurPrice() +
                              "," + tData.getCurValue() +
                              "," + tData.getNewTicker() +
                              "," + tData.getNewQty() +
                              "," + tData.getNewPrice() +
                              "," + tData.getNewValue() +
                              "," + tData.getNewQty() +
                              "," + tData.getNewValue() +
                              "," + tData.getTradeType());

         }
         writer.println();
         writer.close();

      }
   }

}
