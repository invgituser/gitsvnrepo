package com.invessence.yodlee.dao;

import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.invessence.yodlee.model.InvestmentHolding;
import com.invessence.yodlee.util.HibernateUtil;

@Repository
@Transactional
public class InvestmentHoldingsDAOImpl implements InvestmentHoldingsDAO {

	@Autowired
    private SessionFactory sessionFactory;
	@Autowired
	HibernateUtil hibernateutil;
	
	public InvestmentHolding insertInvestmentHoldings(InvestmentHolding investmentHoldings) {
		try {
			sessionFactory.getCurrentSession().save(investmentHoldings);
		} catch (Exception e) {e.printStackTrace();
		}
		return investmentHoldings;
	}

	public InvestmentHolding updateInvestmentHoldings(InvestmentHolding investmentHoldings) {
		try {
			sessionFactory.getCurrentSession().update(investmentHoldings);
		} catch (Exception e) {e.printStackTrace();
		}
		return investmentHoldings;
	}

	public InvestmentHolding deleteInvestmentHoldings(InvestmentHolding investmentHoldings) {
		try {
			sessionFactory.getCurrentSession().delete(investmentHoldings);
		} catch (Exception e) {e.printStackTrace();
		}
		return investmentHoldings;
	}

	public List<InvestmentHolding> getInvestmentHoldingsList() {
		List<InvestmentHolding> list = null;
		try {
			list = sessionFactory.getCurrentSession().createQuery("from InvestmentHolding").list();
		} catch (Exception e) {e.printStackTrace();
		}
		return list;
	}

	public InvestmentHolding findByPK(Long Id) {
		InvestmentHolding invData= null;
		try {
			invData=(InvestmentHolding)sessionFactory.getCurrentSession().get(InvestmentHolding.class, Id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return invData;
	}

	public List<InvestmentHolding> findByWhereCluase(String where, Object[] values) {
		List<InvestmentHolding> list = null;
		try {
			list = hibernateutil.executeSQLQuery(sessionFactory.getCurrentSession(), "ydl_investment_holdings", InvestmentHolding.class, where, values);
		} catch (Exception e) {e.printStackTrace();
		}
		return list;
	}

	public List<InvestmentHolding> findByWhereCluase(String where) {
		List<InvestmentHolding> lst = null; 
		try {
			lst=(List<InvestmentHolding>)sessionFactory.getCurrentSession().createQuery("from InvestmentHolding where "+where).list();
		} catch (Exception e) {e.printStackTrace();
		}
		return lst;
	}
	
	public int deleteInvestmentHoldings(String where){
		int result=0;
		try {		
			result=sessionFactory.getCurrentSession().createQuery("delete InvestmentHolding where "+where).executeUpdate();
			System.out.println("Rows affected: " + result);
		} catch (Exception e) {e.printStackTrace();
		}
		return result;
		
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}
