package com.invessence.yodlee.dao;

import java.util.List;

import com.invessence.yodlee.model.InvestmentTransaction;

public interface InvestmentTransactionsDAO {

	public InvestmentTransaction insertInvestmentTransactions(InvestmentTransaction investmentTransactions);
	
	public InvestmentTransaction updateInvestmentTransactions(InvestmentTransaction investmentTransactions);
	
	public InvestmentTransaction deleteInvestmentTransactions(InvestmentTransaction investmentTransactions);
	
	public List<InvestmentTransaction> getInvestmentTransactionsList();
	
	public InvestmentTransaction findByPK(Long Id);
	
	public List<InvestmentTransaction> findByWhereCluase(String where, Object[] values);
	
	public List<InvestmentTransaction> findByWhereCluase(String where);
}
