/**
 * Created by prashant on 6/14/2017.
 */
import java.math.BigDecimal;
import java.util.List;

import org.ojalgo.finance.portfolio.FinancePortfolio;
import org.ojalgo.finance.portfolio.MarketEquilibrium;
import org.ojalgo.finance.portfolio.MarkowitzModel;
import org.ojalgo.matrix.BasicMatrix;
import org.ojalgo.matrix.decomposition.Bidiagonal;

public class testOjAlgo
{
   public void main(String[] args) throws Exception {
      FinancePortfolio fp = null;
      FinancePortfolio.Context fpc = null;
      MarkowitzModel mm;

      fpc = new FinancePortfolio.Context()
      {
         @Override
         public double calculatePortfolioReturn(FinancePortfolio weightsPortfolio)
         {
            return 0;
         }

         @Override
         public double calculatePortfolioVariance(FinancePortfolio weightsPortfolio)
         {
            return 0;
         }

         @Override
         public BasicMatrix getAssetReturns()
         {
            return null;
         }

         @Override
         public BasicMatrix getAssetVolatilities()
         {
            return null;
         }

         @Override
         public BasicMatrix getCorrelations()
         {
            return null;
         }

         @Override
         public BasicMatrix getCovariances()
         {
            return null;
         }

         @Override
         public int size()
         {
            return 0;
         }
      };

      mm = new MarkowitzModel(fpc);
      BigDecimal bdl = new BigDecimal(0.1);
      BigDecimal bdu = new BigDecimal(0.9);
      mm.setLowerLimit(1, bdl);
      mm.setUpperLimit(1,bdu);
   }
}
