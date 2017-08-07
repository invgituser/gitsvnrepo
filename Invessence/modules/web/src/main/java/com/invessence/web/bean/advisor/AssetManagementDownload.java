package com.invessence.web.bean.advisor;

import java.io.*;
import java.util.*;
import javax.faces.bean.*;
//import javax.faces.context.FacesContext;
//import javax.wsdl.Input;
//
//import com.invessence.fileProcessor.bean.DBParameters;
//import com.invessence.service.bean.ServiceRequest;
//import com.invessence.service.bean.fileProcessor.*;
//import com.invessence.service.util.Constant;
import com.invessence.web.bean.custody.BaseTD;
import com.invessence.web.dao.advisor.AdvisorListDataDAO;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

@ManagedBean(name = "AssetManagement")
/**
 * Created by sagar on 7/10/2017.
 */
public class AssetManagementDownload extends BaseTD
{
   @ManagedProperty("#{advisorListDataDAO}")
   private AdvisorListDataDAO advisorListDataDAO;
   private StreamedContent file;


   public AssetManagementDownload()
   {
   }

   public StreamedContent getFile(String model,String rqstFileNm)
   {
      List<String> lFileData = null;
      File dataFile = null;
      FileWriter fr = null;
      BufferedWriter br = null;
      InputStream stream = null;
      File fileStreamObj = null;
      String theme;
      try
      {
         theme=getWebutil().getWebprofile().getWebInfo().get("DEFAULT.MODEL").toString();
         lFileData = advisorListDataDAO.getAssetAllocFile(model,rqstFileNm,theme);
         dataFile = new File("filename_1.txt");
         fr = new FileWriter(dataFile.getAbsoluteFile());
         br = new BufferedWriter(fr);
         for (int i = 0; i < lFileData.size(); i++)
         {
            br.write(lFileData.get(i));
            br.newLine();
         }
         br.close();
         fr.close();
         fileStreamObj = new File(dataFile.getAbsolutePath());
         stream = new FileInputStream(fileStreamObj);
         file = new DefaultStreamedContent(stream, "application/csv", model+"_" + rqstFileNm + ".csv");
         System.out.print("hello" + rqstFileNm);
      }
      catch (Exception e)
      {
         System.out.print("hello" + e);
      }
      finally
      {
         try
         {
            theme = null;
            rqstFileNm = null;
            model = null;
            lFileData = null;
            dataFile = null;
            fr = null;
            br = null;
            stream = null;
            fileStreamObj = null;
         }
         catch (Exception e)
         {
         }
      }
      return file;
   }

   public AdvisorListDataDAO getAdvisorListDataDAO()
   {
      return advisorListDataDAO;
   }

   public void setAdvisorListDataDAO(AdvisorListDataDAO advisorListDataDAO)
   {
      this.advisorListDataDAO = advisorListDataDAO;
   }
}