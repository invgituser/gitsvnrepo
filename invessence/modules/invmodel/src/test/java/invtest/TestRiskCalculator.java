package invtest;

import com.invmodel.dao.*;
import com.invmodel.inputData.ProfileData;
import com.invmodel.riskCalculator.RiskIndex;

import static java.lang.String.valueOf;

/**
 * Hello world!
 */
public class TestRiskCalculator
{
   private static TestRiskCalculator instance = null;

   public static synchronized TestRiskCalculator getInstance()
   {
      if (instance == null)
      {
         instance = new TestRiskCalculator();
      }

      return instance;
   }


   /**
    * @param args
    */
   public static void main(String[] args) throws Exception
   {
      //getPortfolioAllocation(args);
      //testAllocation(args);
      //testPorfolioModel(args);
      //testPorfolioModel_noargs();
      testRiskModel(args);
      //testAllocationV2(args);
   }

   public static void testRiskModel(String[] args) throws Exception
   {
      AssetDBCollection assetdao = AssetDBCollection.getInstance();
      SecurityDBCollection securityDao = SecurityDBCollection.getInstance();


      int age, duration;
      String risk, tax;

      Integer invCapital, riskOffset;
      Integer yearlyInvestments;
      Integer stayInvested;
      String name = "";
      ProfileData profileData = new ProfileData();

      // Get asset allocation
      if (args.length > 0)
      {
         age = Integer.parseInt(args[0]);
      }
      else
      {
         age = 30;
      }

      if (age < 21)
      {
         age = 21;
      }
      if (age > 100)
      {
         age = 100;
      }

      // we also have risk if it is second arg
      if (args.length > 1)
      {
         risk = args[1];
      }
      else
      {
         risk = "M";
      }

      // we have horizon/duration if we have third arg.
      if (args.length > 2)
      {
         duration = Integer.parseInt(args[2]);
      }
      else
      {
         duration = 100 - age;
      }

      if (args.length > 3)
      {
         invCapital = Integer.parseInt(args[3]);
      }
      else
      {
         invCapital = 130000;
      }

      if (args.length > 4)
      {
         yearlyInvestments = Integer.parseInt(args[4]);
      }
      else
      {
         yearlyInvestments = 0;
      }

      if (args.length > 5)
      {

         tax = args[5];

         if (tax.equals("No"))
         {
            profileData.setAccountTaxable(false);
         }
         else
         {
            profileData.setAccountTaxable(true);
         }
      }
      else
      {
         tax = "No";
         profileData.setAccountTaxable(false);
      }

      if (args.length > 6)
      {
         riskOffset = Integer.parseInt(args[6]);
      }
      else
      {
         riskOffset = 1;
      }

      if (args.length > 7)
      {
         stayInvested = Integer.parseInt(args[7]);
      }
      else
      {
         stayInvested = 0;
      }

      if (args.length > 8)
      {
         name = args[1];
      }
      else
      {
         name = "NoName";
      }


      RiskIndex myRisk = new RiskIndex();

      Integer riskChoices[] = new Integer[]{0, 0, 1, 0, 0, 0, 4};

      Integer riskAnswer = myRisk.getRiskOffset(riskChoices);

      System.out.println("Risk Offset =" + riskAnswer);

   }

}