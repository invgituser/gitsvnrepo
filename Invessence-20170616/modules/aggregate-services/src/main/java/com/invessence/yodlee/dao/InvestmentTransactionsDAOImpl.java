package com.invessence.yodlee.dao;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.invessence.yodlee.model.BankDetail;
import com.invessence.yodlee.model.ConsolidateData;
import com.invessence.yodlee.model.InvestmentTransaction;
import com.invessence.yodlee.util.HibernateUtil;

@Repository
@Transactional
public class InvestmentTransactionsDAOImpl implements InvestmentTransactionsDAO {

	@Autowired
    private SessionFactory sessionFactory;
	@Autowired
	HibernateUtil hibernateutil;
	
	public InvestmentTransaction insertInvestmentTransactions(InvestmentTransaction investmentTransactions) {
		try {
			sessionFactory.getCurrentSession().save(investmentTransactions);
		} catch (Exception e) {e.printStackTrace();
		}
		return investmentTransactions;
	}

	public InvestmentTransaction updateInvestmentTransactions(InvestmentTransaction investmentTransactions) {
		try {
			sessionFactory.getCurrentSession().update(investmentTransactions);
		} catch (Exception e) {e.printStackTrace();
		}
		return investmentTransactions;
	}

	public InvestmentTransaction deleteInvestmentTransactions(InvestmentTransaction investmentTransactions) {
		try {
			sessionFactory.getCurrentSession().delete(investmentTransactions);
		} catch (Exception e) {e.printStackTrace();
		}
		return investmentTransactions;
	}

	public List<InvestmentTransaction> getInvestmentTransactionsList() {
		List<InvestmentTransaction> list = null;
		try {
			list = sessionFactory.getCurrentSession().createQuery("from InvestmentTransaction").list();
		} catch (Exception e) {e.printStackTrace();
		}
		return list;
	}

	public InvestmentTransaction findByPK(Long Id) {
		InvestmentTransaction invData= null;
		try {
			invData=(InvestmentTransaction)sessionFactory.getCurrentSession().get(InvestmentTransaction.class, Id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return invData;
	}

	public List<InvestmentTransaction> findByWhereCluase(String where, Object[] values) {
		List<InvestmentTransaction> list = null;
		try {
			list = hibernateutil.executeSQLQuery(sessionFactory.getCurrentSession(), "ydl_investment_transactions", InvestmentTransaction.class, where, values);
		} catch (Exception e) {e.printStackTrace();
		}
		return list;
	}

	public List<InvestmentTransaction> findByWhereCluase(String where) {
		List<InvestmentTransaction> lst = null; 
		try {
			lst=(List<InvestmentTransaction>)sessionFactory.getCurrentSession().createQuery("from InvestmentTransaction where "+where).list();
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
