package com.invessence.yodlee.dao;

import java.util.List;

import com.invessence.yodlee.model.InvestmentDetail;

public interface InvestmentDetailsDAO {

	public InvestmentDetail insertInvestmentDetails(InvestmentDetail investmentDetails);
	
	public InvestmentDetail updateInvestmentDetails(InvestmentDetail investmentDetails);
	
	public InvestmentDetail deleteInvestmentDetails(InvestmentDetail investmentDetails);
	
	public List<InvestmentDetail> getInvestmentDetailsList();
	
	public InvestmentDetail findByPK(Long Id);
	
	public List<InvestmentDetail> findByWhereCluase(String where,Object[] values);
	
	public List<InvestmentDetail> findByWhereCluase(String where);
}
