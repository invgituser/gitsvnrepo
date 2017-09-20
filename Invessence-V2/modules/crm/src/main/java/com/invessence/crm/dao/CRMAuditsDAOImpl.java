package com.invessence.crm.dao;

import java.util.List;

import com.invessence.crm.model.RedTailAudit;
import com.invessence.crm.util.CRMHibernateUtil;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class CRMAuditsDAOImpl implements CRMAuditsDAO
{

	@Autowired @Qualifier("crmSessionFactory")
	private SessionFactory crmSessionFactory;
	@Autowired
	CRMHibernateUtil hibernateUtil;

	@Override
	public RedTailAudit insertRedTailAudits(RedTailAudit redTailAudit)
	{
		try {
			crmSessionFactory.getCurrentSession().save(redTailAudit);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return redTailAudit;
	}

	@Override
	public RedTailAudit updateRedTailAudits(RedTailAudit redTailAudit)
	{
		try {
			crmSessionFactory.getCurrentSession().update(redTailAudit);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return redTailAudit;
	}

	@Override
	public RedTailAudit deleteRedTailAudits(RedTailAudit redTailAudit)
	{
		return null;
	}

	@Override
	public List<RedTailAudit> getRedTailAuditsList()
	{
		return null;
	}

	@Override
	public RedTailAudit findByPK(Long Id)
	{
		return null;
	}

	@Override
	public List<RedTailAudit> findByWhereCluase(String where, Object[] values)
	{
		return null;
	}

	@Override
	public List<RedTailAudit> findByWhereCluase(String where)
	{
		return null;
	}



//	public RedTailAudit insertRedTailAudit(RedTailAudit redTailAudit) {
//		try {
//			crmSessionFactory.getCurrentSession().save(redTailAudit);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return redTailAudit;
//	}
//
//	public RedTailAudit updateRedTailAudit(RedTailAudit redTailAudit) {
//		try {
//			crmSessionFactory.getCurrentSession().update(redTailAudit);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return redTailAudit;
//	}
//
//	public RedTailAudit deleteRedTailAudit(RedTailAudit redTailAudit) {
//		try {
//			crmSessionFactory.getCurrentSession().delete(redTailAudit);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return redTailAudit;
//	}
//
//	public List<RedTailAudit> getRedTailAuditList() {
//		List<RedTailAudit> list = null;
//		try {
//			list = crmSessionFactory.getCurrentSession().createQuery("from RedTailAudit").list();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return list;
//	}
//
//	public RedTailAudit findByPK(Long Id) {
//		List<RedTailAudit> lst = null;
//		try {
//			lst = (List<RedTailAudit>) crmSessionFactory.getCurrentSession()
//					.createQuery("from RedTailAudit sm where sm.id=:ID").setLong("ID", Id).list();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return lst == null ? null : lst.size() == 0 ? null : lst.get(0);
//	}
//
//	public List<RedTailAudit> findByWhereCluase(String where, Object[] values) {
//		List<RedTailAudit> list = null;
//		try {
//			list = hibernateutil.executeSQLQuery(crmSessionFactory.getCurrentSession(), "ydl_account_details",
//					RedTailAudit.class, where, values);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return list;
//	}
//
//	public List<RedTailAudit> findByWhereCluase(String where) {
//		List<RedTailAudit> lst = null;
//		try {
//			lst = (List<RedTailAudit>) crmSessionFactory.getCurrentSession()
//					.createQuery("from RedTailAudit where " + where).list();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return lst;
//	}
//
//	public SessionFactory getSessionFactory() {
//		return crmSessionFactory;
//	}
//
//	public void setSessionFactory(SessionFactory crmSessionFactory) {
//		this.crmSessionFactory = crmSessionFactory;
//	}

}
