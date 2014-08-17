package com.invessence.bo.impl;

import com.invessence.bo.GoalsBo;
import com.invessence.bo.PortfolioBo;
import com.invessence.dao.GoalsDao;
import com.invessence.dao.PortfolioDao;
import com.invessence.data.ManageGoals;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: ramesh
 * Date: 9/20/13
 * Time: 7:02 PM
 * To change this template use File | Settings | File Templates.
 */
public class PortfolioBoImpl  implements Serializable,PortfolioBo {
    PortfolioDao portfolioDao;

    public void setPortfolioDao(PortfolioDao portfolioDao) {
        this.portfolioDao = portfolioDao;
    }

    public void addPortfolio(ManageGoals goals){

        portfolioDao.addPortfolio(goals);
    }
}
