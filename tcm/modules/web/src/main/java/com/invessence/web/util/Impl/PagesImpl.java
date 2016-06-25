package com.invessence.web.util.Impl;

import java.io.Serializable;

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
   Integer maxNoofPages;

   public PagesImpl()
   {
      setPage(0);
   }

   public PagesImpl(Integer maxpages)
   {
      setPage(0);
      setMaxNoofPages(maxpages);
   }

   @Override
   public void initPage(){
      pageNo = 0;
   }

   @Override
   public Integer getPage() {
      if (pageNo == null)
         return 0;
      return pageNo;
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
         if (pageNo <  maxNoofPages)
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

}
