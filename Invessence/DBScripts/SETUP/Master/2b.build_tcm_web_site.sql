DELETE FROM `invdb`.`web_site_info` where url in ('traditionadvisers' , 'tmp');

INSERT INTO `invdb`.`web_site_info` (`url`, `name`, `status`, `value`, `encrFlag`, `created`, `updated`) VALUES ('tmp', 'ARCHIVE.CLOSED', 'A', '10', 'N', null, null);
INSERT INTO `invdb`.`web_site_info` (`url`, `name`, `status`, `value`, `encrFlag`, `created`, `updated`) VALUES ('tmp', 'ARCHIVE.INACTIVE', 'A', '30', 'N', null, null);
INSERT INTO `invdb`.`web_site_info` (`url`, `name`, `status`, `value`, `encrFlag`, `created`, `updated`) VALUES ('tmp', 'ARCHIVE.UNOPENED', 'A', '30', 'N', null, null);
INSERT INTO `invdb`.`web_site_info` (`url`, `name`, `status`, `value`, `encrFlag`, `created`, `updated`) VALUES ('tmp', 'CSS.CUSTODY', 'A', 'css/tcm.css', 'N', null, null);
INSERT INTO `invdb`.`web_site_info` (`url`, `name`, `status`, `value`, `encrFlag`, `created`, `updated`) VALUES ('tmp', 'CSS.CUSTOM', 'A', 'css/tcm.css', 'N', null, null);
INSERT INTO `invdb`.`web_site_info` (`url`, `name`, `status`, `value`, `encrFlag`, `created`, `updated`) VALUES ('tmp', 'CSS.DIR', 'A', 'tcm', 'N', null, null);
INSERT INTO `invdb`.`web_site_info` (`url`, `name`, `status`, `value`, `encrFlag`, `created`, `updated`) VALUES ('tmp', 'CUSTODY.SERVICE', 'A', 'INTERNAL', 'N', null, null);
INSERT INTO `invdb`.`web_site_info` (`url`, `name`, `status`, `value`, `encrFlag`, `created`, `updated`) VALUES ('tmp', 'CUSTODY.URL', 'A', '', 'N', null, null);
INSERT INTO `invdb`.`web_site_info` (`url`, `name`, `status`, `value`, `encrFlag`, `created`, `updated`) VALUES ('tmp', 'DEFAULT.ADVISOR', 'A', 'BB-TCM', 'N', null, null);
INSERT INTO `invdb`.`web_site_info` (`url`, `name`, `status`, `value`, `encrFlag`, `created`, `updated`) VALUES ('tmp', 'DEFAULT.MODEL', 'A', '0.TA', 'N', null, null);
INSERT INTO `invdb`.`web_site_info` (`url`, `name`, `status`, `value`, `encrFlag`, `created`, `updated`) VALUES ('tmp', 'DEFAULT.REP', 'A', '', 'N', null, null);
INSERT INTO `invdb`.`web_site_info` (`url`, `name`, `status`, `value`, `encrFlag`, `created`, `updated`) VALUES ('tmp', 'DIR.CONSUMER', 'A', 'tcm', 'N', null, null);
INSERT INTO `invdb`.`web_site_info` (`url`, `name`, `status`, `value`, `encrFlag`, `created`, `updated`) VALUES ('tmp', 'DIR.CUSTODY', 'A', 'td', 'N', null, null);
INSERT INTO `invdb`.`web_site_info` (`url`, `name`, `status`, `value`, `encrFlag`, `created`, `updated`) VALUES ('tmp', 'DIR.TEMPLATE', 'A', '/template/common/spark', 'N', null, null);
INSERT INTO `invdb`.`web_site_info` (`url`, `name`, `status`, `value`, `encrFlag`, `created`, `updated`) VALUES ('tmp', 'EMAIL.MAIN', 'A', 'info@traditionadvisers.net', 'N', null, null);
INSERT INTO `invdb`.`web_site_info` (`url`, `name`, `status`, `value`, `encrFlag`, `created`, `updated`) VALUES ('tmp', 'EMAIL.OPERATION', 'A', 'operations@traditionadvisers.net', 'N', null, null);
INSERT INTO `invdb`.`web_site_info` (`url`, `name`, `status`, `value`, `encrFlag`, `created`, `updated`) VALUES ('tmp', 'EMAIL.SUPPORT', 'A', 'support@traditionadvisers.net', 'N', null, null);
INSERT INTO `invdb`.`web_site_info` (`url`, `name`, `status`, `value`, `encrFlag`, `created`, `updated`) VALUES ('tmp', 'EMAIL.USER', 'A', 'noreply@traditionadvisers.net', 'N', null, null);
INSERT INTO `invdb`.`web_site_info` (`url`, `name`, `status`, `value`, `encrFlag`, `created`, `updated`) VALUES ('tmp', 'HTML.BASE.PATH', 'A', '/inv/www/invessence/ROOT/template/html/', 'N', '2017-04-10 00:00:00', null);
INSERT INTO `invdb`.`web_site_info` (`url`, `name`, `status`, `value`, `encrFlag`, `created`, `updated`) VALUES ('tmp', 'HTML.FORGOT', 'A', 'traditionadvisers-Forgot.html', 'N', null, null);
INSERT INTO `invdb`.`web_site_info` (`url`, `name`, `status`, `value`, `encrFlag`, `created`, `updated`) VALUES ('tmp', 'HTML.LOCKED', 'A', 'traditionadvisers-Locked.html', 'N', null, null);
INSERT INTO `invdb`.`web_site_info` (`url`, `name`, `status`, `value`, `encrFlag`, `created`, `updated`) VALUES ('tmp', 'HTML.RESET', 'A', 'traditionadvisers-Reset.html', 'N', null, null);
INSERT INTO `invdb`.`web_site_info` (`url`, `name`, `status`, `value`, `encrFlag`, `created`, `updated`) VALUES ('tmp', 'HTML.WELCOME', 'A', 'traditionadvisers-Welcome.html', 'N', null, null);
INSERT INTO `invdb`.`web_site_info` (`url`, `name`, `status`, `value`, `encrFlag`, `created`, `updated`) VALUES ('tmp', 'HTML.WELCOME.ADV', 'A', 'traditionadvisers-Welcome.html', 'N', null, null);
INSERT INTO `invdb`.`web_site_info` (`url`, `name`, `status`, `value`, `encrFlag`, `created`, `updated`) VALUES ('tmp', 'INVESTMENT.MIN1ST', 'A', '2000', 'N', null, null);
INSERT INTO `invdb`.`web_site_info` (`url`, `name`, `status`, `value`, `encrFlag`, `created`, `updated`) VALUES ('tmp', 'INVESTMENT.MIN2ND', 'A', '50', 'N', null, null);
INSERT INTO `invdb`.`web_site_info` (`url`, `name`, `status`, `value`, `encrFlag`, `created`, `updated`) VALUES ('tmp', 'INVESTMENT.RECURRING1ST', 'A', '50', 'N', null, null);
INSERT INTO `invdb`.`web_site_info` (`url`, `name`, `status`, `value`, `encrFlag`, `created`, `updated`) VALUES ('tmp', 'INVESTMENT.RECURRING2ND', 'A', '50', 'N', null, null);
INSERT INTO `invdb`.`web_site_info` (`url`, `name`, `status`, `value`, `encrFlag`, `created`, `updated`) VALUES ('tmp', 'PHONE.MAIN', 'A', '(908) 333-4733', 'N', null, null);
INSERT INTO `invdb`.`web_site_info` (`url`, `name`, `status`, `value`, `encrFlag`, `created`, `updated`) VALUES ('tmp', 'PHONE.SUPPORT', 'A', '(908) 333-4733', 'N', null, null);
INSERT INTO `invdb`.`web_site_info` (`url`, `name`, `status`, `value`, `encrFlag`, `created`, `updated`) VALUES ('tmp', 'SERVICE.CUSTODY', 'A', 'TCM', 'N', '2017-04-08 19:44:52', null);
INSERT INTO `invdb`.`web_site_info` (`url`, `name`, `status`, `value`, `encrFlag`, `created`, `updated`) VALUES ('tmp', 'SERVICE.DOCUSIGN.MODE', 'A', 'PROD', 'N', '2017-04-08 19:44:52', null);
INSERT INTO `invdb`.`web_site_info` (`url`, `name`, `status`, `value`, `encrFlag`, `created`, `updated`) VALUES ('tmp', 'SERVICE.EMAILER.MODE', 'A', 'PROD', 'N', '2017-04-08 19:44:52', null);
INSERT INTO `invdb`.`web_site_info` (`url`, `name`, `status`, `value`, `encrFlag`, `created`, `updated`) VALUES ('tmp', 'SERVICE.PRICING.MODE', 'A', 'PROD', 'N', '2017-04-08 19:44:52', null);
INSERT INTO `invdb`.`web_site_info` (`url`, `name`, `status`, `value`, `encrFlag`, `created`, `updated`) VALUES ('tmp', 'SUBJECT.EMAIL.ACTIVATE', 'A', 'Welcome to Tradition Advisers - Activate Your Account', 'N', null, null);
INSERT INTO `invdb`.`web_site_info` (`url`, `name`, `status`, `value`, `encrFlag`, `created`, `updated`) VALUES ('tmp', 'SUBJECT.EMAIL.LOCKED', 'A', 'Tradition Advisers - Account is locked', 'N', null, null);
INSERT INTO `invdb`.`web_site_info` (`url`, `name`, `status`, `value`, `encrFlag`, `created`, `updated`) VALUES ('tmp', 'SUBJECT.EMAIL.RESET', 'A', 'Tradition Advisers - Reset Your Password', 'N', null, null);
INSERT INTO `invdb`.`web_site_info` (`url`, `name`, `status`, `value`, `encrFlag`, `created`, `updated`) VALUES ('tmp', 'SUBJECT.EMAIL.WELCOME', 'A', 'Welcome to Tradition Advisers - Activate Your Account', 'N', null, null);
INSERT INTO `invdb`.`web_site_info` (`url`, `name`, `status`, `value`, `encrFlag`, `created`, `updated`) VALUES ('tmp', 'URL.MOBILE', 'A', 'https://traditionadvisers.net/mobilelogin.xhtml', 'N', '2017-06-03 14:14:13', null);
INSERT INTO `invdb`.`web_site_info` (`url`, `name`, `status`, `value`, `encrFlag`, `created`, `updated`) VALUES ('tmp', 'URL.SECURE', 'A', 'https://traditionadvisers.net', 'N', null, null);
INSERT INTO `invdb`.`web_site_info` (`url`, `name`, `status`, `value`, `encrFlag`, `created`, `updated`) VALUES ('tmp', 'URL.WEBSITE', 'A', 'http://traditionadvisers.net', 'N', null, null);
INSERT INTO `invdb`.`web_site_info` (`url`, `name`, `status`, `value`, `encrFlag`, `created`, `updated`) VALUES ('tmp', 'WEB.COMPANYNAME', 'A', 'Tradition Advisers', 'N', null, null);
INSERT INTO `invdb`.`web_site_info` (`url`, `name`, `status`, `value`, `encrFlag`, `created`, `updated`) VALUES ('tmp', 'WEB.COPYRIGHT', 'A', 'Tradition Capital Management', 'N', null, null);
INSERT INTO `invdb`.`web_site_info` (`url`, `name`, `status`, `value`, `encrFlag`, `created`, `updated`) VALUES ('tmp', 'WEB.FAVICONLOGO', 'A', '/javax.faces.resource/images/tcmicon.png.xhtml?ln=tcm', 'N', null, null);
INSERT INTO `invdb`.`web_site_info` (`url`, `name`, `status`, `value`, `encrFlag`, `created`, `updated`) VALUES ('tmp', 'WEB.GOOGLEANALYTICS', 'A', 'bb_googleanalytics.js', 'N', null, null);
INSERT INTO `invdb`.`web_site_info` (`url`, `name`, `status`, `value`, `encrFlag`, `created`, `updated`) VALUES ('tmp', 'WEB.LOGO', 'A', 'images/logo/tcm-logo.jpe', 'N', null, null);
INSERT INTO `invdb`.`web_site_info` (`url`, `name`, `status`, `value`, `encrFlag`, `created`, `updated`) VALUES ('tmp', 'WEB.LOGOLIB', 'A', 'invessence', 'N', null, null);
INSERT INTO `invdb`.`web_site_info` (`url`, `name`, `status`, `value`, `encrFlag`, `created`, `updated`) VALUES ('tmp', 'WEB.LOGOSIZE', 'A', '200px', 'N', null, null);
INSERT INTO `invdb`.`web_site_info` (`url`, `name`, `status`, `value`, `encrFlag`, `created`, `updated`) VALUES ('tmp', 'WEB.MODE', 'A', 'PROD', 'N', null, null);
INSERT INTO `invdb`.`web_site_info` (`url`, `name`, `status`, `value`, `encrFlag`, `created`, `updated`) VALUES ('tmp', 'WEB.SESSION.COUNTDOWNTIME', 'A', '2', 'N', '2017-06-03 14:14:13', null);
INSERT INTO `invdb`.`web_site_info` (`url`, `name`, `status`, `value`, `encrFlag`, `created`, `updated`) VALUES ('tmp', 'WEB.SESSION.TIMEOUT', 'A', '15', 'N', '2017-06-03 14:14:13', null);
INSERT INTO `invdb`.`web_site_info` (`url`, `name`, `status`, `value`, `encrFlag`, `created`, `updated`) VALUES ('tmp', 'WEB.SSLSEAL', 'A', 'https://seal.thawte.com/getthawteseal?host_name=www.buildingbenjamins.com&amp;size=S&amp;lang=en', 'N', null, null);
INSERT INTO `invdb`.`web_site_info` (`url`, `name`, `status`, `value`, `encrFlag`, `created`, `updated`) VALUES ('tmp', 'WEB.THEME', 'A', 'spark', 'N', null, null);
INSERT INTO `invdb`.`web_site_info` (`url`, `name`, `status`, `value`, `encrFlag`, `created`, `updated`) VALUES ('tmp', 'WEB.THEMELIB', 'A', 'spark-layout', 'N', null, null);


