package org.ojalgo.finance.portfolio;

import java.math.BigDecimal;
import java.util.*;

import org.ojalgo.access.Access1D;
import org.ojalgo.constant.*;
import org.ojalgo.function.PrimitiveFunction;
import org.ojalgo.matrix.BasicMatrix;
import org.ojalgo.netio.BasicLogger;
import org.ojalgo.optimisation.*;
import org.ojalgo.scalar.Scalar;
import org.ojalgo.type.context.NumberContext;

import static org.ojalgo.constant.BigMath.ZERO;

/**
 * Created by jvyas on 6/26/2017.
 */
public final class InvEfficientFrontier extends OptimisedPortfolio
{
   private static final double _0_0 = ZERO.doubleValue();
   private static final double INIT = PrimitiveFunction.SQRT.invoke(PrimitiveMath.TEN);
   private static final double MAX = PrimitiveMath.HUNDRED * PrimitiveMath.HUNDRED;
   private static final double MIN = PrimitiveMath.HUNDREDTH;
   private static final NumberContext TARGET_CONTEXT = NumberContext.getGeneral(5, 4);

   private final HashMap<int[], LowerUpper> myConstraints = new HashMap<>();
   private transient ExpressionsBasedModel myOptimisationModel;
   private BigDecimal myTargetReturn;
   private BigDecimal myTargetVariance;

   public InvEfficientFrontier(final BasicMatrix covarianceMatrix, final BasicMatrix expectedExcessReturns)
   {
      super(covarianceMatrix, expectedExcessReturns);
   }

   public InvEfficientFrontier(final FinancePortfolio.Context portfolioContext)
   {
      super(portfolioContext);
   }

   public InvEfficientFrontier(final MarketEquilibrium marketEquilibrium, final BasicMatrix expectedExcessReturns)
   {
      super(marketEquilibrium, expectedExcessReturns);
   }

   /**
    * Will add a constraint on the sum of the asset weights specified by the asset indices. Either (but not
    * both) of the limits may be null.
    */
   public LowerUpper addConstraint(final BigDecimal lowerLimit, final BigDecimal upperLimit, final int... assetIndeces)
   {
      return myConstraints.put(assetIndeces, new LowerUpper(lowerLimit, upperLimit));
   }

   public final void clearAllConstraints()
   {
      myConstraints.clear();
      this.reset();
   }

   public final void setLowerLimit(final int assetIndex, final BigDecimal lowerLimit)
   {
      this.getVariable(assetIndex).lower(lowerLimit);
      this.reset();
   }

   public final void setConstraints(double[] lowerBound, double[] upperBound)
   {
      for (int assetIndex = 0; assetIndex < lowerBound.length; assetIndex++)
      {


      }

   }

   /**
    * <p>
    * Will set the target return to whatever you input and the target variance to <code>null</code>.
    * </p>
    * <p>
    * Setting the target return implies that you disregard the risk aversion factor and want the minimum risk
    * portfolio with return that is equal to or as close to the target as possible.
    * </p>
    * <p>
    * There is a performance penalty for setting a target return as the underlying optimisation model has to
    * be solved several (many) times with different pararmeters (different risk aversion factors).
    * </p>
    * <p>
    * Setting a target return (or variance) is not recommnded. It's much better to simply modify the risk
    * aversion factor.
    * </p>
    *
    * @see #setTargetVariance(BigDecimal)
    */
   public final void setTargetReturn(final BigDecimal targetReturn) {
      myTargetReturn = targetReturn;
      myTargetVariance = null;
      this.reset();
   }

   /**
    * <p>
    * Will set the target variance to whatever you input and the target return to <code>null</code>.
    * </p>
    * <p>
    * Setting the target variance implies that you disregard the risk aversion factor and want the maximum
    * return portfolio with risk that is equal to or as close to the target as possible.
    * </p>
    * <p>
    * There is a performance penalty for setting a target variance as the underlying optimisation model has
    * to be solved several (many) times with different pararmeters (different risk aversion factors).
    * </p>
    * <p>
    * Setting a target variance is not recommnded. It's much better to modify the risk aversion factor.
    * </p>
    *
    * @see #setTargetReturn(BigDecimal)
    */
   public final void setTargetVariance(final BigDecimal targetVariance) {
      myTargetVariance = targetVariance;
      myTargetReturn = null;
      this.reset();
   }

   public final void setUpperLimit(final int assetIndex, final BigDecimal upperLimit) {
      this.getVariable(assetIndex).upper(upperLimit);
      this.reset();
   }

   @Override
   public String toString() {

      if (myOptimisationModel == null) {
         this.calculateAssetWeights();
      }

      return myOptimisationModel.toString();
   }

