package com.invessence.yodlee.dao;

import java.util.List;


import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.invessence.yodlee.model.BankDetail;
import com.invessence.yodlee.model.ItemDetail;
import com.invessence.yodlee.util.HibernateUtil;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class ItemDetailsDAOImpl implements ItemDetailsDAO {

	@Autowired
    private SessionFactory sessionFactory;
	@Autowired
	HibernateUtil hibernateutil;
	
	public ItemDetail insertItemDetails(ItemDetail itemDetails) {
		try {
			sessionFactory.getCurrentSession().save(itemDetails);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return itemDetails;
	}

	public ItemDetail updateItemDetails(ItemDetail itemDetails) {
		try {
			sessionFactory.getCurrentSession().update(itemDetails);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return itemDetails;
	}

	public ItemDetail deleteItemDetails(ItemDetail itemDetails) {
		try {
			sessionFactory.getCurrentSession().delete(itemDetails);
		} catch (Exception e) {e.printStackTrace();
		}
		return itemDetails;
	}

	public List<ItemDetail> getItemDetailsList() {
		List<ItemDetail> list = null;
		try {
			list = sessionFactory.getCurrentSession().createQuery("from ItemDetail").list();
		} catch (Exception e) {e.printStackTrace();
		}
		return list;
	}

	public ItemDetail findByPK(Long Id) {
		List<ItemDetail> lst = null;
		try {
			lst=(List<ItemDetail>)sessionFactory.getCurrentSession().createQuery("from ItemDetail sm where sm.SM_ID=:ID").setLong("ID", Id).list();
		} catch (Exception e) {e.printStackTrace();
		}
		return lst==null?null:lst.size()==0?null:lst.get(0);
	}

	public List<ItemDetail> findByWhereCluase(String where, Object[] values) {
		List<ItemDetail> list = null;
		try {
			list = hibernateutil.executeSQLQuery(sessionFactory.getCurrentSession(), "SKILL_MASTER", ItemDetail.class, where, values);
		} catch (Exception e) {e.printStackTrace();
		}
		return list;
	}

	public List<ItemDetail> findByWhereCluase(String where) {
		List<ItemDetail> lst = null; 
		try {
			lst=(List<ItemDetail>)sessionFactory.getCurrentSession().createQuery("from ItemDetail where "+where).list();
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
