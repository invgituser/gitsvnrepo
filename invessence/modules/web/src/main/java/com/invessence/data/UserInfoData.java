package com.invessence.data;


import java.util.*;

import javax.faces.context.FacesContext;

import com.invessence.constant.Const;
import org.springframework.security.core.*;


public class UserInfoData extends org.springframework.security.core.userdetails.User
{
   private Long logonID = null;
   private String userID, email;
   private String password = null;
   private String ip, macaddress, cookieID, resetID, acctownertype, logo, groupname, emailmsgtype;
   private String stateRegistered;
   private Map questAns;
   private String answer;
   private Collection<GrantedAuthority> authorities;
   private Integer randomQuestion;
   private String logonStatus;
   private Integer attempts;
   private String access;
   private Boolean user_enabled,user_acctexpired, user_crediatialexpired,user_locked;

   private List<String> authList = new ArrayList<String>();

   public UserInfoData(Long logonID, String userID, String username, String email, String password,
                       boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired,
                       boolean accountNonLocked, Collection<GrantedAuthority> authorities,
                       String ip, String macaddress, String cookieID, String resetID, String acctownertype,
                       String logo, String groupname, String stateRegistered,
                       Map questAns, Integer attempts, String access, String logonStatus, Integer randomQuestion, String emailmsgtype)
   {
      super(username, password, enabled
         , accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);

      setLogonID(logonID);
      setUserID(userID);
      setUserID(username);
      setEmail(email);
      setPassword(password);
      setIp(ip);
      setMacaddress(macaddress);
      setCookieID(cookieID);
      setResetID(resetID);
      setAcctownertype(acctownertype);
      setLogo(logo);
      setGroupname(groupname);
      setAuthorities(authorities);
      setQuestAns(questAns);
      setAttempts(attempts);
      setAccess(access);
      setLogonStatus(logonStatus);
      setRandomQuestion(randomQuestion);
      setEmailmsgtype(emailmsgtype);
      setStateRegistered(stateRegistered);

      FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(Const.LOGONID_PARAM, logonID);
   }
   public Long getLogonID()
   {
      return logonID;
   }

   public void setLogonID(Long logonID)
   {
      this.logonID = logonID;
   }

   public String getUserID()
   {
      return userID;
   }

   public void setUserID(String userID)
   {
      this.userID = userID;
   }

   public String getEmail()
   {
      return email;
   }

   public void setEmail(String email)
   {
      this.email = email;
   }

   public String getPassword()
   {
      return password;
   }

   public void setPassword(String password)
   {
      this.password = password;
   }

   public String getIp()
   {
      return ip;
   }

   public void setIp(String ip)
   {
      this.ip = ip;
   }

   public String getMacaddress()
   {
      return macaddress;
   }

   public void setMacaddress(String macaddress)
   {
      this.macaddress = macaddress;
   }

   public String getCookieID()
   {
      return cookieID;
   }

   public void setCookieID(String cookieID)
   {
      this.cookieID = cookieID;
   }

   public String getResetID()
   {
      return resetID;
   }

   public void setResetID(String resetID)
   {
      this.resetID = resetID;
   }

   public String getAcctownertype()
   {
      return acctownertype;
   }

   public void setAcctownertype(String acctownertype)
   {
      this.acctownertype = acctownertype;
   }

   public String getLogo()
   {
      return logo;
   }

   public void setLogo(String logo)
   {
      this.logo = logo;
   }

   public String getGroupname()
   {
      return groupname;
   }

   public void setGroupname(String groupname)
   {
      this.groupname = groupname;
   }

   public String getSelectedQuestion()
   {
      String [] question;
      if (getRandomQuestion() != null) {
         question = (String []) questAns.get(getRandomQuestion());
         return question[0];
      }
      else {
         question = (String []) questAns.get(0);
         return question[0];
      }
   }

   public String getSelectedAnswer()
   {
      String [] ans;
      if (getRandomQuestion() != null) {
         ans = (String []) questAns.get(getRandomQuestion());
         return ans[0];
      }
      else {
         ans = (String []) questAns.get(0);
         return ans[0];
      }
   }

   public Map getQuestAns()
   {
      return questAns;
   }

   public void setQuestAns(Map questAns)
   {
      this.questAns = questAns;
   }

   public Integer getRandomQuestion()
   {
      return randomQuestion;
   }

   public void setRandomQuestion(Integer randomQuestion)
   {
      this.randomQuestion = randomQuestion;
   }

   public List<String> getAuthList()
   {
      return authList;
   }

   public void setAuthList(List<String> authList)
   {
      this.authList = authList;
   }

   public Collection<GrantedAuthority> getAuthorities()
   {
      return authorities;
   }

   public void setAuthorities(Collection<GrantedAuthority> authorities)
   {
      this.authorities = authorities;
   }

   public Integer getAttempts()
   {
      return attempts;
   }

   public void setAttempts(Integer attempts)
   {
      this.attempts = attempts;
   }

   public String getAccess()
   {
      return access;
   }

   public void setAccess(String access)
   {
      this.access = access;
   }

   public String getLogonStatus()
   {
      return logonStatus;
   }

   public void setLogonStatus(String logonStatus)
   {
      this.logonStatus = logonStatus;
   }

   public Boolean getUser_enabled()
   {
      return user_enabled;
   }

   public void setUser_enabled(Boolean user_enabled)
   {
      this.user_enabled = user_enabled;
   }

   public Boolean getUser_acctexpired()
   {
      return user_acctexpired;
   }

   public void setUser_acctexpired(Boolean user_acctexpired)
   {
      this.user_acctexpired = user_acctexpired;
   }

   public Boolean getUser_crediatialexpired()
   {
      return user_crediatialexpired;
   }

   public void setUser_crediatialexpired(Boolean user_crediatialexpired)
   {
      this.user_crediatialexpired = user_crediatialexpired;
   }

   public Boolean getUser_locked()
   {
      return user_locked;
   }

   public void setUser_locked(Boolean user_locked)
   {
      this.user_locked = user_locked;
   }

   public String getAnswer()
   {
      return answer;
   }

   public void setAnswer(String answer)
   {
      this.answer = answer;
   }

   public String getEmailmsgtype()
   {
      return emailmsgtype;
   }

   public void setEmailmsgtype(String emailmsgtype)
   {
      this.emailmsgtype = emailmsgtype;
   }

   public String getStateRegistered()
   {
      return stateRegistered;
   }

   public void setStateRegistered(String stateRegistered)
   {
      this.stateRegistered = stateRegistered;
   }
}
