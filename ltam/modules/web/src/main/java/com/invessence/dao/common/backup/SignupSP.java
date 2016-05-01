package com.invessence.dao.common.backup;


import javax.sql.DataSource;

import com.invessence.data.common.UserData;
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

	public static void main(String[] args) {
		//SignupStoredProcedure sp = new SignupStoredProcedure(ds);	
	}
}
