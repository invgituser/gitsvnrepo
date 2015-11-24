package com.invessence.dao;


import java.sql.Types;
import java.util.*;
import javax.sql.DataSource;

import com.invessence.data.MsgData;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;

//import java.sql.CallableStatement;


public class MsgSP extends StoredProcedure
{

   public MsgSP(DataSource ds)
   {
      super(ds, "sp_email_messages_add_mod");

      declareParameter(new SqlParameter("p_addmodflag", Types.VARCHAR));
      declareParameter(new SqlParameter("p_source", Types.VARCHAR));
      declareParameter(new SqlParameter("p_messageid", Types.BIGINT));
      declareParameter(new SqlParameter("p_sender", Types.VARCHAR));
      declareParameter(new SqlParameter("p_receiver", Types.VARCHAR));
      declareParameter(new SqlParameter("p_cc", Types.VARCHAR));
      declareParameter(new SqlParameter("p_bcc", Types.VARCHAR));
      declareParameter(new SqlParameter("p_subject", Types.VARCHAR));
      declareParameter(new SqlParameter("p_status", Types.TINYINT));
      declareParameter(new SqlParameter("p_category", Types.TINYINT));
      declareParameter(new SqlParameter("p_priority", Types.TINYINT));
      declareParameter(new SqlParameter("p_logonid", Types.BIGINT));
      declareParameter(new SqlParameter("p_sentdate", Types.VARCHAR));
      declareParameter(new SqlParameter("p_msg", Types.VARCHAR));
      declareParameter(new SqlParameter("p_comment", Types.VARCHAR));
      declareParameter(new SqlParameter("p_mimetype", Types.VARCHAR));
      declareParameter(new SqlParameter("p_attachments", Types.VARCHAR));
      compile();

   }

   @SuppressWarnings({"unchecked", "rawtypes"})
   public Map execute(String function, MsgData data)
   {

      Map inputs = new HashMap();

      inputs.put("p_addmodflag", function);
      inputs.put("p_source", data.getSource());
      inputs.put("p_messageid", data.getMsgID());
      if (data.getSender() == null)
         data.setSender("no-replay@invessence.com");
      inputs.put("p_sender", data.getSender());
      inputs.put("p_receiver", data.getReceiver());
      inputs.put("p_cc", data.getCc());
      inputs.put("p_bcc", data.getBcc());
      inputs.put("p_subject", data.getSubject());
      if (data.getSubject() == null)
         data.setSubject("Invessence");
      inputs.put("p_msg", data.getMsg());
      inputs.put("p_status", data.getStatus());
      inputs.put("p_category", data.getCategory());
      inputs.put("p_priority", data.getPriority());
      inputs.put("p_logonid", data.getLogonID());
      inputs.put("p_sentdate", null);
      inputs.put("p_comment", data.getComment());
      if (data.getMimeType() == null || data.getMimeType().length() < 1)
         data.setMimeType("HTML");
      inputs.put("p_mimetype", data.getMimeType());
      inputs.put("p_attachments", data.getAttachmentFile());

      return super.execute(inputs);
   }

}




