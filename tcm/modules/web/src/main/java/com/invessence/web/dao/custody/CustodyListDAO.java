package com.invessence.web.dao.custody;

import java.io.Serializable;
import java.util.*;
import javax.faces.bean.*;
import javax.sql.DataSource;

import com.invessence.web.data.custody.td.AcctOwnersDetails;
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

   public void getTDAccountHolder( TDMasterData data)
   {
      DataSource ds = getDataSource();
      CustodyListSP sp = new CustodyListSP(ds, "sel_tddc_acct_owner",0);
      Map outMap = sp.getTDAccountHolder(data.getAcctnum());
      try {
         if (outMap != null)
         {
            Integer whichAcct;
            ArrayList<Map<String, Object>> rows = (ArrayList<Map<String, Object>>) outMap.get("#result-set-1");
            if (rows == null)
               return;
            int i = 0;
            for (Map<String, Object> map : rows)
            {
               Map rs = (Map) rows.get(i);
               AcctOwnersDetails acctHolder = null;
               whichAcct = convert.getIntData(rs.get("acctOwnerId"));
               switch (whichAcct) {
                  case 1:
                     acctHolder = data.getAcctOwnersDetail();
                     break;
                  case 2:
                     acctHolder = data.getAcctOwnersDetail();
                     break;
                  default:
               }
               if (acctHolder != null) {
               acctHolder.setAcctnum(convert.getLongData(rs.get("acctnum")));
               acctHolder.setAcctOwnerId(convert.getIntData(rs.get("acctOwnerId")));
               acctHolder.setOwnership(convert.getStrData(rs.get("ownership")));
               acctHolder.setFirstName(convert.getStrData(rs.get("firstName")));
               acctHolder.setMidInitial(convert.getStrData(rs.get("midInitial")));
               acctHolder.setSsn(convert.getStrData(rs.get("ssn")));
               acctHolder.setDob(convert.getStrData(rs.get("dob")));
               acctHolder.setPhoneNumber(convert.getStrData(rs.get("phoneNumber")));
               acctHolder.setPhoneNumberNonUS(convert.getStrData(rs.get("phoneNumberNonUS")));
               acctHolder.setSecondPhoneNumber(convert.getStrData(rs.get("secondPhoneNumber")));
               acctHolder.setSecondPhoneNumberNonUS(convert.getStrData(rs.get("secondPhoneNumberNonUS")));
               acctHolder.setEmailAddress(convert.getStrData(rs.get("emailAddress")));
               acctHolder.setPhysicalAddressStreet(convert.getStrData(rs.get("physicalAddressStreet")));
               acctHolder.setPhysicalAddressCity(convert.getStrData(rs.get("physicalAddressCity")));
               acctHolder.setPhysicalAddressState(convert.getStrData(rs.get("physicalAddressState")));
               acctHolder.setPhysicalAddressZipCode(convert.getStrData(rs.get("physicalAddressZipCode")));
               acctHolder.setMailingAddressStreet(convert.getStrData(rs.get("mailingAddressStreet")));
               acctHolder.setMailingAddressCity(convert.getStrData(rs.get("mailingAddressCity")));
               acctHolder.setMailingAddressState(convert.getStrData(rs.get("mailingAddressState")));
               acctHolder.setMailingAddressZipCode(convert.getStrData(rs.get("mailingAddressZipCode")));
               acctHolder.setCitizenshiId(convert.getStrData(rs.get("citizenshiId")));
               acctHolder.setCountryOfCitizenship(convert.getStrData(rs.get("countryOfCitizenship")));
               acctHolder.setCountryOfBirth(convert.getStrData(rs.get("countryOfBirth")));
               acctHolder.setSPF(convert.getStrData(rs.get("isSPF")));
               acctHolder.setSpfDetail(convert.getStrData(rs.get("spfDetail")));
               acctHolder.setDirectorShareholder(convert.getStrData(rs.get("isDirectorShareholder")));
               acctHolder.setDirectorShareholderDetail(convert.getStrData(rs.get("directorShareholderDetail")));
               acctHolder.setBd(convert.getStrData(rs.get("bd")));
               acctHolder.setBdDetail(convert.getStrData(rs.get("bdDetail")));
               acctHolder.setOwnership(convert.getStrData(rs.get("ownershipPercent")));
               acctHolder.setCreatedBy(convert.getStrData(rs.get("createdBy")));
               }
            }
         }
      }
      catch (Exception ex) {

      }
   }

   public void getTDEmployment(TDMasterData data)
   {
      DataSource ds = getDataSource();
      CustodyListSP sp = new CustodyListSP(ds, "sel_tddc_employment",1);
      Map outMap = sp.getTDAccountHolder(data.getAcctnum());
   }

   public void getTDBeneficiary(TDMasterData data)
   {
      DataSource ds = getDataSource();
      CustodyListSP sp = new CustodyListSP(ds, "sel_tddc_benefiaciary",2);
      Map outMap = sp.getTDBeneficiary(data.getAcctnum());
   }

}
