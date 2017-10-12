package com.invessence.web.data.common;

/**
 * Created by prashant on 10/4/2017.
 */
public class BasketInfo
{
   private String advisor;
   private String theme;
   private String status;
   private String displayname;
   private String sortorder;
   private String primary;
   private String taxable;
   private String model;
   private String baseCurrency;

   public BasketInfo()
   {
   }

   public BasketInfo(String advisor, String theme, String status, String displayname,
                     String sortorder, String primary, String taxable, String model,
                     String baseCurrency)
   {
      this.advisor = advisor;
      this.theme = theme;
      this.status = status;
      this.displayname = displayname;
      this.sortorder = sortorder;
      this.primary = primary;
      this.taxable = taxable;
      this.model = model;
      this.baseCurrency = baseCurrency;
   }

   public String getAdvisor()
   {
      return advisor;
   }

   public void setAdvisor(String advisor)
   {
      this.advisor = advisor;
   }

   public String getTheme()
   {
      return theme;
   }

   public void setTheme(String theme)
   {
      this.theme = theme;
   }

   public String getStatus()
   {
      return status;
   }

   public void setStatus(String status)
   {
      this.status = status;
   }

   public String getDisplayname()
   {
      return displayname;
   }

   public void setDisplayname(String displayname)
   {
      this.displayname = displayname;
   }

   public String getSortorder()
   {
      return sortorder;
   }

   public void setSortorder(String sortorder)
   {
      this.sortorder = sortorder;
   }

   public String getPrimary()
   {
      return primary;
   }

   public void setPrimary(String primary)
   {
      this.primary = primary;
   }

   public String getTaxable()
   {
      return taxable;
   }

   public void setTaxable(String taxable)
   {
      this.taxable = taxable;
   }

   public String getModel()
   {
      return model;
   }

   public void setModel(String model)
   {
      this.model = model;
   }

   public String getBaseCurrency()
   {
      return baseCurrency;
   }

   public void setBaseCurrency(String baseCurrency)
   {
      this.baseCurrency = baseCurrency;
   }
}
