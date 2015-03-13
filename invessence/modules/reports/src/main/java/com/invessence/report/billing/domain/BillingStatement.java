package com.invessence.report.billing.domain;

import java.io.*;
import java.text.DecimalFormat;

import com.google.common.io.ByteStreams;
import com.itextpdf.text.*;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.pdf.*;

public class BillingStatement
{
   private String outputLocation;

   private String firstName;
   private String lastName;
   private String id;

   private String month;
   private String year;
   private String priormonth;
   private String prioryear;

   private Double otherFee;
   private Double ytdotherFees;
   private Double commissionFee;
   private Double ytdcommissionFees;
   private Double totalAdvisorFee;
   private Double ytdTotalAdvisorFees;

   private Double cash;
   private Double priorcash;
   private Double currentVested;
   private Double priorVested;

   private static final String IMAGE_LOCATION = "InvessenceLogo.jpg";

   private BillingStatement(Builder builder)
   {
      outputLocation = builder.outputLocation;

      firstName = builder.firstName;
      lastName = builder.lastName;
      id = builder.id;

      month = builder.month;
      year = builder.year;
      priormonth = builder.priormonth;
      prioryear = builder.prioryear;

      otherFee = builder.otherFee;
      ytdotherFees = builder.ytdotherFees;
      commissionFee = builder.commissionFee;
      ytdcommissionFees = builder.ytdcommissionFees;
      totalAdvisorFee = builder.totalAdvisorFee;
      ytdTotalAdvisorFees = builder.ytdTotalAdvisorFees;

      cash = builder.cash;
      priorcash = builder.priorcash;
      currentVested = builder.currentVested;
      priorVested = builder.priorVested;

   }

   public static class Builder
   {
      private String outputLocation;

      private String firstName;
      private String lastName;
      private String id;

      private String month;
      private String year;
      private String priormonth;
      private String prioryear;

      private Double commissionFee;
      private Double ytdcommissionFees;
      private Double otherFee;
      private Double ytdotherFees;
      private Double totalAdvisorFee;
      private Double ytdTotalAdvisorFees;

      private Double cash;
      private Double priorcash;
      private Double currentVested;
      private Double priorVested;

      public Builder withOutputLocation(String outputLocation)
      {
         this.outputLocation = outputLocation;
         return this;
      }

      public Builder withTotalFees(Double commissionFee, Double ytdcommissionFees,
                                   Double otherFee, Double ytdotherFees,
                                   Double totalAdvisorFee, Double ytdTotalAdvisorFees)
      {
            // Since we are printing all numbers as positive,
            this.commissionFee =   Math.abs(commissionFee) ;
            this.ytdcommissionFees = Math.abs(ytdcommissionFees);
            this.otherFee = Math.abs(otherFee);
            this.ytdotherFees =  Math.abs(ytdotherFees);
            this.totalAdvisorFee =   Math.abs(totalAdvisorFee - this.commissionFee - this.otherFee);
            this.ytdTotalAdvisorFees = Math.abs(ytdTotalAdvisorFees - this.ytdcommissionFees - this.ytdotherFees);
         return this;
      }

      public Builder withTotalValue(Double cash, Double priorcash,
                                    Double currentVested, Double priorVested)
      {
         this.cash =   cash;
         this.priorcash =  priorcash;
         this.currentVested =   currentVested;
         this.priorVested =   priorVested;
         return this;
      }

      public Builder withUserDetails(String firstName, String lastName, String id)
      {
         this.firstName = firstName;
         this.lastName = lastName;
         this.id = id;
         return this;
      }

      public Builder withBillMonthAndYear(String month, String year, String priormonth, String prioryear)
      {
         this.month = month;
         this.year = year;
         this.priormonth = priormonth;
         this.prioryear = prioryear;
         return this;
      }

      public BillingStatement build()
      {
         return new BillingStatement(this);
      }
   }

   public String displayAsMoney(Double value)
   {
      if (value != null)
      {
         DecimalFormat df = new DecimalFormat("###,####,##0.00");
         if (value < 0) {
            String strValue = df.format(Math.abs(value));
            return "-$ " + strValue;
         }
         else {
            String strValue = df.format(Math.abs(value));
            return "$ " + strValue;
         }
      }
      else
      {
         return "-";
      }
   }

