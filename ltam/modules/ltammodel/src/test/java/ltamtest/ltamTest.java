package ltamtest;


import com.invessence.LTAMOptimizer;
import com.invessence.data.LTAMTheme;

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

      LTAMOptimizer ltamoptimzer = LTAMOptimizer.getInstance();

      ltamoptimzer.refreshDataFromDB();
      LTAMTheme theme =  ltamoptimzer.getTheme(51.0);
      if (theme != null)
         System.out.println("Data Loaded: " + theme.getTheme());
      else
         System.out.println("Data NOT Loaded: ");

   }

}

