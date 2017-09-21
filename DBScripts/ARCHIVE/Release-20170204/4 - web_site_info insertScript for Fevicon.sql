INSERT INTO `invdb`.`web_site_info` (`url`, `name`, `status`, `value`, `encrFlag`, `created`, `updated`) VALUES ('invessence', 'WEB.FAVICONLOGO', 'A', '/javax.faces.resource/images/BBicon.png.xhtml?ln=tcm', 'N', now(), null);
INSERT INTO `invdb`.`web_site_info` (`url`, `name`, `status`, `value`, `encrFlag`, `created`, `updated`) VALUES ('uob', 'WEB.FAVICONLOGO', 'A', '/javax.faces.resource/images/Invessenceicon.png.xhtml?ln=tcm', 'N', now(), null);
INSERT INTO `invdb`.`web_site_info` (`url`, `name`, `status`, `value`, `encrFlag`, `created`, `updated`) VALUES ('buildingbenjamins', 'WEB.FAVICONLOGO', 'A', '/javax.faces.resource/images/UOBicon.png.xhtml?ln=tcm', 'N', now(), null);



UPDATE `invdb`.`web_site_info` SET `value`='/javax.faces.resource/images/Invessenceicon.png.xhtml?ln=tcm' WHERE `url`='invessence' and`name`='WEB.FAVICONLOGO';
UPDATE `invdb`.`web_site_info` SET `value`='/javax.faces.resource/images/BBicon.png.xhtml?ln=tcm' WHERE `url`='buildingbenjamins' and`name`='WEB.FAVICONLOGO';
UPDATE `invdb`.`web_site_info` SET `value`='/javax.faces.resource/images/UOBicon.png.xhtml?ln=tcm' WHERE `url`='uob' and`name`='WEB.FAVICONLOGO';

