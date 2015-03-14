package com.invessence.data.common;

import java.util.*;


import com.invessence.util.*;
import com.invessence.data.*;
import com.invessence.constant.*;

public class UserData
{
   private SecurityQuestions securityQuestions = new SecurityQuestions();

   private static UserData instance = null;
   private long logonID = 0;
   private String logonstatus = "A";

   private String prefix = null;
   private String firstName = null;
   private String middleInitial = null;
   private String lastName = null;
   private String suffix = null;

   private String address1 = null;
   private String address2 = null;
   private String address3 = null;
   private String address4 = null;
   private String city = null;
   private String stateCode = null;
   private String stateName = null;
   private String stateProvince = null;
   private String zipCode = null;
   private String country = null;

   private String phone = null;
   private String phonetype = null;
   private String phonealt = null;
   private String phonealttype = null;

   private String email = null;
   private String emailtype = null;
   private String emailalt = null;
   private String emailalttype = null;

   private String userID = null;
   private String emailID = null;
   private String emailIDVerify = null;
   private String password = null;

   private String leadsource = null;
   private String question1 = null;
   private String answer1 = null;

   //private String newPassword = null;
   private String confirmNewPassword = null;
   private String currentPassword = null;
   private String secCode = null;

   private int commentCount = 0;

   private String motto = null;
   //private String imageFile = null;
   private String aboutMe = null;

   //private ImageData imageData = new ImageData();
   private RoleData roleData = new RoleData();
   private List<RoleData> roleDataList = new ArrayList<RoleData>();
   private int sendText = -1;
   private int status = 0;
   private String captchaAnswer = null;
   private String fullName = null;
   private String message = null;

   private String joinedDate = null;
   private String q1, q2, q3;
   private String ans1, ans2, ans3;
   private String ip, cookieID;
   Integer resetID;

   private String emailmsgtype = null;


   public UserData()
   {
      super();
      instance = this;
   }

   public static UserData getInstance()
   {
/*
      if (instance == null) {
         instance = new UserData();
      }
*/

      return instance;
   }

   public long getLogonID()

   {
      return logonID;
   }

   public void setLogonID(long logonID)
   {
      this.logonID = logonID;
   }

   public String getLogonstatus()
   {
      return logonstatus;
   }

   public void setLogonstatus(String logonstatus)
   {
      this.logonstatus = logonstatus;
   }

   public String getPassword()
   {
      return password;
   }

   public void setPassword(String password)
   {
      this.password = password;
   }

   /*
   public ImageData getImageData() {
       return imageData;
   }
   public void setImageData(ImageData imageData) {
       this.imageData = imageData;
   }
   */
   public String getAboutMe()
   {
      return aboutMe;
   }

   public void setAboutMe(String aboutMe)
   {
      this.aboutMe = aboutMe;
   }

   public String getAddress1()
   {
      return address1;
   }

   public void setAddress1(String address1)
   {
      this.address1 = address1;
   }

   public String getAddress2()
   {
      return address2;
   }

   public void setAddress2(String address2)
   {
      this.address2 = address2;
   }

   public String getAddress3()
   {
      return address3;
   }

   public void setAddress3(String address3)
   {
      this.address3 = address3;
   }

   public String getAddress4()
   {
      return address4;
   }

   public void setAddress4(String address4)
   {
      this.address4 = address4;
   }

   public String getCity()
   {
      return city;
   }

   public void setCity(String city)
   {
      this.city = city;
   }

   public String getStateCode()
   {
      return stateCode;
   }

   public void setStateCode(String stateCode)
   {
      this.stateCode = stateCode;
   }

   public String getStateName()
   {
      return stateName;
   }

   public void setStateName(String stateName)
   {
      this.stateName = stateName;
   }

   public String getStateProvince()
   {
      return stateProvince;
   }

   public void setStateProvince(String stateProvince)
   {
      this.stateProvince = stateProvince;
   }

   public String getZipCode()
   {
      return zipCode;
   }

