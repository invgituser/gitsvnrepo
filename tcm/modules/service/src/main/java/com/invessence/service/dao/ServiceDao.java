package com.invessence.service.dao;

import java.sql.SQLException;
import java.util.*;

import com.invessence.service.bean.*;

/**
 * Created by abhangp on 1/19/2016.
 */
public interface ServiceDao
{
   public Map<String, SwitchDetails> getSwitchDetails();
   public List<ServiceConfigDetails> getServiceConfigDetails(String serviceMode, String company)throws SQLException;
   public List<ServiceDetails> getServiceOperationDetails(String serviceMode, String company)throws SQLException;
   public List<WebConfigDetails> getWebServiceDetails(String serviceMode, String company) throws SQLException;
}
