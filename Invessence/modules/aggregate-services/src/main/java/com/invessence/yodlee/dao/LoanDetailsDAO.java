package com.invessence.yodlee.dao;

import java.util.List;

import com.invessence.yodlee.model.LoanDetail;

public interface LoanDetailsDAO {

	public LoanDetail insertLoanDetails(LoanDetail loanDetails);
	
	public LoanDetail updateLoanDetails(LoanDetail loanDetails);
	
	public LoanDetail deleteLoanDetails(LoanDetail loanDetails);
	
	public List<LoanDetail> getLoanDetailsList();
	
	public LoanDetail findByPK(Long Id);
	
	public List<LoanDetail> findByWhereCluase(String where,Object[] values);
	
	public List<LoanDetail> findByWhereCluase(String where);
}
