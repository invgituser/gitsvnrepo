package com.invessence.web.util;


/* Prashant 1/7/2014 UserValidation and Util are merged into WebUtil
public class UserValidation
{

   public void validateSession()
   {
      try {
         if (getCurrentInstance().getExternalContext().getSessionMap().get(Const.LOGONID_PARAM) == null)
         {
            redirect("/login.xhtml", null);
         }
         //collectData();
      }
      catch (Exception ex) {
         String url="/message.xhtml?faces-redirect=true&type=Error&title=mtse&message=mbse";
         redirect(url,null);
      }
   }

   public String getMode() {
      return ("Prod");
   }

   public Long getAcctnum() {

      try {
         Long acctnum = (Long) getCurrentInstance().getExternalContext().getSessionMap().get(Const.ACCTNO_PARAM);

         return acctnum;
      }
      catch (Exception ex) {
         String url="/message.xhtml?faces-redirect=true&type=Error&title=mtse&message=mbse";
         redirect(url,null);
         System.out.println("Warning: Context data is null, user must not be logged on.");
      }
      return null;
   }

   public void setAcctnum(Long acctnum) {

      try {
         getCurrentInstance().getExternalContext().getSessionMap().put(Const.ACCTNO_PARAM, acctnum);

      }
      catch (Exception ex) {
         String url="/message.xhtml?faces-redirect=true&type=Error&title=mtse&message=mbse";
         redirect(url,null);
         System.out.println("Warning: Context data is null, user must not be logged on.");
      }
   }


   public UserInfoData getUserInfoData() {

      try {
         Authentication auth = (Authentication) ((SecurityContext)
            SecurityContextHolder.getContext()).getAuthentication();

         if ( (auth != null) && (auth.getPrincipal() instanceof UserInfoData )) {
            return (UserInfoData) auth.getPrincipal();

         } else {
            return null;
         }

      }
      catch (Exception ex) {
         String url="/message.xhtml?faces-redirect=true&type=Error&title=mtse&message=mbse";
         redirect(url,null);
         System.out.println("Warning: Context data is null, user must not be logged on.");
      }
      return null;

   }

   public void redirect(String url, Map obj) {
      String strToPass = "";
      try {
         if (url == null)
            return;
         if (obj != null) {
            String key, val,delimiter;
            Iterator it = obj.entrySet().iterator();
            while (it.hasNext())
            {
               Map.Entry pairs = (Map.Entry) it.next();
               key = pairs.getKey().toString();
               val = pairs.getValue().toString();
               if (strToPass.contains("?") || url.contains("?"))
                  delimiter = "&";
               else
                  delimiter = "?";

               strToPass = strToPass + delimiter +
                     key + "=" + val;
            }
         }
         //System.out.println("Redirecting to:" + url + strToPass);
         getCurrentInstance().getExternalContext().redirect(url + strToPass );
      }
      catch (Exception ex) {
      }
   }
}

*/
