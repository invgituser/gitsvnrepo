package com.invessence.aggr.dao;

import com.invessence.aggr.bean.UserProfile;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by abhangp on 11/30/2016.
 */
@Repository
@Transactional
public class AggrCommonDaoImpl implements AggrCommonDao
{
   private static final Logger logger = Logger.getLogger(AggrCommonDaoImpl.class);
   @Autowired @Qualifier("aggrSessionFactory")
   private SessionFactory aggrSessionFactory;

   private final String getSverviceDetails="select * from vw_service_details where company =? and serviceStatus='A' and operationStatus='A' order by service, provider";

   @Override
   public UserProfile getUserAccDetailsByLogonId(UserProfile userProfile) throws Exception
   {
      aggrSessionFactory.getCurrentSession().save(userProfile);
      return userProfile;
   }
}
