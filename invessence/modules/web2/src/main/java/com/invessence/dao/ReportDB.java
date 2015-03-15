package com.invessence.dao;


import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;

import javax.sql.DataSource;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;


public class ReportDB extends StoredProcedure
{
    public ReportDB(DataSource datasource, String sp_name, Integer mode)
    {
        super(datasource, sp_name);
        switch (mode) {
            case 0:
                declareParameter(new SqlParameter("p_logonid", Types.BIGINT));
                declareParameter(new SqlParameter("p_filter", Types.VARCHAR));
                break;
            default:
        }
        compile();
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    public Map fetchReports(Long logonid, String filter)
    {
        Map inputMap = new HashMap();
        inputMap.put("p_acctnum", logonid);
        inputMap.put("p_filter", filter);
        return super.execute(inputMap);
    }

}

