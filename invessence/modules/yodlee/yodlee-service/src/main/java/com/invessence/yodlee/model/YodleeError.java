package com.invessence.yodlee.model;

public class YodleeError {

	private String errorOccurred;
	private String exceptionType;
	private String referenceCode;
	private String message;
   private int loginStatus;

   public int getLoginStatus()
   {
      return loginStatus;
   }

   public void setLoginStatus(int loginStatus)
   {
      this.loginStatus = loginStatus;
   }

   public String getErrorOccurred() {
		return errorOccurred;
	}
	public void setErrorOccurred(String errorOccurred) {
		this.errorOccurred = errorOccurred;
	}
	public String getExceptionType() {
		return exceptionType;
	}
	public void setExceptionType(String exceptionType) {
		this.exceptionType = exceptionType;
	}
	public String getReferenceCode() {
		return referenceCode;
	}
	public void setReferenceCode(String referenceCode) {
		this.referenceCode = referenceCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
