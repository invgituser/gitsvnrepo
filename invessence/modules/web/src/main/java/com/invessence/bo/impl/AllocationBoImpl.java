package com.invessence.bo.impl;

import java.io.Serializable;

import com.invessence.bo.AllocationBo;
import com.invessence.dao.AllocationDao;
import com.invessence.data.ManageGoals;

public class AllocationBoImpl implements Serializable,AllocationBo{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 98692464387763554L;
	AllocationDao allocationDao;
	 
	public void setAllocationDao(AllocationDao allocationDao) {
		this.allocationDao = allocationDao;
	}
 
	public void saveAllocation(ManageGoals goals){
 		allocationDao.saveAllocation(goals);
 	}
}
