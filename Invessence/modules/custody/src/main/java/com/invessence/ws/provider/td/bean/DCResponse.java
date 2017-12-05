package com.invessence.ws.provider.td.bean;

/**
 * Created by abhangp on 3/16/2017.
 */
public class DCResponse
{

   private String envelopeId;
   private String errorCode;
   private String message;

   public DCResponse(String envelopeId, String errorCode, String message)
   {
      this.envelopeId = envelopeId;
      this.errorCode = errorCode;
      this.message = message;
   }

   public String getEnvelopeId()
   {
      return envelopeId;
   }

   public void setEnvelopeId(String envelopeId)
   {
      this.envelopeId = envelopeId;
   }

   public String getErrorCode()
   {
      return errorCode;
   }

   public void setErrorCode(String errorCode)
   {
      this.errorCode = errorCode;
   }

   public String getMessage()
   {
      return message;
   }

   public void setMessage(String message)
   {
      this.message = message;
   }

   @Override
   public String toString()
   {
      return "DCResponse{" +
         "envelopeId='" + envelopeId + '\'' +
         ", errorCode='" + errorCode + '\'' +
         ", message='" + message + '\'' +
         '}';
   }
}
