package com.invessence.service;

import com.invessence.service.dao.ServiceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by abhangp on 5/17/2016.
 */
@Service
public class TestImpl
{
   @Autowired
   ServiceDao serviceDao;

   public void process(){
      try{
         serviceDao.getServiceConfigDetails("","","");
      }catch(Exception e){
         e.printStackTrace();
      }

}
}
