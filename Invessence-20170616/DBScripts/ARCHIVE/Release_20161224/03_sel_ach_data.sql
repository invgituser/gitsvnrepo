use invdb;
DROP PROCEDURE IF EXISTS `invdb`.`sel_fund_ach_data`;

DELIMITER $$
CREATE PROCEDURE `invdb`.`sel_fund_ach_data`(
IN  p_acctnum	bigint(20)
)
BEGIN
SELECT 
    b.reqId,
    b.acctnum,
    b.tranStartDate,
    b.tranAmount,
    b.tranFreqId,
    `dc_ach_bank_details`.`moveMoneyPayMethodID`,
    `dc_ach_bank_details`.`achId`,
    `dc_ach_bank_details`.`bankAcctType`,
    `dc_ach_bank_details`.`bankName`,
    `dc_ach_bank_details`.`bankABARouting`,
    `dc_ach_bank_details`.`bankCityState`,
    `dc_ach_bank_details`.`bankPhoneNumber`,
    `dc_ach_bank_details`.`bankAcctName`,
    `dc_ach_bank_details`.`bankAcctNumber`,
    `dc_ach_bank_details`.`createdBy`
FROM
    `dc_ach_bank_details`
        JOIN
    dc_elecfund_transfer_details b ON `dc_ach_bank_details`.`achId` = b.achId
        AND `dc_ach_bank_details`.moveMoneyPayMethodID = b.moveMoneyPayMethodID
        JOIN
    `dc_mp_movemoney_paymethod` ON `dc_mp_movemoney_paymethod`.`moveMoneyPayMethId` = `dc_ach_bank_details`.`moveMoneyPayMethodID`
WHERE
    b.tranStartDate IS NULL and b.acctnum= `p_acctnum`
    and b.reqId=(select max(reqid) from dc_requests where acctnum=b.acctnum and dc_requestFor='ACH');

END$$
DELIMITER ;
    
