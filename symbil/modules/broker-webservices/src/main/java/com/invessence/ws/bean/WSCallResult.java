package com.invessence.ws.bean;

/**
 * Created by abhangp on 3/22/2016.
 */
public class WSCallResult implements java.io.Serializable {
   private WSCallStatus WSCallStatus;
   private Object genericObject;

   public WSCallResult(com.invessence.ws.bean.WSCallStatus WSCallStatus, Object genericObject)
   {
      this.WSCallStatus = WSCallStatus;
      this.genericObject = genericObject;
   }

   public WSCallStatus getWSCallStatus()
   {
      return WSCallStatus;
   }

   public void setWSCallStatus(WSCallStatus WSCallStatus)
   {
      this.WSCallStatus = WSCallStatus;
   }

   public Object getGenericObject()
   {
      return genericObject;
   }

   public void setGenericObject(Object genericObject)
   {
      this.genericObject = genericObject;
   }

   @Override
   public String toString()
   {
      return "WSCallResult{" +
         "WSCallStatus=" + WSCallStatus +
         ", genericObject=" + genericObject +
         '}';
   }
}
