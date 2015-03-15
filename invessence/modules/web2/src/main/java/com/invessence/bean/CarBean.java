package com.invessence.bean;

import java.io.*;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import com.invessence.data.*;
import com.lowagie.text.*;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.primefaces.event.*;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 8/26/14
 * Time: 1:16 PM
 * To change this template use File | Settings | File Templates.
 */
@ManagedBean(name = "carBean")
@SessionScoped
public class CarBean implements Serializable {
   private List<Car> cars;

   private Car selectedCar;
   private List<Car> selectedCars;

   @ManagedProperty("#{carService}")
   private CarService service;

   @PostConstruct
   public void init() {
      cars = service.createCars(48);
   }

   public List<Car> getCars() {
      return cars;
   }

   public void setService(CarService service) {
      this.service = service;
   }

   public Car getSelectedCar() {
      return selectedCar;
   }

   public void setSelectedCar(Car selectedCar) {
      this.selectedCar = selectedCar;
   }

   public List<Car> getSelectedCars() {
      return selectedCars;
   }

   public void setSelectedCars(List<Car> selectedCars) {
      this.selectedCars = selectedCars;
   }

   public void onRowSelect(SelectEvent event) {
      FacesMessage msg = new FacesMessage("Car Selected", ((Car) event.getObject()).getId());
      FacesContext.getCurrentInstance().addMessage(null, msg);
   }

   public void onRowUnselect(UnselectEvent event) {
      FacesMessage msg = new FacesMessage("Car Unselected", ((Car) event.getObject()).getId());
      FacesContext.getCurrentInstance().addMessage(null, msg);
   }

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
      Document pdf = (Document) document;
      pdf.open();
      pdf.setPageSize(PageSize.A4);

      ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
      String logo = servletContext.getRealPath("") + File.separator + "images" + File.separator + "invessence_logo_RGB1-300x65.jpg";

      pdf.add(Image.getInstance(logo));
   }

}
