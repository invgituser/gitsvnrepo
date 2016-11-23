DROP PROCEDURE IF EXISTS invdb.`save_tddc_acct_transfer_details`;

DELIMITER $$
CREATE PROCEDURE invdb.`save_tddc_acct_transfer_details`(
  `p_acctnum` bigint(20),
  `p_reqId` bigint(20),
  `p_fromAccountTitle` varchar(100),
  `p_accountNumber2` varchar(100),
  `p_contraFirmList` varchar(100),
  `p_fromFirmAddress` varchar(100),
  `p_fromFirmPhoneNumber` varchar(50),
  `p_fromEEPlanType` varchar(45),
  `p_simpleFunded` varchar(1),
  `p_fromOtherAccountType` varchar(45),
  `p_transferTypeId` varchar(45),
  `p_createdBy` varchar(45),
  `p_otherContraFirmList` varchar(100)
)
BEGIN
  DECLARE tfound Integer;
  
  SELECT count(*)
  INTO tfound
  FROM `dc_acct_transfer_details`
  WHERE `acctnum` = `p_acctnum`;
  
  
  IF (IFNULL(tfound,0) = 0)
  THEN
	  INSERT INTO `dc_acct_transfer_details`
			(`acctnum`,
			`reqId`,
			`fromAccountTitle`,
			`accountNumber2`,			
			`fromFirmAddress`,
			`fromFirmPhoneNumber`,
			`fromEEPlanType`,
			`simpleFunded`,
			`fromOtherAccountType`,
			`transferTypeId`,
            `contraFirmList` ,
            `otherContraFirmList` ,
			`created`,
			`createdBy`)
		VALUES
			(`p_acctnum`,
			`p_reqId`,
			`p_fromAccountTitle`,
			`p_accountNumber2`,			
			`p_fromFirmAddress`,
			`p_fromFirmPhoneNumber`,
			`p_fromEEPlanType`,
			`p_simpleFunded`,
			`p_fromOtherAccountType`,
			`p_transferTypeId`,
            `p_contraFirmList` ,
            `p_otherContraFirmList` ,
			now(),
			`p_createdBy`);

  ELSE
		  UPDATE `dc_acct_transfer_details`
			SET
						`reqId`			=`p_reqId`,
				`fromAccountTitle`	= `p_fromAccountTitle`,
				`accountNumber2`	= `p_accountNumber2`,				
				`fromFirmAddress`	= `p_fromFirmAddress`,
				`fromFirmPhoneNumber` = `p_fromFirmPhoneNumber`,
				`fromEEPlanType`	= `p_fromEEPlanType`,
				`simpleFunded`		= `p_simpleFunded`,
				`fromOtherAccountType`	= `p_fromOtherAccountType`,
				`transferTypeId`	= `p_transferTypeId`,
                `contraFirmList`  =`p_contraFirmList` ,
                `otherContraFirmList`  =`p_otherContraFirmList` ,
				`updated`			= now(),
				`updatedBy`			= `p_createdBy`
			WHERE `acctnum`	= `p_acctnum` 
            ;

		
  END IF;
  

END$$
DELIMITER ;