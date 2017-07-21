package com.invessence.web.util;

import com.invessence.service.bean.*;
import com.invessence.service.bean.fileProcessor.FileDetails;
import com.invessence.web.service.fileProcessor.FileProcessWebLayer;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook.*;
import org.primefaces.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.ConfigurableMimeFileTypeMap.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.mail.javamail.ConfigurableMimeFileTypeMap;

import javax.activation.FileTypeMap;
import javax.activation.FileTypeMap.*;
import javax.faces.bean.*;
import java.io.*;
import java.net.*;
import java.util.*;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.*;


/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 8/17/14
 * Time: 11:45 AM
 * To change this template use File | Settings | File Templates.
 */

@Service("filesIO")
public class FilesIO
{
   @Autowired
   private FileProcessWebLayer fileProcessWebLayer;

   private FileTypeMap fileTypeMap;
   private static Integer MAX_ROWS = 1500;
   private static Integer MAX_COLS = 300;
   private enum CellType {
            CELL_INTEGER, CELL_DOUBLE, CELL_STRING, CELL_BLANK, CELL_
}

   public FilesIO() {
      fileTypeMap = new ConfigurableMimeFileTypeMap();
   }

   public FileProcessWebLayer getFileProcessWebLayer()
   {
      return fileProcessWebLayer;
   }

   public void setFileProcessWebLayer(FileProcessWebLayer fileProcessWebLayer)
   {
      this.fileProcessWebLayer = fileProcessWebLayer;
   }

   public String getContentType(String fileName) throws IOException
   {
      if (fileName == null) {
         return null;
      }
      return fileTypeMap.getContentType(fileName.toLowerCase());
   }

   public void writeToExcelFile(String filename, Map<String, Object[][]> data)
   {
      if (filename == null)
         return;

      try {
            //Blank workbook
            HSSFWorkbook workbook = new HSSFWorkbook();

            for (String sheetname : data.keySet()) {
               //Get first sheet from the data
               HSSFSheet sheet = workbook.createSheet(sheetname);

               //Iterate over data and write to sheet
               Object[][] values = data.get(sheetname);
               for (int rownum=0; rownum < values.length; rownum ++)
               {
                  Row row = sheet.createRow(rownum);
                  for (int cellnum=0; cellnum < values[rownum].length; cellnum ++)
                  {
                     Cell cell = row.createCell(cellnum);
                     if(values[rownum][cellnum] instanceof String)
                        cell.setCellValue((String)values[rownum][cellnum]);
                     else if(values[rownum][cellnum] instanceof Integer)
                        cell.setCellValue((Integer)values[rownum][cellnum]);
                     else if(values[rownum][cellnum] instanceof Double)
                        cell.setCellValue((Double)values[rownum][cellnum]);
                     else
                        cell.setCellValue((String)values[rownum][cellnum]);
                  }
               }

            }

            //Write the workbook in file system
            FileOutputStream out = new FileOutputStream(new File(filename));
            workbook.write(out);
            out.close();
            //System.out.println("howtodoinjava_demo.xlsx written successfully on disk.");

      }
      catch (Exception ex) {
         ex.printStackTrace();
      }
   }

