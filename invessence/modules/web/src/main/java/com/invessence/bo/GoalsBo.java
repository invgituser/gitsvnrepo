package com.invessence.bo;

import com.invessence.data.*;

public interface GoalsBo
{

   Long addGoals(ManageGoals goals);

   void addAssetAndLiabilities(ManageGoals goals);

   void addRiskTolerance(ManageGoals goals);

   ManageGoals findGoals(ManageGoals goals);

   ManageGoals findRiskTolerance(ManageGoals goals);

   void saveAllocation(ManageGoals goals);

   void addPortfolio(ManageGoals goals);

   PDFData getPDFData(Long accountNum);

   String validateState(Long acctnum, String state);

}