   public void generate() throws IOException, DocumentException
   {
      Document document = new Document();
      PdfWriter.getInstance(document, new FileOutputStream(outputLocation));
      document.open();

      document.setMargins(1.0f, 1.0f, 1.0f, 1.0f);
      addLogoAndHeading(document);
      addBorder(document);
      addAccountNumber(document);
      addLetter(document);
      addBillingTable(document);
      addBorder(document);
      addAdvisoryNote(document);
      addDisclaimer(document);
      document.close();
   }

   private void write(Document document, Element data, boolean addline) throws IOException, DocumentException
   {
      document.add(data);
      if (addline)
         document.add(Chunk.NEWLINE);

   }
/*
   private void addLogoAndHeading(Document document) throws IOException, DocumentException
   {

      InputStream resourceAsStream = getClass().getResourceAsStream("/" + IMAGE_LOCATION);
      byte[] bytes = ByteStreams.toByteArray(resourceAsStream);
      Image logo = Image.getInstance(bytes);
      logo.scaleAbsolute(96, 38);
      PdfPCell logoCell = new PdfPCell(logo);
      logoCell.setHorizontalAlignment(Element.ALIGN_LEFT);
      logoCell.disableBorderSide(Rectangle.BOX);

      PdfPCell summaryTextCell = new PdfPCell(new Phrase("Monthly Summary Statement", new Font(FontFamily.HELVETICA, 18.0f, Font.BOLD, BaseColor.GRAY)));
      summaryTextCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
      summaryTextCell.disableBorderSide(Rectangle.BOX);

      PdfPTable tbl = new PdfPTable(2);
      tbl.addCell(logoCell);
      tbl.addCell(summaryTextCell);
      document.add(tbl);
   }
*/

   private void addLogoAndHeading(Document document) throws IOException, DocumentException
   {
      InputStream resourceAsStream = getClass().getResourceAsStream("/" + IMAGE_LOCATION);
      byte[] bytes = ByteStreams.toByteArray(resourceAsStream);
      Image logo = Image.getInstance(bytes);
      logo.scaleAbsolute(92, 18);
      logo.setAlignment(Image.LEFT);
      PdfPCell logoCell = new PdfPCell(logo);
      logoCell.setHorizontalAlignment(Element.ALIGN_LEFT);
      logoCell.disableBorderSide(Rectangle.BOX);

      Font font = new Font(FontFamily.HELVETICA, 14.0f, Font.BOLD, BaseColor.GRAY);
      PdfPCell summaryTextCell = new PdfPCell(new Phrase("Monthly Summary Statement", font));
      summaryTextCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
      summaryTextCell.disableBorderSide(Rectangle.BOX);

      PdfPTable tbl = new PdfPTable(2);
      tbl.addCell(logoCell);
      tbl.addCell(summaryTextCell);
      write(document, tbl, false);
   }

   private void addBorder(Document document) throws IOException, DocumentException {
      Font font = new Font(FontFamily.HELVETICA, 14.0f, Font.BOLD, BaseColor.GRAY);
      Chunk c = new Chunk(" ",font);
      c.setBackground(new BaseColor(0, 154, 187), 50, 2, 700, 2);
      Paragraph border = new Paragraph(c);
      document.add(border);
      document.add(Chunk.NEWLINE);
   }

   private void addAccountNumber(Document document) throws DocumentException
   {
      Integer idlen = id.length();
      String hiddenid;
      PdfPTable tbl = new PdfPTable(1);
      try {
         if (idlen > 6)
            hiddenid = id.substring(0,1) + "XXXXXXXXXXXXXX".substring(0,idlen-3) + id.substring(idlen-2);
         else
            hiddenid = id;
         PdfPCell cell = createBillingTableCellHeader("Account#: " + hiddenid, Element.ALIGN_RIGHT);
         //cell.setPaddingTop(0.5f);
         //cell.setPaddingBottom(0.5f);
         tbl.addCell(cell);
         document.add(tbl);
      }
      catch (Exception ex) {
          ex.printStackTrace();
      }

   }

