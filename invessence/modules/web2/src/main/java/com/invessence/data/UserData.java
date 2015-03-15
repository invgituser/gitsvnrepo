package com.invessence.data;

import java.util.*;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;

import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;


import com.invessence.util.*;
import com.invessence.dao.*;
import com.invessence.data.*;
import com.invessence.constant.*;

public class UserData
{
   private SecurityQuestions securityQuestions = new SecurityQuestions();

   private static UserData instance = null;

}