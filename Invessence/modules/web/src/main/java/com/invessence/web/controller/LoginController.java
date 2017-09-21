package com.invessence.web.controller;

import java.io.IOException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.*;
import javax.faces.context.*;
import javax.faces.event.*;
import javax.servlet.*;
import javax.servlet.http.*;

import com.invessence.web.constant.*;
import com.invessence.web.dao.*;
import com.invessence.web.data.common.UserInfoData;
import com.invessence.web.util.*;
import org.apache.commons.logging.*;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.web.WebAttributes;

import static javax.faces.context.FacesContext.getCurrentInstance;

@ManagedBean(name = "loginController")
@SessionScoped
public class LoginController implements PhaseListener
{

   protected final Log logger = LogFactory.getLog(getClass());
   private Long logonid;

   private String userID, password, question, answer, savedAnswer;
   private Boolean needAdditionalInfo = false;
   private UserInfoData uid;

   @ManagedProperty("#{webutil}")
   private WebUtil webutil;
   public void setWebutil(WebUtil webutil)
   {
      this.webutil = webutil;
   }

   @ManagedProperty("#{uiLayout}")
   private UILayout uiLayout;

   public void setUiLayout(UILayout uiLayout)
   {
      this.uiLayout = uiLayout;
   }

   @ManagedProperty("#{auditDAO}")
   private AuditDAO auditDAO;

   public AuditDAO getAuditDAO()
   {
      return auditDAO;
   }

   public void setAuditDAO(AuditDAO auditDAO)
   {
      this.auditDAO = auditDAO;
   }

   /*
   @ManagedProperty("#{emailMessage}")
   private EmailMessage emailMessage;
   public void setEmailMessage(EmailMessage emailMessage)
   {
      this.emailMessage = emailMessage;
   }
*/

   /**
    * Redirects the login request directly to spring security check.
    * Leave this method as it is to properly support spring security.
    *
    * @return
    * @throws ServletException
    * @throws IOException
    */

   public void doLogin() throws ServletException, IOException
   {
      ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();

      if (answer != null && !answer.isEmpty())
      {
         uid = (UserInfoData) getCurrentInstance().getExternalContext().getSessionMap().get(WebConst.USER_INFO);
         if (uid != null)
         {
            uid.setAnswer(answer);
         }
      }

      RequestDispatcher dispatcher = ((ServletRequest) context.getRequest())
         .getRequestDispatcher("/j_spring_security_check");

      dispatcher.forward((ServletRequest) context.getRequest(),
                         (ServletResponse) context.getResponse());

      FacesContext.getCurrentInstance().responseComplete();
      if(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("SPRING_SECURITY_CONTEXT")!=null)
      {
         Authentication authentication = (Authentication) ((SecurityContextImpl) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("SPRING_SECURITY_CONTEXT")).getAuthentication();
         if (authentication != null)
         {
            if (authentication.isAuthenticated() == true)
            {

               FacesContext fCtx = FacesContext.getCurrentInstance();
               HttpSession session = (HttpSession) fCtx.getExternalContext().getSession(false);
               String sessionId = session.getId();
               System.out.println("sessionId = " + sessionId);
               HttpServletRequest req = (HttpServletRequest) fCtx.getExternalContext().getRequest();

               UserInfoData userInfo = (UserInfoData) authentication.getPrincipal();
               Long logonAuditId=auditDAO.loginAuditEntry(new LoginAudit(null,  userInfo.getLogonID(),userInfo.getUsername(), sessionId, req.getRemoteAddr(), "S", "Success", null));
               userInfo.setLogonAuditID(logonAuditId);

               FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove(WebConst.USER_INFO);
               FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(WebConst.USER_INFO, userInfo);
            }
         }
      }
   }

