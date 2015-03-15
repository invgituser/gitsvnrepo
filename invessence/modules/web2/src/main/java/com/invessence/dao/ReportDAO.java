package com.invessence.dao;


import com.invessence.data.common.ReportData;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Map;
import com.invessence.converter.SQLData;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

@ManagedBean(name = "reportDAO")
@ApplicationScoped
public class ReportDAO extends JdbcDaoSupport
{
    SQLData convert = new SQLData();

    public ArrayList<ReportData> fetchReports(Long logonid, String filter) {
        DataSource ds = getDataSource();
        ReportDB sp = new ReportDB(ds, "fetchReports",0);
        Map outMap = sp.fetchReports(logonid, filter);
        ArrayList<ReportData> listofReports = new ArrayList<ReportData>();
        try {
            if (outMap != null)
            {
                ArrayList<Map<String, Object>> rows = (ArrayList<Map<String, Object>>) outMap.get("#result-set-1");
                int datarow = 0;
                for (Map<String, Object> map : rows)
                {
                    Map rs = (Map) rows.get(datarow);
                    ReportData data = new ReportData();
                    data.setFilename(convert.getStrData(rs.get("filename")));
                    data.setLocation(convert.getStrData(rs.get("location")));
                    data.setReporttype(convert.getStrData(rs.get("reporttype")));
                    data.setCreated(convert.getStrData(rs.get("created")));

                    listofReports.add(data);
                    datarow++;
                }
                return listofReports;
            }
        }
        catch (Exception ex) {
        }
        return null;
    }
}

