package com.invessence.dao;

import com.invessence.data.*;

public interface GoalsDao {

    Long addGoals(ManageGoals goals);
    void addAssetAndLiabilities(ManageGoals goals);
    void addRiskTolerance(ManageGoals goals);

    ManageGoals findGoals(ManageGoals goals);
    ManageGoals findRiskTolerance(ManageGoals goals);
    String validateState(Long acctnum, String state);

    void saveAllocation(ManageGoals goals);
    void addPortfolio(ManageGoals goals);

    PDFData getPDFData(Long accountNum);
}
