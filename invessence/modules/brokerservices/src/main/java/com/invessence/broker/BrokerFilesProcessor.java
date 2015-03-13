package com.invessence.broker;

import java.util.List;

import org.apache.commons.net.ftp.FTPFile;
import org.apache.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.invessence.broker.dao.FileProcessingConfig;
import com.invessence.broker.net.*;
import com.invessence.broker.processors.FileProcessor;

public class BrokerFilesProcessor {
    public static void main(String[] args) {
        Logger logger = Logger.getLogger(BrokerFilesProcessor.class.getName());
        logger.info("Starting Broker files processor");
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(
                "META-INF/spring/spring-config.xml");
        try {

            BrokerFtpClient brokerClient = (BrokerFtpClient) applicationContext.getBean("ftpClient");
            FileProcessingConfig processingConfig = (FileProcessingConfig) applicationContext
                    .getBean("processingConfig");
            FileProcessor fileProcessor = (FileProcessor) applicationContext.getBean("fileProcessor");

            RemoteFiles remoteFiles = brokerClient.retrieveFileList();
            List<FTPFile> filesToRetrieve = processingConfig.filterFiles(remoteFiles.getRemoteFiles());
            List<FTPFile> filterDirectories = processingConfig.filterDirectories(remoteFiles.getRemoteDirs());

            for (FTPFile ftpFile : filesToRetrieve) {
                // fileProcessor.process(ftpFile, brokerClient.getFtpClient());
            }

            fileProcessor.retrieveReports(filterDirectories, brokerClient.getFtpClient());
            applicationContext.close();
        } catch (Exception e) {
            logger.error("Error running broker files processor", e);
        }
        logger.info("Broker files processor ... done");
    }
}
