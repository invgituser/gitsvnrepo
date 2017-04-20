package com.invessence.price.csidata;

import java.io.*;
import java.text.*;
import java.util.*;

import com.invessence.price.processor.Service.CallingService;
import com.invessence.price.processor.bean.*;
import com.invessence.service.bean.ServiceRequest;
import com.invessence.service.util.*;
import com.jcraft.jsch.*;
import com.jcraft.jsch.Logger;
import org.apache.log4j.*;
import org.springframework.beans.factory.annotation.Value;

/**
 * Created by sagar on 2/21/2017.
 */
public class CallingServiceCSIData implements CallingService
{

   private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(CallingServiceCSIData.class);
   //   private Properties objProp = null;
   private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
   private static SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");


   public static void main(String[] args)
   {


      try
      {
//         HashMap<String, Object> obj = new CallingServiceCSIData().getDailyPriceData("2017-01-26", "EIMI-L.CSV");

//         System.out.println("***************size of date2 " + obj.get("status"));

//         HashMap<String, Object> obj2 = new CallingServiceCSIData().getHistoryPriceData("2017-01-26", "EIMI-L.CSV");
//         System.out.println("***************size of date2 " +obj2);

      }
      catch (Exception e)
      {

         System.out.println("***************size of date2 " + e);
      }

   }

   @Override
   public HashMap<String, Object> getDailyPriceData(String businessDate, String ticker, ServiceRequest serviceRequest) throws Exception
   {
      HashMap<String, Object> objPriceData = null;
      List<PriceData> pdList = null;
      String line = "";
      String prevBusinessdate = null, prevClosePrice = null;
      BufferedReader br = null;
      boolean bflag = false;
      PriceData hpd = null;
      int counter = 0;
      String destnDir = null;

      try
      {

         pdList = new ArrayList<PriceData>();
         objPriceData = new HashMap<String, Object>();
         bflag = downloadSFTPFile(businessDate, ticker + ".CSV", serviceRequest);
         logger.info("CSIDATA getDailyPriceData file download success flag " + bflag);
         if (bflag)
         {
            destnDir = ServiceDetails.getConfigProperty(serviceRequest.getProduct(), Constant.SERVICES.PRICING.toString(), serviceRequest.getMode(), Constant.PRICING.CSIDATA.toString(), "DESTINATION.DIRECTORY");
            logger.info("CSIDATA getDailyPriceData destnDir " + destnDir);
            br = new BufferedReader(new FileReader(destnDir + businessDate + "/" + ticker + ".CSV"));
            /*
            * csidata[0]-Business Date
            * csidata[1]-Open Price
            * csidata[2]-High Price
            * csidata[3]-Low Price
            * csidata[4]-Close Price
            * csidata[5]-Volume
            * */
            while ((line = br.readLine()) != null)
            {
               String[] csidata = line.split(",");
               if (businessDate.equalsIgnoreCase(csidata[0]))
               {
                  if (counter == 0)
                  {
                     hpd = new PriceData(ticker,
                                         csidata[0].trim(),
                                         Double.valueOf(csidata[1].trim()), Double.valueOf(csidata[4].trim()),
                                         Double.valueOf(csidata[2].trim()), Double.valueOf(csidata[3].trim()),
                                         Long.valueOf(csidata[5].trim()), null,
                                         Double.valueOf(csidata[4].trim()), new Long(2), new Date());
                  }
                  else
                  {
                     hpd = new PriceData(ticker,
                                         csidata[0].trim(),
                                         Double.valueOf(csidata[1].trim()), Double.valueOf(csidata[4].trim()),
                                         Double.valueOf(csidata[2].trim()), Double.valueOf(csidata[3].trim()),
                                         Long.valueOf(csidata[5].trim()), sdf.parse(prevBusinessdate),
                                         Double.valueOf(csidata[4].trim()), new Long(2), new Date());
                  }
                  pdList.add(hpd);
                  hpd = null;
                  break;
               }
               prevBusinessdate = null;
               prevClosePrice = null;
               prevBusinessdate = csidata[0].trim();
               prevClosePrice = csidata[4].trim();
               csidata = null;
               counter++;
            }
         }

//         System.out.println("***************size of pdlist" + pdList.size());
         if (pdList.size() > 0)
         {
            logger.info("CSIDATA getDailyPriceData price data found datasize " + pdList.size());
            objPriceData.put("status", "success");
            objPriceData.put("priceData", pdList);
         }
         else
         {
            logger.info("CSIDATA getDailyPriceData price data not found ");
            objPriceData.put("status", "failure");
         }
      }
      catch (Exception e)
      {
         logger.error("CSIDATA getDailyPriceData Exception " + e);
         e.printStackTrace();
      }
      finally
      {
         try
         {
            businessDate = null;
            ticker = null;
            line = null;
            prevBusinessdate = null;
            br = null;
            pdList = null;
//            objProp = null;
         }
         catch (Exception e)
         {
         }
      }
      return objPriceData;
   }


