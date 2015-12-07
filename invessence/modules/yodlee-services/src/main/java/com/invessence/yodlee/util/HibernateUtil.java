package com.invessence.yodlee.util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.stereotype.Component;


@Component
public class HibernateUtil {

    public List executeSQLQuery(Session session,String tableName, Class theClass, String where, Object arg[]) {
        SQLQuery q = session.createSQLQuery( "select {" + tableName + ".*} from " + tableName + " where " + where);
        q.addEntity( tableName, theClass );
        for (int i = 0; i < arg.length; i++) {
        	
        	if(arg[i]!=null)
        	//System.out.println(arg[i].getClass().getName());
        	
            if (arg[i] instanceof Integer) {
                q.setInteger( i, ((Integer)arg[i]).intValue() );
            }
            else if (arg[i] instanceof Short) {
                q.setShort( i, ((Short)arg[i]).shortValue() );
            }
            else if (arg[i] instanceof Long) {
                q.setLong( i, ((Long)arg[i]).longValue() );
            }
            else if (arg[i] instanceof Double) {
                q.setDouble( i, ((Double) arg[i]).doubleValue() );
            }
            else if (arg[i] instanceof Float) {
                q.setFloat( i, ((Float) arg[i]).floatValue() );
            }
            else if (arg[i] instanceof BigDecimal) {
                q.setBigDecimal( i, (BigDecimal) arg[i] );
            }
            else if (arg[i] instanceof BigInteger) {
                q.setBigInteger( i, (BigInteger) arg[i] );
            }
            else if (arg[i] instanceof String) {
                q.setString( i, (String) arg[i] );
            }
            else if (arg[i] instanceof Boolean) {
                q.setBoolean( i, ((Boolean) arg[i]).booleanValue() );
            }
            else if (arg[i] instanceof Date) {
                q.setDate( i, (Date) arg[i] );
            }
            else if (arg[i] instanceof Timestamp) {
            	
                q.setTimestamp( i, (Timestamp) arg[i] );
            }
            else {
                throw new IllegalStateException( "bad type" );
            }
        }

        List list = q.list();

        return list;
    }

    
    public int executeDeleteQuery(Session session,String tableName, Class theClass, String where, Object arg[]) {
        SQLQuery q = session.createSQLQuery( "delete from " + tableName + " where " + where);
        q.addEntity( tableName, theClass );
        for (int i = 0; i < arg.length; i++) {
            if (arg[i] instanceof Integer) {
                q.setInteger( i, ((Integer)arg[i]).intValue() );
            }
            else if (arg[i] instanceof Short) {
                q.setShort( i, ((Short)arg[i]).shortValue() );
            }
            else if (arg[i] instanceof Long) {
                q.setLong( i, ((Long)arg[i]).longValue() );
            }
            else if (arg[i] instanceof Double) {
                q.setDouble( i, ((Double) arg[i]).doubleValue() );
            }
            else if (arg[i] instanceof Float) {
                q.setFloat( i, ((Float) arg[i]).floatValue() );
            }
            else if (arg[i] instanceof BigDecimal) {
                q.setBigDecimal( i, (BigDecimal) arg[i] );
            }
            else if (arg[i] instanceof BigInteger) {
                q.setBigInteger( i, (BigInteger) arg[i] );
            }
            else if (arg[i] instanceof String) {
                q.setString( i, (String) arg[i] );
            }
            else if (arg[i] instanceof Boolean) {
                q.setBoolean( i, ((Boolean) arg[i]).booleanValue() );
            }
            else if (arg[i] instanceof Date) {
                q.setDate( i, (Date) arg[i] );
            }
            else {
                throw new IllegalStateException( "bad type" );
            }
        }

        int result= q.executeUpdate();

        return result;
    }
    
    
    


	
}
