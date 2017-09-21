CREATE or REPLACE
VIEW `invdb`.`vw_fees_charged` AS
    SELECT 
        `fees`.`clientAccountID` AS `clientAccountID`,
        `fees`.`processed` AS `processed`,
        `ext_acct_info`.`applicantLName` AS `lastname`,
        `ext_acct_info`.`applicantLName` AS `firstname`,
        `ext_acct_info`.`acctnum` AS `Invessence_acctnum`,
        `ext_acct_info`.`email` AS `email`,
        `fees`.`fromDate` AS `fromDate`,
        `fees`.`toDate` AS `toDate`,
        `fees`.`firstBusinessDate` AS `firstBusinessDate`,
        `fees`.`lastBusinessDate` AS `lastBusinessDate`,
        `fees`.`billingYear` AS `billingYear`,
        `fees`.`advisor` AS `advisor`,
        `fees`.`cash` AS `cash`,
        `fees`.`invested` AS `invested`,
        `fees`.`priorfromDate` AS `priorfromDate`,
        `fees`.`priortoDate` AS `priortoDate`,
        `fees`.`priorcash` AS `priorcash`,
        `fees`.`priorinvested` AS `priorinvested`,
        `fees`.`billingDays` AS `billingDays`,
        `fees`.`commission` AS `commission`,
        `fees`.`otherFee` AS `otherFee`,
        `fees`.`invessenceFee` AS `invessenceFee`,
        `fees`.`advisorFee` AS `advisorFee`,
        `fees`.`totalAdvisorFee` AS `totalAdvisorFee`,
        `fees`.`invoiceFee` AS `invoiceFee`,
        `fees`.`ytdcommission` AS `ytdcommission`,
        `fees`.`ytdotherFee` AS `ytdotherFee`,
        `fees`.`ytdinvessenceFee` AS `ytdinvessenceFee`,
        `fees`.`ytdAdvisorFee` AS `ytdAdvisorFee`,
        `fees`.`ytdtotalAdvisorFee` AS `ytdtotalAdvisorFee`,
        `fees`.`ytdinvoiceFee` AS `ytdinvoiceFee`,
        MONTHNAME(DATE_FORMAT(`fees`.`fromDate`, '%Y%m%d')) AS `currentMonth`,
        YEAR(DATE_FORMAT(`fees`.`fromDate`, '%Y%m%d')) AS `currentYear`,
        MONTHNAME(DATE_FORMAT(`fees`.`priorfromDate`, '%Y%m%d')) AS `priorMonth`,
        YEAR(DATE_FORMAT(`fees`.`priorfromDate`, '%Y%m%d')) AS `priorYear`,
        `fees`.`sentDate` AS `sentDate`
    FROM
        ((`fees_charged` `fees`
        JOIN `ext_acct_info` `ext_acct_info`)
        LEFT JOIN `fees_charged` `prior` ON (((`fees`.`clientAccountID` = `prior`.`clientAccountID`)
            AND (CONVERT( FUNCT_FIRSTDAYOFPRIORMONTH(`fees`.`fromDate`) USING UTF8) = `prior`.`fromDate`))))
    WHERE
        (`fees`.`clientAccountID` = CONVERT( `ext_acct_info`.`clientAccountID` USING UTF8));
