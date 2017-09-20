package com.invessence.aggr.provider.mx.bean;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.invessence.aggr.bean.*;
import com.invessence.aggr.service.IConvertToAggrBean;

/**
 * Created by abhangp on 11/22/2016.
 */
@JsonRootName("user")
public class User implements Serializable
{
   private String id;
   private Integer credit_score ;
   private String email;
   private String first_name ;
   private String guid ;
   private Boolean is_disabled;
   private String last_name;
   private String metadata ;
   private String phone;
   private String zip_code ;
   private String birthdate;
   private String gender ;
   private Integer logged_in_at;

   public User()
   {
   }

   public User(String id, Integer credit_score, String email, String first_name, String last_name, String metadata, String phone, String zip_code, String birthdate, String gender)
   {
      this.id = id;
      this.credit_score = credit_score;
      this.email = email;
      this.first_name = first_name;
      this.last_name = last_name;
      this.metadata = metadata;
      this.phone = phone;
      this.zip_code = zip_code;
      this.birthdate = birthdate;
      this.gender = gender;
   }


   public String getId()
   {
      return id;
   }

   public void setId(String id)
   {
      this.id = id;
   }

   public String getEmail()
   {
      return email;
   }

   public void setEmail(String email)
   {
      this.email = email;
   }

   public String getFirst_name()
   {
      return first_name;
   }

   public void setFirst_name(String first_name)
   {
      this.first_name = first_name;
   }

   public String getLast_name()
   {
      return last_name;
   }

   public void setLast_name(String last_name)
   {
      this.last_name = last_name;
   }

   public String getPhone()
   {
      return phone;
   }

   public void setPhone(String phone)
   {
      this.phone = phone;
   }

   public String getBirthdate()
   {
      return birthdate;
   }

   public void setBirthdate(String birthdate)
   {
      this.birthdate = birthdate;
   }

   public String getGender()
   {
      return gender;
   }

   public void setGender(String gender)
   {
      this.gender = gender;
   }

   public String getMetadata()
   {
      return metadata;
   }

   public void setMetadata(String metadata)
   {
      this.metadata = metadata;
   }

   public Integer getCredit_score()
   {
      return credit_score;
   }

   public void setCredit_score(Integer credit_score)
   {
      this.credit_score = credit_score;
   }

   public String getGuid()
   {
      return guid;
   }

   public void setGuid(String guid)
   {
      this.guid = guid;
   }

   public Boolean getIs_disabled()
   {
      return is_disabled;
   }

   public void setIs_disabled(Boolean is_disabled)
   {
      this.is_disabled = is_disabled;
   }

   public Integer getLogged_in_at()
   {
      return logged_in_at;
   }

   public void setLogged_in_at(Integer logged_in_at)
   {
      this.logged_in_at = logged_in_at;
   }

   public String getZip_code()
   {
      return zip_code;
   }

   public void setZip_code(String zip_code)
   {
      this.zip_code = zip_code;
   }

   @Override
   public String toString()
   {
      return "User{" +
         "id='" + id + '\'' +
         ", credit_score=" + credit_score +
         ", email='" + email + '\'' +
         ", first_name='" + first_name + '\'' +
         ", guid='" + guid + '\'' +
         ", is_disabled=" + is_disabled +
         ", last_name='" + last_name + '\'' +
         ", metadata='" + metadata + '\'' +
         ", phone='" + phone + '\'' +
         ", zip_code='" + zip_code + '\'' +
         ", birthdate='" + birthdate + '\'' +
         ", gender='" + gender + '\'' +
         ", logged_in_at=" + logged_in_at +
         '}';
   }

   public UserProfile convertToAggrBean( UserProfile userProfile)
   {
      userProfile.setUserName(this.getId());
      userProfile.setGuid(this.getGuid());
      userProfile.setDisabled(this.getIs_disabled());
      userProfile.setLoggedInAt(this.getLogged_in_at());
      return userProfile;

//      return new UserProfile(this.getId(), this.getCredit_score(), this.getEmail(), this.getFirst_name()
//         , this.getGuid(), this.getIs_disabled(), this.getLast_name(), this.getMetadata(), this.getPhone(), this.getZip_code(),
//                             this.getBirthdate(), this.getGender(), this.getLogged_in_at());
   }
}
