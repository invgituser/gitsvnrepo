package com.invessence.bean.advisor;

import java.io.Serializable;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;

import com.invessence.constant.Const;
import com.invessence.data.common.UserInfoData;
import com.invessence.util.*;

import static javax.faces.context.FacesContext.getCurrentInstance;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 7/1/14
 * Time: 11:50 AM
 * To change this template use File | Settings | File Templates.
 */

@ManagedBean(name = "institutiongenericBean")
@SessionScoped
public class InstitutionGenericBean implements Serializable
{
   private static final long serialVersionUID = 100002L;

   @ManagedProperty("#{advisorBean}")
   private AdvisorBean abean;

   @ManagedProperty("#{webutil}")
   private WebUtil webutil;
   public void setWebutil(WebUtil webutil)
   {
      this.webutil = webutil;
   }

   private Long logonid;

   public AdvisorBean getAbean()
   {
      return abean;
   }

   public void setAbean(AdvisorBean abean)
   {
      this.abean = abean;
   }

   public Long getLogonid()
   {
      return logonid;
   }

   public void setLogonid(Long logonid)
   {
      this.logonid = logonid;
   }

   public String getLogo()
   {
      String institutionImage= "/images/InvessenceLogo.jpg";
      String accttype;
      UserInfoData uid;
      try {
         if (getCurrentInstance().getExternalContext().getSessionMap().get(Const.USERLOGON_ACCTTYPE) != null)
         {
            accttype = getCurrentInstance().getExternalContext().getSessionMap().get(Const.USERLOGON_ACCTTYPE).toString();
            if (accttype.equalsIgnoreCase(Const.ROLE_ADVISOR)) {
               uid =  (UserInfoData) getCurrentInstance().getExternalContext().getSessionMap().get(Const.USER_INFO);
               institutionImage= "/images/InvessenceLogo.jpg";
               //institutionImage = uid.getLogo();
               if (institutionImage == null)
                  institutionImage= "/images/InvessenceLogo.jpg";
            }
         }
      }
      catch (Exception ex) {
         ex.printStackTrace();
      }
      return institutionImage;
   }

   public void newAccount() {
      //FacesContext.getCurrentInstance().getApplication().createValueBinding("#{advisorBean}").setValue(FacesContext.getCurrentInstance(), null );
      abean.resetAdvisorBean();
      webutil.redirect("/advisor/add.xhtml", null);
   }

   public void logout() {
      try {
         FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
      }
      catch (Exception ex) {

      }
      webutil.redirect("/j_spring_security_logout", null);
   }
}
