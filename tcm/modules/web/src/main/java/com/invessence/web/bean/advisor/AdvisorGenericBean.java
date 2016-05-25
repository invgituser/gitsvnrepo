package com.invessence.web.bean.advisor;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.*;

@ManagedBean(name = "advisorGenericBean")
@SessionScoped
public class AdvisorGenericBean implements Serializable
{
   private static final long serialVersionUID = 100000L;

   @PostConstruct
   public void init()
   {
   }

}