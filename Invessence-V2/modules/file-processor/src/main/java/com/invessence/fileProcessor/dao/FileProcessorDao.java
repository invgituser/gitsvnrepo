package com.invessence.fileProcessor.dao;

import java.sql.SQLException;
import java.util.*;

import com.invessence.fileProcessor.bean.*;
import com.invessence.service.bean.*;
import com.invessence.service.bean.docuSign.*;

/**
 * Created by abhangp on 1/19/2016.
 */
public interface FileProcessorDao
{
//   public Object getCommonDetails(String product, String service, String type, String infoType) throws SQLException;;
   public Object dbCall(String product, String service, String type, String procedureName) throws SQLException;
   public Object dbCallAudit(FileProcessAudit fileProcessAudit) throws SQLException;
   public Map<String, DBParameters> getDBParametres()  throws SQLException;
}
