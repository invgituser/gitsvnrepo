package com.invessence.bean;

import java.io.Serializable;
import java.util.*;
import javax.faces.bean.*;

import com.invessence.data.TestData;
import com.invessence.util.UserValidation;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 8/26/14
 * Time: 1:16 PM
 * To change this template use File | Settings | File Templates.
 */
@ManagedBean(name = "testBean")
@SessionScoped
public class TabBean extends TestData implements Serializable {
   UserValidation uv = new UserValidation();


   public String testMessage() {
      String spMsg = "";
      Map<String,String> args = new HashMap<String, String>();
      try {
         args.put("message",getMsgBody());
         args.put("type",getMsgType());
         args.put("title",getMsgTitle());
         uv.redirect("\\message.xhtml?faces-redirect=true",args);
      }
      catch (Exception ex) {
         args.put("message","Hello");
         args.put("type","Warning");
         args.put("title","Hello");
         uv.redirect("\\message.xhtml?faces-redirect=true",args);

      }
      return "Success";
   }

}
