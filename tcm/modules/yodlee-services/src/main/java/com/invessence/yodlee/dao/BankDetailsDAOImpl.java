package com.invessence.yodlee.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.invessence.yodlee.model.BankDetail;
import com.invessence.yodlee.util.HibernateUtil;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class BankDetailsDAOImpl implements BankDetailsDAO {

	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	HibernateUtil hibernateutil;

	public BankDetail insertBankDetails(BankDetail bankDetails) {
		try {
			sessionFactory.getCurrentSession().save(bankDetails);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bankDetails;
	}

	public BankDetail updateBankDetails(BankDetail bankDetails) {
		try {
			sessionFactory.getCurrentSession().update(bankDetails);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bankDetails;
	}

	public BankDetail deleteBankDetails(BankDetail bankDetails) {
		try {
			sessionFactory.getCurrentSession().delete(bankDetails);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bankDetails;
	}

	public List<BankDetail> getBankDetailsList() {
		List<BankDetail> list = null;
		try {
			list = sessionFactory.getCurrentSession().createQuery("from BankDetail").list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public BankDetail findByPK(Long Id) {
		List<BankDetail> lst = null;
		try {
			lst = (List<BankDetail>) sessionFactory.getCurrentSession()
					.createQuery("from BankDetail sm where sm.SM_ID=:ID").setLong("ID", Id).list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lst == null ? null : lst.size() == 0 ? null : lst.get(0);
	}

	public List<BankDetail> findByWhereCluase(String where, Object[] values) {
		List<BankDetail> list = null;
		try {
			list = hibernateutil.executeSQLQuery(sessionFactory.getCurrentSession(), "ydl_bank_details", BankDetail.class,
					where, values);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<BankDetail> findByWhereCluase(String where) {
		List<BankDetail> lst = null;
		try {
			lst = (List<BankDetail>) sessionFactory.getCurrentSession().createQuery("from BankDetail where " + where)
					.list();
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
