DELETE FROM `invdb`.`role` WHERE `logonid`='3' and`role`='Admin';
DELETE FROM `invdb`.`role` WHERE `logonid`='4' and`role`='Admin';
DELETE FROM `invdb`.`role` WHERE `logonid`='5' and`role`='Admin';
DELETE FROM `invdb`.`role` WHERE `logonid`='10' and`role`='Demo';

INSERT INTO `invdb`.`role` (`logonid`, `role`, `status`) VALUES ('61', 'Demo', 'A');
INSERT INTO `invdb`.`role` (`logonid`, `role`, `status`) VALUES ('62', 'Demo', 'A');
INSERT INTO `invdb`.`role` (`logonid`, `role`, `status`) VALUES ('63', 'Demo', 'A');
