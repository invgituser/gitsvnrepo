package com.invessence.data.common;

import com.invessence.constant.*;

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
   String advisor;
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
   String forwardURL;

   String theme;
   String themelib;

   public UIProfile()
   {
   }

   public UIProfile(String cid, String advisor, String companyname,
                    String homepage, String securehomepage,
                    String logo, String logosize, String logolib,
                    String mainemail, String supportemail,
                    String mainphone, String supportphone,
                    String copyright, String forwardURL)
   {
      this.cid = cid;
      this.advisor = advisor;
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
      this.forwardURL = forwardURL;


   }

   public String getCid()
   {
      return cid;
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
      this.advisor = advisor;
   }

   public String getCompanyname()
   {
      if (companyname == null)
         return Const.COMPANY_NAME;

      if (companyname.length() == 0)
         return Const.COMPANY_NAME;

      return companyname;
   }

   public void setCompanyname(String companyname)
   {
      this.companyname = companyname;
   }

   public String getLogo()
   {
      if (logo == null)
         return WebConst.DEFAULT_LOGO;

      if (logo.length() == 0)
         return WebConst.DEFAULT_LOGO;

      return logo;
   }

   public void setLogo(String logo)
   {
      this.logo = logo;
   }

   public String getLogosize()
   {
      return logosize;
   }

   public void setLogosize(String logosize)
   {
      this.logosize = logosize;
   }

   public String getLogolib()
   {
      if (logolib == null)
         return WebConst.DEFAULT_LOGOLIB;

      if (logolib.length() == 0)
         return WebConst.DEFAULT_LOGOLIB;

      return logolib;
   }

   public void setLogolib(String logolib)
   {
      this.logolib = logolib;
   }

   public String getMainemail()
   {
      return mainemail;
   }

   public void setMainemail(String mainemail)
   {
      this.mainemail = mainemail;
   }

   public String getSupportemail()
   {
      return supportemail;
   }

   public void setSupportemail(String supportemail)
   {
      this.supportemail = supportemail;
   }

   public String getMainphone()
   {
      return mainphone;
   }

   public void setMainphone(String mainphone)
   {
      this.mainphone = mainphone;
   }

   public String getSupportphone()
   {
      return supportphone;
   }

   public void setSupportphone(String supportphone)
   {
      this.supportphone = supportphone;
   }

   public String getCopyright()
   {
      return copyright;
   }

   public void setCopyright(String copyright)
   {
      this.copyright = copyright;
   }

   public String getForwardURL()
   {
      return forwardURL;
   }

   public void setForwardURL(String forwardURL)
   {
      this.forwardURL = forwardURL;
   }

   public String getTheme()
   {
      if (theme == null)
         return WebConst.DEFAULT_THEME;

      if (theme.length() == 0)
         return WebConst.DEFAULT_THEME;

      return theme;
   }

   public void setTheme(String theme)
   {
      this.theme = theme;
   }

   public String getThemelib()
   {
      if (themelib == null)
         return getTheme() + "-layout";

      if (themelib.length() == 0)
         return getTheme() + "-layout";

      return themelib;
   }

   public void setThemelib(String themelib)
   {
      this.themelib = themelib;
   }

   public void resetAllInfo(String cid, String advisor, String companyname,
                            String homepage, String securehomepage,
                            String logo, String logosize, String logolib,
                            String mainemail, String supportemail,
                            String mainphone, String supportphone,
                            String copyright, String forwardURL)
   {
      this.cid = cid;
      this.advisor = advisor;
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
      this.forwardURL = forwardURL;
   }


   public void resetTheme(String theme) {
      if (theme != null) {
         theme = theme.trim();
         this.theme = theme;
         this.themelib = theme + "-layout";
      }
      else {

         if (this.theme == null)
            this.theme = WebConst.DEFAULT_THEME;

         if (themelib == null)
            themelib = this.theme + "-layout";
      }

   }

   public void resetTheme(String theme, String library) {
      if (theme != null) {
         theme = theme.trim();
         this.theme = theme;
      }

      if (library != null) {
         library = library.trim();
         this.themelib = library;
      }
      else {
         if (this.theme == null)
            this.theme = WebConst.DEFAULT_THEME;

         if (themelib == null)
            themelib = this.theme + "-layout";
      }

   }

   public String getHomepage()
   {
      return homepage;
   }

   public void setHomepage(String homepage)
   {
      this.homepage = homepage;
   }

   public String getSecurehomepage()
   {
      return securehomepage;
   }

   public void setSecurehomepage(String securehomepage)
   {
      this.securehomepage = securehomepage;
   }
}
