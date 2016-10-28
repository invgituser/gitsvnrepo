package com.invessence.yodlee.dao;

import java.util.List;

import com.invessence.yodlee.model.ConsolidateData;


public interface ConsolidateDataDAO {

	public ConsolidateData insertConsolidateData(ConsolidateData consolidateData);
	
	public ConsolidateData updateConsolidateData(ConsolidateData consolidateData);
	
	public ConsolidateData deleteConsolidateData(ConsolidateData consolidateData);
	
	public List<ConsolidateData> getConsolidateDataList();
	
	public ConsolidateData findByPK(Long Id);
	
	public List<ConsolidateData> findByWhereCluase(String where, Object[] values);
	
	public List<ConsolidateData> findByWhereCluase(String where);
}
