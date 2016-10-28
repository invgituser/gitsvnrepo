package com.invessence.yodlee.dao;

import java.util.List;

import com.invessence.yodlee.model.CardDetail;

public interface CardDetailsDAO {

	public CardDetail insertCardDetails(CardDetail cardDetails);
	
	public CardDetail updateCardDetails(CardDetail cardDetails);
	
	public CardDetail deleteCardDetails(CardDetail cardDetails);
	
	public List<CardDetail> getCardDetailsList();
	
	public CardDetail findByPK(Long Id);
	
	public List<CardDetail> findByWhereCluase(String where,Object[] values);
	
	public List<CardDetail> findByWhereCluase(String where);
}
