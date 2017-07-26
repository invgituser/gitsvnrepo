
delete from invdb.web_site_info where name='URL.MOBILE' and url in ('buildingbenjamins','traditionadvisers','uatbb','uattcm','prebb','pretcm');

INSERT INTO invdb.web_site_info (url, name, status, value, encrFlag, created)values('buildingbenjamins','URL.MOBILE','A','https://BuildingBenjamins.com/mobile.xhtml','N',now());
INSERT INTO invdb.web_site_info (url, name, status, value, encrFlag, created)values('traditionadvisers','URL.MOBILE','A','https://traditionadvisers.net/mobilelogin.xhtml','N',now());
INSERT INTO invdb.web_site_info (url, name, status, value, encrFlag, created)values('uatbb','URL.MOBILE','A','http://uatbb.invessence.com:8080/mobile.xhtml','N',now());
INSERT INTO invdb.web_site_info (url, name, status, value, encrFlag, created)values('uattcm','URL.MOBILE','A','http://uattcm.invessence.com:8080/mobilelogin.xhtml','N',now());
INSERT INTO invdb.web_site_info (url, name, status, value, encrFlag, created)values('prebb','URL.MOBILE','A','http://prebb.invessence.com:8080/mobile.xhtml','N',now());
INSERT INTO invdb.web_site_info (url, name, status, value, encrFlag, created)values('pretcm','URL.MOBILE','A','http://pretcm.invessence.com:8080/mobilelogin.xhtml','N',now());

SET SQL_SAFE_UPDATES = 1;