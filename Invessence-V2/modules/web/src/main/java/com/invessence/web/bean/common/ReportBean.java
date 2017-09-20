package com.invessence.web.bean.common;

import java.io.*;
import java.util.*;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;

import com.invessence.web.dao.consumer.ConsumerListDataDAO;
import com.invessence.web.data.consumer.ReportData;
import com.invessence.web.util.*;
import org.primefaces.context.RequestContext;
import org.primefaces.model.*;

@ManagedBean(name = "reportBean")
@SessionScoped
public class ReportBean implements Serializable
{
   private ReportData selectReport;
   private Map<String, String> accountList = new HashMap<String, String>();
   private ArrayList<ReportData> reportList;
   private ArrayList<ReportData> filterReportList;
   private String account;
   private String fromDate, toDate;
   private String reportFilter;

   @ManagedProperty("#{webutil}")
   private WebUtil webutil;
   public void setWebutil(WebUtil webutil)
   {
      this.webutil = webutil;
   }

   @ManagedProperty("#{consumerListDataDAO}")
   private ConsumerListDataDAO listDAO;
   public void setListDAO(ConsumerListDataDAO listDAO)
   {
      this.listDAO = listDAO;
   }

   public ReportData getSelectReport()
   {
      return selectReport;
   }

   public void setSelectReport(ReportData selectReport)
   {
      this.selectReport = selectReport;
   }

   public Map<String, String> getAccountList()
   {
      return accountList;
   }

   public ArrayList<ReportData> getReportList()
   {
      return reportList;
   }

   public ArrayList<ReportData> getFilterReportList()
   {
      return filterReportList;
   }

   public void setFilterReportList(ArrayList<ReportData> filterReportList)
   {
      this.filterReportList = filterReportList;
   }

   public String getFromDate()
   {
      return fromDate;
   }

   public String getAccount()
   {
      return account;
   }

   public void setAccount(String account)
   {
      this.account = account;
   }

   public void setFromDate(String fromDate)
   {
      this.fromDate = fromDate;
   }

   public String getToDate()
   {
      return toDate;
   }

   public void setToDate(String toDate)
   {
      this.toDate = toDate;
   }

   public String getReportFilter()
   {
      return reportFilter;
   }

   public void setReportFilter(String reportFilter)
   {
      this.reportFilter = reportFilter;
   }

   public void preRenderView()
   {

      try
      {
         if (!FacesContext.getCurrentInstance().isPostback())
         {
            collectData();
         }
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
   }

   public void collectData() {
      try {
         Long logonid = webutil.getLogonid();
         reportList = listDAO.loadReports(logonid, fromDate, toDate);
         filterReportList = reportList;
         accountList.clear();
         for (ReportData rdata: reportList) {
           accountList.put(rdata.getAcctnum(),rdata.getAcctnum());
         }
      }
      catch (Exception ex) {

      }
   }

   public String getReportContent() {
      String reportContent = null;
      try {
         if (selectReport != null) {
            BufferedReader br = new BufferedReader(new FileReader(selectReport.getFilename()));
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
               sb.append(line);
               sb.append(System.lineSeparator());
               line = br.readLine();
            }
            reportContent = sb.toString();
            br.close();
         }
      }
      catch (Exception ex) {
      }
      return reportContent;
   }

   public void filterAccount() {
      if (account != null) {
         filterReportList.clear();
         for (ReportData rpt : reportList) {
            if (rpt.getAcctnum().equals(account)) {
               filterReportList.add(rpt);
            }
         }
      }
   }
   public String viewReport() {
      ReportData thisReport = selectReport;
      if (thisReport != null) {
         if (thisReport.getViewReport())
            RequestContext.getCurrentInstance().openDialog("creportDialog");
      }
      return "Success";
   }


   public void downloadReport(String filename) {
      StreamedContent file;

      InputStream stream = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("/resources/demo/images/optimus.jpg");
      file = new DefaultStreamedContent(stream, "application/pdf", filename);

    }
}