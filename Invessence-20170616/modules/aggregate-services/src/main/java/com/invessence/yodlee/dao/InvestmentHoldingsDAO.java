package com.invessence.yodlee.dao;

import java.util.List;

import com.invessence.yodlee.model.InvestmentHolding;

public interface InvestmentHoldingsDAO {

	public InvestmentHolding insertInvestmentHoldings(InvestmentHolding investmentHoldings);
	
	public InvestmentHolding updateInvestmentHoldings(InvestmentHolding investmentHoldings);
	
	public InvestmentHolding deleteInvestmentHoldings(InvestmentHolding investmentHoldings);
	
	public List<InvestmentHolding> getInvestmentHoldingsList();
	
	public InvestmentHolding findByPK(Long Id);
	
	public List<InvestmentHolding> findByWhereCluase(String where, Object[] values);
	
	public List<InvestmentHolding> findByWhereCluase(String where);
	
	public int deleteInvestmentHoldings(String where);
}
