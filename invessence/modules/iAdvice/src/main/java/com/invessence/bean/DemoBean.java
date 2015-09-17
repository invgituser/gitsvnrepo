package com.invessence.bean;

import java.io.Serializable;
import java.util.*;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;

import com.invessence.util.*;


/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 2/4/15
 * Time: 1:18 PM
 * To change this template use File | Settings | File Templates.
 */

@ManagedBean(name = "demobean")
@SessionScoped
public class DemoBean implements Serializable
{
   private String cid;
   private String advisor;
   private WebUtil webutil = new WebUtil();

   @ManagedProperty("#{uiLayout}")
   private UILayout uilayout;
   public void setUilayout(UILayout uilayout)
   {
      this.uilayout = uilayout;
   }

   public UILayout getUilayout()
   {
      return uilayout;
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

   public void preRenderView()
   {
      try
      {
         if (!FacesContext.getCurrentInstance().isPostback())
         {
            if (uilayout != null) {
               if (cid == null) {
                  uilayout.resetCIDProfile("1");
               }
               else {
                  uilayout.resetCIDProfile(cid);
               }
            }
            Map<String, String> obj = new HashMap<String, String>();
            obj.put("mode","Demo");
            webutil.redirect("/start.xhtml", obj);
         }
      }
      catch (Exception ex)
      {
         ex.printStackTrace();
      }
   }

   public void redirect() {
      Map<String, String> obj = new HashMap<String, String>();
      obj.put("mode","Demo");
      webutil.redirect("/start.xhtml", obj);
   }

}

