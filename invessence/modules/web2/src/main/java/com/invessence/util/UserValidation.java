package com.invessence.util;

import java.util.*;

import com.invessence.constant.Const;
import com.invessence.data.UserInfoData;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.*;

import static javax.faces.context.FacesContext.getCurrentInstance;


public class UserValidation {
	
    public static boolean isValidEmail(String emailID) {

        if ( (Util.isNull(emailID)) ||
             (emailID.indexOf('.') == -1) ||
             (emailID.indexOf('@') == -1) )  {
            return false;

        }  else  {
            return true;
        }
    }



    public static boolean isValidPassword(String password) {

        if ( (StringUtils.isEmpty(password)) ||
              (password.length() < 6) ) {
            return false;
        }  else {
            return true;
        }

    }

   public void validateSession()
   {
      try {
         if (getCurrentInstance().getExternalContext().getSessionMap().get(Const.LOGONID_PARAM) == null)
         {
            getCurrentInstance().getExternalContext().redirect("/login.xhtml");
         }
         //collectData();
      }
      catch (Exception ex) {

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

