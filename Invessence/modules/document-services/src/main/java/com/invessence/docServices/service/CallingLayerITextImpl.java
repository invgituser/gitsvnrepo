package com.invessence.docServices.service;

import java.util.*;

import com.invessence.docServices.iText.pdf.*;
import com.invessence.service.bean.*;
import com.invessence.service.bean.documentServices.iText.PDFFileRules;
import com.invessence.service.util.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.invessence.custody.uob.*;

/**
 * Created by abhangp on 3/11/2016.
 */
@Service("itextDocService")
public class CallingLayerITextImpl implements CallingLayer
{
   @Autowired
   UOBPDFWriter uobPDFWriter;
   private static final Logger logger = Logger.getLogger(CallingLayerITextImpl.class);
   @Override
   public WSCallResult createDoc(ServiceRequest serviceRequest, Object dataObject)
   {

      Map<String,Map<String, List<PDFFileRules>>> pdfFileRulesDetails=(Map<String,Map<String, List<PDFFileRules>>>) ServiceDetails.getCommonDetails(serviceRequest.getProduct(), Constant.SERVICES.DOCUMENT_SERVICES.toString(), Constant.COMMON_DETAILS.PDF_FILE_RULES.toString());
      uobPDFWriter.writPDF(pdfFileRulesDetails.get("UOb_ACCT_OPEN_NEW_CLIENT"), dataObject);

//      Map<String,Country> countryDetails=(Map<String,Country>) ServiceDetails.genericDetails.get(Constant.GENERIC_DETAILS.COUNTRY.toString());
//      Map<String, Object> data=null;
//      try
//      {
//         data= Reflection.getFieldNamesAndValues(object, false);
//
//      }
//      catch (IllegalAccessException e)
//      {
//         e.printStackTrace();
//      }
      return null;
   }

   @Override
   public WSCallResult sendDoc(ServiceRequest serviceRequest, Object object)
   {
      return null;
   }
}
