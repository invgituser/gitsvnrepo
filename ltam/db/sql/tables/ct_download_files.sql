DROP TABLE download_files;

CREATE TABLE `download_files` (
  `vendor` varchar(20) NOT NULL,
  `filename` varchar(40) NOT NULL,
  `active` varchar(1) NOT NULL DEFAULT 'N',
  `tmp_tableName` varchar(45) DEFAULT NULL,
  `available` varchar(7) NOT NULL DEFAULT 'N',
  `sourcepath` varchar(7) DEFAULT NULL,
  `downloaddir` varchar(45) NOT NULL,
  `format` varchar(7) NOT NULL,
  `required` varchar(1) NOT NULL DEFAULT 'N',
  `canbeempty` varchar(1) NOT NULL DEFAULT 'Y',
  `postProcess` varchar(7) DEFAULT NULL,
  `postInstruction` varchar(80) DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  `lastupdated` datetime DEFAULT NULL,
  PRIMARY KEY (`vendor`,`filename`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


insert into download_files
SELECT `tmp`.`vendor`,
    `tmp`.`filename`,
    `tmp`.`available`,
    `tmp`.`sourcepath`,
    `tmp`.`downloaddir`,
    `tmp`.`format`,
    `tmp`.`required`,
    `tmp`.`canbeempty`,
    `tmp`.`postProcess`,
    `tmp`.`postInstruction`,
    `tmp`.`created`,
    `tmp`.`lastupdated`
FROM `invdb`.`tmp_download_files` as `tmp`;
