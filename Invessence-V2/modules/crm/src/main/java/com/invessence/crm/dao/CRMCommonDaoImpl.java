package com.invessence.crm.dao;

import com.invessence.crm.bean.UserProfile;
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
public class CRMCommonDaoImpl implements CRMCommonDao
{
   private static final Logger logger = Logger.getLogger(CRMCommonDaoImpl.class);
   @Autowired @Qualifier("crmSessionFactory")
   private SessionFactory crmSessionFactory;

   private final String getSverviceDetails="select * from vw_service_details where company =? and serviceStatus='A' and operationStatus='A' order by service, provider";

   @Override
   public UserProfile getUserAccDetailsByLogonId(UserProfile userProfile) throws Exception
   {
      crmSessionFactory.getCurrentSession().save(userProfile);
      return userProfile;
   }

}
