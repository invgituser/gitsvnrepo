CREATE TABLE `invessence_db_info` (
  `obj_type` varchar(20) NOT NULL,
  `obj_name` varchar(60) NOT NULL,
  `description` varchar(80) DEFAULT NULL,
  `sort_order` int(11) DEFAULT NULL,
  PRIMARY KEY (`obj_type`,`obj_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


INSERT INTO `invessence_db_info`
(
  `obj_type`,
  `obj_name`
)
SELECT ROUTINE_TYPE, ROUTINE_NAME
FROM INFORMATION_SCHEMA.ROUTINES
WHERE 
	ROUTINE_SCHEMA = 'invdb'
;

INSERT INTO `invessence_db_info`
(
  `obj_type`,
  `obj_name`
)
SELECT TABLE_TYPE, TABLE_NAME
FROM INFORMATION_SCHEMA.TABLES
WHERE 
	TABLE_SCHEMA = 'invdb'
;