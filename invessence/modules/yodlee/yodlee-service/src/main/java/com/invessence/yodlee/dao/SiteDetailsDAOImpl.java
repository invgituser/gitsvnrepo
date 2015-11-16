package com.invessence.yodlee.dao;

import java.util.List;


import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.invessence.yodlee.model.BankDetail;
import com.invessence.yodlee.model.SiteDetail;
import com.invessence.yodlee.util.HibernateUtil;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class SiteDetailsDAOImpl implements SiteDetailsDAO{
	@Autowired
    private SessionFactory sessionFactory;
	@Autowired
	HibernateUtil hibernateutil;
	
	public SiteDetail insertSiteDetails(SiteDetail siteDetails) {
		System.out.println("SiteDetailsDAOImpl.insertSiteDetails()");
		try {
			sessionFactory.getCurrentSession().save(siteDetails);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return siteDetails;
	}

	public SiteDetail updateSiteDetails(SiteDetail siteDetails) {
		try {
			sessionFactory.getCurrentSession().update(siteDetails);
		} catch (Exception e) {
		}
		return siteDetails;
	}

	public SiteDetail deleteSiteDetails(SiteDetail siteDetails) {
		try {
			sessionFactory.getCurrentSession().delete(siteDetails);
		} catch (Exception e) {
		}
		return siteDetails;
	}

	public List<SiteDetail> getSiteDetailsList() {
		List<SiteDetail> list = null;
		try {
			list = sessionFactory.getCurrentSession().createQuery("from SiteDetails").list();
		} catch (Exception e) {
		}
		return list;
	}

	public SiteDetail findByPK(Long Id) {
		SiteDetail sd = null;
		try {
			sd=(SiteDetail)sessionFactory.getCurrentSession().get(SiteDetail.class, Id);
		} catch (Exception e) {
		}
		return sd;
	}

	public List<SiteDetail> findByWhereCluase(String where, Object[] values) {
		List<SiteDetail> list = null;
		try {
			list = hibernateutil.executeSQLQuery(sessionFactory.getCurrentSession(), "SKILL_MASTER", SiteDetail.class, where, values);
		} catch (Exception e) {
		}
		return list;
	}

	public List<SiteDetail> findByWhereCluase(String where) {
		List<SiteDetail> lst = null; 
		try {
			lst=(List<SiteDetail>)sessionFactory.getCurrentSession().createQuery("from SiteDetail where "+where).list();
		} catch (Exception e) {
		}
		return lst;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}
