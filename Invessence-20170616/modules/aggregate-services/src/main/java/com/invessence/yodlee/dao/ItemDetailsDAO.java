package com.invessence.yodlee.dao;

import java.util.List;

import com.invessence.yodlee.model.ItemDetail;

public interface ItemDetailsDAO {

	public ItemDetail insertItemDetails(ItemDetail itemDetails);
	
	public ItemDetail updateItemDetails(ItemDetail itemDetails);
	
	public ItemDetail deleteItemDetails(ItemDetail itemDetails);
	
	public List<ItemDetail> getItemDetailsList();
	
	public ItemDetail findByPK(Long Id);
	
	public List<ItemDetail> findByWhereCluase(String where,Object[] values);
	
	public List<ItemDetail> findByWhereCluase(String where);
}