   public void setZipCode(String zipCode)
   {
      this.zipCode = zipCode;
   }

   public String getCountry()
   {
      return country;
   }

   public void setCountry(String country)
   {
      this.country = country;
   }

   public String getPhone()
   {
      return phone;
   }

   public void setPhone(String phone)
   {
      this.phone = phone;
   }

   public String getPhonetype()
   {
      return phonetype;
   }

   public void setPhonetype(String phonetype)
   {
      this.phonetype = phonetype;
   }

   public String getPhonealt()
   {
      return phonealt;
   }

   public void setPhonealt(String phonealt)
   {
      this.phonealt = phonealt;
   }

   public String getPhonealttype()
   {
      return phonealttype;
   }

   public void setPhonealttype(String phonealttype)
   {
      this.phonealttype = phonealttype;
   }

   public String getEmail()
   {
      return email;
   }

   public void setEmail(String email)
   {
      this.email = email;
   }

   public String getEmailtype()
   {
      return emailtype;
   }

   public void setEmailtype(String emailtype)
   {
      this.emailtype = emailtype;
   }

   public String getEmailalt()
   {
      return emailalt;
   }

   public void setEmailalt(String emailalt)
   {
      this.emailalt = emailalt;
   }

   public String getEmailalttype()
   {
      return emailalttype;
   }

   public void setEmailalttype(String emailalttype)
   {
      this.emailalttype = emailalttype;
   }

   public void setAddressData(String address1, String address2, String address3, String address4,
                              String city, String state, String statename, String stateProvince,
                              String zip, String country,
                              String phone, String phonetype, String phonealt, String phonealttype,
                              String email, String emailtype, String emailalt, String emailalttype)
   {
      this.setAddress1(address1);
      this.setAddress2(address2);
      this.setAddress3(address3);
      this.setAddress4(address4);

      this.setCity(city);
      this.setStateCode(state);
      this.setStateName(statename);
      this.setStateProvince(stateProvince);
      this.setZipCode(zip);
      this.setCountry(country);

      this.setPhone(phone);
      this.setPhonetype(phonetype);
      this.setPhonealt(phonealt);
      this.setPhonealttype(phonealttype);

      this.setEmail(email);
      this.setEmailtype(emailtype);
      this.setEmailalt(emailalt);
      this.setEmailalttype(emailalttype);
   }

   public String getMotto()
   {
      return motto;
   }

   public void setMotto(String motto)
   {
      this.motto = motto;
   }

   public int getCommentCount()
   {
      return commentCount;
   }

   public void setCommentCount(int commentCount)
   {
      this.commentCount = commentCount;
   }

   public String getUserID()
   {
      return userID;
   }

   public void setUserID(String userID)
   {
      this.userID = userID;
   }

   public String getEmailID()
   {
      return emailID;
   }

   public void setEmailID(String emailID)
   {
      System.out.println("Signon Try:" + emailID);
      this.emailID = emailID;
   }

   public String getConfirmNewPassword()
   {
      return confirmNewPassword;
   }

   public void setConfirmNewPassword(String confirmNewPassword)
   {
      this.confirmNewPassword = confirmNewPassword;
   }

   public String getCurrentPassword()
   {
      return currentPassword;
   }

   public void setCurrentPassword(String currentPassword)
   {
      this.currentPassword = currentPassword;
   }

   public RoleData getRoleData()
   {
      return roleData;
   }

   public void setRoleData(RoleData roleData)
   {
      this.roleData = roleData;
   }

   public int getSendText()
   {
      return sendText;
   }

   public void setSendText(int sendText)
   {
      this.sendText = sendText;
   }

   public List<RoleData> getRoleDataList()
   {
      return roleDataList;
   }

   public void setRoleDataList(List<RoleData> roleDataList)
   {
      this.roleDataList = roleDataList;
   }

   public int getStatus()
   {
      return status;
   }

   public void setStatus(int status)
   {
      this.status = status;
   }

   public String getSecCode()
   {
      return secCode;
   }