   private void addLetter(Document document) throws DocumentException
   {
      try {
         String to = "To" + " " + firstName + " " + lastName + ",";
         String para1= "This is your invoice for " + month + " " + year + ".";
         String para2= "Please let us know if there are any material changes in your life which may impact your investments.";
         String para3= "If you have any questions, please contact us at (201) 977-2704 or email us at support@invessence.com.";
         Font boldfont = new Font(FontFamily.HELVETICA, 11.0f, Font.BOLD, BaseColor.BLACK);
         PdfPCell cellTo = new PdfPCell(new Phrase(to, boldfont));
         cellTo.disableBorderSide(Rectangle.BOX);
         Font font = new Font(FontFamily.HELVETICA, 11.0f, Font.NORMAL, BaseColor.BLACK);
         PdfPCell cellPara1 = new PdfPCell(new Phrase(para1, font));
         cellPara1.disableBorderSide(Rectangle.BOX);
         PdfPCell cellPara2 = new PdfPCell(new Phrase(para2, font));
         cellPara2.disableBorderSide(Rectangle.BOX);
         PdfPCell cellPara3 = new PdfPCell(new Phrase(para3, font));
         cellPara3.disableBorderSide(Rectangle.BOX);
         PdfPCell cellNewLine = new PdfPCell(new Phrase("\n", font));
         cellNewLine.disableBorderSide(Rectangle.BOX);

         PdfPTable tbl = new PdfPTable(1);
         tbl.setSpacingAfter(2.0f);

         tbl.addCell(cellTo);
         tbl.addCell(cellNewLine);
         tbl.addCell(cellPara1);
         tbl.addCell(cellNewLine);
         tbl.addCell(cellPara2);
         tbl.addCell(cellNewLine);
         tbl.addCell(cellPara3);
         tbl.addCell(cellNewLine);
         document.add(tbl);

      }
      catch (Exception ex) {
         ex.printStackTrace();
      }
   }

   private PdfPCell createBillingTableCellHeader(String content, int alignment)
   {
      PdfPCell cell = new PdfPCell(new Phrase(content, new Font(FontFamily.HELVETICA, 11.0f, Font.BOLD, BaseColor.BLACK)));
      cell.setHorizontalAlignment(alignment);
      cell.disableBorderSide(Rectangle.BOX);
      return cell;
   }

   private PdfPCell createBillingTableCell(String content, int alignment, Font font)
   {
      PdfPCell cell = new PdfPCell(new Phrase(content,font));
      cell.setHorizontalAlignment(alignment);
      cell.disableBorderSide(Rectangle.BOX);
      return cell;
   }

   private void addBillingTable(Document document) throws DocumentException
   {
      document.add(Chunk.NEWLINE);

      PdfPTable tbl = new PdfPTable(3);
      tbl.setWidths(new float[]{2f, 2f, 2f});

      addTableHeader(tbl);
      addCommissionAndBrokerageFees(tbl);
      // addOtherFees(tbl);  This is part of above Commission + Brokerage Fee
      addAdvisoryFees(tbl);

      PdfPCell fullRowSpan = new PdfPCell();
      fullRowSpan.setColspan(3);
      fullRowSpan.setBackgroundColor(BaseColor.BLACK);
      fullRowSpan.disableBorderSide(Rectangle.BOX);
      fullRowSpan.setFixedHeight(2f);
      tbl.addCell(fullRowSpan);

      addTotals(tbl);
      document.add(tbl);

      document.add(Chunk.NEWLINE);
      document.add(Chunk.NEWLINE);
   }

   private void addTotals(PdfPTable tbl)
   {
      Font font = new Font(FontFamily.HELVETICA, 11.0f, Font.BOLD, BaseColor.BLACK);
      PdfPCell totalText = createBillingTableCell("Invoiced", Element.ALIGN_LEFT, font);
      font = new Font(FontFamily.COURIER, 11.0f, Font.BOLD, BaseColor.BLACK);
      PdfPCell fee = createBillingTableCell(displayAsMoney(commissionFee + otherFee + totalAdvisorFee), Element.ALIGN_RIGHT, font);
      PdfPCell yearToDate = createBillingTableCell(displayAsMoney(ytdcommissionFees + ytdotherFees + ytdTotalAdvisorFees), Element.ALIGN_RIGHT, font);

      tbl.addCell(totalText);
      tbl.addCell(fee);
      tbl.addCell(yearToDate);
   }

   private void addCommissionAndBrokerageFees(PdfPTable tbl)
   {
      Font font = new Font(FontFamily.HELVETICA, 11.0f, Font.NORMAL, BaseColor.BLACK);
      PdfPCell brokerageFeesText = createBillingTableCell("Brokerage Charges", Element.ALIGN_LEFT, font);
      font = new Font(FontFamily.COURIER, 11.0f, Font.NORMAL, BaseColor.BLACK);
      double brokageFee = commissionFee + otherFee;
      double ytdbrokageFee = ytdcommissionFees + ytdotherFees;
      PdfPCell fee = createBillingTableCell(displayAsMoney(brokageFee), Element.ALIGN_RIGHT, font);
      PdfPCell yearToDate = createBillingTableCell(displayAsMoney(ytdbrokageFee), Element.ALIGN_RIGHT, font);

      tbl.addCell(brokerageFeesText);
      tbl.addCell(fee);
      tbl.addCell(yearToDate);
   }

