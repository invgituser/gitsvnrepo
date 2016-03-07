package com.invessence.price.processor.bean;

import java.util.Date;

public class PriceData {

	private String  ticker;
	private String  businessDate;
	private Double  openPrice;
	private Double  closePrice ;
	private Double  highPrice;
	private Double  lowPrice;
	private Long  volume;
	private Date  prevBusinessdate ;
	private Double  prevClosePrice;
	private Double  dailyReturn;
	private Double  monthlyReturn;
	private Long  insertedBy ;
	private Date insertedOn ;
	private Long  updatedBy;
	private Date updatedOn;
		
	public PriceData(String ticker, String businessDate, Double openPrice, Double closePrice,Double highPrice,Double lowPrice, Long volume,
			Date prevBusinessdate, Double prevClosePrice, Long insertedBy, Date insertedOn) {
		super();
		this.ticker = ticker;
		this.businessDate = businessDate;
		this.openPrice = openPrice;
		this.closePrice = closePrice;
		this.highPrice = highPrice;
		this.lowPrice = lowPrice;
		this.volume = volume;
		this.prevBusinessdate = prevBusinessdate;
		this.prevClosePrice = prevClosePrice;
		this.insertedBy = insertedBy;
		this.insertedOn = insertedOn;
	}
	public String getTicker() {
		return ticker;
	}
	public void setTicker(String ticker) {
		this.ticker = ticker;
	}
	public String getBusinessDate() {
		return businessDate;
	}
	public void setBusinessDate(String businessDate) {
		this.businessDate = businessDate;
	}
	public Double getOpenPrice() {
		return openPrice;
	}
	public void setOpenPrice(Double openPrice) {
		this.openPrice = openPrice;
	}
	public Double getClosePrice() {
		return closePrice;
	}
	public void setClosePrice(Double closePrice) {
		this.closePrice = closePrice;
	}
	public Long getVolume() {
		return volume;
	}
	public void setVolume(Long volume) {
		this.volume = volume;
	}
	public Date getPrevBusinessdate() {
		return prevBusinessdate;
	}
	public void setPrevBusinessdate(Date prevBusinessdate) {
		this.prevBusinessdate = prevBusinessdate;
	}
	public Double getPrevClosePrice() {
		return prevClosePrice;
	}
	public void setPrevClosePrice(Double prevClosePrice) {
		this.prevClosePrice = prevClosePrice;
	}
	public Double getDailyReturn() {
		return dailyReturn;
	}
	public void setDailyReturn(Double dailyReturn) {
		this.dailyReturn = dailyReturn;
	}
	public Double getMonthlyReturn() {
		return monthlyReturn;
	}
	public void setMonthlyReturn(Double monthlyReturn) {
		this.monthlyReturn = monthlyReturn;
	}
	public Long getInsertedBy() {
		return insertedBy;
	}
	public void setInsertedBy(Long insertedBy) {
		this.insertedBy = insertedBy;
	}
	public Date getInsertedOn() {
		return insertedOn;
	}
	public void setInsertedOn(Date insertedOn) {
		this.insertedOn = insertedOn;
	}
	public Long getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}
	public Date getUpdatedOn() {
		return updatedOn;
	}
	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}
	@Override
	public String toString() {
		return "PriceData [ticker=" + ticker + ", businessDate=" + businessDate + ", openPrice=" + openPrice
				+ ", closePrice=" + closePrice + ", volume=" + volume + ", prevBusinessdate=" + prevBusinessdate
				+ ", prevClosePrice=" + prevClosePrice + ", dailyReturn=" + dailyReturn + ", monthlyReturn="
				+ monthlyReturn + ", insertedBy=" + insertedBy + ", insertedOn=" + insertedOn + ", updatedBy="
				+ updatedBy + ", updatedOn=" + updatedOn + "]";
	}

	public Double getHighPrice()
	{
		return highPrice;
	}

	public void setHighPrice(Double highPrice)
	{
		this.highPrice = highPrice;
	}

	public Double getLowPrice()
	{
		return lowPrice;
	}

	public void setLowPrice(Double lowPrice)
	{
		this.lowPrice = lowPrice;
	}
}
