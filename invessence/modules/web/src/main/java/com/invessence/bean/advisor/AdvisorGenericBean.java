package com.invessence.bean.advisor;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;

@ManagedBean(name = "advisorGenericBean")
@ApplicationScoped
public class AdvisorGenericBean implements Serializable
{
   private static final long serialVersionUID = 100000L;

   @PostConstruct
   public void init()
   {
   }

}