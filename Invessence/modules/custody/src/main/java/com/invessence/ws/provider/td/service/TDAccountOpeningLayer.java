package com.invessence.ws.provider.td.service;

import java.util.List;

import com.docusign.esign.model.CompositeTemplate;
import com.invessence.service.bean.ServiceRequest;
import com.invessence.ws.bean.WSCallResult;
import com.invessence.ws.provider.td.bean.DCRequest;

/**
 * Created by abhangp on 8/17/2016.
 */
public interface TDAccountOpeningLayer
{
   public WSCallResult docuSignRequestHandler(List<DCRequest> dcRequests);
   public WSCallResult docuSignRequestHandler(ServiceRequest serviceRequest, List<DCRequest> dcRequests);
   public WSCallResult docuSignRequestHandler(ServiceRequest serviceRequest, List<DCRequest> dcRequests, Object object);
}
