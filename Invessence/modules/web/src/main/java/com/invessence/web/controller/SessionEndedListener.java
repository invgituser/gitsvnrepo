package com.invessence.web.controller;

import javax.faces.context.FacesContext;

import com.invessence.web.constant.WebConst;
import com.invessence.web.dao.*;
import com.invessence.web.data.common.UserInfoData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.session.SessionDestroyedEvent;
import org.springframework.stereotype.Component;

/**
 * Created by abhangp on 9/14/2017.
 */
@Component
public class SessionEndedListener implements ApplicationListener<SessionDestroyedEvent>
{
   @Autowired
   private AuditDAO auditDAO;

   @Override
   public void onApplicationEvent(SessionDestroyedEvent event)
   {
      for (SecurityContext securityContext : event.getSecurityContexts())
      {
         Authentication authentication = securityContext.getAuthentication();
         UserInfoData userInfo = (UserInfoData) authentication.getPrincipal();
//            UserInfoData userInfo=(UserInfoData) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(WebConst.USER_INFO);
         if(userInfo!=null && userInfo.getLogonAuditID() != null)
         {
            auditDAO.loginAuditEntry(new LoginAudit(userInfo.getLogonAuditID(), userInfo.getLogonID(), userInfo.getUsername(), null, null, null, null, "Session Timeout"));
         }
      }
   }

}
