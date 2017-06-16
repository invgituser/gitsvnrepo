package com.invessence.price.util;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

import com.jcraft.jsch.*;
import org.apache.log4j.Logger;

/**
 * Created by sagar on 2/7/2017.
 */


public class FileTranferHandler {

    public static void main(String args[]) {

        try {
            new ThreadImpl();
        } catch (Exception e) {
            System.out.println("FileTranferHandler.main():start" + e);
            e.printStackTrace();
        }
    }
}

class ThreadImpl implements Runnable {
    private static final Logger logger = Logger.getLogger(ThreadImpl.class);
    private SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");
    private SimpleDateFormat sdf2 = new SimpleDateFormat("HH");
    Date today;
    Calendar calendar = null;
    HashMap<String, Object> fileDetails = null;
    Thread mythread;
    boolean bFileTrfFlag = true;
    String strfiles = null;
    Date tomorrow = null;
    int modFileCnt = 0;
    int trdSlpTm = 15;
    String TodaysDt = null;

    {
        mythread = new Thread(this);
        mythread.start();
    }

    public void run() {
        try {
            while (true) {

                logger.info("ThreadImpl Inside Run method");

                Properties objprop = null;
                objprop = this.getProperties();
                if (objprop != null) {
                    today = new Date();
                    TodaysDt = sdf.format(today);
                    calendar = Calendar.getInstance();
                    calendar.setTime(today);
                    calendar.get(Calendar.HOUR_OF_DAY);
                    tomorrow = calendar.getTime();
                    trdSlpTm = Integer.parseInt(objprop.get("thread.sleep.time").toString());
                    int inCurrentHr = Integer.parseInt(sdf2.format(tomorrow));
                    int inExecHr = Integer.parseInt(objprop.get("file.start.time.hr").toString());
                    if (inCurrentHr >= inExecHr) {
                        logger.info("ThreadImpl Hour Execution criteria matched ");
                        if (bFileTrfFlag) {
                            logger.info("ThreadImpl Getting file deails Start ");
                            fileDetails = this.getModifiedFiles(objprop.get("file.source.directory").toString(), TodaysDt);
                            logger.info("ThreadImpl Getting file deails End ");
                            logger.info("ThreadImpl file transfer impementation Start ");
                            strfiles = fileDetails.get("status").toString();
                            modFileCnt = Integer.parseInt(fileDetails.get("modFileCnt").toString());
                            if (strfiles.equalsIgnoreCase("success") && modFileCnt > 0) {
                                this.TransferFiles(objprop, fileDetails);
                            }
                            logger.info("ThreadImpl file transfer impementation End ");
                            strfiles = null;
                            modFileCnt = 0;
                        }
                    } else {
                        bFileTrfFlag = true;
                    }
                }
                objprop = null;
                logger.info("ThreadImpl Thread going to sleep, sleep time in minute : "+trdSlpTm);
                Thread.sleep(1000 * trdSlpTm * 60);
            }

        } catch (Exception e) {
            logger.error("ThreadImpl Thread Execution Interrupted " + e);
            e.printStackTrace();
        }
    }


    public Properties getProperties() {

        InputStream is = null;
        Properties objProp = null;

        try {
            logger.info("getProperties reading properties from Configuration.properties");
            is = FileTranferHandler.class.getResourceAsStream("/Configuration.properties");
            objProp = new Properties();
            objProp.load(is);
            if (objProp.isEmpty()) {
                logger.info("getProperties No properties are defined");
                objProp = null;
            } else {
                logger.info("getProperties properties are defined for " + objProp.keySet().toString());

                if (objProp.get("file.source.directory") == null ||
                        objProp.get("file.sftp.host") == null ||
                        objProp.get("file.sftp.user") == null ||
                        objProp.get("file.sftp.password") == null ||
                        objProp.get("file.sftp.directory") == null ||
                        objProp.get("file.sftp.port") == null) {
                    logger.info("getProperties properties are missing in configuration file " + objProp.keySet().toString());
                    objProp = null;
                }
            }

        } catch (Exception e) {
            objProp = null;
            logger.error("Exception in getProperties : " + e.getMessage());
            e.printStackTrace();
        } finally {
            is = null;
        }
        return objProp;
    }

