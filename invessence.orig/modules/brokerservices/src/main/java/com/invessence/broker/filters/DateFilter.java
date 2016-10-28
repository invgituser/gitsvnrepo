package com.invessence.broker.filters;

import static com.invessence.broker.constants.BrokerServiceConstants.YEAR_MONTH_DAY_FORMAT;
import static com.invessence.broker.constants.BrokerServiceConstants.YEAR_MONTH_FORMAT;

import java.text.*;
import java.util.Date;

import org.apache.log4j.Logger;

import com.invessence.broker.dao.DatabaseBean;

public class DateFilter {
    private Date dbDate;
    private Logger logger = Logger.getLogger(DateFilter.class.getName());
    private Date previousMonthDate;
    private Date firstDayOfMonth;

    public void setDatabaseBean(DatabaseBean databaseBean) {
        try {
            dbDate = new SimpleDateFormat(YEAR_MONTH_DAY_FORMAT).parse(databaseBean.getBrokerBusinessDate());
            previousMonthDate = new SimpleDateFormat(YEAR_MONTH_FORMAT).parse(databaseBean.getPreviousMonthDate());
            firstDayOfMonth = new SimpleDateFormat(YEAR_MONTH_DAY_FORMAT).parse(databaseBean.getFirstDayOfMonth());
        } catch (ParseException e) {
            logger.error("Error parsing date:", e);
        }
    }

    public boolean accept(String fileName) {
        if (dbDate != null) {
            try {
                Date fileDate;
                if (fileName.length() == 8) {
                    fileDate = new SimpleDateFormat(YEAR_MONTH_DAY_FORMAT).parse(fileName);
                } else {
                    String[] strings = fileName.split("\\.");
                    fileDate = new SimpleDateFormat(YEAR_MONTH_DAY_FORMAT).parse(strings[strings.length - 2]);
                }
                return fileDate.after(dbDate);
            } catch (ParseException e) {
                logger.error("Error parsing date in file name " + fileName, e);
            }
        }
        return false;
    }

    public boolean acceptSummaryFile(String fileName) {
        if (previousMonthDate != null) {
            String[] strings = fileName.split("\\.");
            try {
                Date fileDate = new SimpleDateFormat(YEAR_MONTH_FORMAT).parse(strings[strings.length - 2]);
                return fileDate.after(previousMonthDate);
            } catch (ParseException e) {
                logger.error("Error parsing date in file name " + fileName, e);
            }
        }
        return false;
    }

    public boolean acceptFirstDayOfMonth(String fileName) {
        if (firstDayOfMonth != null) {
            String[] strings = fileName.split("\\.");
            try {
                Date fileDate = new SimpleDateFormat(YEAR_MONTH_DAY_FORMAT).parse(strings[strings.length - 2]);
                return fileDate.after(firstDayOfMonth);
            } catch (ParseException e) {
                logger.error("Error parsing date in file name " + fileName, e);
            }
        }
        return false;
    }
}
