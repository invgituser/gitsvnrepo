package com.invessence.dao;

import com.invessence.data.ManageGoals;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;

import javax.sql.DataSource;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: ramesh
 * Date: 10/28/13
 * Time: 7:13 PM
 * To change this template use File | Settings | File Templates.
 */
public class AllocationDelSP extends StoredProcedure
{

    public AllocationDelSP(DataSource datasource)
    {

        super(datasource, "del_asset_alloc");
        declareParameter(new SqlParameter("p_acctnum", Types.BIGINT));
        declareParameter(new SqlParameter("p_allocationmodel", Types.VARCHAR));
        declareParameter(new SqlParameter("p_assetyear", Types.TINYINT));
        compile();
    }
    public void deleteAllocation(ManageGoals data)
    {
        Map inputMap = new HashMap();
        inputMap.put("p_acctnum", data.getAcctnum());
        inputMap.put("p_allocationmodel", 'D');
        inputMap.put("p_assetyear", data.getAssetyear());
        super.execute(inputMap);
    }
}

