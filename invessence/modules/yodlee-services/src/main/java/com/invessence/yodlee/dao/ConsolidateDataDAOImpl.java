package com.invessence.yodlee.dao;

import java.util.*;

import org.springframework.transaction.annotation.Transactional;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.invessence.yodlee.model.ConsolidateData;
import com.invessence.yodlee.util.HibernateUtil;

@Repository
@Transactional
public class ConsolidateDataDAOImpl implements ConsolidateDataDAO {

	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	HibernateUtil hibernateutil;

	public ConsolidateData insertConsolidateData(ConsolidateData consolidateData) {
		try {
			sessionFactory.getCurrentSession().save(consolidateData);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return consolidateData;
	}

	public ConsolidateData updateConsolidateData(ConsolidateData consolidateData) {
		try {
			sessionFactory.getCurrentSession().update(consolidateData);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return consolidateData;
	}

	public ConsolidateData deleteConsolidateData(ConsolidateData consolidateData) {
		try {
			sessionFactory.getCurrentSession().delete(consolidateData);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return consolidateData;
	}

	public List<ConsolidateData> getConsolidateDataList() {
		List<ConsolidateData> list = null;
		try {
			list = sessionFactory.getCurrentSession().createQuery("from ConsolidateData").list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public ConsolidateData findByPK(Long Id) {
		ConsolidateData consData= null;
		try {
			consData=(ConsolidateData)sessionFactory.getCurrentSession().get(ConsolidateData.class, Id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return consData;
	}

	public List<ConsolidateData> findByWhereCluase(String where, Object[] values) {
		List<ConsolidateData> list = null;
		try {
			list = hibernateutil.executeSQLQuery(sessionFactory.getCurrentSession(), "ydl_consolidate_data",
															 ConsolidateData.class, where, values);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<ConsolidateData> findByWhereCluase(String where) {
		List<ConsolidateData> lst = null;
		try {
			lst = (List<ConsolidateData>) sessionFactory.getCurrentSession()
				.createQuery("from ConsolidateData where " + where).list();
			Iterator<ConsolidateData> itr=lst.iterator();
			while (itr.hasNext()) {
				ConsolidateData consolidateData = (ConsolidateData) itr.next();
				consolidateData.getSiteDetail().getSiteName();
				consolidateData.getItemDetail().getItemDispName();
				consolidateData.getAccountDetail().getAccName();
			}
		} catch (Exception e) {
			e.printStackTrace();
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
