package com.invessence.web.dao;

import java.sql.Types;
import java.util.*;
import javax.sql.DataSource;

import com.google.inject.internal.Maps;
import org.springframework.jdbc.core.*;
import org.springframework.jdbc.object.StoredProcedure;

public class AbsDataRetrieverByAccountNumber<T> extends StoredProcedure
{
   public AbsDataRetrieverByAccountNumber(DataSource dataSource, String procedureName, RowMapper<T> rowMapper)
   {
      super(dataSource, procedureName);
      declareParameter(new SqlReturnResultSet("rs", rowMapper));
      declareParameter(new SqlParameter("p_acctnum", Types.BIGINT));
      compile();
   }

   @SuppressWarnings("unchecked")
   public T execute(Long accountNum , Object obj)
   {
        Map<String, Long> hashMap = Maps.newHashMap();
        hashMap.put("p_acctnum", accountNum);
        Map<String, Object> result = super.execute(hashMap);
        List resultSet = (List) result.get("rs");
        if(!resultSet.isEmpty() )
        {
           return (T) resultSet.get(0);
        }
        else
        {
           return (T) obj;
        }
   }
 }