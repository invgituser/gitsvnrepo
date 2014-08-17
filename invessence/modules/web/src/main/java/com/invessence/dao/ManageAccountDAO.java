package com.invessence.dao;

import java.util.*;
import javax.sql.DataSource;

import com.invessence.data.ManageAccount;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;

public class ManageAccountDAO extends SimpleJdbcDaoSupport
{
   public List<ManageAccount> getManageAccount(Long p_logonid, String p_userid)
   {
      DataSource ds = getDataSource();
      ManageAccountSP sp = new ManageAccountSP(ds);
      List<ManageAccount> manageAccountList = new ArrayList<ManageAccount>();

      Map outMap = sp.getManageAccount(p_logonid, p_userid);
      if (outMap != null)
      {
         ArrayList<Map<String, Object>> rows = (ArrayList<Map<String, Object>>) outMap.get("#result-set-1");
         int i = 0;
         for (Map<String, Object> map : rows)
         {
            Map rs = (Map) rows.get(i);
            ManageAccount data = new ManageAccount();
            data.setLogonid(Long.parseLong(getData(rs.get("logonid"))));
            data.setAcctnum(Long.parseLong(getData(rs.get("acctnum"))));
            data.setIB_acctnum(getData(rs.get("IB_Account")));
            data.setFunctionid(getData(rs.get("functionid")));
            data.setRole(getData(rs.get("role")));
            data.setPrivileges(getData(rs.get("privileges")));
            data.setGoal(getData(rs.get("goal")));
            data.setAccounttype(getData(rs.get("accttype")));
            data.setAcctstate(getData(rs.get("acctstate")));
            data.setCreated(getData(rs.get("created")));
            data.setLastupdated(getData(rs.get("lastupdated")));
            manageAccountList.add(i, data);
            i++;
         }
      }

      return manageAccountList;

   }

   private String getData(Object dataobj)
   {
      String val = null;
      try
      {
         if (dataobj != null)
         {
            val = dataobj.toString();
         }
      }
      catch (Exception ex)
      {
         System.out.println("Stored procedure dataobj IS NULL!");
      }
      return val;
   }
    /*
    public List<ManageAccount> getInvestmentsList(Long logonid, String userid) throws DataAccessException {

        String sql = "call sp_manageaccount_sel(?,?)";

        ParameterizedRowMapper<ManageAccount> mapper = new ParameterizedRowMapper<ManageAccount>() {
            public ManageAccount mapRow(ResultSet rs, int rowNum) throws SQLException {

                ManageAccount data = new ManageAccount();
                data.setLogonid(rs.getLong("logonid"));
                data.setAcctnum(rs.getLong("acctnum"));
                data.setFunctionid(rs.getInt("functionid"));
                data.setRole(rs.getString("role"));
                data.setPrivileges(rs.getString("privileges"));
                data.setAccounttype(rs.getString("accttype"));
                data.setName(rs.getString("name"));
                data.setAcctstate(rs.getString("acctstate"));
                data.setCreated(rs.getString("created"));
                data.setLastupdated(rs.getString("lastupdated"));

                return data;
            }
        };

        System.out.println(sql);

        return getSimpleJdbcTemplate().query(sql, mapper, logonid, userid);

    }
    */
}

