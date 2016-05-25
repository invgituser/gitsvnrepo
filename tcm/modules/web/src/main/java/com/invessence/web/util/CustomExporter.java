package com.invessence.web.util;

import java.io.*;
import java.util.*;
import javax.faces.bean.*;
import javax.servlet.ServletContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import com.lowagie.text.*;
import org.apache.poi.hssf.usermodel.*;
import com.invessence.web.bean.advisor.InstitutionGenericBean;
import org.apache.poi.hssf.util.HSSFColor;

import static javax.faces.context.FacesContext.getCurrentInstance;


/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 8/17/14
 * Time: 11:45 AM
 * To change this template use File | Settings | File Templates.
 */

@ManagedBean(name = "customExporter")
@SessionScoped
public class CustomExporter
{
   public void postProcessXLS(Object document) {
      HSSFWorkbook wb = (HSSFWorkbook) document;
      HSSFSheet sheet = wb.getSheetAt(0);
      HSSFRow header = sheet.getRow(0);

      HSSFCellStyle cellStyle = wb.createCellStyle();
      cellStyle.setFillForegroundColor(HSSFColor.GREEN.index);
      cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

      for(int i=0; i < header.getPhysicalNumberOfCells();i++) {
         HSSFCell cell = header.getCell(i);

         cell.setCellStyle(cellStyle);
      }
   }

   public void preProcessPDF(Object document) throws IOException, BadElementException, DocumentException
   {
      InstitutionGenericBean ibean = new InstitutionGenericBean();
      String imagelogo = ibean.getLogo();
      Document pdf = (Document) document;
      pdf.open();
      pdf.setPageSize(PageSize.LEGAL);

      HttpServletRequest request = (HttpServletRequest) getCurrentInstance().getExternalContext().getRequest();
      ServletContext context = request.getSession().getServletContext();
      String realContextPath = context.getRealPath(request.getContextPath());

      // String logo = realContextPath + imagelogo;
      // pdf.add(Image.getInstance(logo));
   }
}
