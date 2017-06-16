DELETE FROM `invdb`.`web_site_info` 
where url in ('buildingbenjamins', 'traditionadvisers') 
and name in ('CHART.ASSET.ALLOCATION', 'CHART.RECOMMENDED.ASSET.ALLOCATION', 'PRODUCT.NAME');

INSERT INTO `invdb`.`web_site_info` (`url`, `name`, `status`, `value`, `encrFlag`, `created`, `updated`) 
VALUES ('buildingbenjamins', 'CHART.ASSET.ALLOCATION', 'A', 'HIGHCHART.2DDONUT', 'N', now(), null),
('buildingbenjamins', 'CHART.RECOMMENDED.ASSET.ALLOCATION', 'A', 'PRIMEFACES.BARCHART', 'N', now(), null),
('buildingbenjamins', 'PRODUCT.NAME', 'A', 'BUILDINGBENJAMINS', 'N', now(), null);


INSERT INTO `invdb`.`web_site_info` (`url`, `name`, `status`, `value`, `encrFlag`, `created`, `updated`) 
VALUES 
('traditionadvisers', 'CHART.ASSET.ALLOCATION', 'A', 'HIGHCHART.2DDONUT', 'N', now(), null),
('traditionadvisers', 'CHART.RECOMMENDED.ASSET.ALLOCATION', 'A', 'PRIMEFACES.BARCHART', 'N', now(), null),
('traditionadvisers', 'PRODUCT.NAME', 'A', 'TRADITIONADVISERS', 'N', now(), null);

