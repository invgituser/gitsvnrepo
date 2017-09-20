package com.invessence.aggr.bean;

import com.invessence.aggr.provider.mx.bean.User;
import com.invessence.aggr.service.IConvertToMXBean;

/**
 * Created by abhangp on 11/22/2016.
 */
public class UserProfile implements IConvertToMXBean<User>
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
   private String mode;

   public UserProfile()
   {
   }

   public UserProfile(Long id, String userName, String pwd, Integer creditScore, String email, String firstName, String guid, Boolean isDisabled, String lastName, String metadata, String phone, String zipCode, String birthDate, String gender, Integer loggedInAt, Status errorStatus)
   {
      this.id = id;
      this.userName = userName;
      this.pwd = pwd;
      this.creditScore = creditScore;
      this.email = email;
      this.firstName = firstName;
      this.guid = guid;
      this.isDisabled = isDisabled;
      this.lastName = lastName;
      this.metadata = metadata;
      this.phone = phone;
      this.zipCode = zipCode;
      this.birthDate = birthDate;
      this.gender = gender;
      this.loggedInAt = loggedInAt;
      this.errorStatus = errorStatus;
   }

   public UserProfile(Long id, String userName, String pwd, Integer creditScore, String email, String firstName, String guid, Boolean isDisabled, String lastName, String metadata, String phone, String zipCode, String birthDate, String gender, Integer loggedInAt)
   {
      this.id = id;
      this.userName = userName;
      this.pwd = pwd;
      this.creditScore = creditScore;
      this.email = email;
      this.firstName = firstName;
      this.guid = guid;
      this.isDisabled = isDisabled;
      this.lastName = lastName;
      this.metadata = metadata;
      this.phone = phone;
      this.zipCode = zipCode;
      this.birthDate = birthDate;
      this.gender = gender;
      this.loggedInAt = loggedInAt;
   }

   public UserProfile(Long id, String userName, String pwd, String email, String firstName, String lastName, Credentials credentials, String mode)
   {
      this.id = id;
      this.userName = userName;
      this.pwd = pwd;
      this.email = email;
      this.firstName = firstName;
      this.lastName = lastName;
      this.credentials = credentials;
      this.mode=mode;
   }

   public UserProfile(String userName, Integer creditScore, String email, String firstName,
                      String guid, Boolean isDisabled, String lastName, String metadata,
                      String phone, String zipCode, String birthDate, String gender, Integer loggedInAt)
   {
      this.userName = userName;
      this.creditScore = creditScore;
      this.email = email;
      this.firstName = firstName;
      this.guid = guid;
      this.isDisabled = isDisabled;
      this.lastName = lastName;
      this.metadata = metadata;
      this.phone = phone;
      this.zipCode = zipCode;
      this.birthDate = birthDate;
      this.gender = gender;
      this.loggedInAt = loggedInAt;
   }

   public String getMode()
   {
      return mode;
   }

   public void setMode(String mode)
   {
      this.mode = mode;
   }

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

   @Override
   public String toString()
   {
      return "UserProfile{" +
         "id=" + id +
         ", userName='" + userName + '\'' +
         ", pwd='" + pwd + '\'' +
         ", creditScore=" + creditScore +
         ", email='" + email + '\'' +
         ", firstName='" + firstName + '\'' +
         ", guid='" + guid + '\'' +
         ", isDisabled=" + isDisabled +
         ", lastName='" + lastName + '\'' +
         ", metadata='" + metadata + '\'' +
         ", phone='" + phone + '\'' +
         ", zipCode='" + zipCode + '\'' +
         ", birthDate='" + birthDate + '\'' +
         ", gender='" + gender + '\'' +
         ", loggedInAt=" + loggedInAt +
         ", errorStatus=" + errorStatus +
         ", credentials=" + credentials +
         '}';
   }

   @Override
   public User convertToMXBean()
   {
      return new User(this.getUserName(),this.getCreditScore(),this.getEmail(),this.getFirstName(),
                      this.getLastName(),this.getMetadata(),this.getPhone(),
                      this.getZipCode(),this.getBirthDate(), this.getGender());
   }
}
