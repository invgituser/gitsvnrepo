package com.invessence.price.processor.DAO;

import java.sql.SQLException;

import com.invessence.price.processor.bean.EmailMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class MessageDaoImpl  implements MessageDao{
	
    @Autowired
    JdbcTemplate jdbcTemplate;
    
    @Value(value="${email.sender}")
    String senderEmail;
    
    @Value(value="${email.subject}")
    String subject;
    
    @Value(value="${email.receiver}")
    String receiver;
    
    
	
	public void insert(EmailMsg md) throws SQLException{
		
		
		try {
			String sql = "insert into invdb.email_alerts" +"(SENDER,RECEIVER,SUBJECT,CC,BCC,MSG,STATUS,CATEGORY,PRIORITY,LOGONID,SENTDATE,COMMENT,CREATED,LASTUPDATED,MIMETYPE,ATTACHMENTS) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			System.out.println("SQL: "+sql);
			   //System.out.println("message_dao_impl.insert(): "+Messages.getString("email.sender"));
			   System.out.println(senderEmail);
			   
			   jdbcTemplate.update
			   (sql, new Object[]{senderEmail,receiver,subject,
					   null,null,md.getMsg(),null,null,null,null,null,null,null,null,null,null});
			
		} catch (Exception e) {
			System.out.println("message_dao_impl.insert(): "+e.getMessage());
		}
		
		   
		   
		
	}

}
