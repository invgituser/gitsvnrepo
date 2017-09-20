package com.invessence.web.service.fileProcessor;

import com.invessence.fileProcessor.service.FileProcessor;
import com.invessence.service.bean.ServiceRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by abhangp on 5/19/2017.
 */
@Service("fileProcessWebLayer")
public class FileProcessWebLayerImpl implements FileProcessWebLayer
{
   @Autowired
   FileProcessor fileProcessor;
   public boolean processFile(ServiceRequest serviceRequest){

      fileProcessor.process(serviceRequest);
      return false;
   }
}
