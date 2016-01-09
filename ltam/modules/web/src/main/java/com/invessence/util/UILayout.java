package com.invessence.util;

import java.io.Serializable;
import java.util.*;
import javax.annotation.PostConstruct;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;

import com.invessence.constant.Const;
import com.invessence.data.UIProfile;
import org.primefaces.component.tabview.TabView;
import org.primefaces.context.RequestContext;
import org.primefaces.event.TabChangeEvent;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 10/20/14
 * Time: 10:03 AM
 * To change this template use File | Settings | File Templates.
 */

@ManagedBean(name = "uiLayout")
@SessionScoped
public class UILayout implements Serializable
{
   private static final long serialVersionUID = -1992L;

   private UIProfile uiprofile = new UIProfile();
   private String cid;
   private String rep;
   private Integer tabMenu = 0;
   private TabView menuTab = new TabView();
   private String default_page;

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
            String homeurl, securehomeurl;
            String logo, logosize, logolib;
            String mainemail, supportemail;
            String mainphone, supportphone;
            String copyright, forwardURL;
            String theme, themelib;

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

            uiprofile.resetAllInfo(cid, rep, companyname,
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
         setRep("");
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
               setRep("");
            }
            resetCIDProfile(getCid());
         }
      }
      catch (Exception e)
      {
      }
   }



   public static long getSerialVersionUID()
   {
      return serialVersionUID;
   }

   public Integer getTabMenu()
   {
      return tabMenu;
   }

   public void setTabMenu(Integer tabMenu)
   {
      this.tabMenu = tabMenu;
   }

   public TabView getMessagesTab () {
      return menuTab;
   }

   public void setMessagesTab(TabView messagesTab ) {
      this.menuTab = messagesTab;
   }

   public String getCid()
   {
      return cid;
   }

   public void setCid(String cid)
   {
      this.cid = cid;
   }

   public String getRep()
   {
      return rep;
   }

   public void setRep(String rep)
   {
      this.rep = rep;
   }

   public String getDefault_page()
   {
      return default_page;
   }

   public void setDefault_page(String default_page)
   {
      this.default_page = default_page;
   }

   public void goToStartPage() {
     if ((default_page != null) && (! default_page.isEmpty())) {
         webutil.redirect(default_page, null);
     }
     else {
        if (webutil.hasAccess(Const.ROLE_ADVISOR)) {
           webutil.redirect("/pages/advisor/index.xhtml", null);
        }
        else {
         webutil.redirect("/pages/consumer/index.xhtml", null);
        }
     }
   }

   public void onTabChange(TabChangeEvent event) {
      TabView tabView = (TabView) event.getComponent();
      Integer fromTab = null, toTab = null;

      int activeIndex = tabView.getChildren().indexOf(event.getTab());
      fromTab = toTab;  // prior tab
      toTab = activeIndex; // new tab.

      this.menuTab.setActiveIndex(activeIndex);
      this.setTabMenu(activeIndex);
   }


   public void forwardURL(String menuItem){
      webutil.redirect(menuItem,null);
   }

   public void logout() {
      try {
         FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
         cid="0";
         rep="0";
      }
      catch (Exception ex) {

      }
      webutil.redirect("/j_spring_security_logout",null);
   }

   public void faqURL() {
      Map<String,Object> options = new HashMap<String, Object>();
      options.put("modal", true);
      options.put("draggable", false);
      options.put("resizable", false);
      options.put("contentHeight", 520);

      RequestContext.getCurrentInstance().openDialog("faqURL", options, null);

   }

   public String getPageInfo(Integer pageno)
   {
      String txt = null;
      String msg;
      if (webutil != null)
      {
         if (pageno != null)
         {
            if (pageno >= 0) {
               msg = "p" + pageno.toString() + "info." + cid;
            }
            else {
               msg = "pinfo." + cid;
            }
            txt = webutil.getMessageText().lookupMessage(msg, null);
         }
      }
      if (txt != null && txt.length() > 0)
         return txt;
      else
         return null;
   }

   public String getDisclaimer() {
      String txt = null;
      if (webutil != null) {
         txt = webutil.getMessageText().lookupMessage("disclaimer." + cid, null);
      }
      if (txt != null && txt.length() > 0)
         return txt;
      else
         return null;

   }

   public Boolean getProdMode() {
      return webutil.isWebProdMode();
   }

}
