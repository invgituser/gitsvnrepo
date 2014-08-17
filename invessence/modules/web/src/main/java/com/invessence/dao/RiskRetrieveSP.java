package com.invessence.dao;

import com.invessence.data.ManageGoals;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: ramesh
 * Date: 10/22/13
 * Time: 8:56 PM
 * To change this template use File | Settings | File Templates.
 */
public class RiskRetrieveSP extends AbsDataRetrieverByAccountNumber<ManageGoals>{

    public RiskRetrieveSP(DataSource dataSource)
    {
        super(dataSource, "sel_user_risk_questions", new RiskMapper());
    }

    static class RiskMapper implements RowMapper<ManageGoals>
    {
        @Override
        public ManageGoals mapRow(ResultSet resultSet, int i) throws SQLException
        {
            ManageGoals manageGoals = new ManageGoals();
            manageGoals.setSelectedchoice1(resultSet.getString("ans1"));
            manageGoals.setSelectedchoice2(resultSet.getString("ans2"));
            manageGoals.setSelectedchoice3(resultSet.getString("ans3"));
            manageGoals.setSelectedchoice4(resultSet.getString("ans4"));
            manageGoals.setSelectedchoice5(resultSet.getString("ans5"));
            manageGoals.setSelectedchoice6(resultSet.getString("ans6"));
            manageGoals.setSelectedchoice7(resultSet.getString("ans7"));
            return manageGoals;
        }
    }
}
