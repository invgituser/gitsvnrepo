package com.invessence.crm.service;

import com.invessence.crm.bean.*;
import com.invessence.service.bean.*;

/**
 * Created by abhangp on 11/30/2016.
 */
public interface CRMTraffic
{
   public WSCallResult authentication(ServiceRequest serviceRequest, Object object);
   public WSCallResult ssoLogin(ServiceRequest serviceRequest, Object object);
}