   private void addOtherFees(PdfPTable tbl)
   {
      Font font = new Font(FontFamily.HELVETICA, 11.0f, Font.NORMAL, BaseColor.BLACK);
      PdfPCell otherFeesText = createBillingTableCell("Other Fees", Element.ALIGN_LEFT, font);
      font = new Font(FontFamily.COURIER, 11.0f, Font.NORMAL, BaseColor.BLACK);
      PdfPCell fee = createBillingTableCell(displayAsMoney(otherFee), Element.ALIGN_RIGHT, font);
      PdfPCell yearToDate = createBillingTableCell(displayAsMoney(ytdotherFees), Element.ALIGN_RIGHT, font);

      tbl.addCell(otherFeesText);
      tbl.addCell(fee);
      tbl.addCell(yearToDate);
   }

   private void addAdvisoryFees(PdfPTable tbl)
   {
      Font font = new Font(FontFamily.HELVETICA, 11.0f, Font.NORMAL, BaseColor.BLACK);
      PdfPCell advisoryFee = createBillingTableCell("Advisory Fee", Element.ALIGN_LEFT, font);
      font = new Font(FontFamily.COURIER, 11.0f, Font.NORMAL, BaseColor.BLACK);
      PdfPCell fee = createBillingTableCell(displayAsMoney(totalAdvisorFee), Element.ALIGN_RIGHT, font);
      PdfPCell yearToDate = createBillingTableCell(displayAsMoney(ytdTotalAdvisorFees), Element.ALIGN_RIGHT, font);

      tbl.addCell(advisoryFee);
      tbl.addCell(fee);
      tbl.addCell(yearToDate);
   }

   private void addTableHeader(PdfPTable tbl) throws DocumentException
   {
      PdfPCell accountFees = createBillingTableCellHeader(year + " Account Fees", Element.ALIGN_LEFT);
      PdfPCell monthCell = createBillingTableCellHeader(month, Element.ALIGN_RIGHT);
      PdfPCell yearToDate = createBillingTableCellHeader("YTD", Element.ALIGN_RIGHT);

      tbl.addCell(accountFees);
      tbl.addCell(monthCell);
      tbl.addCell(yearToDate);
   }

   private void addDisclaimer(Document document) throws DocumentException
   {
      String text = "Disclaimer: As per our advisory agreement, this amount will be deducted directly from your account(s) at Interactive " +
         "Brokers. Invessence highly recommends that you review your statement from Interactive Broker to verify that the correct " +
         "amount has been deducted from your account(s). Interactive Brokers will not automatically verify the accuracy of our " +
         "advisory fees. If any discrepancies are noted, please contact us immediately. Please notify us immediately if you are not " +
         "receiving statements from Interactive Brokers.";

      Font font = new Font(FontFamily.HELVETICA, 8.0f, Font.ITALIC, BaseColor.BLACK);
      PdfPTable tbl = new PdfPTable(1);
      PdfPCell cell = new PdfPCell(new Paragraph(text, font));
      cell.disableBorderSide(Rectangle.BOX);
      cell.setPadding(0.5f);
      cell.setColspan(3);
      tbl.addCell(cell);
      document.add(tbl);
      document.add(Chunk.NEWLINE);
   }

   private void addAdvisoryNote(Document document) throws DocumentException
   {
      String text = "Advisory Fee Formula: Maximum of ($250*(days per month)/365) or (NAV * 0.25%)*(days per month/365); Net Asset Value (NAV) on the last business day of the month. \n\n" +
         "Invoice = Advisory Fees - Commissions - Other Fees.\n\n" +
         "Commissions: Fees charged by Interactive Brokers for trades.\n\n" +
         "Other Fees: Fees charged by Interactive Brokers to administer the account.";

      Font font = new Font(FontFamily.HELVETICA, 8.0f, Font.ITALIC, BaseColor.BLACK);
      PdfPTable tbl = new PdfPTable(1);
      PdfPCell cell = new PdfPCell(new Paragraph(text, font));
      cell.disableBorderSide(Rectangle.BOX);
      tbl.addCell(cell);
      document.add(tbl);
      document.add(Chunk.NEWLINE);
   }


}
