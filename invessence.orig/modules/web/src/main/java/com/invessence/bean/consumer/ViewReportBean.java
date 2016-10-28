package com.invessence.bean.consumer;

import java.io.*;
import java.util.*;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;

import com.invessence.dao.consumer.ConsumerListDataDAO;
import com.invessence.data.consumer.ReportData;
import com.invessence.util.*;
import org.primefaces.context.RequestContext;

@ManagedBean(name = "viewReportBean")
@RequestScoped
public class ViewReportBean implements Serializable
{
   private String file;

   @ManagedProperty("#{webutil}")
   private WebUtil webutil;
   public void setWebutil(WebUtil webutil)
   {
      this.webutil = webutil;
   }

   public void setFile(String file)
   {
      this.file = file;
   }

   public void preRenderView()
   {

      try
      {
         if (!FacesContext.getCurrentInstance().isPostback())
         {
         }
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
   }

   public String getReportContent() {
      String reportContent = null;
      try {
         if (file != null) {
            BufferedReader br = new BufferedReader(new FileReader(file));
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

}