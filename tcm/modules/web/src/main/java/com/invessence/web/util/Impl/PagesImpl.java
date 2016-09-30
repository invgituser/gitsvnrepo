package com.invessence.web.util.Impl;

import java.io.Serializable;
import java.util.*;

import com.invessence.web.util.Pages;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 7/14/15
 * Time: 10:42 AM
 * To change this template use File | Settings | File Templates.
 */
public class PagesImpl implements Serializable, Pages
{
   Integer pageNo;
   Map<Integer, String> errorMessgage;
   Integer maxNoofPages;

   public PagesImpl()
   {
      setPage(0);
      errorMessgage = new HashMap<Integer, String>();
   }

   public PagesImpl(Integer maxpages)
   {
      setPage(0);
      setMaxNoofPages(maxpages);
      errorMessgage = new HashMap<Integer, String>();
   }

   @Override
   public void initPage(){
      pageNo = 0;
      if(errorMessgage!=null)
      errorMessgage.clear();
   }

   @Override
   public Integer getPage() {
      if (pageNo == null)
         return 0;
      return pageNo;
   }

   public Integer getMaxNoofPages()
   {
      return maxNoofPages;
   }

   @Override
   public void setMaxNoofPages(Integer maxpages) {
      maxNoofPages =  maxpages;
   }


   @Override
   public void setPage(Integer pageno) {
      pageNo = pageno;
   }

   @Override
   public Boolean isLastPage() {
      if (pageNo >= maxNoofPages)
         return true;
      else
         return false;
   }

   @Override
   public Boolean isNextToLastPage() {
      if ((pageNo + 1) >= maxNoofPages)
         return true;
      else
         return false;
   }


   @Override
   public Boolean isFirstPage() {
      if (pageNo <= 0)
         return true;
      else
         return false;
   }


   @Override
   public void nextPage(){
      if (maxNoofPages > 0) {
         if (pageNo <=  maxNoofPages)
            setPage(++pageNo);
      }
   }

   @Override
   public void prevPage() {
      if (maxNoofPages > 0) {
         if (pageNo >  0)
            setPage(--pageNo);
      }
   }

   @Override
   public Boolean getPrevButtonStat(){
      if (pageNo > 0)
         return true;
      else
         return false;
   }

   @Override
   public Boolean getNextButtonStat(){
      if (maxNoofPages > 0) {
         if (pageNo >=  maxNoofPages)
            return false;
      }
      return true;
   }
   @Override
   public String getErrorMessage(){
      if (errorMessgage.containsKey(pageNo))
         return errorMessgage.get(pageNo);

      return null;
   }

   @Override
   public String getErrorMessage(Integer pagenum){
      if(errorMessgage!=null)
      {
         if (errorMessgage.containsKey(pagenum))
            return errorMessgage.get(pagenum);
      }
      return null;
   }


   @Override
   public void setErrorMessage(String message){

      if (errorMessgage.containsKey(pageNo)) {
         if (errorMessgage.get(pageNo) != null && ! errorMessgage.get(pageNo).isEmpty())
            errorMessgage.put(pageNo, errorMessgage.get(pageNo) + "<br/>" + message);
         return;
      }

      errorMessgage.put(pageNo, message);
   }

   @Override
   public void clearAllErrorMessage(){
      errorMessgage.clear();
   }

   @Override
   public void clearErrorMessage(Integer pagenum){
      if(errorMessgage!=null)
      errorMessgage.remove(pagenum);
   }
}
