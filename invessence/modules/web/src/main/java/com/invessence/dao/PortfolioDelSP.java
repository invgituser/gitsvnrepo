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
 * Time: 8:03 PM
 * To change this template use File | Settings | File Templates.
 */
public class PortfolioDelSP extends StoredProcedure
{

    public PortfolioDelSP(DataSource datasource)
    {

        super(datasource, "del_virtual_portfolio");
        declareParameter(new SqlParameter("p_acctnum", Types.BIGINT));
        compile();
    }
    public void deletePortfolio(ManageGoals data)
    {
        Map inputMap = new HashMap();
        inputMap.put("p_acctnum", data.getAcctnum());
        super.execute(inputMap);
    }
}
