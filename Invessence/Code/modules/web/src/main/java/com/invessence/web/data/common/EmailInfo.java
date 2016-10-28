package com.invessence.web.data.common;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 9/21/16
 * Time: 6:18 PM
 * To change this template use File | Settings | File Templates.
 */
public class EmailInfo
{
   private String subject;
   private String template;
   private String format;

   public EmailInfo()
   {
   }

   public EmailInfo(String subject, String template, String format)
   {
      this.subject = subject;
      this.template = template;
      this.format = format;
   }

   public String getSubject()
   {
      return subject;
   }

   public void setSubject(String subject)
   {
      this.subject = subject;
   }

   public String getTemplate()
   {
      return template;
   }

   public void setTemplate(String template)
   {
      this.template = template;
   }

   public String getFormat()
   {
      return format;
   }

   public void setFormat(String format)
   {
      this.format = format;
   }
}
