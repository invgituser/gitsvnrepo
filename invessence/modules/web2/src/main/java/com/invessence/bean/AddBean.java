package com.invessence.bean;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import com.invessence.data.*;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SlideEndEvent;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 1/15/15
 * Time: 11:42 PM
 * To change this template use File | Settings | File Templates.
 */
@ManagedBean(name = "addBean")
@SessionScoped
public class AddBean extends SamplerData implements Serializable
{

   @PostConstruct
   public void init() {
      RequestContext context = RequestContext.getCurrentInstance();
      context.execute("PF('custProfileDialog').show();");
   }

   public String refresh()
   {
      try
      {
      }
      catch (Exception ex)
      {
         ex.printStackTrace();
      }
      return "success";
   }

   public void onSlideEnd(SlideEndEvent event) {
      FacesMessage message = new FacesMessage("Slide Ended", "Value: " + event.getValue());
      FacesContext.getCurrentInstance().addMessage(null, message);
   }

   public void changeEvent(ValueChangeEvent event)
   {
      String oldValue = null;
      String newValue = null;
      try
      {
            if (event.getNewValue() == null)
            {
               return;
            }
      }
      catch (Exception ex)
      {
         ex.printStackTrace();
      }
   }

}
