package com.invessence.web.dao.common.backup;


import javax.sql.DataSource;

import com.invessence.web.data.common.UserData;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.object.StoredProcedure;
import java.sql.Types;
import java.util.*;
import org.springframework.jdbc.core.SqlParameter;

public class SignupSP extends StoredProcedure {
	
    public SignupSP(DataSource datasource, String sp) {    	
	    super(datasource, sp);
	    
	    
	    declareParameter(new SqlParameter("firstName", Types.VARCHAR));
	    declareParameter(new SqlParameter("lastName", Types.VARCHAR));
	    declareParameter(new SqlParameter("userID", Types.VARCHAR));
	    declareParameter(new SqlParameter("emailID", Types.VARCHAR));
	    declareParameter(new SqlParameter("password", Types.VARCHAR));
	    declareParameter(new SqlParameter("secCode", Types.VARCHAR));
	    declareParameter(new SqlOutParameter("userInfoID",  Types.INTEGER ) );
	    
	    compile();
	}
	   
	public Map addAddUser(UserData data) {
		
		
	    Map inputMap = new HashMap();
	    inputMap.put( "firstName", data.getFirstName());
	    inputMap.put( "lastName", data.getLastName() );
	    inputMap.put( "userID", data.getUserID() );
	    inputMap.put( "emailID", data.getEmailID() );
	    inputMap.put( "password", data.getPassword() );	
	    inputMap.put( "secCode", data.getSecCode());

	    return super.execute(inputMap); 
    }
	
	public static void main(String[] args) {
		//SignupStoredProcedure sp = new SignupStoredProcedure(ds);	
	}
}
