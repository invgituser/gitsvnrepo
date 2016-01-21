package com.invessence.data;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 1/20/16
 * Time: 8:33 PM
 * To change this template use File | Settings | File Templates.
 */

import java.io.Serializable;

public class InvitedGuestData implements Serializable
{
   private String email = null;
   private String name = null;
   private String weburl = null;

   public String getEmail()
   {
      return email;
   }

   public void setEmail(String email)
   {
      this.email = email;
   }

   public String getName()
   {
      return name;
   }

   public void setName(String name)
   {
      this.name = name;
   }

   public String getWeburl()
   {
      return weburl;
   }

   public void setWeburl(String weburl)
   {
      this.weburl = weburl;
   }
}
