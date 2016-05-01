package com.invessence.service.gemini;

import javax.faces.bean.ManagedProperty;

import com.invessence.ws.service.ServiceLayer;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 4/11/16
 * Time: 10:13 PM
 * To change this template use File | Settings | File Templates.
 */
public class AccountDetails
{

   @ManagedProperty("#{serviceLayer}")
   private ServiceLayer serviceLayer;
   public void setServiceLayer(ServiceLayer serviceLayer)
   {
      this.serviceLayer = serviceLayer;
   }



}
