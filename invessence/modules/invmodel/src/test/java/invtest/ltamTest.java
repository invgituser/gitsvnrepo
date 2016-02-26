package invtest;

import com.invmodel.model.fixedmodel.FixedModelOptimizer;
import com.invmodel.model.fixedmodel.data.FIData;


/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 2/21/15
 * Time: 10:21 PM
 * To change this template use File | Settings | File Templates.
 */
public class ltamTest
{


   public static void main(String[] args)
   {

      //try {

      FixedModelOptimizer ltamoptimzer = FixedModelOptimizer.getInstance();

      ltamoptimzer.refreshDataFromDB();
      FIData theme =  ltamoptimzer.getTheme(51);
      if (theme != null)
         System.out.println("Data Loaded: " + theme.getTheme());
      else
         System.out.println("Data NOT Loaded: ");

   }

}

