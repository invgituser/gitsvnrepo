package com.invessence.bean.advisor;

import java.io.Serializable;
import javax.faces.bean.*;

import com.invessence.constant.Const;
import com.invessence.data.UserInfoData;

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
               institutionImage = uid.getLogo();
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
}
