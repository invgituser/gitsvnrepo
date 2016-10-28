DROP VIEW IF EXISTS `vw_fees_charged`;

CREATE 
VIEW `vw_fees_charged` AS
    select 
        `fees`.`clientAccountID` AS `clientAccountID`,
        `fees`.`processed` AS `processed`,
        `IB`.`lastName` AS `lastname`,
        `IB`.`firstName` AS `firstname`,
        `IB`.`acctnum` AS `Invessence_acctnum`,
        `IB`.`email` AS `email`,
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
        monthname(date_format(`fees`.`fromDate`, '%Y%m%d')) AS `currentMonth`,
        year(date_format(`fees`.`fromDate`, '%Y%m%d')) AS `currentYear`,
        monthname(date_format(`fees`.`priorfromDate`, '%Y%m%d')) AS `priorMonth`,
        year(date_format(`fees`.`priorfromDate`, '%Y%m%d')) AS `priorYear`,
		`fees`.sentDate
    from
        ((`fees_charged` `fees`
        join `IB_Accounts` `IB`)
        left join `fees_charged` `prior` ON (((`fees`.`clientAccountID` = `prior`.`clientAccountID`)
            and (convert( funct_FirstDayofPriorMonth(`fees`.`fromDate`) using utf8) = `prior`.`fromDate`))))
    where
        (`fees`.`clientAccountID` = convert( `IB`.`IB_acctnum` using utf8));
