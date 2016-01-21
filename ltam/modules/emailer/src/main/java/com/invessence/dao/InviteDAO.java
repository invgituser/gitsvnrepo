package com.invessence.dao;

import java.sql.*;
import java.util.List;

import com.invessence.data.*;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

public class InviteDAO extends JdbcDaoSupport
{
   private String weburl;
   public void setWeburl(String weburl)
   {
      this.weburl = weburl;
   }

   public List<InvitedGuestData> getEmailList() throws DataAccessException
   {

      String sql = " select distinct email, " +
                   " acctRegistration as name " +
                   " from ltam_acct_info " +
                   " where invite = 'P' " +
                   " and length(trim(email)) > 0";


      ParameterizedRowMapper<InvitedGuestData> mapper = new ParameterizedRowMapper<InvitedGuestData>()
      {
         public InvitedGuestData mapRow(ResultSet rs, int rowNum) throws SQLException
         {

            InvitedGuestData data = new InvitedGuestData();
            data.setEmail(rs.getString("email"));
            data.setName(rs.getString("name"));
            data.setWeburl(weburl);
            return data;
         }
      };

      System.out.println(sql);
      List<InvitedGuestData> theeList = getJdbcTemplate().query(sql, mapper);
      return theeList;

   }


   public void updMsgStatus(String status, String email)
   {
      String sql = null;
      sql = "update ltam_acct_info set invite = ? where email = ?";

      if (sql != null)
         getJdbcTemplate().update(sql, new Object[]{status, email});

   }

}

