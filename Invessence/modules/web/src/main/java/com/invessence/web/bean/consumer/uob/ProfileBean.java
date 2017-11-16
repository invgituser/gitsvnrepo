package com.invessence.web.bean.consumer.uob;

import javax.faces.bean.*;

import com.invessence.web.bean.consumer.PortfolioCreationUI;
import com.invessence.web.util.Logger;

/**
 * Created by prashant on 11/16/2017.
 */

@ManagedBean(name = "uobProfileBean")
@SessionScoped
public class ProfileBean extends PortfolioCreationUI implements Logger
{


   public void preRenderView()
   {

      try
      {
         System.out.println("Testing UOB Portfolio Creation...");
      }
      catch (Exception ex)
      {
      }
   }
}
