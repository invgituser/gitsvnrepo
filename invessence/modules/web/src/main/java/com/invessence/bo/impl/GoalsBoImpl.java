package com.invessence.bo.impl;

import java.io.Serializable;

import com.invessence.bo.GoalsBo;
import com.invessence.dao.GoalsDao;
import com.invessence.data.*;

public class GoalsBoImpl implements Serializable,GoalsBo{
 
	/**
	 * 
	 */
	private static final long serialVersionUID = -514929263463122772L;
	GoalsDao goalsDao;
 
	public void setGoalsDao(GoalsDao goalsDao) {
		this.goalsDao = goalsDao;
	}
 
	public Long addGoals(ManageGoals goals){
		Long acctnum = goalsDao.addGoals(goals);
        return acctnum;
	}
    public void addAssetAndLiabilities(ManageGoals goals){
        goalsDao.addAssetAndLiabilities(goals);
    }

    public void addRiskTolerance(ManageGoals goals){
        goalsDao.addRiskTolerance(goals);
    }

    public ManageGoals findGoals(ManageGoals goals){
        return goalsDao.findGoals(goals);
    }

    public ManageGoals findRiskTolerance(ManageGoals goals){
        return goalsDao.findRiskTolerance(goals);
    }

    public void saveAllocation(ManageGoals goals) {
        goalsDao.saveAllocation(goals);
    }

    public void addPortfolio(ManageGoals goals) {
         goalsDao.addPortfolio(goals);
    }

   public PDFData getPDFData(Long accountNum){
      return goalsDao.getPDFData(accountNum);
   }

   public String validateState(Long acctnum, String state){
      return goalsDao.validateState(acctnum, state);
   }

}
