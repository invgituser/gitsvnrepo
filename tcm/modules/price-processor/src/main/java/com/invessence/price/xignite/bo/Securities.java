package com.invessence.price.xignite.bo;

public class Securities {
	
	CommonTypes commonTypes;
	String cik;
	String cusip;
	String symbol;
	String isin;
	String valoren;
	String Name;
	String market;
	String categoryOrIndustry;
	
	public CommonTypes getCommonTypes() {
		return commonTypes;
	}
	public void setCommonTypes(CommonTypes commonTypes) {
		this.commonTypes = commonTypes;
	}
	public String getCik() {
		return cik;
	}
	public void setCik(String cik) {
		this.cik = cik;
	}
	public String getCusip() {
		return cusip;
	}
	public void setCusip(String cusip) {
		this.cusip = cusip;
	}
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public String getIsin() {
		return isin;
	}
	public void setIsin(String isin) {
		this.isin = isin;
	}
	public String getValoren() {
		return valoren;
	}
	public void setValoren(String valoren) {
		this.valoren = valoren;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getMarket() {
		return market;
	}
	public void setMarket(String market) {
		this.market = market;
	}
	public String getCategoryOrIndustry() {
		return categoryOrIndustry;
	}
	public void setCategoryOrIndustry(String categoryOrIndustry) {
		this.categoryOrIndustry = categoryOrIndustry;
	}
	
}
