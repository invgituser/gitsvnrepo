package com.invessence.web.dao.custody;

import java.io.Serializable;
import java.util.Map;
import javax.faces.bean.*;
import javax.sql.DataSource;

import org.springframework.jdbc.core.support.JdbcDaoSupport;
import com.invessence.converter.SQLData;
import com.invessence.web.data.custody.TDMasterData;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 8/20/16
 * Time: 11:26 AM
 * To change this template use File | Settings | File Templates.
 */

@ManagedBean(name = "custodyListDAO")
@SessionScoped
public class CustodyListDAO extends JdbcDaoSupport implements Serializable
{
   SQLData convert = new SQLData();

   public Boolean getMasterData( TDMasterData data)
   {
      DataSource ds = getDataSource();
      CustodyListSP sp = new CustodyListSP(ds, "td_getmaster",0);
      Map outMap = sp.getMasterData(data.getAcctnum());
      if (outMap == null)
         return (false);
      else
         return true;
   }

}
