package com.invessence.web.dao.advisor;

import java.sql.SQLException;
import java.util.*;
import javax.sql.DataSource;

import com.invessence.converter.SQLData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * Created by sagar on 7/19/2017.
 */

@Repository("advisorSaveQuery")
public class AdvisorSaveQuery
   {

      @Autowired
      private JdbcTemplate invJdbcTemplate;
      @Autowired
      DataSource dataSource;

      private JdbcTemplate jdbcTemplate;


      public void saveFileData(String query) throws SQLException
      {

         invJdbcTemplate.execute(query);
      }
}
