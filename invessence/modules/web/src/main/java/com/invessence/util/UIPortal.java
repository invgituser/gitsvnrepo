package com.invessence.util;

import java.io.Serializable;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;

import com.invessence.constant.Const;
import com.invessence.data.common.*;
import org.primefaces.component.tabview.TabView;
import org.primefaces.event.TabChangeEvent;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 10/20/14
 * Time: 10:03 AM
 * To change this template use File | Settings | File Templates.
 */

@ManagedBean(name = "uiportal")
@SessionScoped
@Component("config")
public class UIPortal implements Serializable
{
   private static final long serialVersionUID = -2L;

   private String cid;
   private UIProfile uiprofile = new UIProfile();


   @ManagedProperty("#{webutil}")
   private WebUtil webutil = new WebUtil();
   public void setWebutil(WebUtil webutil)
   {
      this.webutil = webutil;
   }

   public WebUtil getWebutil() {
      return webutil;
   }

   public UIProfile getUiprofile()
   {
      return uiprofile;
   }

   public String getCid()
   {
      return cid;
   }

   public void setCid(String cid)
   {
      this.cid = cid;
   }

   public void resetCIDProfile(String cid)
   {
      if (cid != null)
      {
         if (uiprofile == null) {
            uiprofile = new UIProfile();
         }

         if (!cid.equals(uiprofile.getCid()))
         {
            String companyname;
            String webmode;
            String homeurl, securehomeurl;
            String logo, logosize, logolib;
            String mainemail, supportemail;
            String mainphone, supportphone;
            String copyright, forwardURL;
            String theme, themelib;

            webmode = webutil.getMessageText().lookupMessage("web.mode", null);
            companyname = webutil.getMessageText().lookupMessage("companyname." + cid, null);
            homeurl = webutil.getMessageText().lookupMessage("website.url." + cid, null);
            securehomeurl = webutil.getMessageText().lookupMessage("secure.url." + cid, null);
            logo = webutil.getMessageText().lookupMessage("logo." + cid, null);
            logosize = webutil.getMessageText().lookupMessage("logosize." + cid, null);
            logolib = webutil.getMessageText().lookupMessage("logolib." + cid, null);
            mainemail = webutil.getMessageText().lookupMessage("mainemail." + cid, null);
            supportemail = webutil.getMessageText().lookupMessage("supportemail." + cid, null);
            mainphone = webutil.getMessageText().lookupMessage("mainphone." + cid, null);
            supportphone = webutil.getMessageText().lookupMessage("supportphone." + cid, null);
            copyright = webutil.getMessageText().lookupMessage("copyright." + cid, null);
            forwardURL = webutil.getMessageText().lookupMessage("forwardURL." + cid, null);
            theme = webutil.getMessageText().lookupMessage("theme." + cid, null);
            themelib = webutil.getMessageText().lookupMessage("themelib." + cid, null);

            uiprofile.resetAllInfo(webmode, cid, null, companyname,
                                   homeurl, securehomeurl,
                                   logo, logosize, logolib,
                                   mainemail, supportemail,
                                   mainphone, supportphone,
                                   copyright, forwardURL);
            uiprofile.resetTheme(theme, themelib);

         }
      }
   }

   @PostConstruct
   public void init() {
      if (getCid() == null)
      {
         setCid("0");
         resetCIDProfile(getCid());
      }
   }

   public void preRenderView()
   {

      try {
         if (!FacesContext.getCurrentInstance().isPostback())
         {
            if (getCid() == null || getCid().length() == 0) {
               Map<String, String> params =FacesContext.getCurrentInstance().
                  getExternalContext().getRequestParameterMap();
               if (params != null) {
                  String tcid = params.get("cid");
                  if (tcid != null)
                     cid = tcid;
               }
            }

            if (getCid() == null)
            {
               setCid("0");
            }
            resetCIDProfile(getCid());
         }
      }
      catch (Exception e)
      {
      }
   }

}
