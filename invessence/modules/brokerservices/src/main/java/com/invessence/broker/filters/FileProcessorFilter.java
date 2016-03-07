package com.invessence.broker.filters;

import java.util.Map;

import org.apache.log4j.Logger;

public class FileProcessorFilter {
    private Logger logger = Logger.getLogger(FileProcessorFilter.class.getName());

    public DataFileInfo process(Map<String, Object> downloadDateMap) {
        String vendor = String.valueOf(downloadDateMap.get("vendor"));
        String filename = String.valueOf(downloadDateMap.get("filename"));
        String tmp_tableName = String.valueOf(downloadDateMap.get("tmp_tableName"));
        String format = String.valueOf(downloadDateMap.get("format"));
        String required = String.valueOf(downloadDateMap.get("required"));
        String canbeempty = String.valueOf(downloadDateMap.get("canbeempty"));
        String available = String.valueOf(downloadDateMap.get("available"));
        String sourcepath = String.valueOf(downloadDateMap.get("sourcepath"));
        String postProcess = String.valueOf(downloadDateMap.get("postProcess"));
        String postInstruction = String.valueOf(downloadDateMap.get("postInstruction"));

        DataFileInfo data = new DataFileInfo(vendor, filename, tmp_tableName, format, required, canbeempty, available, sourcepath,
                postProcess, postInstruction, null);
        return data;
    }

}
