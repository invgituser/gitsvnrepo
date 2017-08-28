package com.invessence.web.data.common;

/**
 * Created by sagar on 8/22/2017.
 */
public class AdvisorBasket
{
   private String theme;
   private String basket;

   public AdvisorBasket(String theme,String basket){

      this.theme = theme;
      this.basket = basket;
   }

   public String getTheme()
   {
      return theme;
   }

   public void setTheme(String theme)
   {
      this.theme = theme;
   }

   public String getBasket()
   {
      return basket;
   }

   public void setBasket(String basket)
   {
      this.basket = basket;
   }
}
