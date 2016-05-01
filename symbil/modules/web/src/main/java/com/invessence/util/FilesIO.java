package com.invessence.util;

import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.poifs.filesystem.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.mail.javamail.ConfigurableMimeFileTypeMap;

import javax.activation.FileTypeMap;
import java.io.*;
import java.net.*;
import java.util.*;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;


/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 8/17/14
 * Time: 11:45 AM
 * To change this template use File | Settings | File Templates.
 */
public class FilesIO
{
   private FileTypeMap fileTypeMap;
   private static Integer MAX_ROWS = 1500;
   private static Integer MAX_COLS = 300;
   private static enum CellType {
            CELL_INTEGER, CELL_DOUBLE, CELL_STRING, CELL_BLANK, CELL_
}

   public FilesIO() {
      fileTypeMap = new ConfigurableMimeFileTypeMap();
   }

   public String getContentType(String fileName) throws IOException {
      if (fileName == null) {
         return null;
      }
      return fileTypeMap.getContentType(fileName.toLowerCase());
   }

   public Integer convertInteger(String value) {
      try {
         Integer result = Integer.parseInt(value);
         return result;
      }
      catch (Exception ex) {
         return null;
      }
   }

   public Double convertDouble(String value) {
      try {
         Double result = Double.parseDouble(value);
         return result;
      }
      catch (Exception ex) {
         return null;
      }
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
         FileInputStream fileInputStream = new FileInputStream(new File(filename));;
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
                  Object cell = (Object) tokenizer.nextToken();

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

}
