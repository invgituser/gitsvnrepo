package com.invessence.service.bean.fileProcessor;

/**
 * Created by abhangp on 4/20/2017.
 */
public class FileRules
{
   private String fileId;
   private String dataField;
   private String description;
   private Integer seqNo;
   private Integer startPos;
   private Integer endPos;
   private Integer length;
   private String format;
   private Integer decimals;
   private String isDelimited;
   private String delimiter;
   private String justified;
   private String dbColumn;
   private String isRequired;
   private String needToEncrypt;

   public FileRules(String fileId, String dataField, String description, Integer seqNo, Integer startPos, Integer endPos, Integer length, String format, Integer decimals, String isDelimited, String delimiter, String justified, String dbColumn, String isRequired, String needToEncrypt)
   {
      this.fileId = fileId;
      this.dataField = dataField;
      this.description = description;
      this.seqNo = seqNo;
      this.startPos = startPos;
      this.endPos = endPos;
      this.length = length;
      this.format = format;
      this.decimals = decimals;
      this.isDelimited = isDelimited;
      this.delimiter = delimiter;
      this.justified = justified;
      this.dbColumn = dbColumn;
      this.isRequired = isRequired;
      this.needToEncrypt = needToEncrypt;
   }

   @Override
   public String toString()
   {
      return "FileRules{" +
         "fileId='" + fileId + '\'' +
         ", dataField='" + dataField + '\'' +
         ", description='" + description + '\'' +
         ", seqNo=" + seqNo +
         ", startPos=" + startPos +
         ", endPos=" + endPos +
         ", length=" + length +
         ", format='" + format + '\'' +
         ", decimals=" + decimals +
         ", isDelimited='" + isDelimited + '\'' +
         ", delimiter='" + delimiter + '\'' +
         ", justified='" + justified + '\'' +
         ", dbColumn='" + dbColumn + '\'' +
         ", isRequired='" + isRequired + '\'' +
         ", needToEncrypt='" + needToEncrypt + '\'' +
         '}';
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

   public Integer getSeqNo()
   {
      return seqNo;
   }

   public void setSeqNo(Integer seqNo)
   {
      this.seqNo = seqNo;
   }

   public Integer getStartPos()
   {
      return startPos;
   }

   public void setStartPos(Integer startPos)
   {
      this.startPos = startPos;
   }

   public Integer getEndPos()
   {
      return endPos;
   }

   public void setEndPos(Integer endPos)
   {
      this.endPos = endPos;
   }

   public Integer getLength()
   {
      return length;
   }

   public void setLength(Integer length)
   {
      this.length = length;
   }

   public String getFormat()
   {
      return format;
   }

   public void setFormat(String format)
   {
      this.format = format;
   }

   public Integer getDecimals()
   {
      return decimals;
   }

   public void setDecimals(Integer decimals)
   {
      this.decimals = decimals;
   }

   public String getIsDelimited()
   {
      return isDelimited;
   }

   public void setIsDelimited(String isDelimited)
   {
      this.isDelimited = isDelimited;
   }

   public String getDelimiter()
   {
      return delimiter;
   }

   public void setDelimiter(String delimiter)
   {
      this.delimiter = delimiter;
   }

   public String getJustified()
   {
      return justified;
   }

   public void setJustified(String justified)
   {
      this.justified = justified;
   }

   public String getDbColumn()
   {
      return dbColumn;
   }

   public void setDbColumn(String dbColumn)
   {
      this.dbColumn = dbColumn;
   }
}
