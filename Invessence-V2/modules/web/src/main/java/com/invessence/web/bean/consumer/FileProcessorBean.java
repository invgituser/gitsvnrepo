package com.invessence.web.bean.consumer;

import java.io.*;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;

import com.invessence.aggr.bean.*;
import com.invessence.service.bean.ServiceRequest;
import com.invessence.web.service.aggr.*;
import com.invessence.web.service.fileProcessor.FileProcessWebLayer;
import com.invessence.web.util.*;
import org.primefaces.context.RequestContext;
import org.primefaces.model.*;

/**
 * Created by abhangp on 05/19/2017.
 */
@ManagedBean(name = "fileProcessBean")
@SessionScoped
public class FileProcessorBean
{
   @ManagedProperty("#{webutil}")
   private WebUtil webutil;
   @ManagedProperty("#{fileProcessWebLayer}")
   private FileProcessWebLayer fileProcessWebLayer;

   public FileProcessWebLayer getFileProcessWebLayer()
   {
      return fileProcessWebLayer;
   }

   public void setFileProcessWebLayer(FileProcessWebLayer fileProcessWebLayer)
   {
      this.fileProcessWebLayer = fileProcessWebLayer;
   }

   public WebUtil getWebutil()
   {
      return webutil;
   }

   public void setWebutil(WebUtil webutil)
   {
      this.webutil = webutil;
   }

   @ManagedProperty("#{uiLayout}")
   public UILayout uiLayout;

   public UILayout getUiLayout()
   {
      return uiLayout;
   }

   public void setUiLayout(UILayout uiLayout)
   {
      this.uiLayout = uiLayout;
   }

   private Long logonid;

   public Long getLogonid()
   {
      return logonid;
   }

   public void setLogonid(Long logonid)
   {
      this.logonid = logonid;
   }

// Need to return the list of file
   public void processDownloadFile(String processId){
      System.out.println("AggregationBean.startup");
      String product = getWebutil().getWebprofile().getWebInfo().get("SERVICE.PRODUCT").toString();
      String serviceMode = getWebutil().getWebprofile().getWebInfo().get("SERVICE.FILEPROCESS.MODE").toString();
//      String processId= getWebutil().getWebprofile().getWebInfo().get("SERVICE.FILEPROCESS.UPLOADPROCESSID").toString();
      System.out.println("Product " + product);
      System.out.println("ServiceMode " + serviceMode);
      System.out.println("ProcessId = " + processId);
      try {
         if (logonid == null) {
            logonid = webutil.getLogonid();
         }

         boolean  result=fileProcessWebLayer.processFile(new ServiceRequest(product, serviceMode, processId));
         System.out.println("result = " + result);

//         if(result==true){
//            System.out.println("Go for MX Registration");
//            uiLayout.doMenuAction("consumer", "/.xhtml");
//         }else {
//            System.out.println("Go for widget window");
//            uiLayout.doMenuAction("consumer", "/.xhtml");
//         }

      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   public StreamedContent downloadFile(String processId) {
      StreamedContent file = null;
      try {
         InputStream stream = new FileInputStream(new File(processId))
         {
            @Override
            public int read() throws IOException
            {
               return 0;
            }
         };
         stream.close();
         file = new DefaultStreamedContent(stream, "application/csv", processId);
         // application/zip for zip file
         return file;
      }
      catch (Exception ex) {

      }
      return file;
   }
}
