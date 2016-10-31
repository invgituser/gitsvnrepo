package com.invessence.emailer.dao;

import java.sql.*;
import java.util.List;

import com.invessence.emailer.data.InvitedGuestData;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

public class InviteDAO extends JdbcDaoSupport
{
   public List<InvitedGuestData> getEmailList() throws DataAccessException
   {

      String sql = " select distinct email, " +
                   " concat(firstName,' ', lastName)  as name, " +
                   " guesttype " +
                   " from email_invitation " +
                   " where invite = 'P' " +
                   " and length(trim(email)) > 0";

      ParameterizedRowMapper<InvitedGuestData> mapper = new ParameterizedRowMapper<InvitedGuestData>()
      {
         public InvitedGuestData mapRow(ResultSet rs, int rowNum) throws SQLException
         {

            InvitedGuestData data = new InvitedGuestData();
            data.setEmail(rs.getString("email"));
            data.setName(rs.getString("name"));
            data.setGuesttype(rs.getString("guesttype"));
            data.setWeburl(null);
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
      sql = "update email_invitation set invite = ? where email = ?";

      if (sql != null)
         getJdbcTemplate().update(sql, new Object[]{status, email});

   }

}

