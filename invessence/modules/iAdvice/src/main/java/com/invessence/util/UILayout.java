package com.invessence.util;

import java.io.Serializable;
import java.util.*;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;

import com.invessence.constant.Const;
import org.primefaces.component.lightbox.LightBox;
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
@ApplicationScoped
@Component("config")
public class UILayout implements Serializable
{
   private static final long serialVersionUID = -1992L;

   private Integer tabMenu = 0;
   private TabView menuTab = new TabView();
   private String theme = "opensans";
   private String themeLibrary = "opensans-layout";
   private String themeid;
   private String cid, rep;
   private String default_page;
   private String phone, email;
   private String forwardcustodianURL = "Your have made a request to visit Interactive Broker site (Your custodian).  You will be logged out of this site.  Do you want to continue?";

   @ManagedProperty("#{webutil}")
   private WebUtil webutil = new WebUtil();
   public void setWebutil(WebUtil webutil)
   {
      this.webutil = webutil;
   }

   public WebUtil getWebutil() {
      return webutil;
   }

   public String getForwardcustodianURL()
   {
      return forwardcustodianURL;
   }

   public String getPhone()
   {
      return phone;
   }

   public String getEmail()
   {
      return email;
   }

   public String getTheme()
   {
      return theme;
   }

   public String getThemeLibrary()
   {
      return themeLibrary;
   }

   public void resetCIDProfile(String cid) {
      String newTheme,newLibrary;
      if (cid != null) {
         if (! cid.equals(themeid)) {
            themeid = cid;
            email = webutil.getMessageText().lookupMessage("email." + themeid, null);
            phone = webutil.getMessageText().lookupMessage("phone." + themeid, null);
            newTheme = webutil.getMessageText().lookupMessage("theme." + themeid, null);
            newLibrary = webutil.getMessageText().lookupMessage("library." + themeid, null);
            resetTheme(newTheme);

            if (email == null)
               email = "info@invessence.com";

            if (phone == null)
               phone = "(201) 977-2704";
         }

      }
   }

   public void resetTheme(String theme) {
      if (theme != null) {
            theme = theme.trim();
            this.theme = theme;
            this.themeLibrary = theme + "-layout";
      }
      else {

         if (this.theme == null)
            this.theme = "modena";

         if (themeLibrary == null)
            themeLibrary = this.theme + "-layout";
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
               setRep("0");
            }
            resetCIDProfile(getCid());
         }
      }
      catch (Exception e)
      {
         email = "info@invessence.com";
         phone = "(201) 977-2704";
         theme = "modena";
         themeLibrary = "modena-layout";
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

   public String getThemeid()
   {
      return themeid;
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

   public void onTabChange(TabChangeEvent event) {
      TabView tabView = (TabView) event.getComponent();
      Integer fromTab = null, toTab = null;

      int activeIndex = tabView.getChildren().indexOf(event.getTab());
      fromTab = toTab;  // prior tab
      toTab = activeIndex; // new tab.

      this.menuTab.setActiveIndex(activeIndex);
      this.setTabMenu(activeIndex);
   }

   public String getLogo()
   {
      String logo = Const.DEFAULT_LOGO;
      try {
         if (getThemeid() != null) {
            String logoid = "logo." + getThemeid();
            logo = webutil.getMessageText().buildInternalMessage(logoid,null);
         }
         if (logo == null)
            logo = Const.DEFAULT_LOGO;
      }
      catch (Exception ex) {
         logo = Const.DEFAULT_LOGO;
      }
      return logo;
   }

   public String getLogoAlt()
   {
      String logoAlt = Const.COMPANY_NAME;
      try {
         if (getThemeid() != null) {
            String logoid = "company." + getThemeid();
            logoAlt = webutil.getMessageText().buildInternalMessage(logoid, null);
         }
         if (logoAlt == null)
            logoAlt = Const.COMPANY_NAME;
      }
      catch (Exception ex) {
         logoAlt = Const.COMPANY_NAME;
      }
      return logoAlt;
   }

   public void forwardURL(String menuItem){
      webutil.redirect(menuItem,null);
   }

   public void logout() {
      try {
         FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
         cid="0";
         rep="0";
         email=null;
         phone=null;
         theme="modena";
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

   public void aboutusURL() {
      RequestContext.getCurrentInstance().openDialog("aboutus");
   }
}
