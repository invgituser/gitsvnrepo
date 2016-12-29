package com.invessence.aggr.dao;

import java.util.List;

import com.invessence.aggr.model.MXAudit;
import com.invessence.aggr.util.AggrHibernateUtil;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class MXAuditsDAOImpl implements MXAuditsDAO {

	@Autowired
	private SessionFactory aggrSessionFactory;
	@Autowired
	AggrHibernateUtil hibernateutil;

	@Override
	public MXAudit insertMXAudits(MXAudit mxAudit)
	{
		try {
			aggrSessionFactory.getCurrentSession().save(mxAudit);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mxAudit;
	}

	@Override
	public MXAudit updateMXAudits(MXAudit mxAudit)
	{
		try {
			aggrSessionFactory.getCurrentSession().update(mxAudit);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mxAudit;
	}

	@Override
	public MXAudit deleteMXAudits(MXAudit mxAudit)
	{
		return null;
	}

	@Override
	public List<MXAudit> getMXAuditsList()
	{
		return null;
	}

	@Override
	public MXAudit findByPK(Long Id)
	{
		return null;
	}

	@Override
	public List<MXAudit> findByWhereCluase(String where, Object[] values)
	{
		return null;
	}

	@Override
	public List<MXAudit> findByWhereCluase(String where)
	{
		return null;
	}

//	public MXAudit insertMXAudit(MXAudit mxAudit) {
//		try {
//			aggrSessionFactory.getCurrentSession().save(mxAudit);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return mxAudit;
//	}
//
//	public MXAudit updateMXAudit(MXAudit mxAudit) {
//		try {
//			aggrSessionFactory.getCurrentSession().update(mxAudit);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return mxAudit;
//	}
//
//	public MXAudit deleteMXAudit(MXAudit mxAudit) {
//		try {
//			aggrSessionFactory.getCurrentSession().delete(mxAudit);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return mxAudit;
//	}
//
//	public List<MXAudit> getMXAuditList() {
//		List<MXAudit> list = null;
//		try {
//			list = aggrSessionFactory.getCurrentSession().createQuery("from MXAudit").list();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return list;
//	}
//
//	public MXAudit findByPK(Long Id) {
//		List<MXAudit> lst = null;
//		try {
//			lst = (List<MXAudit>) aggrSessionFactory.getCurrentSession()
//					.createQuery("from MXAudit sm where sm.id=:ID").setLong("ID", Id).list();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return lst == null ? null : lst.size() == 0 ? null : lst.get(0);
//	}
//
//	public List<MXAudit> findByWhereCluase(String where, Object[] values) {
//		List<MXAudit> list = null;
//		try {
//			list = hibernateutil.executeSQLQuery(aggrSessionFactory.getCurrentSession(), "ydl_account_details",
//					MXAudit.class, where, values);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return list;
//	}
//
//	public List<MXAudit> findByWhereCluase(String where) {
//		List<MXAudit> lst = null;
//		try {
//			lst = (List<MXAudit>) aggrSessionFactory.getCurrentSession()
//					.createQuery("from MXAudit where " + where).list();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return lst;
//	}
//
//	public SessionFactory getSessionFactory() {
//		return aggrSessionFactory;
//	}
//
//	public void setSessionFactory(SessionFactory aggrSessionFactory) {
//		this.aggrSessionFactory = aggrSessionFactory;
//	}

}
