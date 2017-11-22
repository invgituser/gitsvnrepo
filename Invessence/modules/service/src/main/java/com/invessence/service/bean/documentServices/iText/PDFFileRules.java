package com.invessence.service.bean.documentServices.iText;

/**
 * Created by abhangp on 11/16/2017.
 */
public class PDFFileRules
{
   private String fileId;
   private String dataField;
   private String description;
   private Integer pageNo;
   private Integer xcord;
   private Integer ycord;
   private Integer length;
   private String dbColumn;
   private String role;
   private String isRequired;
   private String needToEncrypt;

   public PDFFileRules(String fileId, String dataField, String description, Integer pageNo, Integer xcord, Integer ycord, Integer length, String dbColumn, String role, String isRequired, String needToEncrypt)
   {
      this.fileId = fileId;
      this.dataField = dataField;
      this.description = description;
      this.pageNo = pageNo;
      this.xcord = xcord;
      this.ycord = ycord;
      this.length = length;
      this.dbColumn = dbColumn;
      this.role=role;
      this.isRequired = isRequired;
      this.needToEncrypt = needToEncrypt;
   }

   @Override
   public String toString()
   {
      return "PDFFileRules{" +
         "fileId='" + fileId + '\'' +
         ", dataField='" + dataField + '\'' +
         ", description='" + description + '\'' +
         ", pageNo=" + pageNo +
         ", xcord=" + xcord +
         ", ycord=" + ycord +
         ", length=" + length +
         ", dbColumn='" + dbColumn + '\'' +
         ", role='" + role + '\'' +
         ", isRequired='" + isRequired + '\'' +
         ", needToEncrypt='" + needToEncrypt + '\'' +
         '}';
   }

   public String getRole()
   {
      return role;
   }

   public void setRole(String role)
   {
      this.role = role;
   }

   public String getFileId()
   {
      return fileId;
   }

   public void setFileId(String fileId)
   {
      this.fileId = fileId;
   }

   public String getDataField()
   {
      return dataField;
   }

   public void setDataField(String dataField)
   {
      this.dataField = dataField;
   }

   public String getDescription()
   {
      return description;
   }

   public void setDescription(String description)
   {
      this.description = description;
   }

   public Integer getPageNo()
   {
      return pageNo;
   }

   public void setPageNo(Integer pageNo)
   {
      this.pageNo = pageNo;
   }

   public Integer getXcord()
   {
      return xcord;
   }

   public void setXcord(Integer xcord)
   {
      this.xcord = xcord;
   }

   public Integer getYcord()
   {
      return ycord;
   }

   public void setYcord(Integer ycord)
   {
      this.ycord = ycord;
   }

   public Integer getLength()
   {
      return length;
   }

   public void setLength(Integer length)
   {
      this.length = length;
   }

   public String getDbColumn()
   {
      return dbColumn;
   }

   public void setDbColumn(String dbColumn)
   {
      this.dbColumn = dbColumn;
   }

   public String getIsRequired()
   {
      return isRequired;
   }

   public void setIsRequired(String isRequired)
   {
      this.isRequired = isRequired;
   }

   public String getNeedToEncrypt()
   {
      return needToEncrypt;
   }

   public void setNeedToEncrypt(String needToEncrypt)
   {
      this.needToEncrypt = needToEncrypt;
   }
}
