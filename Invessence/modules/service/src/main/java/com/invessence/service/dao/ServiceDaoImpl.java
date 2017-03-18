package com.invessence.service.dao;

import java.lang.reflect.*;
import java.sql.SQLException;
import java.util.*;
import com.invessence.converter.SQLData;
import com.invessence.service.bean.*;
import com.invessence.service.bean.docuSign.*;
import com.invessence.service.util.Constant;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

/**
 * Created by abhangp on 1/19/2016.
 */
@Repository("wsCommonDao")
public class ServiceDaoImpl implements ServiceDao
{
   private static final Logger logger = Logger.getLogger(ServiceDaoImpl.class);
   @Autowired
   JdbcTemplate serviceJdbcTemplate;

   private final String getSwitchDetails = "SELECT name, value, format, description FROM vw_invessence_switch where name in('COMPANY_NAME')";
   private final String getServiceConfigDetails ="select * from vw_service_config_details where mode =? and company=? and service=? order by company, mode, service, vendor";
   private final String getServiceConfigDetailsAll ="select * from vw_service_config_details where mode =? and company=? order by company, mode, service, vendor";
   private final String getServiceConfigDetailsWherClause ="select * from vw_service_config_details where mode =? and company=? and service in (?) order by company, mode, service, vendor";


   private final String getServiceOperationDetails ="select * from vw_service_details where company =? and serviceStatus='A' and operationStatus='A' order by service, vendor";
   private final String getWebConfigDetails ="select * from web_site_info where status = 'A' and mode =? and company=? order by service, vendor, name";

   private final String getDCTemplateDetails ="select * from dc_template_details where status = 'A' and mode =? and company=? order by service, tempCode";
   private final String getDCTemplateMapping ="select * from dc_template_mapping where tempCode=? and (dbColumn IS NOT NULL or dbColumn != '')order by role, tab";

   private final String getDCDocumentDetails ="select * from dc_document_details where status = 'A' and company=? order by docSeq";
   private final String getDCDocumentMapping ="select * from dc_document_mapping where docCode=? order by role, tab";


   private final String getLookupDetails ="select * from mast_lookup where status='A'";

   private final String getServiceConfigDetailsNew ="select * from vw_service_config_details_new order by company, service, vendor, mode, name";
   private final String getExceptionExternalDetails ="select * from vw_service_error_external where status = 'A' and service=? and vendor=? order by vendorErrCode";
   private final String getExceptionInternalDetails ="select * from vw_service_error_internal where status = 'A'";

   private final String getDocuSignModeBasedDetails ="select * from service.dc_template_details where mode=? and status = 'A'";
   private final String getDocuSignCommonDetails ="select * from service.dc_template_details where mode=? and status = 'A'";



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

   public List<ServiceConfigDetails> getServiceConfigDetails(String serviceMode, String company, String service) throws SQLException
   {
      logger.info("ServiceDaoImpl.getServiceConfigDetails");
      logger.info("serviceMode = [" + serviceMode + "], company = [" + company + "], service = [" + service + "]");
      List<ServiceConfigDetails> lst = null;
      if(service==null || service.equals("")|| service.equalsIgnoreCase("ALL"))
      {
         logger.debug("getServiceConfigDetailsAll = " + getServiceConfigDetailsAll);
         lst = serviceJdbcTemplate.query(getServiceConfigDetailsAll, new Object[]{serviceMode, company}, ParameterizedBeanPropertyRowMapper.newInstance(ServiceConfigDetails.class));
      }else{
         logger.debug("getServiceConfigDetails = " + getServiceConfigDetails);
         lst = serviceJdbcTemplate.query(getServiceConfigDetails, new Object[]{serviceMode, company, service}, ParameterizedBeanPropertyRowMapper.newInstance(ServiceConfigDetails.class));
      }
      return lst;
   }


