package com.invessence.crm.provider.redTail.bean;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by abhangp on 8/2/2017.
 */
public class AuthResponse  implements Serializable
{
   @JsonProperty
   private String APIKey;
   @JsonProperty
   private String CID ;
   @JsonProperty
   private String DatabaseID;
   @JsonProperty
   private String Message ;
   @JsonProperty
   private String Name;
   @JsonProperty
   private String ReturnURL ;
   @JsonProperty
   private String Status;
   @JsonProperty
   private String UserID;
   @JsonProperty
   private String UserKey ;

   public String getAPIKey()
   {
      return APIKey;
   }

   public void setAPIKey(String APIKey)
   {
      this.APIKey = APIKey;
   }

   public String getCID()
   {
      return CID;
   }

   public void setCID(String CID)
   {
      this.CID = CID;
   }

   public String getDatabaseID()
   {
      return DatabaseID;
   }

   public void setDatabaseID(String databaseID)
   {
      DatabaseID = databaseID;
   }

   public String getMessage()
   {
      return Message;
   }

   public void setMessage(String message)
   {
      Message = message;
   }

   public String getName()
   {
      return Name;
   }

   public void setName(String name)
   {
      Name = name;
   }

   public String getReturnURL()
   {
      return ReturnURL;
   }

   public void setReturnURL(String returnURL)
   {
      ReturnURL = returnURL;
   }

   public String getStatus()
   {
      return Status;
   }

   public void setStatus(String status)
   {
      Status = status;
   }

   public String getUserID()
   {
      return UserID;
   }

   public void setUserID(String userID)
   {
      UserID = userID;
   }

   public String getUserKey()
   {
      return UserKey;
   }

   public void setUserKey(String userKey)
   {
      UserKey = userKey;
   }

   @Override
   public String toString()
   {
      return "AuthResponse{" +
         "APIKey='" + APIKey + '\'' +
         ", CID='" + CID + '\'' +
         ", DatabaseID='" + DatabaseID + '\'' +
         ", Message='" + Message + '\'' +
         ", Name='" + Name + '\'' +
         ", ReturnURL='" + ReturnURL + '\'' +
         ", Status='" + Status + '\'' +
         ", UserID='" + UserID + '\'' +
         ", UserKey='" + UserKey + '\'' +
         '}';
   }
}
