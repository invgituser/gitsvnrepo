package com.invessence.service.bean.docuSign;

/**
 * Created by abhangp on 9/8/2016.
 */
public class DCDocumentMapping
{
   private String docCode;
   private String tab;
   private String lable;
   private String role;
   private String dbColumn;
   private String pageNumber;
   private String xPosition;
   private String yPosition;
   private int width;
   private int height;


   public String getDbColumn()
   {
      return dbColumn;
   }

   public void setDbColumn(String dbColumn)
   {
      this.dbColumn = dbColumn;
   }

   @Override
   public String toString()
   {
      return "DCDocumentMapping{" +
         "docCode='" + docCode + '\'' +
         ", tab='" + tab + '\'' +
         ", lable='" + lable + '\'' +
         ", role='" + role + '\'' +
         ", dbColumn='" + dbColumn + '\'' +
         ", pageNumber='" + pageNumber + '\'' +
         ", xPosition='" + xPosition + '\'' +
         ", yPosition='" + yPosition + '\'' +
         ", width=" + width +
         ", height=" + height +
         '}';
   }

   public String getDocCode()
   {
      return docCode;
   }

   public void setDocCode(String docCode)
   {
      this.docCode = docCode;
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

   public String getRole()
   {
      return role;
   }

   public void setRole(String role)
   {
      this.role = role;
   }

   public String getPageNumber()
   {
      return pageNumber;
   }

   public void setPageNumber(String pageNumber)
   {
      this.pageNumber = pageNumber;
   }

   public String getxPosition()
   {
      return xPosition;
   }

   public void setxPosition(String xPosition)
   {
      this.xPosition = xPosition;
   }

   public String getyPosition()
   {
      return yPosition;
   }

   public void setyPosition(String yPosition)
   {
      this.yPosition = yPosition;
   }

   public int getWidth()
   {
      return width;
   }

   public void setWidth(int width)
   {
      this.width = width;
   }

   public int getHeight()
   {
      return height;
   }

   public void setHeight(int height)
   {
      this.height = height;
   }
}
