package com.invessence.service.bean;

/**
 * Created by abhangp on 8/16/2016.
 */
public class DCTemplateMapping
{
   private String tempCode;
   private String tab;
   private String lable;
   private String dbColumn;
   private String role;

   @Override
   public String toString()
   {
      return "DCTemplateMapping{" +
         "tempCode='" + tempCode + '\'' +
         ", tab='" + tab + '\'' +
         ", lable='" + lable + '\'' +
         ", dbColumn='" + dbColumn + '\'' +
         ", role='" + role + '\'' +
         '}';
   }

   public String getTempCode()
   {
      return tempCode;
   }

   public void setTempCode(String tempCode)
   {
      this.tempCode = tempCode;
   }

   public String getTab()
   {
      return tab;
   }

   public void setTab(String tab)
   {
      this.tab = tab;
   }

   public String getLable()
   {
      return lable;
   }

   public void setLable(String lable)
   {
      this.lable = lable;
   }

   public String getDbColumn()
   {
      return dbColumn;
   }

   public void setDbColumn(String dbColumn)
   {
      this.dbColumn = dbColumn;
   }

   public String getRole()
   {
      return role;
   }

   public void setRole(String role)
   {
      this.role = role;
   }
}
