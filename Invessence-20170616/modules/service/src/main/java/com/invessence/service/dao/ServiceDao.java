package com.invessence.service.dao;

import java.sql.SQLException;
import java.util.*;

import com.invessence.service.bean.*;
import com.invessence.service.bean.docuSign.*;

/**
 * Created by abhangp on 1/19/2016.
 */
public interface ServiceDao
{
   public Map<String, SwitchDetails> getSwitchDetails();
   public List<ServiceConfigDetails> getServiceConfigDetails( List<String> service)throws SQLException;
   public List<ServiceConfigDetails> getServiceConfigDetails(String serviceMode, String company, String service)throws SQLException;
   public List<ServiceConfigDetails> getServiceConfigDetails(String serviceMode, String company, List<String> service)throws SQLException;
   public List<ServiceOperationDetails> getServiceOperationDetails(String serviceMode, String company)throws SQLException;
   public List<ServiceOperationDetails> getServiceOperationDetails(String serviceMode, String company, List<String> service)throws SQLException;
   public List<WebConfigDetails> getWebServiceDetails(String serviceMode, String company) throws SQLException;

   public Map<String,DCTemplateDetails> getDCTemplateDetails(String serviceMode, String company) throws SQLException;
   public Map<String, List<DCTemplateMapping>> getDCTemplateMapping(String serviceMode, String company, String tempCode) throws SQLException;


   public Map<String,DCDocumentDetails> getDCDocumentDetails(String serviceMode, String company) throws SQLException;
   public Map<String, List<DCDocumentMapping>> getDCDocumentMapping(String serviceMode, String company, String tempCode) throws SQLException;

   public List<LookupDetails> getLookupDetails() throws SQLException;

   public List<ExceptionExternal> getExceptionExternalDetails(String service, String vendor) throws SQLException;
   public List<ExceptionInternal> getExceptionInternalDetails() throws SQLException;

   public Object getCommonDetails(String product,String service, String type, String infoType) throws SQLException;;

}
