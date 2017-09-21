use invdb;
CREATE OR REPLACE VIEW invdb.vwdc_td_transfer_details AS
    SELECT 
        IF((ext.clientAccountID IS NULL
                OR ext.clientAccountID = ''),
            'N/A',
            ext.clientAccountID) naAcctNumber,
        ttd.acctnum,
        ttd.reqId,
        GETTITLEREGI(ttd.acctnum) accountTitle,
        ttd.firmName,
        ttd.primaryContact,
        ttd.priorFirmName,
        ttd.retailAccountNumber,
        ttd.isBrokerAcct,
        ttd.advisorID,
        ttd.removeAdvisor,
        ttd.addAdvisor,
        ttd.ssn
    FROM
        (dc_td_transfer_details ttd, dc_acct_details ad, dc_acct_owners_details aod)
            LEFT OUTER JOIN
        ext_acct_info ext ON ext.acctnum = ttd.acctnum
    WHERE
        ad.acctnum = ttd.acctnum
            AND aod.acctnum = ttd.acctnum
            AND aod.ownership IN ('AOPRIMARY' , 'AOCUSTODIAN'); 
 