   public Map<String, Object[][]> readExcelFile(String filename, String type, Integer maxrows, Integer maxcols)
   {
      Map<String, Object[][]> data = new HashMap<String, Object[][]>();
      Integer rowcounter, colcounter;
      String sheetname;

      if (filename == null)
         return data;

      try {
         if (filename == null)
            return data;

         maxrows = (maxrows == null || maxrows > MAX_ROWS) ? MAX_ROWS : maxrows;
         maxcols = (maxcols == null || maxcols > MAX_COLS) ? MAX_COLS : maxcols;

         //Create Workbook instance holding reference to file
         Workbook wb;
         FileInputStream fileInputStream = new FileInputStream(new File(filename));
         if (type.equalsIgnoreCase("xlsx")) {
            wb = new XSSFWorkbook(fileInputStream);
         }
         else {
            wb = new HSSFWorkbook(fileInputStream);
         }


         for (int sheetnum=0; sheetnum < wb.getNumberOfSheets(); sheetnum++) {
            //Get first sheet from the workbook

            Sheet sheet = wb.getSheetAt(sheetnum);

            //Iterate through each rows one by one
            Iterator<Row> rowIterator = sheet.iterator();
            rowcounter=0;
            sheetname = sheet.getSheetName();
            Object[][] obj = new Object[maxrows][maxcols];
            while (rowIterator.hasNext())
            {
               if (rowcounter >= maxrows)
                  break;

               Row row = rowIterator.next();
               //For each row, iterate through all the columns
               Iterator<Cell> cellIterator = row.cellIterator();
               colcounter = 0;
               while (cellIterator.hasNext())
               {
                  if (colcounter >= MAX_COLS)
                     break;
                  Cell cell = cellIterator.next();
                  //Check the cell type and format accordingly
                  switch (cell.getCellType())
                  {
                     case Cell.CELL_TYPE_NUMERIC:
                        obj[rowcounter][colcounter] = cell.getNumericCellValue();
                        break;
                     case Cell.CELL_TYPE_STRING:
                        obj[rowcounter][colcounter] = cell.getStringCellValue();
                        break;
                     case Cell.CELL_TYPE_BLANK:
                        obj[rowcounter][colcounter] = "";
                        break;
                     default:
                        obj[rowcounter][colcounter] = cell.getStringCellValue();
                        break;
                  }
                  colcounter++;
               }
               rowcounter++;
            }
            data.put(sheetname, obj);
         }

         fileInputStream.close();
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
      return data;
   }

   public Map<String, Object[][]> readCSVFile(String filename, Integer maxrows, Integer maxcols)
   {
      Map<String, Object[][]> data = new HashMap<String, Object[][]>();
      Integer rowcounter, colcounter;
      String sheetname = "csv";
      String csvString;

      if (filename == null)
         return data;

      BufferedReader br = null;
      String line = "";
      String cvsSplitBy = ",";
      maxrows = (maxrows == null || maxrows > MAX_ROWS) ? MAX_ROWS : maxrows;
      maxcols = (maxcols == null || maxcols > MAX_COLS) ? MAX_COLS : maxcols;



         try {

            br = new BufferedReader(new FileReader(filename));
            rowcounter=0;
            while ((line = br.readLine()) != null) {

               if (rowcounter >= maxrows)
                  break;
               // use comma as separator
               // parse the csv string
               csvString = line.replaceAll("\"", "");
               StringTokenizer tokenizer = new StringTokenizer(csvString, cvsSplitBy);

               colcounter = 0;
               Object[][] obj = new Object[maxrows][maxcols];
               while (tokenizer.hasMoreElements())
               {
                  if (colcounter >= MAX_COLS)
                     break;
                  Object cell = tokenizer.nextToken();

                  if (cell instanceof String)
                     obj[rowcounter][colcounter] = cell.toString();
                  else if (cell instanceof Integer)
                     obj[rowcounter][colcounter] = ((Integer) cell).intValue();
                  else if (cell instanceof Double)
                     obj[rowcounter][colcounter] = ((Double) cell).doubleValue();
                  else
                     obj[rowcounter][colcounter] = cell.toString();

                  obj[rowcounter][colcounter] = cell;
                  colcounter++;
               }
               rowcounter++;
               data.put(sheetname, obj);
            }


         } catch (FileNotFoundException e) {
            e.printStackTrace();
         } catch (IOException e) {
            e.printStackTrace();
         } finally {
            if (br != null) {
               try {
                  br.close();
               } catch (IOException e) {
                  e.printStackTrace();
               }
            }
         }
      return data;
   }


   // Need to return the list of file
   public Boolean processDownloadFile(String processId, String product, String serviceMode){
      System.out.println("AggregationBean.startup");
//      String product = getWebutil().getWebprofile().getWebInfo().get("SERVICE.PRODUCT").toString();
//      String serviceMode = getWebutil().getWebprofile().getWebInfo().get("SERVICE.FILEPROCESS.MODE").toString();
//      String processId= getWebutil().getWebprofile().getWebInfo().get("SERVICE.FILEPROCESS.UPLOADPROCESSID").toString();
      System.out.println("Product " + product);
      System.out.println("ServiceMode " + serviceMode);
      System.out.println("ProcessId = " + processId);
      try {
         WSCallResult result=fileProcessWebLayer.processFile(new ServiceRequest(product, serviceMode, processId));
         System.out.println("result = " + result);
         if(result!=null && result.getWSCallStatus()!=null){
            if(result.getWSCallStatus().getErrorCode()==0){
               return true;
            }
         }


//         if(wsCallResult!=null && wsCallResult.getWSCallStatus()!=null){
//            if(wsCallResult.getWSCallStatus().getErrorCode()==0){
//            }
//            if(wsCallResult.getGenericObject()==null || ((List<FileDetails>)wsCallResult.getGenericObject()).size()==0){
//               System.out.println("List is empty");
//            }else{
//               List<FileDetails> fileList =(List<FileDetails>)wsCallResult.getGenericObject();
//               System.out.println("fileList = " + fileList);
//               if(fileList.get(0).getFileProcessType().equalsIgnoreCase("DOWNLOAD"))
//               {
//                  fileIO.downloadFile(fileList.get(0).getSourcePath());
//               }
//            }
//         }


      } catch (Exception e) {
         e.printStackTrace();
      }
      return false;
   }

   private StreamedContent downloadFile(String processId, String fileformat, String outputName) {
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
         if (fileformat != null) {
            if (fileformat.equalsIgnoreCase("csv")) {
               file = new DefaultStreamedContent(stream, "application/csv", outputName);
            }else {
               if (fileformat.equalsIgnoreCase("text")) {
                  file = new DefaultStreamedContent(stream, "application/text", outputName);
               }
               else
               {
                  if (fileformat.equalsIgnoreCase("pdf")) {
                     file = new DefaultStreamedContent(stream, "application/pdf", outputName);
                  }
               }
            }
         }
      }
      catch (Exception ex) {

      }
      return file;
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
