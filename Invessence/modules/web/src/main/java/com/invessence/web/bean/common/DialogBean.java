package com.invessence.web.bean.common;

import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

@ManagedBean(name = "dialogBean")
@SessionScoped
public class DialogBean {

   public void keepAliveSession() {
      FacesContext fc = FacesContext.getCurrentInstance();
      HttpServletRequest request = (HttpServletRequest) fc.getExternalContext().getRequest();
      request.getSession();
   }

}