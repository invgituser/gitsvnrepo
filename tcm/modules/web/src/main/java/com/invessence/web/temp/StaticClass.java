package com.invessence.web.temp;

import javax.faces.bean.*;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 6/6/16
 * Time: 9:48 AM
 * To change this template use File | Settings | File Templates.
 */



@ManagedBean(name = "staticclass")
@SessionScoped
public class StaticClass
{
   private String applicationLogo;

   public String getApplicationLogo()
   {                       // return hash.get(cId).get
      return applicationLogo;
   }

   public void setApplicationLogo(String applicationLogo)
   {
      this.applicationLogo = applicationLogo;
   }



//   static{
//      //Load property from
//
//
//
//   }


}