   public List<ServiceConfigDetails> getServiceConfigDetails(String serviceMode, String company, List<String> service) throws SQLException
   {
      logger.info("ServiceDaoImpl.getServiceConfigDetails");
      logger.info("serviceMode = [" + serviceMode + "], company = [" + company + "], service = [" + service + "]");
      List<ServiceConfigDetails> lst = null;
      if(service==null || service.size()==0 || service.contains("ALL"))
      {
         logger.debug("getServiceConfigDetailsAll = " + getServiceConfigDetailsAll);
         lst = serviceJdbcTemplate.query(getServiceConfigDetailsAll, new Object[]{serviceMode, company}, ParameterizedBeanPropertyRowMapper.newInstance(ServiceConfigDetails.class));
      }else if(service.size()>0){
         logger.debug("getServiceConfigDetailsWherClause = " + getServiceConfigDetailsWherClause);
         NamedParameterJdbcTemplate npjt=new NamedParameterJdbcTemplate(serviceJdbcTemplate);
//         lst = serviceJdbcTemplate.query(getServiceConfigDetailsWherClause, new Object[]{serviceMode, company, Collections.singletonMap("names", service)},  ParameterizedBeanPropertyRowMapper.newInstance(ServiceConfigDetails.class));
         lst = npjt.query("select * from vw_service_config_details where mode ='"+serviceMode+"' and company='"+company+"' and service in (:names)", Collections.singletonMap("names", service), ParameterizedBeanPropertyRowMapper.newInstance(ServiceConfigDetails.class));
      }
      return lst;
   }

   private String createWhereClause(List<String> lstString){
      StringBuilder whereClause=new StringBuilder();
      Iterator<String> itr=lstString.iterator();
      int size=lstString.size();
      int curPos=1;
      while(itr.hasNext()){

         String str=itr.next();
         System.out.println("str = " + str);
         whereClause.append("'"+str+"'");
         if(curPos<size){
            whereClause.append(",");
         }
         curPos++;
      }
      return whereClause.toString();
   }

   public List<ServiceOperationDetails> getServiceOperationDetails(String serviceMode, String company) throws SQLException
   {
      logger.info("ServiceDaoImpl.getServiceOperationDetails");
      logger.info("serviceMode = [" + serviceMode + "], company = [" + company + "]");
      logger.debug("getServiceOperationDetails = "+ getServiceOperationDetails);
      List<ServiceOperationDetails> lst = null;
      lst = serviceJdbcTemplate.query(getServiceOperationDetails, new Object[]{company}, ParameterizedBeanPropertyRowMapper.newInstance(ServiceOperationDetails.class));
      return lst;
   }

