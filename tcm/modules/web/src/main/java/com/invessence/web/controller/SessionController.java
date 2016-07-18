package com.invessence.web.controller;

import java.io.IOException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.*;
import javax.faces.context.*;
import javax.faces.event.*;
import javax.servlet.*;

import com.invessence.web.constant.WebConst;
import com.invessence.web.data.common.UserInfoData;
import com.invessence.web.util.*;
import org.apache.commons.logging.*;
import org.springframework.security.authentication.*;
import org.springframework.security.web.WebAttributes;

import static javax.faces.context.FacesContext.getCurrentInstance;

@ManagedBean(name = "sessionControl")
@ViewScoped
public class SessionController
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

   public String getRedirect()
   {
      uiLayout.doMenuAction("consumer", "cadd.xhtml");
      return "success";
   }

}
