package com.invessence.docServices.service;

import com.invessence.service.bean.*;
import org.springframework.stereotype.Service;

/**
 * Created by abhangp on 3/11/2016.
 */

@Service("docusignDocService")
public class CallingLayerDocuSignImpl implements CallingLayer
{

   @Override
   public WSCallResult createDoc(ServiceRequest serviceRequest, Object object)
   {
      return null;
   }

   @Override
   public WSCallResult sendDoc(ServiceRequest serviceRequest, Object object)
   {
      return null;
   }
}
