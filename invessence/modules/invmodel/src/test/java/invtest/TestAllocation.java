package invtest;

import com.invmodel.Const.InvConst;
import com.invmodel.asset.AssetAllocationModel;
import com.invmodel.asset.data.AssetClass;
import com.invmodel.dao.*;
import com.invmodel.inputData.ProfileData;

public class TestAllocation
{

   public static void main(String[] args) throws Exception
   {
      testAllocation(args);
   }

   public static void testAllocation(String[] args) throws Exception
   {
      int age, duration;
      String risk;
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

      ProfileData pdata = new ProfileData();
      pdata.setAge(age);
      pdata.setHorizon(duration);
      pdata.setRisk(risk);
      pdata.setAdvisor("Invessence");

      AssetAllocationModel assetAllocationModel = AssetAllocationModel.getInstance();
      assetAllocationModel.setAssetdao(AssetDBCollection.getInstance());
      assetAllocationModel.setHr(HistoricalReturns.getInstance());
      AssetClass[] aamc = assetAllocationModel.getAssetDistribution(pdata);

      System.out.println("Input: Age=" + String.valueOf(age) + ", Risk="
                            + risk + ",Duration=" + String.valueOf(duration));
      //getAssetWeightRounded("Equity")
      System.out.println("    ***The weights of the assets are  ***");
      String header = "Year";
      String asset = "";
      if (aamc != null)
      {
         for (int loop = 0; loop < aamc[0].getOrderedAsset().size(); loop++)
         {
            header = header + "\t" + aamc[0].getOrderedAsset().get(loop);
         }
         for (int i = 0; i < ((duration > InvConst.MAX_DURATION) ? InvConst.MAX_DURATION : duration); i++)
         {
            for (int loop = 0; loop < aamc[0].getOrderedAsset().size(); loop++)
            {
               asset = aamc[i].getOrderedAsset().get(loop);
               if (loop == 0)
               {
                  System.out.println("\n" + i);
               }
               System.out.print("\t" + aamc[i].getAssetRoundedWeight(asset));
            }
         }
      }
   }
}
