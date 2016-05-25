package com.invessence.web.bean.common;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

@ManagedBean(name = "dialogBean")
@ViewScoped
public class DialogBean {

   public void keepAliveSession() {
      FacesContext fc = FacesContext.getCurrentInstance();
      HttpServletRequest request = (HttpServletRequest) fc.getExternalContext().getRequest();
      request.getSession();
   }

}