   public void setSecCode(String secCode)
   {
      this.secCode = secCode;
   }

   public String getCaptchaAnswer()
   {
      return captchaAnswer;
   }

   public void setCaptchaAnswer(String captchaAnswer)
   {
      this.captchaAnswer = captchaAnswer;
   }

   public String getFullName()
   {

      if (!WebUtil.isNull(firstName))
      {
         this.fullName = firstName;
      }

      if (!WebUtil.isNull(middleInitial))
      {
         this.fullName += " " + middleInitial;
      }

      if (!WebUtil.isNull(lastName))
      {
         this.fullName += " " + lastName;
      }

      return this.fullName;


   }

   public void setFullName(String fullName)
   {
      this.fullName = fullName;
   }

   public String getEmailIDVerify()
   {
      return emailIDVerify;
   }

   public void setEmailIDVerify(String emailIDVerify)
   {
      this.emailIDVerify = emailIDVerify;
   }

   public String getJoinedDate()
   {
      return joinedDate;
   }

   public void setJoinedDate(String joinedDate)
   {
      this.joinedDate = joinedDate;
   }

   public String getPrefix()
   {
      return prefix;
   }

   public void setPrefix(String prefix)
   {
      this.prefix = prefix;
   }

   public String getFirstName()
   {
      return firstName;
   }

   public void setFirstName(String firstName)
   {
      this.firstName = firstName;
   }

   public String getMiddleInitial()
   {
      return middleInitial;
   }

   public void setMiddleInitial(String middleInitial)
   {
      this.middleInitial = middleInitial;
   }

   public String getLastName()
   {
      return lastName;
   }

   public void setLastName(String lastName)
   {
      this.lastName = lastName;
   }

   public String getSuffix()
   {
      return suffix;
   }

   public void setSuffix(String suffix)
   {
      this.suffix = suffix;
   }

   public String getLeadsource()
   {
      return leadsource;
   }

   public void setLeadsource(String leadsource)
   {
      this.leadsource = leadsource;
   }

   public String getQuestion1()
   {
      return question1;
   }

   public void setQuestion1(String question1)
   {
      this.question1 = question1;
   }

   public String getAnswer1()
   {
      return answer1;
   }

   public void setAnswer1(String answer1)
   {
      this.answer1 = answer1;
   }

   public String getMessage()
   {
      return message;
   }


   public void setMessage(String message)
   {
      this.message = message;
   }

   public String getQ1()
   {
      return q1;
   }

   public void setQ1(String q1)
   {
      this.q1 = q1;
   }

   public String getQ2()
   {
      return q2;
   }

   public void setQ2(String q2)
   {
      this.q2 = q2;
   }

   public String getQ3()
   {
      return q3;
   }

   public void setQ3(String q3)
   {
      this.q3 = q3;
   }

   public String getAns1()
   {
      return ans1;
   }

   public void setAns1(String ans1)
   {
      this.ans1 = ans1;
   }

   public String getAns2()
   {
      return ans2;
   }

   public void setAns2(String ans2)
   {
      this.ans2 = ans2;
   }

   public String getAns3()
   {
      return ans3;
   }

   public void setAns3(String ans3)
   {
      this.ans3 = ans3;
   }

   public String getIp()
   {
      return ip;
   }

   public void setIp(String ip)
   {
      this.ip = ip;
   }

   public String getCookieID()
   {
      return cookieID;
   }

   public void setCookieID(String cookieID)
   {
      this.cookieID = cookieID;
   }

   public Integer getResetID()
   {
      return resetID;
   }

   public void setResetID(Integer resetID)
   {
      this.resetID = resetID;
   }

   public Map<String, String> getQuestion(Integer qnum)
   {
      return securityQuestions.getQuestion(qnum);
   }

   public String getEmailmsgtype()
   {
      return emailmsgtype;
   }

   public void setEmailmsgtype(String emailmsgtype)
   {
      this.emailmsgtype = emailmsgtype;
   }

}