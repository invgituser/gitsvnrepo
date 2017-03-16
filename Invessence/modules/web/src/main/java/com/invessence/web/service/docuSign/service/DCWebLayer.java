package com.invessence.web.service.docuSign.service;

import com.invessence.service.bean.ServiceRequest;
import com.invessence.ws.bean.WSCallResult;

/**
 * Created by abhangp on 3/22/2016.
 */
public interface DCWebLayer
{
   public WSCallResult processDCRequest(ServiceRequest serviceRequest, Long acctNum, Integer eventNum)throws Exception;
}
