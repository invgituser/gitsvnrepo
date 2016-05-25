package com.invessence.web.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
 
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.*;

import com.invessence.web.dao.common.UserInfoDAO;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("request")
@FacesValidator(value="EmailValidator")
public class EmailValidator implements Validator {
 
	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\." +
			"[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*" +
			"(\\.[A-Za-z]{2,})$";
 
	private Pattern pattern;
	private Matcher matcher;

   @ManagedProperty("#{userInfoDAO}")
	private UserInfoDAO userInfoDAO;
    
	public void setUserInfoDAO(UserInfoDAO userInfoDAO) {
		this.userInfoDAO = userInfoDAO;
	}
 
	public EmailValidator(){
		pattern = Pattern.compile(EMAIL_PATTERN);
	}
 
	@Override
	public void validate(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {

		
		String emailID = (String)value.toString();
		matcher = pattern.matcher(emailID);
		
		FacesMessage msg = null;
		String msgStr =  validateEmailPattern(emailID);
		if ( msgStr != null )  {
			msg =  new FacesMessage(msgStr, msgStr);
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);

			FacesContext.getCurrentInstance().addMessage(null, msg);
			throw new ValidatorException(msg);

		}

		if (userInfoDAO == null) {
			System.out.println("null userInfoDAO");
		}

      String pwd = userInfoDAO.checkEmailID(emailID);
		if (pwd != null && pwd.length() > 0) {
			msg =  new FacesMessage("Email already exists.", "Duplicate email.");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(msg);
			
		}
		
	}

   public String validateEmailPattern(String emailID) {
      String msg = null;
      if ( (emailID == null) || (emailID.trim().equals("")) ||
         (emailID.indexOf('.') == -1) ||
         (emailID.indexOf('@') == -1) )  {
         msg =  "Invalid E-mail.";
      }

      matcher = pattern.matcher(emailID);
      if(!matcher.matches()){

         msg =  "E-mail validation failed.";
      }
      return msg;

   }
	

}