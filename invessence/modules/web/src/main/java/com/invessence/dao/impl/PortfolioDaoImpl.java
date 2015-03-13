package com.invessence.dao.impl;

import com.invessence.dao.*;
import com.invessence.data.ManageGoals;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: ramesh
 * Date: 9/20/13
 * Time: 7:06 PM
 * To change this template use File | Settings | File Templates.
 */
public class PortfolioDaoImpl extends JdbcDaoSupport implements Serializable, PortfolioDao
{
   public void addPortfolio(ManageGoals goals)
   {

      PortfolioSP portfolioSP = new PortfolioSP(getDataSource());

       int row = portfolioSP.checkPortfolio(goals.getAcctnum());
       if (row > 0){
           // System.out.println("Deleting Portfolio Record.");
           PortfolioDelSP portfolioDelSP = new PortfolioDelSP(getDataSource());
           portfolioDelSP.deletePortfolio(goals);
       }
      portfolioSP.updatePortfolio(goals);
   }
}
