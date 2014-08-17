package com.invessence.dao.impl;

import java.io.Serializable;

import com.invessence.dao.AllocationDelSP;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;

import com.invessence.dao.AllocationDao;
import com.invessence.dao.AllocationSP;
import com.invessence.data.ManageGoals;

public class AllocationDaoImpl extends SimpleJdbcDaoSupport implements Serializable, AllocationDao
{

   /**
    *
    */
   private static final long serialVersionUID = 4069549962608795104L;


   public void saveAllocation(ManageGoals goals)
   {
      AllocationSP allocationSP = new AllocationSP(getDataSource());

      int row = allocationSP.checkAllocation(goals.getAcctnum());
      if (row > 0){
          // System.out.println("Deleting Allocation Record.");
          AllocationDelSP allocationDelSP = new AllocationDelSP(getDataSource());
          allocationDelSP.deleteAllocation(goals);
      }
      allocationSP.updateAllocation(goals);
   }
}
