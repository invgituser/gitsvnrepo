package com.invessence.bean.advisor;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.*;

@ManagedBean(name = "advisorGenericBean")
@SessionScoped
public class AdvisorGenericBean implements Serializable
{
   private static final long serialVersionUID = 100000L;
   private int menuBar = 0;
   private Long acctnum = null;

   @PostConstruct
   public void init()
   {
   }

   public int getMenuBar()
   {
      return menuBar;
   }

   public void setMenuBar(int menuBar)
   {
      this.menuBar = menuBar;
   }

   public Long getAcctnum()
   {
      return acctnum;
   }

   public void setAcctnum(Long acctnum)
   {
      this.acctnum = acctnum;
   }
}