package com.invessence.broker.net;

import java.io.IOException;

import org.apache.commons.net.ftp.*;
import org.apache.log4j.Logger;

public class BrokerFtpClient {
    private Logger logger = Logger.getLogger(BrokerFtpClient.class);
    private String ftpHost;
    private String ftpUsername;
    private String ftpPassword;
    private FTPSClient ftpsClient;

    public BrokerFtpClient(String ftpHost, String ftpUsername, String ftpPassword) {
        this.ftpHost = ftpHost;
        this.ftpUsername = ftpUsername;
        this.ftpPassword = ftpPassword;
    }

    public RemoteFiles retrieveFileList() throws IOException {
        try {
           ftpsClient = loginToRemoteServer(ftpHost, ftpUsername, ftpPassword);
            changeRemoteDirectory(ftpsClient);
            return retrieveFileList(ftpsClient);
        } catch (IOException e) {
            logger.error("Error retrieving file list", e);
            throw e;
        }
    }

    private RemoteFiles retrieveFileList(FTPClient ftpClient) throws IOException {
        RemoteFiles remoteFiles = new RemoteFiles();
        FTPFile[] ftpFiles = ftpClient.listFiles();

        for (FTPFile file : ftpFiles) {
            if (file.isFile()) {
                remoteFiles.addRemoteFile(file);
            } else if (file.isDirectory()) {
                remoteFiles.addRemoteDir(file);
            }
        }

        return remoteFiles;
    }

    private void changeRemoteDirectory(FTPClient ftpClient) throws IOException {
        ftpClient.enterLocalPassiveMode();
        ftpClient.changeWorkingDirectory("Outgoing");
        logger.info("Current directory is " + ftpClient.printWorkingDirectory());
    }

    private FTPSClient loginToRemoteServer(String ftpHost, String ftpUsername, String ftpPassword) throws IOException {
        FTPSClient ftpsClient = new FTPSClient();
       ftpsClient.connect(ftpHost);

        if (!ftpsClient.login(ftpUsername, ftpPassword)) {
           ftpsClient.logout();
            String error = "Cannot login to " + ftpHost + " with username " + ftpUsername + " and password "
                    + ftpPassword;
            logger.error(error);
            throw new IllegalStateException(error);
        }
        return ftpsClient;
    }

    public FTPSClient getFtpsClient() {
        return ftpsClient;
    }
}