   @Override
   public HashMap<String, Object> getHistoryPriceData(String businessDate, String ticker, ServiceRequest serviceRequest) throws Exception
   {
      HashMap<String, Object> objPriceData = null;
      List<PriceData> pdList = null;
      String line = "";
      String prevBusinessdate = null, prevClosePrice = null;
      BufferedReader br = null;
      boolean bflag = false;
      PriceData hpd = null;
      int counter = 0;
      String destnDir = null;

      try
      {
         pdList = new ArrayList<PriceData>();
         objPriceData = new HashMap<String, Object>();
         bflag = downloadSFTPFile(businessDate, ticker + ".CSV", serviceRequest);
         logger.info("CSIDATA getHistoryPriceData file download flag " + bflag);
         if (bflag)
         {
            destnDir = ServiceDetails.getConfigProperty(serviceRequest.getProduct(), Constant.SERVICES.PRICING.toString(), serviceRequest.getMode(), Constant.PRICING.CSIDATA.toString(), "DESTINATION.DIRECTORY");
            logger.info("CSIDATA getHistoryPriceData file destination directory " + destnDir);
            br = new BufferedReader(new FileReader(destnDir + businessDate + "/" + ticker + ".CSV"));
            while ((line = br.readLine()) != null)
            {
               String[] csidata = line.split(",");

               if (counter == 0)
               {
                  hpd = new PriceData(ticker,
                                      csidata[0].trim(),
                                      Double.valueOf(csidata[1].trim()), Double.valueOf(csidata[4].trim()),
                                      Double.valueOf(csidata[2].trim()), Double.valueOf(csidata[3].trim()),
                                      Long.valueOf(csidata[5].trim()), null,
                                      Double.valueOf(csidata[4].trim()), new Long(2), new Date());
               }
               else
               {
                  hpd = new PriceData(ticker,
                                      csidata[0].trim(),
                                      Double.valueOf(csidata[1].trim()), Double.valueOf(csidata[4].trim()),
                                      Double.valueOf(csidata[2].trim()), Double.valueOf(csidata[3].trim()),
                                      Long.valueOf(csidata[5].trim()), sdf.parse(prevBusinessdate),
                                      Double.valueOf(csidata[4].trim()), new Long(2), new Date());
               }
               pdList.add(hpd);
               hpd = null;
               prevBusinessdate = null;
               prevClosePrice = null;
               prevBusinessdate = csidata[0].trim();
               prevClosePrice = csidata[4].trim();
               csidata = null;
               counter++;
            }
         }

//         System.out.println("***************size of pdlist" + pdList.size());
         if (pdList.size() > 0)
         {
            objPriceData.put("status", "success");
            objPriceData.put("priceData", pdList);
            logger.info("CSIDATA getHistoryPriceData price data found size " + pdList.size());
         }
         else
         {
            objPriceData.put("status", "failure");
            logger.info("CSIDATA getHistoryPriceData price data not found  ");
         }
      }
      catch (Exception e)
      {
         logger.error("CSIDATA getHistoryPriceData Exception " + e);
         e.printStackTrace();
      }
      finally
      {
         try
         {
            businessDate = null;
            ticker = null;
            line = null;
            prevBusinessdate = null;
            br = null;
            pdList = null;
//            objProp = null;
         }
         catch (Exception e)
         {
         }
      }
      return objPriceData;
   }

   @Override
   public List<PriceData> getHistoricalPriceData(String businessDate, String ticker)
   {
      System.out.println("Not Support historical price process for CSIDATA");
      return null;
   }


   @Override
   public List<PriceData> getDailyPriceData(String businessDate, List<SecMaster> tickerList)
   {
      System.out.println("Not Support daily price process for CSIDATA");
      return null;
   }

