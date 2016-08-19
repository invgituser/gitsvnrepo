package com.invessence.service.dao;

import java.sql.SQLException;
import java.util.*;

import com.invessence.service.bean.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

/**
 * Created by abhangp on 1/19/2016.
 */
@Repository
public class ServiceDaoImpl implements ServiceDao
{
   private static final Logger logger = Logger.getLogger(ServiceDaoImpl.class);
   @Autowired
   JdbcTemplate serviceJdbcTemplate;

   private final String getSwitchDetails = "SELECT name, value, format, description FROM vw_invessence_switch where name in('COMPANY_NAME')";
   private final String getServiceConfigDetails ="select * from vw_service_config_details where mode =? and company=? order by company, mode, service, vendor";
   private final String getServiceDetails ="select * from vw_service_details where company =? and serviceStatus='A' and operationStatus='A' order by service, vendor";
   private final String getWebConfigDetails ="select * from web_site_info where status = 'A' and mode =? and company=? order by service, vendor, name";

   private final String getDCTemplateDetails ="select * from dc_template_details where status = 'A' and mode =? and company=? order by service";
   private final String getDCTemplateMapping ="select * from dc_template_mapping where tempCode=? and (dbColumn IS NULL or dbColumn != '')";

   private final String getLookupDetails ="select * from dc_m_lookup where status='A'";


   public Map<String, SwitchDetails> getSwitchDetails() {
      logger.info("ServiceDaoImpl.getSwitchDetails");
      logger.debug("getSwitchDetails = "+ getSwitchDetails);

      List<SwitchDetails> switchDetailsLst = null;
      Map<String, SwitchDetails> switchDetailsMap = null;
      try {

         switchDetailsLst = serviceJdbcTemplate.query(getSwitchDetails, ParameterizedBeanPropertyRowMapper.newInstance(SwitchDetails.class));
         if(switchDetailsLst.size()>0){
            switchDetailsMap=new HashMap<String, SwitchDetails>();
            Iterator<SwitchDetails> itr=switchDetailsLst.iterator();
            while (itr.hasNext()) {
               SwitchDetails dbParameters = (SwitchDetails) itr.next();
               switchDetailsMap.put(dbParameters.getName(), dbParameters);
            }
         }
         return switchDetailsMap;
      } catch (Exception e) {
         e.printStackTrace();
      }
      return null;

   }

   public List<ServiceConfigDetails> getServiceConfigDetails(String serviceMode, String company) throws SQLException
   {
      logger.info("ServiceDaoImpl.getServiceConfigDetails");
      logger.info("serviceMode = [" + serviceMode + "], company = [" + company + "]");
      logger.debug("getServiceConfigDetails = "+ getServiceConfigDetails);
      List<ServiceConfigDetails> lst = null;
      lst = serviceJdbcTemplate.query(getServiceConfigDetails, new Object[]{serviceMode,company}, ParameterizedBeanPropertyRowMapper.newInstance(ServiceConfigDetails.class));
      return lst;
   }

   public List<ServiceDetails> getServiceOperationDetails(String serviceMode, String company) throws SQLException
   {
      logger.info("ServiceDaoImpl.getServiceOperationDetails");
      logger.info("serviceMode = [" + serviceMode + "], company = [" + company + "]");
      logger.debug("getServiceOperationDetails = "+ getServiceDetails);
      List<ServiceDetails> lst = null;
      lst = serviceJdbcTemplate.query(getServiceDetails, new Object[]{company}, ParameterizedBeanPropertyRowMapper.newInstance(ServiceDetails.class));
      return lst;
   }

   public List<WebConfigDetails> getWebServiceDetails(String serviceMode, String company) throws SQLException
   {
      logger.info("ServiceDaoImpl.getWebServiceDetails");
      logger.info("serviceMode = [" + serviceMode + "], company = [" + company + "]");
      logger.debug("WebConfigDetails = "+ getWebConfigDetails);
      List<WebConfigDetails> lst = null;
      lst = serviceJdbcTemplate.query(getWebConfigDetails, new Object[]{serviceMode,company}, ParameterizedBeanPropertyRowMapper.newInstance(WebConfigDetails.class));
      return lst;
   }

   @Override
   public Map<String,DCTemplateDetails> getDCTemplateDetails(String serviceMode, String company) throws SQLException
   {
      logger.info("ServiceDaoImpl.getDCTemplateDetails");
      logger.info("serviceMode = [" + serviceMode + "], company = [" + company + "]");
      logger.debug("getDCTemplateDetails = "+ getDCTemplateDetails);
      Map<String,DCTemplateDetails> map=null;
      List<DCTemplateDetails> lst = null;
      lst = serviceJdbcTemplate.query(getDCTemplateDetails, new Object[]{serviceMode,company}, ParameterizedBeanPropertyRowMapper.newInstance(DCTemplateDetails.class));
      if(lst !=null && lst.size()>0){
         map=new HashMap<>();
         Iterator<DCTemplateDetails> itr=lst.iterator();
         while(itr.hasNext()){
            DCTemplateDetails dct=(DCTemplateDetails)itr.next();
            map.put(dct.getTempCode(),dct);
         }
      }
      return map;
   }

   @Override
   public Map<String,DCTemplateMapping> getDCTemplateMapping(String serviceMode, String company, String tempCode) throws SQLException
   {
      logger.info("ServiceDaoImpl.getDCTemplateMapping");
      logger.info("serviceMode = [" + serviceMode + "], company = [" + company + "], tempCode = [" + tempCode + "]");
      logger.debug("getDCTemplateMapping = "+ getDCTemplateMapping);
      Map<String,DCTemplateMapping> map=null;
      List<DCTemplateMapping> lst = null;
      lst = serviceJdbcTemplate.query(getDCTemplateMapping, new Object[]{tempCode}, ParameterizedBeanPropertyRowMapper.newInstance(DCTemplateMapping.class));
      if(lst !=null && lst.size()>0){
         map=new HashMap<>();
         Iterator<DCTemplateMapping> itr=lst.iterator();
         while(itr.hasNext()){
            DCTemplateMapping dctm=(DCTemplateMapping)itr.next();
            map.put(dctm.getTempCode(),dctm);
         }
      }
      return map;
   }

   @Override
   public List<LookupDetails> getLookupDetails() throws SQLException
   {
      logger.info("ServiceDaoImpl.gegetLookupDetailstWebServiceDetails");
      logger.debug("LookupDetails = "+ getLookupDetails);
      List<LookupDetails> lst = null;
      lst = serviceJdbcTemplate.query(getLookupDetails, ParameterizedBeanPropertyRowMapper.newInstance(LookupDetails.class));
      return lst;
   }


}
