package com.invessence.web.data.common;

import java.util.*;

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
   String custodyprocess;
   String theme;
   String themelib;
   String templatedir;
   String consumerdir;
   String cssdir;
   String customcss;
   String webmode;
   String googleAnalytic;
   String emailUser;

   String custodydir;
   String custodycss;
   //  These properties are based on the visitor or person logged on.
   //  These will be created after the visitor opens a landing page.
   String advisor;
   String rep;

   AdvisorProfile advisorProfile;

   // List of email templates for appropriate correspondance
   Map<String,String> emailTemplateMap;
   Map<String,String> emailSubjectMap;

   public UIProfile()
   {
      emailTemplateMap = new HashMap<String, String>();
      emailSubjectMap = new HashMap<String, String>();
   }

   public UIProfile(String cid,
                    String companyname, String homepage, String securehomepage,
                    String logo, String logosize, String logolib,
                    String mainemail, String supportemail, String mainphone, String supportphone,
                    String copyright,
                    String forwardservice, String custodyURL, String custodyprocess,
                    String theme, String themelib,
                    String templatedir,
                    String consumerdir, String cssdir, String customcss, String webmode,
                    String emailUser)
   {
      emailTemplateMap = new HashMap<String, String>();
      emailSubjectMap = new HashMap<String, String>();
      advisorProfile = new AdvisorProfile();
      resetAllInfo(cid,
                   companyname, homepage, securehomepage,
                   logo, logosize, logolib,
                   mainemail, supportemail, mainphone, supportphone,
                   copyright,
                   forwardservice, custodyURL, custodyprocess,
                   theme, themelib,
                   templatedir,
                   consumerdir, cssdir, customcss, webmode,
                   emailUser);
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

   public String getCustodyprocess()
   {
      return custodyprocess;
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

   public Boolean getHasCustomcss()
   {
      return (customcss == null) ? false : (customcss.length() > 0) ? true : false;
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

   public Boolean getHasGoogleAnalytic()
   {
      if (googleAnalytic == null)
         return false;
      if (googleAnalytic.length() > 0)
         return true;
      else
         return false;

   }
   public String getGoogleAnalytic()
   {
      return googleAnalytic;
   }

   public void setGoogleAnalytic(String googleAnalytic)
   {
      this.googleAnalytic = googleAnalytic;
   }

   public void setCid(String cid)
   {
      this.cid = cid;
   }

   public String getCustodydir()
   {
      return custodydir;
   }

   public void setCustodydir(String custodydir)
   {
      this.custodydir = custodydir;
   }

   public Boolean getHasCustodycss()
   {
      return (custodycss == null) ? false : (custodycss.length() > 0) ? true : false;
   }
   public String getCustodycss()
   {
      return custodycss;
   }

   public void setCustodycss(String custodycss)
   {
      this.custodycss = custodycss;
   }

   public String getAdvisor()
   {
      return advisor;
   }

   public void setAdvisor(String advisor)
   {
      this.advisor = advisor;
   }

   public String getRep()
   {
      return rep;
   }

   public void setRep(String rep)
   {
         this.rep = rep;
   }

   public void setEmailTemplateMap(Map<String, String> emailTemplateList)
   {
      this.emailTemplateMap = emailTemplateList;
   }

   public String getEmailTemplate(String key)
   {
      if (key == null || emailTemplateMap == null)
         return null;

      if (emailTemplateMap.containsKey(key))
         return emailTemplateMap.get(key);
      return null;
   }

   public void setEmailTemplate(String key, String value)
   {
      if (emailTemplateMap != null)
         this.emailTemplateMap.put(key,value);
   }

   public String getEmailSubject(String key)
   {
      if (key == null || emailSubjectMap == null)
         return null;

      if (emailSubjectMap.containsKey(key))
         return emailSubjectMap.get(key);
      return null;
   }

   public void setEmailSubject(String key, String value)
   {
      if (emailSubjectMap != null)
         this.emailSubjectMap.put(key,value);
   }

   public Map<String, String> getEmailSubjectMap()
   {
      return emailSubjectMap;
   }

   public void setEmailSubjectMap(Map<String, String> emailSubjectMap)
   {
      this.emailSubjectMap = emailSubjectMap;
   }

   public String getEmailUser()
   {
      return emailUser;
   }

   public void setEmailUser(String emailUser)
   {
      this.emailUser = emailUser;
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
                            String webmode,
                            String emailUser)
   {
      setCid(cid);
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
      this.custodyprocess = accountOpeningURL;
      setConsumerdir(consumerdir);
      resetTheme(theme, themelib, templatedir, cssdir, customcss);
      this.webmode = webmode;
      this.emailUser = emailUser;
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
