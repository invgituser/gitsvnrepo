package com.invessence.crm.provider.salesFource;

import com.invessence.crm.bean.*;
import com.invessence.crm.service.CRMService;
import com.invessence.service.bean.*;
import org.springframework.stereotype.Service;

/**
 * Created by abhangp on 11/25/2016.
 */
@Service("SalesForceCRM")
public class CRMServiceSalesFourceImpl implements CRMService
{
   @Override
   public WSCallResult authentication(ServiceRequest serviceRequest, Object object) throws Exception
   {
      return null;
   }

   @Override
   public WSCallResult ssoLogin(ServiceRequest serviceRequest, Object object) throws Exception
   {
      return null;
   }
}
