package com.invessence.aggr.model;

import java.io.Serializable;
import javax.persistence.*;

import com.invessence.aggr.bean.*;

/**
 * Created by abhangp on 11/22/2016.
 */
@Entity
@Table(name = "aggr_user_logon", catalog = "invdb")
public class UserProfile implements Serializable
{
   private Long id;
   private String userName;
   private String pwd;
   private Integer creditScore ;
   private String email;
   private String firstName ;
   private String guid ;
   private Boolean isDisabled;
   private String lastName;
   private String metadata ;
   private String phone;
   private String zipCode ;
   private String birthDate;
   private String gender ;
   private Integer loggedInAt;
   private Status errorStatus;
   private Credentials credentials;

   @Id
   @GeneratedValue
   @Column(name = "id", unique = true, nullable = false)
   public Long getId()
   {
      return id;
   }

   public void setId(Long id)
   {
      this.id = id;
   }

   public String getUserName()
   {
      return userName;
   }

   public void setUserName(String userName)
   {
      this.userName = userName;
   }

   public String getPwd()
   {
      return pwd;
   }

   public void setPwd(String pwd)
   {
      this.pwd = pwd;
   }

   public Integer getCreditScore()
   {
      return creditScore;
   }

   public void setCreditScore(Integer creditScore)
   {
      this.creditScore = creditScore;
   }

   public String getEmail()
   {
      return email;
   }

   public void setEmail(String email)
   {
      this.email = email;
   }

   public String getFirstName()
   {
      return firstName;
   }

   public void setFirstName(String firstName)
   {
      this.firstName = firstName;
   }

   public String getGuid()
   {
      return guid;
   }

   public void setGuid(String guid)
   {
      this.guid = guid;
   }

   public Boolean getDisabled()
   {
      return isDisabled;
   }

   public void setDisabled(Boolean disabled)
   {
      isDisabled = disabled;
   }

   public String getLastName()
   {
      return lastName;
   }

   public void setLastName(String lastName)
   {
      this.lastName = lastName;
   }

   public String getMetadata()
   {
      return metadata;
   }

   public void setMetadata(String metadata)
   {
      this.metadata = metadata;
   }

   public String getPhone()
   {
      return phone;
   }

   public void setPhone(String phone)
   {
      this.phone = phone;
   }

   public String getZipCode()
   {
      return zipCode;
   }

   public void setZipCode(String zipCode)
   {
      this.zipCode = zipCode;
   }

   public String getBirthDate()
   {
      return birthDate;
   }

   public void setBirthDate(String birthDate)
   {
      this.birthDate = birthDate;
   }

   public String getGender()
   {
      return gender;
   }

   public void setGender(String gender)
   {
      this.gender = gender;
   }

   public Integer getLoggedInAt()
   {
      return loggedInAt;
   }

   public void setLoggedInAt(Integer loggedInAt)
   {
      this.loggedInAt = loggedInAt;
   }

   public Status getErrorStatus()
   {
      return errorStatus;
   }

   public void setErrorStatus(Status errorStatus)
   {
      this.errorStatus = errorStatus;
   }

   public Credentials getCredentials()
   {
      return credentials;
   }

   public void setCredentials(Credentials credentials)
   {
      this.credentials = credentials;
   }
}
