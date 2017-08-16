DROP PROCEDURE IF EXISTS `invdb`.`sel_position`;

DELIMITER $$
CREATE PROCEDURE `invdb`.`sel_position`(
 	IN p_logonid  bigint(20),
 	IN p_acctnum  bigint(20)
)
BEGIN

	DECLARE tAdvisorAccess VARCHAR(25);
    DECLARE tfilterType   VARCHAR(1);
    
    DECLARE tTotalPos	 	DOUBLE;
    DECLARE tMaxReportDate	VARCHAR(10);

	SELECT 
    MIN(advisor)
	INTO tAdvisorAccess FROM
    user_advisor_access
	WHERE logonid = p_logonid;
        
	IF (tAdvisorAccess is NOT NULL)
		THEN set tfilterType = 'A';
		ELSE set tfilterType = 'O';  
	END IF;

			select SUM(`ext_position`.`positionValue`), MAX(`ext_position`.`reportDate`)
            INTO `tTotalPos`, `tMaxReportDate`
			FROM `ext_position`
			WHERE `ext_position`.`acctnum` = p_acctnum
			AND   `ext_position`.`reportDate` = (select max(`reportDate`) from `ext_position` where `ext_position`.`acctnum` = p_acctnum)
			;
            
            IF (`tTotalPos` = 0)
             THEN set `tTotalPos` = 1;
			END IF;

	IF (tfilterType = 'O')
		THEN
			select
				 (IFNULL(`sec_asset_mapping`.`assetsortorder`,0) * 10000) + IFNULL(`sec_asset_mapping`.`subclasssortorder`,9999) as `sortorder`
				,`ext_position`.`clientAccountID`
				,`ext_position`.`symbol`
				,`ext_position`.`reportDate`
				,`ext_position`.`side`
				,SUM(`ext_position`.`quantity`) as `quantity`
				,AVG(`ext_position`.`costBasisPrice`) `costBasisPrice`
				,SUM(`ext_position`.`costBasisMoney`) as `costBasisMoney`
				,AVG(`ext_position`.`markPrice`) `markPrice`
				,SUM(`ext_position`.`positionValue`) as `positionValue`
                ,SUM(`ext_position`.`pnlUnrealized`) as `fifoPnlUnrealized`
                ,SUM(`ext_position`.`positionValue`/`tTotalPos`) as `weight`
				, `ext_acct_info`.`acctnum`
                , IFNULL(`ext_acct_info`.`applicantFName`,`user_logon`.`firstname`) as `firstname`
                , IFNULL(`ext_acct_info`.`applicantLName`,`user_logon`.`lastname`) as `lastname`
				,`ext_acct_info`.`rep`
				,`ext_acct_info`.`dateOpened`
				,`sec_master`.`name` as `description`
				,IFNULL(`sec_asset_mapping`.`assetName`,`sec_master`.`assetclass`) as `assetclass`
				,IFNULL(`sec_asset_mapping`.`assetName`,`sec_master`.`assetclass`) as `assetclass`
				,`sec_master`.`subclass`
                ,IFNULL(`sec_asset_mapping`.`assetcolor`,'#ffffff') as `color`
                ,`user_trade_profile`.`theme`
				,`user_trade_profile`.`portfolioName` as `accountAlias`
			FROM `ext_position`
				 INNER JOIN `ext_acct_info`
				 ON (`ext_position`.`clientAccountID` = `ext_acct_info`.`clientAccountID`)
				 INNER JOIN `user_trade_profile`
				 ON (`ext_acct_info`.`acctnum` = `user_trade_profile`.`acctnum`)
				 INNER JOIN `user_access_role`
				 ON (`ext_acct_info`.`acctnum` = `user_access_role`.`acctnum`
				 AND  `user_access_role`.`role` in ('OWNER', 'USER'))
				 INNER JOIN `user_logon`
				 ON (`user_access_role`.`logonid` = `user_logon`.`logonid`)
				 LEFT JOIN `sec_master`
				 ON (`ext_position`.`symbol`= `sec_master`.`ticker`)
				 LEFT JOIN `sec_asset_mapping`
				 ON (`sec_master`.`ticker`= `sec_asset_mapping`.`ticker`
				 AND `sec_asset_mapping`.`theme` = `user_trade_profile`.`theme`)
			WHERE 
				  `ext_acct_info`.`status`  in ('A')
			AND   `ext_acct_info`.`acctnum` = p_acctnum
			AND   `user_logon`.`logonid`    = p_logonid
			AND   `ext_position`.`reportDate` = tMaxReportDate
            GROUP BY
				 (IFNULL(`sec_asset_mapping`.`assetsortorder`,0) * 10000) + IFNULL(`sec_asset_mapping`.`subclasssortorder`,9999)
				,`ext_position`.`clientAccountID`
				,`ext_position`.`symbol`
				,`ext_position`.`reportDate`
				,`ext_position`.`side`
				, `ext_acct_info`.`acctnum`
                , IFNULL(`ext_acct_info`.`applicantFName`,`user_logon`.`firstname`)
                , IFNULL(`ext_acct_info`.`applicantLName`,`user_logon`.`lastname`)
				,`ext_acct_info`.`rep`
				,`ext_acct_info`.`dateOpened`
				,`sec_master`.`name`
				,IFNULL(`sec_asset_mapping`.`assetName`,`sec_master`.`assetclass`)
				,IFNULL(`sec_asset_mapping`.`assetName`,`sec_master`.`assetclass`)
				,`sec_master`.`subclass`
                ,IFNULL(`sec_asset_mapping`.`assetcolor`,'#ffffff')
                ,`user_trade_profile`.`theme`
				,`user_trade_profile`.`portfolioName`
			ORDER BY 1, `sec_master`.`assetclass`, `ext_position`.`symbol`
			;
        ELSE
			select
				 (IFNULL(`sec_asset_mapping`.`assetsortorder`,0) * 10000) + IFNULL(`sec_asset_mapping`.`subclasssortorder`,9999) as `sortorder`
				,`ext_position`.`clientAccountID`
				,`ext_position`.`symbol`
				,`ext_position`.`reportDate`
				,`ext_position`.`side`
				,SUM(`ext_position`.`quantity`) as `quantity`
				,AVG(`ext_position`.`costBasisPrice`) `costBasisPrice`
				,SUM(`ext_position`.`costBasisMoney`) as `costBasisMoney`
				,AVG(`ext_position`.`markPrice`) `markPrice`
				,SUM(`ext_position`.`positionValue`) as `positionValue`
                ,SUM(`ext_position`.`pnlUnrealized`) as `fifoPnlUnrealized`
                ,SUM(`ext_position`.`positionValue`/`tTotalPos`) as `weight`
				, `ext_acct_info`.`acctnum`
                , IFNULL(`ext_acct_info`.`applicantFName`,`user_logon`.`firstname`) as `firstname`
                , IFNULL(`ext_acct_info`.`applicantLName`,`user_logon`.`lastname`) as `lastname`
				,`ext_acct_info`.`rep`
				,`ext_acct_info`.`dateOpened`
				,`sec_master`.`name` as `description`
				,IFNULL(`sec_asset_mapping`.`assetName`,`sec_master`.`assetclass`) as `assetclass`
				,IFNULL(`sec_asset_mapping`.`assetName`,`sec_master`.`assetclass`) as `assetclass`
				,`sec_master`.`subclass`
                ,IFNULL(`sec_asset_mapping`.`assetcolor`,'#ffffff') as `color`
                ,`user_trade_profile`.`theme`
				,`user_trade_profile`.`portfolioName` as `accountAlias`
		FROM `ext_position`
				 INNER JOIN `ext_acct_info`
				 ON (`ext_position`.`clientAccountID` = `ext_acct_info`.`clientAccountID`)
				 INNER JOIN `user_trade_profile`
				 ON (`ext_acct_info`.`acctnum` = `user_trade_profile`.`acctnum`)
				 INNER JOIN `user_access_role`
				 ON (`ext_acct_info`.`acctnum` = `user_access_role`.`acctnum`
				 AND  `user_access_role`.`role` in ('OWNER'))
				 INNER JOIN `user_logon`
				 ON (`user_access_role`.`logonid` = `user_logon`.`logonid`)
				 LEFT JOIN `sec_master`
				 ON (`ext_position`.`symbol`= `sec_master`.`ticker`)
				 LEFT JOIN `sec_asset_mapping`
				 ON (`sec_master`.`ticker`= `sec_asset_mapping`.`ticker`
				 AND `sec_asset_mapping`.`theme` = `user_trade_profile`.`theme`)
			WHERE 
				  `ext_acct_info`.`status`  in ('A')
			AND   `ext_acct_info`.`acctnum` = p_acctnum
			AND   IFNULL(`user_trade_profile`.`advisor`,'Invessence') like tAdvisorAccess
			AND   `ext_position`.`reportDate` = tMaxReportDate
            GROUP BY
				(IFNULL(`sec_asset_mapping`.`assetsortorder`,0) * 10000) + IFNULL(`sec_asset_mapping`.`subclasssortorder`,9999)
				,`ext_position`.`clientAccountID`
				,`ext_position`.`symbol`
				,`ext_position`.`reportDate`
				,`ext_position`.`side`
				, `ext_acct_info`.`acctnum`
                , IFNULL(`ext_acct_info`.`applicantFName`,`user_logon`.`firstname`)
                , IFNULL(`ext_acct_info`.`applicantLName`,`user_logon`.`lastname`)
				,`ext_acct_info`.`rep`
				,`ext_acct_info`.`dateOpened`
				,`sec_master`.`name`
				,IFNULL(`sec_asset_mapping`.`assetName`,`sec_master`.`assetclass`)
				,IFNULL(`sec_asset_mapping`.`assetName`,`sec_master`.`assetclass`)
				,`sec_master`.`subclass`
                ,IFNULL(`sec_asset_mapping`.`assetcolor`,'#ffffff')
                ,`user_trade_profile`.`theme`
				,`user_trade_profile`.`portfolioName`
			ORDER BY 1, `sec_master`.`assetclass`, `ext_position`.`symbol`
			;
	END IF;



END$$
DELIMITER ;
