package com.invessence.web.data.consumer;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 10/12/15
 * Time: 8:54 PM
 * To change this template use File | Settings | File Templates.
 */
public class ReportData
{
   private String acctnum;
   private String source;
   private String reportName;
   private String businessdate;
   private String filename;
   private Boolean viewReport;
   private Boolean downloadReport;

   public ReportData()
   {
   }

   public String getAcctnum()
   {
      return acctnum;
   }

   public void setAcctnum(String acctnum)
   {
      this.acctnum = acctnum;
   }

   public String getSource()
   {
      return source;
   }

   public void setSource(String source)
   {
      this.source = source;
   }

   public String getReportName()
   {
      return reportName;
   }

   public void setReportName(String reportName)
   {
      this.reportName = reportName;
   }

   public String getBusinessdate()
   {
      return businessdate;
   }

   public void setBusinessdate(String businessdate)
   {
      this.businessdate = businessdate;
   }

   public String getFilename()
   {
      return filename;
   }

   public void setFilename(String filename)
   {
      this.filename = filename;
   }

   public Boolean getViewReport()
   {
      return viewReport;
   }

   public void setViewReport(Boolean viewReport)
   {
      this.viewReport = viewReport;
   }

   public Boolean getDownloadReport()
   {
      return downloadReport;
   }

   public void setDownloadReport(Boolean downloadReport)
   {
      this.downloadReport = downloadReport;
   }

   public Boolean getIsPdf()
   {
      if (this.filename != null) {
         if (this.filename.toUpperCase().endsWith("PDF"))
            return true;
      }
      return false;

   }
}