   public List<ServiceOperationDetails> getServiceOperationDetails(String serviceMode, String company, List<String> service) throws SQLException
   {
      logger.info("ServiceDaoImpl.getServiceOperationDetails");
      logger.info("serviceMode = [" + serviceMode + "], company = [" + company + "]");
      logger.debug("getServiceOperationDetails = "+ getServiceOperationDetails);
      List<ServiceOperationDetails> lst = null;
      if(service==null || service.size()==0 || service.contains("ALL"))
      {
         logger.debug("getServiceConfigDetailsAll = " + getServiceConfigDetailsAll);
         lst = serviceJdbcTemplate.query(getServiceOperationDetails, new Object[]{company}, ParameterizedBeanPropertyRowMapper.newInstance(ServiceOperationDetails.class));
      }else if(service.size()>0){
         logger.debug("getServiceConfigDetailsWherClause = " + getServiceConfigDetailsWherClause);
         NamedParameterJdbcTemplate npjt=new NamedParameterJdbcTemplate(serviceJdbcTemplate);
//         lst = serviceJdbcTemplate.query(getServiceConfigDetailsWherClause, new Object[]{serviceMode, company, Collections.singletonMap("names", service)},  ParameterizedBeanPropertyRowMapper.newInstance(ServiceConfigDetails.class));
         lst = npjt.query("select * from vw_service_details where company='"+company+"' and serviceStatus='A' and operationStatus='A' and service in (:names)", Collections.singletonMap("names", service), ParameterizedBeanPropertyRowMapper.newInstance(ServiceOperationDetails.class));
         //select * from vw_service_details where company =? and serviceStatus='A' and operationStatus='A' order by service, vendor
      }

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
   public Map<String, List<DCTemplateMapping>> getDCTemplateMapping(String serviceMode, String company, String tempCode) throws SQLException
   {
      logger.info("ServiceDaoImpl.getDCTemplateMapping");
      logger.info("serviceMode = [" + serviceMode + "], company = [" + company + "], tempCode = [" + tempCode + "]");
      logger.debug("getDCTemplateMapping = "+ getDCTemplateMapping);
      Map<String,DCTemplateMapping> map=null;
      List<DCTemplateMapping> lst = null;
      lst = serviceJdbcTemplate.query(getDCTemplateMapping, new Object[]{tempCode}, ParameterizedBeanPropertyRowMapper.newInstance(DCTemplateMapping.class));
//      if(lst !=null && lst.size()>0){
//         map=new HashMap<>();
//         Iterator<DCTemplateMapping> itr=lst.iterator();
//         while(itr.hasNext()){
//            DCTemplateMapping dctm=(DCTemplateMapping)itr.next();
//            map.put(dctm.getTempCode(),dctm);
//         }
//      }

      Map<String, List<DCTemplateMapping>> clientWiseMapping = new LinkedHashMap<>();
      List<DCTemplateMapping> listOfColumn = null;
      String role = null;//, tab = null;
      DCTemplateMapping dcTemplateMapping = null;
      Iterator<DCTemplateMapping> itr=lst.iterator();
      while (itr.hasNext())
      {
         dcTemplateMapping = (DCTemplateMapping) itr.next();
         if (role == null)
         {
            role = dcTemplateMapping.getRole();

            listOfColumn = new ArrayList<DCTemplateMapping>();
            listOfColumn.add(dcTemplateMapping);

         }
         else if (dcTemplateMapping.getRole().equalsIgnoreCase(role))
         {
            listOfColumn.add(dcTemplateMapping);
         }
         else if (!dcTemplateMapping.getRole().equalsIgnoreCase(role))
         {
            clientWiseMapping.put(role, listOfColumn);

            role = dcTemplateMapping.getRole();
            listOfColumn = new ArrayList<DCTemplateMapping>();
            listOfColumn.add(dcTemplateMapping);
         }
         //System.out.println("servDetails = " + servDetails);

      }
      if (clientWiseMapping != null)
      {
         clientWiseMapping.put(role, listOfColumn);
      }
      return clientWiseMapping;
   }

   @Override
   public Map<String,DCDocumentDetails> getDCDocumentDetails(String serviceMode, String company) throws SQLException
   {
      logger.info("ServiceDaoImpl.getDCDocumentDetails");
      logger.info("serviceMode = [" + serviceMode + "], company = [" + company + "]");
      logger.debug("getDCDocumentDetails = "+ getDCDocumentDetails);
      Map<String,DCDocumentDetails> map=null;
      List<DCDocumentDetails> lst = null;
      lst = serviceJdbcTemplate.query(getDCDocumentDetails, new Object[]{company}, ParameterizedBeanPropertyRowMapper.newInstance(DCDocumentDetails.class));
      if(lst !=null && lst.size()>0){
         map=new LinkedHashMap<>();
         Iterator<DCDocumentDetails> itr=lst.iterator();
         while(itr.hasNext()){
            DCDocumentDetails dct=(DCDocumentDetails)itr.next();
            map.put(dct.getDocCode(),dct);
         }
      }
      return map;
   }

   @Override
   public Map<String, List<DCDocumentMapping>> getDCDocumentMapping(String serviceMode, String company, String tempCode) throws SQLException
   {
      logger.info("ServiceDaoImpl.getDCDocumentMapping");
      logger.info("serviceMode = [" + serviceMode + "], company = [" + company + "], tempCode = [" + tempCode + "]");
      logger.debug("getDCDocumentMapping = "+ getDCDocumentMapping);
      Map<String,DCDocumentMapping> map=null;
      List<DCDocumentMapping> lst = null;
      lst = serviceJdbcTemplate.query(getDCDocumentMapping, new Object[]{tempCode}, ParameterizedBeanPropertyRowMapper.newInstance(DCDocumentMapping.class));

      Map<String, List<DCDocumentMapping>> clientWiseMapping = new LinkedHashMap<>();
      List<DCDocumentMapping> listOfColumn = null;
      String role = null;//, tab = null;
      DCDocumentMapping dcDocumentMapping = null;
      Iterator<DCDocumentMapping> itr=lst.iterator();
      while (itr.hasNext())
      {
         dcDocumentMapping = (DCDocumentMapping) itr.next();
         if (role == null)
         {
            role = dcDocumentMapping.getRole();

            listOfColumn = new ArrayList<DCDocumentMapping>();
            listOfColumn.add(dcDocumentMapping);

         }
         else if (dcDocumentMapping.getRole().equalsIgnoreCase(role))
         {
            listOfColumn.add(dcDocumentMapping);
         }
         else if (!dcDocumentMapping.getRole().equalsIgnoreCase(role))
         {
            clientWiseMapping.put(role, listOfColumn);

            role = dcDocumentMapping.getRole();
            listOfColumn = new ArrayList<DCDocumentMapping>();
            listOfColumn.add(dcDocumentMapping);
         }
         //System.out.println("servDetails = " + servDetails);

      }
      if (clientWiseMapping != null)
      {
         clientWiseMapping.put(role, listOfColumn);
      }
      return clientWiseMapping;
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



   public List<ServiceConfigDetails> getServiceConfigDetails() throws SQLException
{
   logger.info("ServiceDaoImpl.getServiceConfigDetailsNew");
   List<ServiceConfigDetails> lst = null;

   logger.debug("getServiceConfigDetailsNew = " + getServiceConfigDetailsNew);
   lst = serviceJdbcTemplate.query(getServiceConfigDetailsNew, ParameterizedBeanPropertyRowMapper.newInstance(ServiceConfigDetails.class));

   return lst;
}


   public List<ExceptionExternal> getExceptionExternalDetails(String service, String vendor) throws SQLException
   {
      logger.info("ServiceDaoImpl.getExceptionExternalDetails");
      List<ExceptionExternal> lst = null;

      logger.debug("getExceptionExternalDetails = " + getExceptionExternalDetails);
      lst = serviceJdbcTemplate.query(getExceptionExternalDetails, new Object[]{service,vendor}, ParameterizedBeanPropertyRowMapper.newInstance(ExceptionExternal.class));

      return lst;
   }

   public List<ExceptionInternal> getExceptionInternalDetails() throws SQLException
   {
      logger.info("ServiceDaoImpl.getExceptionInternalDetails");
      List<ExceptionInternal> lst = null;

      logger.debug("getExceptionInternalDetails = " + getExceptionInternalDetails);
      lst = serviceJdbcTemplate.query(getExceptionInternalDetails, ParameterizedBeanPropertyRowMapper.newInstance(ExceptionInternal.class));

      return lst;
   }
   SQLData convert = new SQLData();
   public Object getCommonDetails(String product, String service, String type, String infoType) throws SQLException
   {
      System.out.println("ServiceDaoImpl.getCommonDetails");
      try
      {
         ServiceSP serviceSP = new ServiceSP(serviceJdbcTemplate,"sel_service_details", 0);
         try
         {
            Map outMap = serviceSP.getCommonDetails(product,service, type, infoType);
            if (outMap != null)
            {
               if(service.equalsIgnoreCase(Constant.SERVICES.TRADE_PROCESS.toString()) && type.equalsIgnoreCase(Constant.SERVICES_DETAILS.COMMON_DETAILS.toString()) && infoType.equalsIgnoreCase(Constant.COMMON_DETAILS.TRADE_FILE_DETAILS.toString()))
               {
                  ArrayList<Map<String, Object>> rows = (ArrayList<Map<String, Object>>) outMap.get("#result-set-1");
                  if (rows != null)
                  {
                     return getTradeFileDetails(rows);
                  }
               }else if(service.equalsIgnoreCase(Constant.SERVICES.DOCUSIGN_SERVICES.toString())&& type.equalsIgnoreCase(Constant.SERVICES_DETAILS.COMMON_DETAILS.toString()) && infoType.equalsIgnoreCase(Constant.COMMON_DETAILS.DOCUSIGN_MAPPING.toString()))
               {
                  ArrayList<Map<String, Object>> rows = (ArrayList<Map<String, Object>>) outMap.get("#result-set-1");
                  if (rows != null)
                  {
                     return getDocuSignMappingDetails(rows);
                  }
               }else if(service.equalsIgnoreCase(Constant.SERVICES.DOCUSIGN_SERVICES.toString())&& type.equalsIgnoreCase(Constant.SERVICES_DETAILS.ADDITIONAL_DETAILS.toString()) && infoType.equalsIgnoreCase(Constant.ADDITIONAL_DETAILS.TEMPLATE_DETAILS.toString()))
               {
                  ArrayList<Map<String, Object>> rows = (ArrayList<Map<String, Object>>) outMap.get("#result-set-1");
                  if (rows != null)
                  {
                     return getDocuSignTemplateDetails(rows);
                  }
               }else if(type.equalsIgnoreCase(Constant.SERVICES_DETAILS.OPERATION_DETAILS.toString()))
               {
                  ArrayList<Map<String, Object>> rows = (ArrayList<Map<String, Object>>) outMap.get("#result-set-1");
                  if (rows != null)
                  {
                     return getOperationDetails(rows);
                  }
               }
            }
         }
         catch (Exception ex) {
            ex.printStackTrace();
         }
      }catch (Exception e){
         logger.error("Issue while storing web request in DB :"+e.getMessage());
      }

      return null;
   }

   private HashMap<String, HashMap<String,DCTemplateDetails>> getDocuSignTemplateDetails(ArrayList<Map<String, Object>> rows){
      HashMap<String, HashMap<String,DCTemplateDetails>> rs=null;
      try{
         Iterator<Map<String, Object>> itr = rows.iterator();
         rs=new HashMap<>();
         HashMap<String,DCTemplateDetails> tempMap = null;
         String mode = null;
         while (itr.hasNext())
         {
            Map<String, Object> map = itr.next();
            if (mode == null)
            {
               mode = convert.getStrData(map.get("mode"));
               tempMap = new HashMap<>();
               tempMap.put(convert.getStrData(map.get("tempCode")),
                           new DCTemplateDetails(convert.getStrData(map.get("mode")),
                                                convert.getStrData(map.get("company")),
                                                convert.getStrData(map.get("service")),
                                                convert.getStrData(map.get("tempCode")),
                                                convert.getStrData(map.get("tempId")),
                                                convert.getStrData(map.get("tempName")),
                                                convert.getStrData(map.get("status")),
                                                convert.getStrData(map.get("remark")),
                                                convert.getStrData(map.get("authRequired"))
               ));
            }
            else if (mode.equalsIgnoreCase(convert.getStrData(map.get("mode"))))
            {
               tempMap.put(convert.getStrData(map.get("tempCode")),
                           new DCTemplateDetails(convert.getStrData(map.get("mode")),
                                                 convert.getStrData(map.get("company")),
                                                 convert.getStrData(map.get("service")),
                                                 convert.getStrData(map.get("tempCode")),
                                                 convert.getStrData(map.get("tempId")),
                                                 convert.getStrData(map.get("tempName")),
                                                 convert.getStrData(map.get("status")),
                                                 convert.getStrData(map.get("remark")),
                                                 convert.getStrData(map.get("authRequired"))
                           ));
            }
            else if (!mode.equalsIgnoreCase(convert.getStrData(map.get("mode"))))
            {

               rs.put(mode, tempMap);
               mode = convert.getStrData(map.get("mode"));
               tempMap = new HashMap<>();

               tempMap.put(convert.getStrData(map.get("tempCode")),
                           new DCTemplateDetails(convert.getStrData(map.get("mode")),
                                                 convert.getStrData(map.get("company")),
                                                 convert.getStrData(map.get("service")),
                                                 convert.getStrData(map.get("tempCode")),
                                                 convert.getStrData(map.get("tempId")),
                                                 convert.getStrData(map.get("tempName")),
                                                 convert.getStrData(map.get("status")),
                                                 convert.getStrData(map.get("remark")),
                                                 convert.getStrData(map.get("authRequired"))
                           ));
            }
         }
         if (tempMap != null)
         {
            rs.put(mode, tempMap);
         }
      }catch(Exception e)
      {
         e.printStackTrace();
      }
      return rs;

   }
   private LinkedHashMap<String, LinkedHashMap<String, List<DCTemplateMapping>>> getDocuSignMappingDetails(ArrayList<Map<String, Object>> rows){
      LinkedHashMap<String, LinkedHashMap<String, List<DCTemplateMapping>>> rs=null;
      LinkedHashMap<String, List<DCTemplateMapping>> tempMap=null;
      try{
         Iterator<Map<String, Object>> itr = rows.iterator();
         rs = new LinkedHashMap<>();
         List<DCTemplateMapping> mapLst = null;
         String tempCode = null;
         String role = null;
         while (itr.hasNext())
         {
            Map<String, Object> map = itr.next();
            if (tempCode == null && role==null)
            {
               tempCode = convert.getStrData(map.get("tempCode"));
               role=convert.getStrData(map.get("role"));

               tempMap=new LinkedHashMap<>();
               mapLst = new ArrayList<>();
               mapLst.add(new DCTemplateMapping(convert.getStrData(map.get("tempCode")),
                                                convert.getStrData(map.get("tab")),
                                                convert.getStrData(map.get("lable")),
                                                convert.getStrData(map.get("dbColumn")),
                                                convert.getStrData(map.get("role")),
                                                convert.getStrData(map.get("isDisabled"))
               ));
            }
            else if (tempCode.equalsIgnoreCase(convert.getStrData(map.get("tempCode"))))
            {
               if(role.equalsIgnoreCase(convert.getStrData(map.get("role")))){

                  mapLst.add(new DCTemplateMapping(convert.getStrData(map.get("tempCode")),
                                                   convert.getStrData(map.get("tab")),
                                                   convert.getStrData(map.get("lable")),
                                                   convert.getStrData(map.get("dbColumn")),
                                                   convert.getStrData(map.get("role")),
                                                   convert.getStrData(map.get("isDisabled"))
                  ));

               }else{
                  tempMap.put(role,mapLst);

                  role=convert.getStrData(map.get("role"));
                  mapLst = new ArrayList<>();
                  mapLst.add(new DCTemplateMapping(convert.getStrData(map.get("tempCode")),
                                                   convert.getStrData(map.get("tab")),
                                                   convert.getStrData(map.get("lable")),
                                                   convert.getStrData(map.get("dbColumn")),
                                                   convert.getStrData(map.get("role")),
                                                   convert.getStrData(map.get("isDisabled"))
                  ));
               }
            }
            else if (!tempCode.equalsIgnoreCase(convert.getStrData(map.get("tempCode"))))
            {
               tempMap.put(role,mapLst);
               rs.put(tempCode, tempMap);

               tempCode = convert.getStrData(map.get("tempCode"));
               role=convert.getStrData(map.get("role"));
               tempMap=new LinkedHashMap<>();
               mapLst = new ArrayList<>();

               mapLst.add(new DCTemplateMapping(convert.getStrData(map.get("tempCode")),
                                                convert.getStrData(map.get("tab")),
                                                convert.getStrData(map.get("lable")),
                                                convert.getStrData(map.get("dbColumn")),
                                                convert.getStrData(map.get("role")),
                                                convert.getStrData(map.get("isDisabled"))
               ));
            }
         }
         if (mapLst != null)
         {
            tempMap.put(role,mapLst);
            rs.put(tempCode, tempMap);
         }
      }catch(Exception e)
      {
         e.printStackTrace();
      }
      return rs;

   }
   private List<TradeProcessFile> getTradeFileDetails(ArrayList<Map<String, Object>> rows){
      List<TradeProcessFile> rs=null;
      try
      {
         Iterator<Map<String, Object>> itr = rows.iterator();
         rs = new ArrayList<>();
         while (itr.hasNext())
         {
            Map<String, Object> map = itr.next();

            rs.add(new TradeProcessFile(convert.getStrData(map.get("vendor")),
                                        convert.getStrData(map.get("fileName")),
                                        convert.getStrData(map.get("fileType")),
                                        convert.getStrData(map.get("fileExtension")),
                                        convert.getStrData(map.get("delimeter")),
                                        convert.getStrData(map.get("containsHeader")),
                                        convert.getStrData(map.get("active")),
                                        convert.getIntData(map.get("seqNum")),
                                        convert.getStrData(map.get("uploadDir")),
                                        convert.getStrData(map.get("dbStoredProc")),
                                        convert.getStrData(map.get("preInstruction")),
                                        convert.getStrData(map.get("postInstruction"))
            ));
         }
      }catch(Exception e)
      {
         e.printStackTrace();
      }
      return rs;
   }
   private LinkedHashMap<String,ServiceOperationDetails> getOperationDetails(ArrayList<Map<String, Object>> rows){
      LinkedHashMap<String,ServiceOperationDetails> rs=null;
      try
      {
         Iterator<Map<String, Object>> itr = rows.iterator();
         rs = new LinkedHashMap<String,ServiceOperationDetails>();
         while (itr.hasNext())
         {
            Map<String, Object> map = itr.next();

            rs.put(convert.getStrData(map.get("operation")),new ServiceOperationDetails(convert.getStrData(map.get("company")),
                                               convert.getStrData(map.get("service")),
                                               convert.getStrData(map.get("serviceStatus")),
                                               convert.getStrData(map.get("operation")),
                                               convert.getStrData(map.get("vendor")),
                                               convert.getStrData(map.get("operationStatus")),
                                               convert.getStrData(map.get("refValue")),
                                               convert.getIntData(map.get("priority"))
            ));
         }
      }catch(Exception e)
      {
         e.printStackTrace();
      }
      return rs;
   }

   public List<Object> getListDBObject(List<String> dbParam, ArrayList<Map<String, Object>> rows){
      List<Object> lst=null;
      Iterator<String> itr=dbParam.iterator();
      while(itr.hasNext()){

      }

      return lst;
   }


   public static List<String> getFieldNames(final Class c1, boolean publicOnly)
      throws IllegalArgumentException,IllegalAccessException
   {
      StringBuilder sb=new StringBuilder();
      List<String> lst = new ArrayList<String>();
      Field[] fields = c1.getDeclaredFields();
      for (int i = 0; i < fields.length; i++) {
         String name = fields[i].getName();
         if (publicOnly) {
            if(Modifier.isPublic(fields[i].getModifiers())) {

                  lst.add(name);}
         }
         else {
            fields[i].setAccessible(true);

               lst.add(name);}
      }
//      System.out.println("Avoided properties of "+c1+" due to empty or null value : (" + sb + ")");
      logger.info("Properties for further process = " + lst);
      return lst;
   }

}





/* Tab wise Mapping COde
*
*
*
*
*    public Map<String, Map<String, List<DCTemplateMapping>>> getDCTemplateMapping(String serviceMode, String company, String tempCode) throws SQLException
   {
      logger.info("ServiceDaoImpl.getDCTemplateMapping");
      logger.info("serviceMode = [" + serviceMode + "], company = [" + company + "], tempCode = [" + tempCode + "]");
      logger.debug("getDCTemplateMapping = "+ getDCTemplateMapping);
      Map<String,DCTemplateMapping> map=null;
      List<DCTemplateMapping> lst = null;
      lst = serviceJdbcTemplate.query(getDCTemplateMapping, new Object[]{tempCode}, ParameterizedBeanPropertyRowMapper.newInstance(DCTemplateMapping.class));
//      if(lst !=null && lst.size()>0){
//         map=new HashMap<>();
//         Iterator<DCTemplateMapping> itr=lst.iterator();
//         while(itr.hasNext()){
//            DCTemplateMapping dctm=(DCTemplateMapping)itr.next();
//            map.put(dctm.getTempCode(),dctm);
//         }
//      }

      Map<String, Map<String, List<DCTemplateMapping>>> dcTStringMapMap = new LinkedHashMap<String, Map<String, List<DCTemplateMapping>>>();
      Map<String, List<DCTemplateMapping>> apiDetails = null;
      List<DCTemplateMapping> listOfOperation = null;
      String role = null, tab = null;
      DCTemplateMapping dcTemplateMapping = null;
      Iterator<DCTemplateMapping> itr=lst.iterator();
      while (itr.hasNext())
      {
         dcTemplateMapping = (DCTemplateMapping) itr.next();
//            System.out.println("servDetails = " + servDetails);
         if (role == null)
         {
            role = dcTemplateMapping.getRole();
            tab = dcTemplateMapping.getTab();
            apiDetails = new LinkedHashMap<String, List<DCTemplateMapping>>();

            listOfOperation = new ArrayList<DCTemplateMapping>();
            listOfOperation.add(dcTemplateMapping);

         }
         else if (dcTemplateMapping.getRole().equalsIgnoreCase(role))
         {
            if (dcTemplateMapping.getTab().equalsIgnoreCase(tab))
            {
               listOfOperation.add(dcTemplateMapping);
            }
            else if (!dcTemplateMapping.getTab().equalsIgnoreCase(tab))
            {

               apiDetails.put(tab, listOfOperation);

               tab = dcTemplateMapping.getTab();
               listOfOperation = new ArrayList<DCTemplateMapping>();
               listOfOperation.add(dcTemplateMapping);
            }

         }
         else if (!dcTemplateMapping.getRole().equalsIgnoreCase(role))
         {
            apiDetails.put(tab, listOfOperation);
            dcTStringMapMap.put(role, apiDetails);

            role = dcTemplateMapping.getRole();

            tab = dcTemplateMapping.getTab();
            apiDetails = new LinkedHashMap<String, List<DCTemplateMapping>>();
            listOfOperation = new ArrayList<DCTemplateMapping>();
            listOfOperation.add(dcTemplateMapping);


         }
         //System.out.println("servDetails = " + servDetails);

      }
      if (apiDetails != null)
      {
         apiDetails.put(tab, listOfOperation);
         dcTStringMapMap.put(role, apiDetails);
      }


      return dcTStringMapMap;
   }


   */