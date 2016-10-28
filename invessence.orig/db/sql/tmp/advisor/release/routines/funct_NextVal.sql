delimiter $$
DROP FUNCTION IF EXISTS `funct_NextVal`
$$

CREATE FUNCTION `funct_NextVal`(
        vSeq_Key       VARCHAR(10)
) RETURNS bigint(20)
    DETERMINISTIC
BEGIN
	DECLARE tNextID BIGINT;
	BEGIN
		IF (vSeq_Key is NULL)
		   THEN RETURN NULL;
		END IF;

		BEGIN
			SELECT MAX(ID) INTO tNextID
			FROM `sequence`
			WHERE seqkey = vSeq_Key;
		END;

		IF (tNextID is NULL)
			THEN
			BEGIN
				SET tNextID = 100;
				INSERT INTO `sequence`
				(seqkey, id)
				VALUES (vSeq_Key,tNextID);
			END;
			ELSE
			BEGIN
				SET tNextID = IFNULL(tNextID,100)+1;
				UPDATE `sequence`
				set id = tNextID
				WHERE seqkey = vSeq_Key;
			END;
			
		END IF;
		RETURN tNextID;
	END;
END$$

