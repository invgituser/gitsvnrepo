package com.invessence.ws.provider.td.docusign.web;

import com.invessence.service.bean.ServiceRequest;
import com.invessence.ws.bean.WSCallResult;

/**
 * Created by abhangp on 3/22/2016.
 */
public interface DCWebLayerClass
{
   public WSCallResult processDCRequest(ServiceRequest serviceRequest, Long acctNum, Integer eventNum)throws Exception;
}
