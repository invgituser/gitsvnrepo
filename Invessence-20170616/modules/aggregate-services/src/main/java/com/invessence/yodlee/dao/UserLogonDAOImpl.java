package com.invessence.yodlee.dao;

import java.util.ArrayList;
import java.util.List;


import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.invessence.yodlee.model.UserLogon;
import com.invessence.yodlee.util.HibernateUtil;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class UserLogonDAOImpl implements UserLogonDAO {

	@Autowired
    private SessionFactory sessionFactory;
	@Autowired
	HibernateUtil hibernateutil;
	
	
	public List<UserLogon> getInvUserList(){
		
		List<UserLogon> ulLst=null;
		try {
		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery("select logonid, userid, email from invdb.user_logon");
		ulLst=new ArrayList<UserLogon>();
		List<Object[]> rows = query.list();		
			for(Object[] row : rows){
				
				UserLogon ul = new UserLogon();				
			    ul.setInvUserId(Long.parseLong(row[0].toString()));
			    ul.setUserId(row[1].toString());
			    ul.setEmail(row[2].toString());
			    //System.out.println(emp);
			    ulLst.add(ul);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ulLst;
	}
	public UserLogon getInvUserDetails(Long id){
		UserLogon ul=null;
		try {
		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery("select logonid, userid, email from invdb.user_logon where logonid= "+id);
		List<Object[]> rows = query.list();
		
			for(Object[] row : rows){
				
				ul = new UserLogon();
				
			    ul.setInvUserId(Long.parseLong(row[0].toString()));
			    ul.setUserId(row[1].toString());
			    ul.setEmail(row[2].toString());
			    //System.out.println(emp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ul;
	}

	public UserLogon insertUserLogon(UserLogon userLogon) {
		try {
			sessionFactory.getCurrentSession().save(userLogon);
		} catch (Exception e) {e.printStackTrace();
		}
		return userLogon;
	}

	public UserLogon updateUserLogon(UserLogon userLogon) {
		try {
			sessionFactory.getCurrentSession().update(userLogon);
		} catch (Exception e) {e.printStackTrace();
		}
		return userLogon;
	}

	public UserLogon deleteUserLogon(UserLogon userLogon) {
		try {
			sessionFactory.getCurrentSession().delete(userLogon);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userLogon;
	}

	public List<UserLogon> getUserLogonList() {
		System.out.println("UserLogonDAOImpl.getUserLogonList()");;
		List<UserLogon> list = null;
		try {
			list = sessionFactory.getCurrentSession().createQuery("from UserLogon").list();
			System.out.println(list.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public UserLogon findByPK(Long Id) {
		List<UserLogon> lst = null;
		try {
			lst=(List<UserLogon>)sessionFactory.getCurrentSession().createQuery("from UserLogon ur where ur.id=:ID").setLong("ID", Id).list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lst==null?null:lst.size()==0?null:lst.get(0);
	}

	public List<UserLogon> findByWhereCluase(String where, Object[] values) {
		List<UserLogon> list = null;
		try {
			list = hibernateutil.executeSQLQuery(sessionFactory.getCurrentSession(), "ydl_user_logon", UserLogon.class, where, values);
		} catch (Exception e) {e.printStackTrace();
		}
		return list;
	}

	public List<UserLogon> findByWhereCluase(String where) {
		List<UserLogon> lst = null; 
		try {
			lst=(List<UserLogon>)sessionFactory.getCurrentSession().createQuery("from UserLogon where "+where).list();
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