   public boolean downloadSFTPFile(String businessDate, String tickerfile, ServiceRequest serviceRequest)
   {
      boolean bflag = false;

//      InputStream is = null;
      String SFTPHOST = null;
      int SFTPPORT = 22;
      String SFTPUSER = null;
      String SFTPPASS = null;
      String SFTPWORKINGDIR = null;
      String dstnDir = null;
      Session session = null;
      Channel channel = null;
      ChannelSftp channelSftp = null;

      try
      {
//         is = CallingServiceCSIData.class.getResourceAsStream("/CSIDATAConfig.properties");
//         objProp = new Properties();
//         objProp.load(is);

         SFTPHOST = ServiceDetails.getConfigProperty(serviceRequest.getProduct(), Constant.SERVICES.PRICING.toString(), serviceRequest.getMode(), Constant.PRICING.CSIDATA.toString(), "SFTP.HOST");
         SFTPPORT = Integer.parseInt(ServiceDetails.getConfigProperty(serviceRequest.getProduct(), Constant.SERVICES.PRICING.toString(), serviceRequest.getMode(), Constant.PRICING.CSIDATA.toString(), "SFTP.PORT"));
         SFTPUSER = ServiceDetails.getConfigProperty(serviceRequest.getProduct(), Constant.SERVICES.PRICING.toString(), serviceRequest.getMode(), Constant.PRICING.CSIDATA.toString(), "SFTP.USER");
         SFTPPASS = ServiceDetails.getConfigProperty(serviceRequest.getProduct(), Constant.SERVICES.PRICING.toString(), serviceRequest.getMode(), Constant.PRICING.CSIDATA.toString(), "SFTP.PASSWORD");
         SFTPWORKINGDIR = ServiceDetails.getConfigProperty(serviceRequest.getProduct(), Constant.SERVICES.PRICING.toString(), serviceRequest.getMode(), Constant.PRICING.CSIDATA.toString(), "SFTP.DIRECTORY");
         dstnDir = ServiceDetails.getConfigProperty(serviceRequest.getProduct(), Constant.SERVICES.PRICING.toString(), serviceRequest.getMode(), Constant.PRICING.CSIDATA.toString(), "DESTINATION.DIRECTORY");

         logger.info("CSIDATA downloadSFTPFile SFTPHOST-" + SFTPHOST + " SFTPPORT-" + SFTPPORT + " SFTPUSER-" + SFTPUSER + " SFTPPASS-" + SFTPPASS + " SFTPWORKINGDIR-" + SFTPWORKINGDIR + " dstnDir-" + dstnDir);

         if (SFTPHOST == null || SFTPUSER == null ||
            SFTPPASS == null || SFTPWORKINGDIR == null || dstnDir == null)
         {
            logger.info("CSIDATA downloadSFTPFile properties are missing in service.service_config_details ");
//               objProp = null;
         }
         else
         {
//               SFTPHOST = objProp.getProperty("file.sftp.host");
//               SFTPPORT = Integer.parseInt(objProp.getProperty("file.sftp.port"));
//               SFTPUSER = objProp.getProperty("file.sftp.user");
//               SFTPPASS = objProp.getProperty("file.sftp.password");
//               SFTPWORKINGDIR = objProp.getProperty("file.sftp.directory");
//               dstnDir = objProp.getProperty("file.destination.directory");

            JSch jsch = new JSch();
            session = jsch.getSession(SFTPUSER, SFTPHOST, SFTPPORT);
            session.setPassword(SFTPPASS);
            java.util.Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);
            session.connect();
            channel = session.openChannel("sftp");
            channel.connect();
            channelSftp = (ChannelSftp) channel;
            channelSftp.cd(SFTPWORKINGDIR);
            byte[] buffer = new byte[1024];
            BufferedInputStream bis = new BufferedInputStream(channelSftp.get(tickerfile));

            File newFile = new File(dstnDir + businessDate);

            if (!newFile.isDirectory())
            {
               newFile.mkdir();
            }

            newFile = null;
            newFile = new File(dstnDir + businessDate + "/" + tickerfile);
            OutputStream os = new FileOutputStream(newFile);
            BufferedOutputStream bos = new BufferedOutputStream(os);
            int readCount;
            while ((readCount = bis.read(buffer)) > 0)
            {
               bos.write(buffer, 0, readCount);
            }
            bis.close();
            bos.close();
            channelSftp.rm(tickerfile);
            channel.disconnect();
            session.disconnect();
            bflag = true;
         }

      }
      catch (Exception e)
      {
         bflag = false;
         logger.error("CSIDATA downloadSFTPFile Exception " + e);
         e.printStackTrace();
      }
      return bflag;
   }
}

