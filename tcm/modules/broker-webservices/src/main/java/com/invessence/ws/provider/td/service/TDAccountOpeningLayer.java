package com.invessence.ws.provider.td.service;

import java.util.List;

import com.docusign.esign.model.CompositeTemplate;
import com.invessence.ws.provider.td.bean.DCRequest;

/**
 * Created by abhangp on 8/17/2016.
 */
public interface TDAccountOpeningLayer
{
   public CompositeTemplate docuSignRequestHandler(List<DCRequest> dcRequests);
}
