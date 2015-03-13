package com.invessence.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
 
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.*;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


 
@Component
@Scope("request")

public class EmailFormatValidator implements Validator {
 
	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\." +
			"[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*" +
			"(\\.[A-Za-z]{2,})$";
 
	private Pattern pattern;
	private Matcher matcher;
	

 
	public EmailFormatValidator(){
		pattern = Pattern.compile(EMAIL_PATTERN);
	}
 
	@Override
	public void validate(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {

		
		String emailID = (String)value.toString();
		matcher = pattern.matcher(emailID);
		
		FacesMessage msg = null;
		
		if ( (emailID == null) || (emailID.trim().equals("")) ||
	         (emailID.indexOf('.') == -1) ||
	         (emailID.indexOf('@') == -1) )  {
			msg =  new FacesMessage("Invalid E-mail.", "Invalid E-mail.");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			
			FacesContext.getCurrentInstance().addMessage(null, msg);
			throw new ValidatorException(msg);
			
		} 
		
		if(!matcher.matches()){

			msg =  new FacesMessage("E-mail validation failed.", "Invalid E-mail format.");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(msg);
				

		}
	

	}
	

}