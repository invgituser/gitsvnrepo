DROP PROCEDURE IF EXISTS `testing`.`sp_emulate_process_account`;
DROP PROCEDURE IF EXISTS `testing`.`sp_emulate_step1_process_account`;

DELIMITER $$
CREATE PROCEDURE `testing`.`sp_emulate_step1_process_account`(
  IN p_acctnum BIGINT
)
  BEGIN
    DECLARE tFound1 INTEGER;
    DECLARE tFound2 INTEGER;
    DECLARE tStatus VARCHAR(2);
    DECLARE tManaged VARCHAR(2);

    SELECT count(*)
    INTO tFound1
    FROM `invdb`.`ext_acct_info`
    WHERE `ext_acct_info`.`acctnum` = `p_acctnum`;

    IF (IFNULL(tFound1, 0) = 0)
    THEN
      SELECT 'No account found!' AS msg;
    ELSE
      SELECT
        `managed`,
        `status`
      INTO tManaged, tStatus
      FROM `invdb`.`ext_acct_info`
      WHERE `ext_acct_info`.`acctnum` = `p_acctnum`;

      IF (IFNULL(tManaged, 'N') != 'N' AND IFNULL(tStatus, 'P') != 'P')
      THEN
        SELECT 'This account is either already active or already processed!' AS msg;
      ELSE
        UPDATE `invdb`.`ext_acct_info`
        SET `managed` = 'N', `status` = 'P'
        WHERE `ext_acct_info`.`acctnum` = `p_acctnum`;
      END IF;

    END IF;

  END$$
DELIMITER ;
