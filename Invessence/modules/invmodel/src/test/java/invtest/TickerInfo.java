package invtest;

import java.util.ArrayList;

import com.invmodel.dao.data.SecurityData;
import com.invmodel.portfolio.data.PortfolioSecurityData;

/**
 * Created by Jigar on 9/19/2017.
 */
public class TickerInfo
{

   public class tickerData
   {
      String ticker;
      double weight;
   }
   public Integer riskNumber;
   public Double expReturns;
   public Double totalRisk;
   public ArrayList<PortfolioSecurityData> ticker = null;
   public Double weight;

}




