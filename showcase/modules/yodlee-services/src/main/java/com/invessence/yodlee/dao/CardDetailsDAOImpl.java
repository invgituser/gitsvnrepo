package com.invessence.yodlee.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.invessence.yodlee.model.CardDetail;
import com.invessence.yodlee.util.HibernateUtil;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class CardDetailsDAOImpl implements CardDetailsDAO {

	@Autowired
    private SessionFactory sessionFactory;
	@Autowired
	HibernateUtil hibernateutil;
	
	public CardDetail insertCardDetails(CardDetail cardDetails) {
		try {
			sessionFactory.getCurrentSession().save(cardDetails);
		} catch (Exception e) {
		}
		return cardDetails;
	}

	public CardDetail updateCardDetails(CardDetail cardDetails) {
		try {
			sessionFactory.getCurrentSession().update(cardDetails);
		} catch (Exception e) {
		}
		return cardDetails;
	}

	public CardDetail deleteCardDetails(CardDetail cardDetails) {
		try {
			sessionFactory.getCurrentSession().delete(cardDetails);
		} catch (Exception e) {
		}
		return cardDetails;
	}

	public List<CardDetail> getCardDetailsList() {
		List<CardDetail> list = null;
		try {
			list = sessionFactory.getCurrentSession().createQuery("from CardDetails").list();
		} catch (Exception e) {
		}
		return list;
	}

	public CardDetail findByPK(Long Id) {
		List<CardDetail> lst = null;
		try {
			lst=(List<CardDetail>)sessionFactory.getCurrentSession().createQuery("from CardDetails sm where sm.SM_ID=:ID").setLong("ID", Id).list();
		} catch (Exception e) {
		}
		return lst==null?null:lst.size()==0?null:lst.get(0);
	}

	public List<CardDetail> findByWhereCluase(String where, Object[] values) {
		List<CardDetail> list = null;
		try {
			list = hibernateutil.executeSQLQuery(sessionFactory.getCurrentSession(), "SKILL_MASTER", CardDetail.class, where, values);
		} catch (Exception e) {
		}
		return list;
	}

	public List<CardDetail> findByWhereCluase(String where) {
		List<CardDetail> lst = null; 
		try {
			lst=(List<CardDetail>)sessionFactory.getCurrentSession().createQuery("from CardDetails where "+where).list();
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
