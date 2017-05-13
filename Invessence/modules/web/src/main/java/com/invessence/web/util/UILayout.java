package com.invessence.web.util;

import java.io.Serializable;
import java.util.*;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;

import com.invessence.service.bean.WebConfigDetails;
import com.invessence.service.util.ServiceParameters;
import com.invessence.web.bean.custody.TdCto;
import com.invessence.web.constant.WebConst;
import com.invessence.web.dao.common.CommonDAO;
import com.invessence.web.data.common.*;
import org.primefaces.component.tabview.TabView;
import org.springframework.beans.factory.annotation.Autowired;

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

   private String cid;
   private String rep;
   private Integer tabMenu = 0;
   private TabView menuTab = new TabView();
   private String defaultHome;

   @Autowired
   private TdCto tdcto;

   @Autowired
   private WebUtil webutil;

   public WebUtil getWebutil()
   {
      return webutil;
   }

   public WebProfile getWebProfile() { return webutil.getWebprofile(); }

   private String webserviceDB(Map<String, WebConfigDetails> webDB, String name)
   {
      String value = null;
      if (webDB != null)
      {
         if (webDB.containsKey(name))
         {
            value = webDB.get(name).getValue();
         }
      }
      return value;
   }


   public void preRenderView()
   {
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

   public TabView getMessagesTab()
   {
      return menuTab;
   }

   public void setMessagesTab(TabView messagesTab)
   {
      this.menuTab = messagesTab;
   }

   public String getRep()
   {
      return rep;
   }

   public void setRep(String rep)
   {
      this.rep = rep;
   }

   /* This is a method to go home, used as callback */
   public void defaultHome()
   {
      forwardURL(getDefaultHome());
   }

   public String getDefaultHome()
   {
      if (defaultHome == null)
      {
         return getDefaultDashBoard();
      }
      else
      {
         return defaultHome;
      }
   }

   public void setDefaultHome(String defaultHome)
   {
      if (this.defaultHome == null)
      {
         this.defaultHome = getDefaultDashBoard();
      }
      else
      {
         this.defaultHome = defaultHome;
      }
   }

   public void goToStartPage()
   {
      if ((defaultHome != null) && (!defaultHome.isEmpty()))
      {
         webutil.redirect(defaultHome, null);
      }
      else
      {
         webutil.redirect(getDefaultDashBoard(), null);
      }
   }

   public String getDefaultDashBoard()
   {
      String dashboard;

      if (webutil.isUserLoggedIn())
      {
         if (webutil.hasAccess(WebConst.WEB_ADVISOR))
         {
            dashboard = "/pages/advisor/index.xhtml";
         }
         else
         {
            if (webutil.getWebprofile().getConsumerdir() != null)
            {
               dashboard = "/pages/consumer/" + webutil.getWebprofile().getConsumerdir().trim() + "/index.xhtml";
            }
            else
            {
               dashboard = "/pages/consumer/invessence/index.xhtml";
            }
         }
      }
      else
      {
            dashboard = webutil.getWebprofile().getHomepage();
      }
      return dashboard;

   }

   public void forwardURL(String menuItem)
   {
      webutil.redirect(menuItem, null);
   }

   public void logout()
   {
      try
      {
         FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
         cid = "0";
         rep = "0";
      }
      catch (Exception ex)
      {

      }
      // webutil.redirect("/j_spring_security_logout", null);
   }

   public void doMenuAction(String menuItem)
   {
      String URL;

      try
      {
         if (menuItem == null)
         {
            URL = "/pages/common/invalid.xhtml";
         }
         else
         {
            if (menuItem.startsWith("http"))
            {
               forwardURL(menuItem);
            }

            if ((menuItem.startsWith("/")))
            {
               forwardURL(menuItem);
            }
            else
            {
               forwardURL("/pages/" + menuItem);
            }
         }
      }
      catch (Exception ex)
      {
         webutil.redirect("/pages/common/invalid.xhtml", null);
      }
   }

   public void doCustody(Long logonid, Long acctnum)
   {
      try
      {
         String custody_service =  webutil.getWebprofile().getWebInfo().get("CUSTODY.SERVICE");
         if (custody_service != null) {
           if (custody_service.equalsIgnoreCase("URL")) {
              String custodyURL =  webutil.getWebprofile().getWebInfo().get("CUSTODY.URL");
              if (custodyURL != null && !custodyURL.isEmpty()) {
                  forwardURL(custodyURL);
                 return;
              }
           }
           else if (custody_service.equalsIgnoreCase("INTERNAL")) {
               if (webutil.getWebprofile().getCustodydir() != null && !webutil.getWebprofile().getCustodydir().isEmpty()) {
                  doMenuAction("custody", "index.xhtml?l="+logonid.toString()+"&acct="+acctnum.toString());
                  // tdcto.startCTO(logonid,acctnum);
                  return;
               }
           }
         }
         String msgheader = "custody.100";
         webutil.redirecttoMessagePage("ERROR", "Service Error", msgheader);
         return;
      }
      catch (Exception ex)
      {
         webutil.redirect("/pages/common/invalid.xhtml", null);
      }
   }


   public void doMenuAction(String location, String menuItem)
   {
      String URL;

      try
      {
         if (location == null || location.trim().length() == 0)
         {
            doMenuAction(menuItem);
         }
         else
         {
            if (location.equalsIgnoreCase("consumer")) {
               // Only for consumer, they can have custom pages.  Go to Custom Directory if defined.
               if (getConsumerDIR() == null || getConsumerDIR().trim().length() == 0)
               {
                  forwardURL("/pages/consumer/inv/" + menuItem);
               }
               else
               {
                  forwardURL("/pages/consumer/" + getConsumerDIR() + "/" + menuItem);
               }
            }
            else {
               if (location.equalsIgnoreCase("custody")) {
                  if (webutil.getWebprofile().getCustodydir() == null || webutil.getWebprofile().getCustodydir().trim().length() == 0)
                  {
                     forwardURL("/pages/custody/invessence/" + menuItem);
                  }
                  else
                  {
                     forwardURL("/pages/custody/" + webutil.getWebprofile().getCustodydir() + "/" + menuItem);
                  }
               }
               else {
                  // For other, just go to advusor as is.
                  forwardURL("/pages/" + location.toLowerCase() + "/" + menuItem);
               }
            }
         }
      }
      catch (Exception ex)
      {
         webutil.redirect("/pages/common/invalid.xhtml", null);
      }
   }

   public void whichPage(String access, String pageinfo)
   {
      if (access == null)
      {
         access = "user";
      }

      if (pageinfo == null)
      {
         pageinfo = "index.xhtml";
      }

      if (pageinfo.equalsIgnoreCase("settings") || pageinfo.equalsIgnoreCase("security"))
      {
         doMenuAction("common", "setting.xhtml");
         return;
      }

      if (access.equalsIgnoreCase("user"))
      {
         doMenuAction("consumer", pageinfo);
         return;
      }

      if (access.equalsIgnoreCase("advisor"))
      {
         doMenuAction("advisor", pageinfo);
         return;
      }

      if (access.equalsIgnoreCase("admin"))
      {
         doMenuAction("advisor", pageinfo);
         return;
      }

/*
      if (pageinfo.contains(".xhtml"))
      {
         doMenuAction(pageinfo);
         return;
      }
*/


      doMenuAction("consumer", "index.xhtml");
   }

   public String getDisclaimer()
   {
      String txt = null;
      if (webutil != null)
      {
         txt = webutil.getMessageText().lookupMessage("disclaimer." + cid, null);
      }
      if (txt != null && txt.length() > 0)
      {
         return txt;
      }
      else
      {
         return null;
      }

   }

   public String getTemplateDIR()
   {
      String templateDIR = "/template/common/spark";
      if (webutil.getWebprofile() != null)
      {
         if (webutil.getWebprofile().getTemplatedir() == null || webutil.getWebprofile().getTemplatedir().trim().length() == 0)
         {
            return (templateDIR);
         }
         else
         {
            return (webutil.getWebprofile().getTemplatedir());
         }
      }
      return templateDIR;
   }

   public String getThemeLib()
   {
      String themeLib = WebConst.DEFAULT_THEME + "-layout";
      if (webutil.getWebprofile() == null)
      {
         return (themeLib);
      }
      else if (webutil.getWebprofile().getThemelib() == null || webutil.getWebprofile().getThemelib().trim().length() == 0)
      {
         return (themeLib);
      }
      else
      {
         return (webutil.getWebprofile().getThemelib());
      }
   }

   public String getTheme()
   {
      String theme = WebConst.DEFAULT_THEME;
      if (webutil.getWebprofile() == null)
      {
         return (theme);
      }
      else if (webutil.getWebprofile().getTheme() == null || webutil.getWebprofile().getTheme().trim().length() == 0)
      {
         return (theme);
      }
      else
      {
         return (webutil.getWebprofile().getTheme());
      }
   }

   public String getLogo()
   {
      if (webutil.getWebprofile() == null)
      {
         return (WebConst.DEFAULT_LOGO);
      }
      else if (webutil.getWebprofile().getLogo() == null || webutil.getWebprofile().getLogo().trim().length() == 0)
      {
         return (WebConst.DEFAULT_LOGO);
      }
      else
      {
         return (webutil.getWebprofile().getLogo());
      }
   }

   public String getConsumerDIR()
   {
      if (webutil.getWebprofile() == null)
      {
         return ("/");
      }
      else
      {
         return (webutil.getWebprofile().getConsumerdir());
      }
   }
}
