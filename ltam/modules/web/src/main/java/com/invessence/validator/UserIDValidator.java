package com.invessence.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.*;

import com.invessence.dao.common.UserInfoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("request")
@FacesValidator(value="UserIDValidator")
public class UserIDValidator implements Validator {

   private static final String UserID_PATTERN = "^[_A-Za-z0-9-_.]+$";

   private Pattern pattern;
   private Matcher matcher;

   @ManagedProperty("#{userInfoDAO}")
   private UserInfoDAO userInfoDAO;

   public void setUserInfoDAO(UserInfoDAO userInfoDAO) {
      this.userInfoDAO = userInfoDAO;
   }

   public UserIDValidator(){
      pattern = Pattern.compile(UserID_PATTERN);
   }

   @Override
   public void validate(FacesContext context, UIComponent component,
                        Object value) throws ValidatorException {


      String userid = (String)value.toString();
      matcher = pattern.matcher(userid);

      FacesMessage msg = null;
      String msgStr =  validatePattern(userid);
      if ( msgStr != null )  {
         msg =  new FacesMessage(msgStr, msgStr);
         msg.setSeverity(FacesMessage.SEVERITY_ERROR);

         FacesContext.getCurrentInstance().addMessage(null, msg);
         throw new ValidatorException(msg);

      }

      if (userInfoDAO != null) {
         String pwd = userInfoDAO.checkEmailID(userid);
         if (pwd != null && pwd.length() > 0) {
            msg =  new FacesMessage("UserID already exists.", "UserID already exists, try again.");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);

         }
      }

   }

   public String validatePattern(String userid) {
      String msg = null;
      if ( (userid == null) || (userid.trim().equals("")) )  {
         msg =  "UserID is required.";
      }

      matcher = pattern.matcher(userid);
      if(!matcher.matches()){

         msg =  "UserID validation failed.";
      }
      else if (userid.length() < 6) {
         msg =  "UserID validation failed.";
      }
      return msg;

   }


}