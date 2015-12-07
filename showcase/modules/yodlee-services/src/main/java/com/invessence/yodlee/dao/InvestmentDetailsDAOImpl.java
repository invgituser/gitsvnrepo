package com.invessence.yodlee.dao;

import java.util.List;


import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.invessence.yodlee.model.BankDetail;
import com.invessence.yodlee.model.InvestmentDetail;
import com.invessence.yodlee.util.HibernateUtil;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class InvestmentDetailsDAOImpl implements InvestmentDetailsDAO {

	@Autowired
    private SessionFactory sessionFactory;
	@Autowired
	HibernateUtil hibernateutil;
	
	public InvestmentDetail insertInvestmentDetails(InvestmentDetail investmentDetails) {
		try {
			sessionFactory.getCurrentSession().save(investmentDetails);
		} catch (Exception e) {e.printStackTrace();
		}
		return investmentDetails;
	}

	public InvestmentDetail updateInvestmentDetails(InvestmentDetail investmentDetails) {
		try {
			sessionFactory.getCurrentSession().update(investmentDetails);
		} catch (Exception e) {e.printStackTrace();
		}
		return investmentDetails;
	}

	public InvestmentDetail deleteInvestmentDetails(InvestmentDetail investmentDetails) {
		try {
			sessionFactory.getCurrentSession().delete(investmentDetails);
		} catch (Exception e) {e.printStackTrace();
		}
		return investmentDetails;
	}

	public List<InvestmentDetail> getInvestmentDetailsList() {
		List<InvestmentDetail> list = null;
		try {
			list = sessionFactory.getCurrentSession().createQuery("from InvestmentDetails").list();
		} catch (Exception e) {e.printStackTrace();
		}
		return list;
	}

	public InvestmentDetail findByPK(Long Id) {
		List<InvestmentDetail> lst = null;
		try {
			lst=(List<InvestmentDetail>)sessionFactory.getCurrentSession().createQuery("from InvestmentDetails sm where sm.SM_ID=:ID").setLong("ID", Id).list();
		} catch (Exception e) {e.printStackTrace();
		}
		return lst==null?null:lst.size()==0?null:lst.get(0);
	}

	public List<InvestmentDetail> findByWhereCluase(String where, Object[] values) {
		List<InvestmentDetail> list = null;
		try {
			list = hibernateutil.executeSQLQuery(sessionFactory.getCurrentSession(), "SKILL_MASTER", InvestmentDetail.class, where, values);
		} catch (Exception e) {e.printStackTrace();
		}
		return list;
	}

	public List<InvestmentDetail> findByWhereCluase(String where) {
		List<InvestmentDetail> lst = null; 
		try {
			lst=(List<InvestmentDetail>)sessionFactory.getCurrentSession().createQuery("from InvestmentDetails where "+where).list();
		} catch (Exception e) {e.printStackTrace();
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
