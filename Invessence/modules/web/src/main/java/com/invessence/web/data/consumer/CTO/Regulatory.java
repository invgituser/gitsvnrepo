package com.invessence.web.data.consumer.CTO;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 7/14/15
 * Time: 9:49 PM
 * To change this template use File | Settings | File Templates.
 */
public class Regulatory
{
   String detailcode;
   String explaination;
   String additinalLink;
   String status;
   Boolean agree;

   public Regulatory()
   {
      agree = false;
   }

   public String getDetailcode()
   {
      return detailcode;
   }

   public void setDetailcode(String detailcode)
   {
      this.detailcode = detailcode;
   }

   public String getExplaination()
   {
      return explaination;
   }

   public void setExplaination(String explaination)
   {
      this.explaination = explaination;
   }

   public String getAdditinalLink()
   {
      return additinalLink;
   }

   public void setAdditinalLink(String additinalLink)
   {
      this.additinalLink = additinalLink;
   }

   public String getStatus()
   {
      return status;
   }

   public void setStatus(String status)
   {
      this.status = status;
   }

   public Boolean getAgree()
   {
      return agree;
   }

   public void setAgree(Boolean agree)
   {
      this.agree = agree;
   }
}
