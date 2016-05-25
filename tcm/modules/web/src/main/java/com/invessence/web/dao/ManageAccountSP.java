package com.invessence.web.dao;


import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;

import javax.sql.DataSource;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;


public class ManageAccountSP extends StoredProcedure {

    public ManageAccountSP(DataSource datasource) {
        super(datasource, "sp_manageaccount_sel");

        declareParameter(new SqlParameter("p_logonid", Types.BIGINT));
        declareParameter(new SqlParameter("p_userid", Types.VARCHAR));
        compile();
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    public Map getManageAccount(Long p_logonid, String p_userid) {

        Map inputMap = new HashMap();
        inputMap.put("p_logonid", p_logonid);
        inputMap.put("p_userid", p_userid);
        return super.execute(inputMap);
    }

}