   public void updateMessages()
   {
      if (!FacesContext.getCurrentInstance().isPostback())
      {
         Exception e = (Exception) FacesContext.getCurrentInstance().
            getExternalContext().getSessionMap().get(WebAttributes.AUTHENTICATION_EXCEPTION);

         if (e != null)
         {
            String exception =null;
            String userName=null;
            if (e instanceof BadCredentialsException)
            {
               FacesContext.getCurrentInstance().addMessage(null,
                                                            new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                                                             "Invalid Password.",
                                                                             "Invalid Password."));
               BadCredentialsException bc=(BadCredentialsException) e;
               if(bc.getAuthentication() !=null){
                  if(bc.getAuthentication().getPrincipal()!=null){
                     userName=bc.getAuthentication().getPrincipal().toString();
                  }
               }
               exception="Invalid Password.";
            }
            else if (e instanceof CredentialsExpiredException)
            {
               FacesContext.getCurrentInstance().addMessage(null,
                                                            new FacesMessage(FacesMessage.SEVERITY_WARN,
                                                                             "Need additional Credential",
                                                                             "Need additional Credential"));
               CredentialsExpiredException bc=(CredentialsExpiredException) e;
               if(bc.getAuthentication() !=null){
                  if(bc.getAuthentication().getPrincipal()!=null){
                     userName=bc.getAuthentication().getPrincipal().toString();
                  }
               }
               exception="Need additional Credential";
            }
            else if (e instanceof LockedException)
            {
               FacesContext.getCurrentInstance().addMessage(null,
                                                            new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                                                             "Account is locked",
                                                                             "Account is locked"));
               LockedException bc=(LockedException) e;
               if(bc.getAuthentication() !=null){
                  if(bc.getAuthentication().getPrincipal()!=null){
                     userName=bc.getAuthentication().getPrincipal().toString();
                  }
               }
               exception="Account is locked";
            }
            else if (e instanceof AuthenticationServiceException)
            {
               String msg = e.getMessage();
               // System.out.println(msg);
               FacesContext.getCurrentInstance().addMessage(null,
                                                            new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                                                             msg,
                                                                             msg));
               AuthenticationServiceException bc=(AuthenticationServiceException) e;
               if(bc.getAuthentication() !=null){
                  if(bc.getAuthentication().getPrincipal()!=null){
                     userName=bc.getAuthentication().getPrincipal().toString();
                  }
               }
               exception=msg;

            }
            else
            {
               System.out.println("Exception during login time: " + e.getMessage());
               String msg = e.getMessage();
               // System.out.println(msg);
               FacesContext.getCurrentInstance().addMessage(null,
                                                            new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                                                             msg,
                                                                             msg));
               exception=msg;
            }
            FacesContext fCtx = FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession) fCtx.getExternalContext().getSession(false);
            String sessionId = session.getId();
            System.out.println("sessionId = " + sessionId);
            HttpServletRequest req = (HttpServletRequest) fCtx.getExternalContext().getRequest();
            UserInfoData userInfo=(UserInfoData)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(WebConst.USER_INFO);
            if(userInfo!=null)
            {
               auditDAO.loginAuditEntry(new LoginAudit(null, userInfo.getLogonID()==null?null:userInfo.getLogonID(),userInfo.getUsername()==null?null:userInfo.getUsername(),
                                                       sessionId, req.getRemoteAddr(), "F", exception, null));
            }else
            {
               auditDAO.loginAuditEntry(new LoginAudit(null, null, userName, sessionId, req.getRemoteAddr(), "F", exception, null));
            }
            FacesContext.getCurrentInstance().
               getExternalContext().getSessionMap().remove(WebAttributes.AUTHENTICATION_EXCEPTION);
         }
      }
   }

   public void afterPhase(PhaseEvent event)
   {
   }

   /* (non-Javadoc)
    * @see javax.faces.event.PhaseListener#beforePhase(javax.faces.event.PhaseEvent)
    *
    * Do something before rendering phase.
    */
   public void beforePhase(PhaseEvent event)
   {
      Exception e = (Exception) FacesContext.getCurrentInstance().
         getExternalContext().getSessionMap().get(WebAttributes.AUTHENTICATION_EXCEPTION);

      if (e instanceof BadCredentialsException)
      {
         logger.debug("Found exception in session map: " + e);
         FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(
            WebAttributes.AUTHENTICATION_EXCEPTION, null);
         FacesContext.getCurrentInstance().addMessage(null,
                                                      new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                                                       "", "Username or password not valid"));
      }
   }

   /* (non-Javadoc)
    * @see javax.faces.event.PhaseListener#getPhaseId()
    *
    * In which phase you want to interfere?
    */
   public PhaseId getPhaseId()
   {
      return PhaseId.RENDER_RESPONSE;
   }

/*
   public String getRedirect()
   {
      FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(WebConst.USERLOGON_ATTEMPTS, 0);
      uiLayout.setDefaultHome(null);
      uiLayout.goToStartPage();
      return "success";
   }
*/



   public Long getLogonid()
   {
      return logonid;
   }

   public void setLogonid(Long logonid)
   {
      this.logonid = logonid;
   }

   public String getUserID()
   {
      return userID;
   }

   public void setUserID(String userID)
   {
      this.userID = userID;
   }

   public String getPassword()
   {
      return password;
   }

   public void setPassword(String password)
   {
      this.password = password;
   }

   public String getQuestion()
   {
      return question;
   }

   public void setQuestion(String question)
   {
      this.question = question;
   }

   public String getAnswer()
   {
      return answer;
   }

   public void setAnswer(String answer)
   {
      this.answer = answer;
   }

   public Boolean getNeedAdditionalInfo()
   {
      //return needAdditionalInfo;
      return false;
   }

   public void setNeedAdditionalInfo(Boolean needAdditionalInfo)
   {
      this.needAdditionalInfo = needAdditionalInfo;
   }

   public String getSavedAnswer()
   {
      return savedAnswer;
   }

   public void setSavedAnswer(String savedAnswer)
   {
      this.savedAnswer = savedAnswer;
   }
}
