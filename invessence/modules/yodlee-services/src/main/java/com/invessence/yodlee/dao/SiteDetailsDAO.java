package com.invessence.yodlee.dao;

import java.util.List;

import com.invessence.yodlee.model.SiteDetail;

public interface SiteDetailsDAO {

	public SiteDetail insertSiteDetails(SiteDetail siteDetails);
	
	public SiteDetail updateSiteDetails(SiteDetail siteDetails);
	
	public SiteDetail deleteSiteDetails(SiteDetail siteDetails);
	
	public List<SiteDetail> getSiteDetailsList();
	
	public SiteDetail findByPK(Long Id);
	
	public List<SiteDetail> findByWhereCluase(String where,Object[] values);
	
	public List<SiteDetail> findByWhereCluase(String where);
}