INSERT INTO `invdb`.`web_site_info` (`url`, `name`, `status`, `value`, `encrFlag`, `created`, `updated`) VALUES ('tmp', 'CHART.ASSET.ALLOCATION', 'A', 'HIGHCHART.2DDONUT', 'N', '2017-02-21 20:02:19', null);
INSERT INTO `invdb`.`web_site_info` (`url`, `name`, `status`, `value`, `encrFlag`, `created`, `updated`) VALUES ('tmp', 'CHART.RECOMMENDED.ASSET.ALLOCATION', 'A', 'PRIMEFACES.BARCHART', 'N', '2017-02-21 20:02:19', null);


-- Using master as source Insert any missing data from saved.
INSERT INTO `invdb`.`web_site_info`
(`url`, `name`, `status`, `value`, `encrFlag`, `created`, `updated`)
SELECT
  'traditionadvisers'
, IFNULL(`official`.`name`,`master`.`name`)
, IFNULL(`official`.`status`,`master`.`status`)
, IFNULL(`official`.`value`,`master`.`value`)
, IFNULL(`official`.`encrFlag`,`master`.`encrFlag`)
, IFNULL(`official`.`created`,`master`.`created`)
, null
FROM `invdb`.`web_site_info` `master`
LEFT JOIN `invdb`.`web_site_info` `official`
ON (`official`.`url` = 'tmp'
AND `official`.`name` = `master`.`name`)
where `master`.`url` = 'master';