package com.invessence.data.consumer.CTO;

import java.io.Serializable;

public class ContactInformation implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8049610688636791543L;
	private Long acctnum;
	private String userid;
    private String phone;
    private String altphone;
    private String email;
    private String altemail;
    private String address1;
    private String altaddress1;
    private String address2;
    private String altaddress2;
    private String city;
    private String altcity;
    private String state;
    private String altstate;
    private String zipcode;
    private String altzipcode;
    
    public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	
    public Long getAcctnum() {
		return acctnum;
	}
	public void setAcctnum(Long acctnum) {
		this.acctnum = acctnum;
	}
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    public String getAltphone() {
        return altphone;
    }
    public void setAltphone(String altphone) {
        this.altphone = altphone;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getAltemail() {
        return altemail;
    }
    public void setAltemail(String altemail) {
        this.altemail = altemail;
    }
    public String getAddress1() {
        return address1;
    }
    public void setAddress1(String address1) {
        this.address1 = address1;
    }
    public String getAltaddress1() {
        return altaddress1;
    }
    public void setAltaddress1(String altaddress1) {
        this.altaddress1 = altaddress1;
    }
    public String getAddress2() {
        return address2;
    }
    public void setAddress2(String address2) {
        this.address2 = address2;
    }
    public String getAltaddress2() {
        return altaddress2;
    }
    public void setAltaddress2(String altaddress2) {
        this.altaddress2 = altaddress2;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getAltcity() {
        return altcity;
    }
    public void setAltcity(String altcity) {
        this.altcity = altcity;
    }
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
    public String getAltstate() {
        return altstate;
    }
    public void setAltstate(String altstate) {
        this.altstate = altstate;
    }
    public String getZipcode() {
        return zipcode;
    }
    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }
    public String getAltzipcode() {
        return altzipcode;
    }
    public void setAltzipcode(String altzipcode) {
        this.altzipcode = altzipcode;
    }
    
}
