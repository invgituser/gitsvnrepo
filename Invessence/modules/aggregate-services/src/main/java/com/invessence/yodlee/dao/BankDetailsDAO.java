package com.invessence.yodlee.dao;

import java.util.List;

import com.invessence.yodlee.model.BankDetail;

public interface BankDetailsDAO {

	public BankDetail insertBankDetails(BankDetail bankDetails);
	
	public BankDetail updateBankDetails(BankDetail bankDetails);
	
	public BankDetail deleteBankDetails(BankDetail bankDetails);
	
	public List<BankDetail> getBankDetailsList();
	
	public BankDetail findByPK(Long Id);
	
	public List<BankDetail> findByWhereCluase(String where,Object[] values);
	
	public List<BankDetail> findByWhereCluase(String where);
}
