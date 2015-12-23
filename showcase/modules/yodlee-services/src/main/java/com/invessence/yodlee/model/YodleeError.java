package com.invessence.yodlee.model;

public class YodleeError {

   private Integer severity;
	private String errorOccurred;
	private String exceptionType;
	private String referenceCode;
	private String message;
	private int errorCode;

	public YodleeError() {
		// TODO Auto-generated constructor stub
	}
 

public YodleeError(Integer severity, String errorOccurred, String exceptionType, String referenceCode,
			String message, int errorCode) {
		super();
		this.severity = severity;
		this.errorOccurred = errorOccurred;
		this.exceptionType = exceptionType;
		this.referenceCode = referenceCode;
		this.message = message;
		this.errorCode = errorCode;
	}


public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

public Integer getSeverity()
   {
      return severity;
   }

   public void setSeverity(Integer severity)
   {
      this.severity = severity;
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