    public HashMap<String, Object> getModifiedFiles(String sourceDir, String TodaysDt) {
        HashMap<String, Object> fileDetails = new HashMap<String, Object>();
        int availFileCnt = 0, modfileCnt = 0;
        File fDir = null;
        String strMdDate = null;
        List<String> filename = new ArrayList<String>();
        try {
            logger.info("getModifiedFiles getting file/s details from Directory : " + sourceDir + " TodaysDt : " + TodaysDt);
            fDir = new File(sourceDir);
            for (File file : fDir.listFiles()) {
                if (file.isFile()) {
                    availFileCnt++;
                    strMdDate = sdf.format(new Date(file.lastModified()));
                    if (TodaysDt.equalsIgnoreCase(strMdDate)) {
                        modfileCnt++;
                        filename.add(file.getName());
                        logger.info("File Modified today : " + file.getName());
                    }
                    strMdDate = null;
                }
            }

            logger.info("getModifiedFiles getting file/s details  \n Available : " + availFileCnt + " \nModified : " + modfileCnt);
            fileDetails.put("status", "success");
            fileDetails.put("dirFileCnt", availFileCnt);
            fileDetails.put("modFileCnt", modfileCnt);
            fileDetails.put("fileDetails", filename);
        } catch (Exception e) {
            fileDetails.put("status", "failure");
            logger.error("Exception in getModifiedFiles : " + e.getMessage());
            e.printStackTrace();

        } finally {
            fDir = null;
            strMdDate = null;
            sourceDir = null;
            TodaysDt = null;
            filename = null;
        }
        return fileDetails;
    }

    public void TransferFiles(Properties objProp, HashMap<String, Object> fileDetails) {

        String strSRc = null, strHost = null, strUserName = null, strPassowrd = null, strDir = null;
        int intPort = 22;
        JSch jsch = null;
        Session session = null;
        ChannelSftp channelSftp = null;
        List<String> filename = null;
        File f = null;
        try {

            strSRc = objProp.get("file.source.directory").toString();
            strHost = objProp.get("file.sftp.host").toString();
            strUserName = objProp.get("file.sftp.user").toString();
            strPassowrd = objProp.get("file.sftp.password").toString();
            strDir = objProp.get("file.sftp.directory").toString();
            intPort = Integer.parseInt(objProp.get("file.sftp.port").toString());

            jsch = new JSch();

            session = jsch.getSession(strUserName, strHost, intPort);
            session.setPassword(strPassowrd);
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();
            if (session.isConnected()) {
                logger.info("TransferFiles SFTP session connected ");
                channelSftp = (ChannelSftp) session.openChannel("sftp");
                channelSftp.connect();
                if (channelSftp.isConnected()) {
                    logger.info("TransferFiles SFTP channel for file transfer succeed ");
                    channelSftp.cd(strDir);
                    filename = (List<String>) fileDetails.get("fileDetails");
                    for (int i = 0; i < filename.size(); i++) {
                        f = new File(strSRc + "/" + filename.get(i));
                        channelSftp.put(new FileInputStream(f), filename.get(i));
                        f = null;
                        logger.info("TransferFiles Transfered File : " + filename.get(i));
                    }
                    bFileTrfFlag = false;
                } else {
                    logger.info("TransferFiles SFTP channel for file transfer failed ");
                }
            } else {
                logger.info("TransferFiles SFTP session connection failed ");
            }


        } catch (Exception e) {
            bFileTrfFlag = true;
            logger.error("TransferFiles Failed " + e);
            e.printStackTrace();

        } finally {
            strSRc = null;
            strHost = null;
            strUserName = null;
            strPassowrd = null;
            strDir = null;
            filename = null;
            f = null;
        }

    }
}