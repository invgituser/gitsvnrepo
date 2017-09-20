package com.invessence.crm.provider.redTail.bean;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.invessence.crm.bean.UserProfile;

/**
 * Created by abhangp on 11/22/2016.
 */
@JsonRootName("user")
public class User implements Serializable
{
   private String userId;
   private String password;
   private String apiKey;
   private String userKey;
   public User()
   {
   }

   public User(String userId, String password, String apiKey, String userKey)
   {
      this.userId = userId;
      this.password = password;
      this.apiKey = apiKey;
      this.userKey = userKey;
   }

   public String getUserId()
   {
      return userId;
   }

   public void setUserId(String userId)
   {
      this.userId = userId;
   }

   public String getPassword()
   {
      return password;
   }

   public void setPassword(String password)
   {
      this.password = password;
   }

   public String getApiKey()
   {
      return apiKey;
   }

   public void setApiKey(String apiKey)
   {
      this.apiKey = apiKey;
   }

   public String getUserKey()
   {
      return userKey;
   }

   public void setUserKey(String userKey)
   {
      this.userKey = userKey;
   }

   @Override
   public String toString()
   {
      return "User{" +
         "userId='" + userId + '\'' +
         ", password='" + password + '\'' +
         ", apiKey='" + apiKey + '\'' +
         ", userKey='" + userKey + '\'' +
         '}';
   }

   //   public UserProfile convertToAggrBean(UserProfile userProfile)
//   {
//      userProfile.setUserName(this.getId());
//      userProfile.setGuid(this.getGuid());
//      userProfile.setDisabled(this.getIs_disabled());
//      userProfile.setLoggedInAt(this.getLogged_in_at());
//      return userProfile;
//
////      return new UserProfile(this.getId(), this.getCredit_score(), this.getEmail(), this.getFirst_name()
////         , this.getGuid(), this.getIs_disabled(), this.getLast_name(), this.getMetadata(), this.getPhone(), this.getZip_code(),
////                             this.getBirthdate(), this.getGender(), this.getLogged_in_at());
//   }
}
