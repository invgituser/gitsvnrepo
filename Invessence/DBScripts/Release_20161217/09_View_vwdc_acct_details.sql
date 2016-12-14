use invdb;

CREATE or replace
 view `vwdc_acct_details` AS
    SELECT 
        `actdet`.`acctnum` AS `acctnum`,
        `acct_info`.`clientAccountID` AS `clientAccountID`,
        `actdet`.`caseNumber` AS `caseNumber`,
        `actdet`.`advisorId` AS `advisorId`,
        `advdet`.`advisorCode` AS `advisorCode`,
        `advdet`.`firmName` AS `firmName`,
        `advdet`.`primaryContact` AS `primaryContact`,
        IF((ISNULL(`actdet`.`acctTypeId`)
                OR (`actdet`.`acctTypeId` = '')),
            NULL,
            (SELECT 
                    `dc_m_lookup`.`value`
                FROM
                    `dc_m_lookup`
                WHERE
                    (`dc_m_lookup`.`lookupCode` = `actdet`.`acctTypeId`))) AS `accountType`,
        'TDACASH' AS `cashsweepvehiclechoice`,
        'Hold' AS `divIntPref`,
        'Electronic' AS `monthlyStatements`,
        'Electronic' AS `tradeConfirmations`,
        `actdet`.`dupStatement` AS `dupStatement`,
        `actdet`.`dupTradeConfirm` AS `dupTradeConfirm`,
        'Client' AS `proxy`
    FROM
        (`dc_acct_details` `actdet`
        JOIN `dc_advisor_details` `advdet`)
        left join `ext_acct_info` `acct_info` 
        on `actdet`.`acctnum`=`acct_info`.`acctnum`
    WHERE
        (`advdet`.`id` = `actdet`.`advisorId`);