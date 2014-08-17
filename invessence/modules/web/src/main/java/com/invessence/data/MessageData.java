package com.invessence.data;


import java.util.*;
import java.io.Serializable;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;

import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;



import com.invessence.util.*;
import com.invessence.dao.*;
import com.invessence.data.*;
import com.invessence.constant.*;

@ManagedBean
@RequestScoped
public class MessageData {
	
	private String message = null;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	

}
