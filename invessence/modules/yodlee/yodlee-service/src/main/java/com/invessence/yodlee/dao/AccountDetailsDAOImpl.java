package com.invessence.yodlee.dao;

import java.util.List;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.invessence.yodlee.model.AccountDetail;
import com.invessence.yodlee.util.HibernateUtil;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class AccountDetailsDAOImpl implements AccountDetailsDAO {

	@Autowired
    private SessionFactory sessionFactory;
	@Autowired
	HibernateUtil hibernateutil;
		
	public AccountDetail insertAccountDetails(AccountDetail accountDetails) {
		try {
			sessionFactory.getCurrentSession().save(accountDetails);
		} catch (Exception e) {e.printStackTrace();
		}
		return accountDetails;
	}

	public AccountDetail updateAccountDetails(AccountDetail accountDetails) {
		try {
			sessionFactory.getCurrentSession().update(accountDetails);
		} catch (Exception e) {e.printStackTrace();
		}
		return accountDetails;
	}

	public AccountDetail deleteAccountDetails(AccountDetail accountDetails) {
		try {
			sessionFactory.getCurrentSession().delete(accountDetails);
		} catch (Exception e) {e.printStackTrace();
		}
		return accountDetails;
	}

	public List<AccountDetail> getAccountDetailsList() {
		List<AccountDetail> list = null;
		try {
			list = sessionFactory.getCurrentSession().createQuery("from AccountDetail").list();
		} catch (Exception e) {e.printStackTrace();
		}
		return list;
	}

	public AccountDetail findByPK(Long Id) {
		List<AccountDetail> lst = null;
		try {
			lst=(List<AccountDetail>)sessionFactory.getCurrentSession().createQuery("from AccountDetail sm where sm.SM_ID=:ID").setLong("ID", Id).list();
		} catch (Exception e) {e.printStackTrace();
		}
		return lst==null?null:lst.size()==0?null:lst.get(0);
	}

	public List<AccountDetail> findByWhereCluase(String where, Object[] values) {
		List<AccountDetail> list = null;
		try {
			list = hibernateutil.executeSQLQuery(sessionFactory.getCurrentSession(), "SKILL_MASTER", AccountDetail.class, where, values);
		} catch (Exception e) {e.printStackTrace();
		}
		return list;
	}

	public List<AccountDetail> findByWhereCluase(String where) {
		List<AccountDetail> lst = null; 
		try {
			lst=(List<AccountDetail>)sessionFactory.getCurrentSession().createQuery("from AccountDetail where "+where).list();
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
