package com.invessence.bean.advisor;

import java.io.*;
import java.text.*;
import java.util.*;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.servlet.http.HttpServletRequest;

import com.invessence.constant.Const;
import com.invessence.dao.advisor.*;
import com.invessence.data.*;
import com.invessence.data.advisor.*;
import com.invessence.util.*;
import com.invmodel.asset.data.AssetClass;
import com.invmodel.inputData.CustomAllocation;
import com.invmodel.portfolio.data.*;
import org.primefaces.event.*;
import org.primefaces.model.TreeNode;
import org.primefaces.model.chart.PieChartModel;

import static javax.faces.context.FacesContext.getCurrentInstance;
import javax.activation.MimetypesFileTypeMap;


@ManagedBean(name = "multiTradeBean")
@SessionScoped
public class MultiTradeBean extends AdvisorData implements Serializable
{
   private static final long serialVersionUID = 100001L;
   private Boolean enableTabs = false;
   private String filename, fileType;
   private InputStream inputstream;

   public Boolean getEnableTabs()
   {
      return enableTabs;
   }

   public void setEnableTabs(Boolean enableTabs)
   {
      this.enableTabs = enableTabs;
   }

   public String getFilename()
   {
      return filename;
   }

   public void setFilename(String filename)
   {
      this.filename = filename;
   }

   public String getFileType()
   {
      return fileType;
   }

   public void setFileType(String fileType)
   {
      this.fileType = fileType;
   }

   public InputStream getInputstream()
   {
      return inputstream;
   }

   public void setInputstream(InputStream inputstream)
   {
      this.inputstream = inputstream;
   }

   public void handleFileUpload(FileUploadEvent event) {

      try
      {
         setFilename(event.getFile().getFileName());
         setFileType(event.getFile().getContentType());
         setInputstream(event.getFile().getInputstream());
      }
      catch (IOException e)
      {
         e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
         setInputstream(null);
      }

      FacesMessage message = new FacesMessage("Succesful", getFilename() + " is uploaded.");
      FacesContext.getCurrentInstance().addMessage(null, message);
   }

   public String getFileType(String filename) {
      FilesIO fileinfo = new FilesIO();
      String filetype = "Not Defined";
      try {
         filetype = fileinfo.getContentType(filename);
      }
      catch (IOException ioex) {
         ioex.printStackTrace();
      }
      System.out.println("Filetype is: " + filetype);
      return filetype;
   }

   public String saveUploadDataFile()
   {
      try
      {
         FilesIO fileinfo = new FilesIO();
         Map<String, Object[][]> data;
         setFilename("C:\\tmp\\data\\Revised-RBSA.xls");
         String filetype = getFileType(getFilename());
         // Copy file first...

         data = null;

         if (filetype.contains("application")) {
            if (filetype.contains("excel")) {
               saveFile(getFilename(), "xls");
               data = fileinfo.readExcelFile(getFilename(),"xls",300,30);
            }
            else {
               saveFile(getFilename(), "xlsx");
               data = fileinfo.readExcelFile(getFilename(),"xlsx",300,30);

            }

         }
         if (filetype.contains("text")) {
            saveFile(getFilename(), "csv");
            data = fileinfo.readCSVFile(getFilename(),300,30);
         }

         FacesMessage message;
         if (data != null)
            message = new FacesMessage("File: " + getFilename() + " is uploaded with " + data.size() + " rows.");
         else
            message = new FacesMessage("File: " + getFilename() + " was not loaded!");

         FacesContext.getCurrentInstance().addMessage(null, message);

      }
      catch (Exception ex)
      {
        ex.printStackTrace();
      }
      return "success";
   }

   public void saveFile(String fileName, String extension) throws IOException{
      try {

         if (fileName == null)
            return;

         // write the inputStream to a FileOutputStream
         Properties properties = new Properties();
         HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();;
         InputStream propInput = null;

         InputStream in;
         String advisorfile;
         DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
         DateFormat timeFormat = new SimpleDateFormat("HHmmss");
         Date date = new Date();

         propInput = req.getSession().getServletContext().getResourceAsStream("/WEB-INF/classes/invessence.properties");
         properties.load(propInput);
         if (getAdvisor() == null)
            advisorfile = "Invessence.";
         else
            advisorfile = getAdvisor();

         String basedir = properties.getProperty("advisor.input.datadir") + dateFormat.format(date).toString() + "/";

         File dirname = new File(basedir);
         if (! dirname.exists() )
            dirname.mkdirs();

         advisorfile =  basedir + advisorfile + "-" + timeFormat.format(date).toString() + "." + extension;
         if (getInputstream() == null)
            in = new FileInputStream(getFilename());
         else
            in = getInputstream();

         OutputStream out = new FileOutputStream(new File(advisorfile));

         int read = 0;
         byte[] bytes = new byte[1024];

         while ((read = in.read(bytes)) != -1) {
            out.write(bytes, 0, read);
         }

         in.close();
         out.flush();
         out.close();

         System.out.println("New file created!");
      } catch (IOException e) {
         System.out.println(e.getMessage());
      }
   }
}