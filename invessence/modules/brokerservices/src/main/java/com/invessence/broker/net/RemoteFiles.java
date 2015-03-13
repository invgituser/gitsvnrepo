package com.invessence.broker.net;

import java.util.*;

import org.apache.commons.net.ftp.FTPFile;

public class RemoteFiles {
    private List<FTPFile> remoteFiles = new LinkedList<FTPFile>();
    private List<FTPFile> remoteDirs = new LinkedList<FTPFile>();

    public void addRemoteFile(FTPFile remoteFile) {
        remoteFiles.add(remoteFile);
    }

    public void addRemoteDir(FTPFile remoteDir) {
        remoteDirs.add(remoteDir);
    }

    public List<FTPFile> getRemoteFiles() {
        return remoteFiles;
    }

    public List<FTPFile> getRemoteDirs() {
        return remoteDirs;
    }
}