   private ExpressionsBasedModel generateOptimisationModel(final double riskAversion) {

      if (myOptimisationModel == null) {
         myOptimisationModel = this.makeModel(myConstraints);
      }

      myOptimisationModel.getExpression(VARIANCE).weight(riskAversion / 2.0);

      if (this.getOptimisationOptions().debug_appender != null) {
         BasicLogger.debug();
         BasicLogger.debug("@@@@@@@@@@@");
         BasicLogger.debug("Iteration RAF: {}", riskAversion);
         BasicLogger.debug("Iteration point: {}", myOptimisationModel.getVariableValues());
         BasicLogger.debug("@@@@@@@@@@@");
         BasicLogger.debug();
      }

      return myOptimisationModel;
   }

   /**
    * Constrained optimisation.
    */
   @Override
   protected BasicMatrix calculateAssetWeights() {

      if (this.getOptimisationOptions().debug_appender != null) {
         BasicLogger.debug();
         BasicLogger.debug("###################################################");
         BasicLogger.debug("BEGIN RAF: {} MarkowitzModel optimisation", this.getRiskAversion());
         BasicLogger.debug("###################################################");
         BasicLogger.debug();
      }

      Optimisation.Result tmpResult;

      if ((myTargetReturn != null) || (myTargetVariance != null)) {

         final double tmpTargetValue;
         if (myTargetVariance != null) {
            tmpTargetValue = myTargetVariance.doubleValue();
         } else if (myTargetReturn != null) {
            tmpTargetValue = myTargetReturn.doubleValue();
         } else {
            tmpTargetValue = _0_0;
         }

         tmpResult = this.generateOptimisationModel(_0_0).minimise();

         double tmpTargetNow = _0_0;
         double tmpTargetDiff = _0_0;
         double tmpTargetLast = _0_0;

         if (tmpResult.getState().isFeasible()) {

            double tmpCurrent;
            double tmpLow;
            double tmpHigh;
            if (this.isDefaultRiskAversion()) {
               tmpCurrent = INIT;
               tmpLow = MAX;
               tmpHigh = MIN;
            } else {
               tmpCurrent = this.getRiskAversion().doubleValue();
               tmpLow = tmpCurrent * INIT;
               tmpHigh = tmpCurrent / INIT;
            }

            do {

               final ExpressionsBasedModel tmpModel = this.generateOptimisationModel(tmpCurrent);
               tmpResult = tmpModel.minimise();

               tmpTargetLast = tmpTargetNow;
               if (myTargetVariance != null) {
                  tmpTargetNow = this.calculatePortfolioVariance(tmpResult).doubleValue();
               } else if (myTargetReturn != null) {
                  tmpTargetNow = this.calculatePortfolioReturn(tmpResult, this.calculateAssetReturns()).doubleValue();
               } else {
                  tmpTargetNow = tmpTargetValue;
               }
               tmpTargetDiff = tmpTargetNow - tmpTargetValue;

               if (this.getOptimisationOptions().debug_appender != null) {
                  BasicLogger.debug();
                  BasicLogger.debug("RAF:   {}", tmpCurrent);
                  BasicLogger.debug("Last: {}", tmpTargetLast);
                  BasicLogger.debug("Now: {}", tmpTargetNow);
                  BasicLogger.debug("Target: {}", tmpTargetValue);
                  BasicLogger.debug("Diff:   {}", tmpTargetDiff);
                  BasicLogger.debug("Iteration:   {}", tmpResult);
                  BasicLogger.debug();
               }

               if (tmpTargetDiff < _0_0) {
                  tmpLow = tmpCurrent;
               } else if (tmpTargetDiff > _0_0) {
                  tmpHigh = tmpCurrent;
               }
               tmpCurrent = PrimitiveFunction.SQRT.invoke(tmpLow * tmpHigh);

            } while (!TARGET_CONTEXT.isSmall(tmpTargetValue, tmpTargetDiff) && TARGET_CONTEXT.isDifferent(tmpHigh, tmpLow));
         }

      } else {

         tmpResult = this.generateOptimisationModel(this.getRiskAversion().doubleValue()).minimise();

      }

      return this.handle(tmpResult);
   }

   @Override
   protected void reset() {

      super.reset();

      myOptimisationModel = null;

   }

   final Scalar<?> calculatePortfolioReturn(final Access1D<?> weightsVctr, final BasicMatrix returnsVctr) {
      return super.calculatePortfolioReturn(MATRIX_FACTORY.columns(weightsVctr), returnsVctr);
   }

   final Scalar<?> calculatePortfolioVariance(final Access1D<?> weightsVctr) {
      return super.calculatePortfolioVariance(MATRIX_FACTORY.columns(weightsVctr));
   }

}
