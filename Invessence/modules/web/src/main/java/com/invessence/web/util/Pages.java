package com.invessence.web.util;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 7/14/15
 * Time: 10:31 AM
 * To change this template use File | Settings | File Templates.
 */
public interface Pages
{
   //Any number of final, static fields
   //Any number of abstract method declarations

   public void initPage();

   public void setMaxNoofPages(Integer maxpages);

   public Integer getPage();

   public void setPage(Integer pageno);

   public void nextPage();

   public void prevPage();

   public Boolean isFirstPage();
   public Boolean isNextToLastPage();
   public Boolean isLastPage();

   public Boolean getPrevButtonStat();

   public Boolean getNextButtonStat();

   public String getErrorMessage();
   public String getErrorMessage(Integer pagenum);
   public void clearAllErrorMessage();
   public void clearErrorMessage(Integer pagenum);
   public void setErrorMessage(String message);

}
