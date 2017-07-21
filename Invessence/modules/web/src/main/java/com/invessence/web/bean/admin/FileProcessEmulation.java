package com.invessence.web.bean.admin;

import java.io.IOException;
import java.util.List;
import javax.faces.bean.*;
import javax.servlet.ServletException;

import com.invessence.service.bean.WSCallResult;
import com.invessence.service.bean.fileProcessor.FileDetails;
import com.invessence.web.dao.admin.AdminEmulationSpDAO;
import com.invessence.web.util.*;

/**
 * Created by sagar on 6/13/2017.
 */

@ManagedBean(name = "filePrcsEmul")
public class FileProcessEmulation
{
   private String businessDate;
   private String processId;
   private String errorMessage;
   @ManagedProperty("#{adminEmulationSpDAO}")
   private AdminEmulationSpDAO adminEmulationSpDAO;
   @ManagedProperty("#{webutil}")
   private WebUtil webutil;
   @ManagedProperty("#{filesIO}")
   private FilesIO fileIO;

   public String processRequest() throws ServletException, IOException
   {
      String response="";
      try
      {
         System.out.print("processAccount businessDate" + businessDate);
         System.out.print("processAccount processId " + processId);
         String product = webutil.getWebprofile().getWebInfo().get("SERVICE.PRODUCT");
         String serviceMode = webutil.getWebprofile().getWebInfo().get("SERVICE.FILEPROCESS.MODE");
         getAdminEmulationSpDAO().updateBusinessDate(businessDate);
         System.out.print("processAccount product" + product);
         System.out.print("processAccount serviceMode " + serviceMode);


         WSCallResult wsCallResult = fileIO.processDownloadFile(processId, product, serviceMode);
         System.out.println("result = " + wsCallResult);
         if(wsCallResult!=null && wsCallResult.getWSCallStatus()!=null){
            if(wsCallResult.getWSCallStatus().getErrorCode()==0){
               errorMessage="success";
               this.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.uob.fileProcessing.status.done", "successfully", null));
            }else{
               errorMessage="failed";
               this.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.uob.fileProcessing.status.failed", "failed", null));
            }
//
//            if(wsCallResult.getGenericObject()==null || ((List<FileDetails>)wsCallResult.getGenericObject()).size()==0){
//               System.out.println("List is empty");
//            }else{
//               List<FileDetails> fileList =(List<FileDetails>)wsCallResult.getGenericObject();
//               System.out.println("fileList = " + fileList);
//            }
         }else{
            errorMessage="failed";
            this.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.uob.fileProcessing.status.failed", "failed", null));
         }

//
//         boolean bflag=fileIO.processDownloadFile(processId, product, serviceMode);
//         if(bflag)
//         {
//            errorMessage="success";
//            this.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.uob.fileProcessing.status.done", "successfully", null));
//         }else{
//            errorMessage="failed";
//            this.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.uob.fileProcessing.status.failed", "failed", null));
//         }
      }
      catch (Exception e)
      {
         System.out.println("Error e");
      }
      return "failed";
   }

   public String getBusinessDate()
   {
      return businessDate;
   }

   public void setBusinessDate(String businessDate)
   {
      this.businessDate = businessDate;
   }

   public String getProcessId()
   {
      return processId;
   }

   public void setProcessId(String processId)
   {
      this.processId = processId;
   }

   public String getErrorMessage()
   {
      return errorMessage;
   }

   public void setErrorMessage(String errorMessage)
   {
      this.errorMessage = errorMessage;
   }

   public WebUtil getWebutil()
   {
      return webutil;
   }

   public void setWebutil(WebUtil webutil)
   {
      this.webutil = webutil;
   }

   public FilesIO getFileIO()
   {
      return fileIO;
   }

   public void setFileIO(FilesIO fileIO)
   {
      this.fileIO = fileIO;
   }

   public AdminEmulationSpDAO getAdminEmulationSpDAO()
   {
      return adminEmulationSpDAO;
   }

   public void setAdminEmulationSpDAO(AdminEmulationSpDAO adminEmulationSpDAO)
   {
      this.adminEmulationSpDAO = adminEmulationSpDAO;
   }
}
