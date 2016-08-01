package com.invessence.web.data.common;

import com.invessence.web.constant.*;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 9/10/15
 * Time: 3:42 PM
 * To change this template use File | Settings | File Templates.
 */
public class UIProfile
{
   String cid;
   String companyname;
   String homepage;
   String securehomepage;
   String logo;
   String logosize;
   String logolib;
   String mainemail;
   String supportemail;
   String mainphone;
   String supportphone;
   String copyright;
   String forwardservice;
   String custodyURL;
   String accountOpeningURL;
   String theme;
   String themelib;
   String templatedir;
   String consumerdir;
   String cssdir;
   String customcss;
   String webmode;

   //  These properties are based on the visitor or person logged on.
   //  These will be created after the visitor opens a landing page.
   String advisor;
   String rep;
   String allocationmodel;


   public UIProfile()
   {
   }

   public UIProfile(String cid,
                    String companyname, String homepage, String securehomepage,
                    String logo, String logosize, String logolib,
                    String mainemail, String supportemail, String mainphone, String supportphone,
                    String copyright,
                    String forwardservice, String custodyURL, String accountOpeningURL,
                    String theme, String themelib,
                    String templatedir,
                    String consumerdir, String cssdir, String customcss, String webmode)
   {
      resetAllInfo(cid,
                   companyname, homepage, securehomepage,
                   logo, logosize, logolib,
                   mainemail, supportemail, mainphone, supportphone,
                   copyright,
                   forwardservice, custodyURL, accountOpeningURL,
                   theme, themelib,
                   templatedir,
                   consumerdir, cssdir, customcss, webmode);
   }

   public String getCid()
   {
      return cid;
   }

   public String getHomepage()
   {
      return homepage;
   }

   public String getSecurehomepage()
   {
      return securehomepage;
   }

   public String getLogosize()
   {
      return logosize;
   }

   public String getMainemail()
   {
      return mainemail;
   }

   public String getSupportemail()
   {
      return supportemail;
   }

   public String getMainphone()
   {
      return mainphone;
   }

   public String getSupportphone()
   {
      return supportphone;
   }

   public String getCopyright()
   {
      return copyright;
   }

   public String getForwardservice()
   {
      return forwardservice;
   }

   public String getCustodyURL()
   {
      return custodyURL;
   }

   public String getAccountOpeningURL()
   {
      return accountOpeningURL;
   }

   public String getConsumerdir()
   {
      return consumerdir;
   }

   public String getTemplatedir()
   {
      return templatedir;
   }


   public String getCssdir()
   {
      return cssdir;
   }

   public String getCustomcss()
   {
      return customcss;
   }

   public String getCompanyname()
   {
      if (companyname == null)
      {
         return Const.COMPANY_NAME;
      }

      if (companyname.length() == 0)
      {
         return Const.COMPANY_NAME;
      }

      return companyname;
   }

   public String getLogo()
   {
      if (logo == null)
      {
         return WebConst.DEFAULT_LOGO;
      }

      if (logo.length() == 0)
      {
         return WebConst.DEFAULT_LOGO;
      }

      return logo;
   }

   public String getLogolib()
   {
      if (logolib == null)
      {
         return WebConst.DEFAULT_LOGOLIB;
      }

      if (logolib.length() == 0)
      {
         return WebConst.DEFAULT_LOGOLIB;
      }

      return logolib;
   }

   public String getTheme()
   {
      if (theme == null)
      {
         return WebConst.DEFAULT_THEME;
      }

      if (theme.length() == 0)
      {
         return WebConst.DEFAULT_THEME;
      }

      return theme;
   }

   public String getThemelib()
   {
      if (themelib == null)
      {
         return getTheme() + "-layout";
      }

      if (themelib.length() == 0)
      {
         return getTheme() + "-layout";
      }

      return themelib;
   }

   public String getWebmode()
   {
      return webmode;
   }

   public void setCid(String cid)
   {
      this.cid = cid;
   }

   public String getAdvisor()
   {
      return advisor;
   }

   public void setAdvisor(String advisor)
   {
      if (advisor == null) {
         if (this.advisor == null)
            this.advisor = Const.INVESSENCE_ADVISOR;
      }
      else {
         this.advisor = advisor;
      }
   }

   public String getRep()
   {
      return rep;
   }

   public void setRep(String rep)
   {
      if (rep == null) {
         if (this.rep == null)
            this.rep = Const.INVESSENCE_REP;
      }
      else {
         this.rep = rep;
      }
   }

   public String getAllocationmodel()
   {
      return allocationmodel;
   }

   public void setAllocationmodel(String allocationmodel)
   {
      if (allocationmodel == null) {
         if (this.allocationmodel == null)
            this.rep = WebConst.DEFAULT_THEME;
      }
      else {
         this.allocationmodel = allocationmodel;
      }
   }

   public void resetAllInfo(String cid,
                            String companyname, String homepage, String securehomepage,
                            String logo, String logosize, String logolib,
                            String mainemail, String supportemail, String mainphone, String supportphone,
                            String copyright,
                            String forwardservice, String custodyURL, String accountOpeningURL,
                            String theme, String themelib,
                            String templatedir,
                            String consumerdir, String cssdir, String customcss,
                            String webmode)
   {
      this.cid = cid;
      this.companyname = companyname;
      this.homepage = homepage;
      this.securehomepage = securehomepage;
      this.logo = logo;
      this.logosize = logosize;
      this.logolib = logolib;
      this.mainemail = mainemail;
      this.supportemail = supportemail;
      this.mainphone = mainphone;
      this.supportphone = supportphone;
      this.copyright = copyright;
      this.forwardservice = forwardservice;
      this.custodyURL = custodyURL;
      this.accountOpeningURL = accountOpeningURL;
      setConsumerdir(consumerdir);
      resetTheme(theme, themelib, templatedir, cssdir, customcss);
      this.webmode = webmode;
      setAdvisor(null);
      setRep(null);
      setAllocationmodel(null);
   }


   public void resetTheme(String theme)
   {
      if (theme != null)
      {
         theme = theme.trim();
         this.theme = theme;
         this.themelib = theme + "-layout";
      }
      else
      {

         if (this.theme == null)
         {
            this.theme = WebConst.DEFAULT_THEME;
         }

         if (themelib == null)
         {
            themelib = this.theme + "-layout";
         }
      }

   }

   private void setConsumerdir(String consumerdir)
   {
      this.consumerdir = consumerdir;
   }

   public void resetTheme(String theme, String library, String defaulttemplate, String cssdir, String customcss)
   {
      resetTheme(theme);
      this.themelib = library;
      this.templatedir = defaulttemplate;
      this.customcss = customcss;
      this.cssdir = cssdir;
   }

}
