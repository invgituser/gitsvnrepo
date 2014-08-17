package com.invessence.data;

public class MsgData
{

   private int msgID = 0;
   private long logonID = 0;
   private String source = null;
   private String sender = null;
   private String receiver = null;
   private String cc = null;
   private String bcc = null;
   private String subject = null;
   private String msg = null;
   private int status = 0;
   private int category = 0;
   private int priority = 1;
   private String enteredDate = null;
   private String updatedDate = null;
   private String sentDate = null;
   private String comment = null;
   private String attachmentFile = null;

   public int getMsgID()
   {
      return msgID;
   }

   public void setMsgID(int msgID)
   {
      this.msgID = msgID;
   }

   public String getSource()
   {
      return source;
   }

   public void setSource(String source)
   {
      this.source = source;
   }

   public String getSender()
   {
      return sender;
   }

   public void setSender(String sender)
   {
      this.sender = sender;
   }

   public String getReceiver()
   {
      return receiver;
   }

   public void setReceiver(String receiver)
   {
      this.receiver = receiver;
   }

   public String getCc()
   {
      return cc;
   }

   public void setCc(String cc)
   {
      this.cc = cc;
   }

   public String getBcc()
   {
      return bcc;
   }

   public void setBcc(String bcc)
   {
      this.bcc = bcc;
   }

   public String getMsg()
   {
      return msg;
   }

   public void setMsg(String msg)
   {
      this.msg = msg;
   }

   public int getStatus()
   {
      return status;
   }

   public void setStatus(int status)
   {
      this.status = status;
   }

   public static void main(String[] args)
   {
      System.out.println("TEST");
   }

   public int getCategory()
   {
      return category;
   }

   public void setCategory(int category)
   {
      this.category = category;
   }

   public int getPriority()
   {
      return priority;
   }

   public void setPriority(int priority)
   {
      this.priority = priority;
   }

   public String getSubject()
   {
      return subject;
   }

   public void setSubject(String subject)
   {
      this.subject = subject;
   }

   public String getComment()
   {
      return comment;
   }

   public void setComment(String comment)
   {
      this.comment = comment;
   }

   public String getEnteredDate()
   {
      return enteredDate;
   }

   public void setEnteredDate(String enteredDate)
   {
      this.enteredDate = enteredDate;
   }

   public String getUpdatedDate()
   {
      return updatedDate;
   }

   public void setUpdatedDate(String updatedDate)
   {
      this.updatedDate = updatedDate;
   }

   public String getSentDate()
   {
      return sentDate;
   }

   public void setSentDate(String sentDate)
   {
      this.sentDate = sentDate;
   }

   public String getAttachmentFile()
   {
      return attachmentFile;
   }

   public void setAttachmentFile(String attachmentFile)
   {
      this.attachmentFile = attachmentFile;
   }

   public long getLogonID()
   {
      return logonID;
   }

   public void setLogonID(long logonID)
   {
      this.logonID = logonID;
   }


}

