DROP PROCEDURE IF EXISTS `sel_user_risk_questions`;

DELIMITER $$
CREATE PROCEDURE `sel_user_risk_questions`
(
	INOUT	p_acctnum	bigint(20)
)
BEGIN
		SELECT `user_risk_questions`.`acctnum`,
			`user_risk_questions`.`ans1`,
			`user_risk_questions`.`ans2`,
			`user_risk_questions`.`ans3`,
			`user_risk_questions`.`ans4`,
			`user_risk_questions`.`ans5`,
			`user_risk_questions`.`ans6`,
			`user_risk_questions`.`ans7`,
			`user_risk_questions`.`ans8`,
			`user_risk_questions`.`ans9`,
			`user_risk_questions`.`ans10`,
			`user_risk_questions`.`ans11`,
			`user_risk_questions`.`ans12`,
			`user_risk_questions`.`ans13`,
			`user_risk_questions`.`ans14`,
			`user_risk_questions`.`ans15`,
			`user_risk_questions`.`created`,
			`user_risk_questions`.`lastUpdated`
		FROM `user_risk_questions`
		WHERE `acctnum`= p_acctnum;
END
$$