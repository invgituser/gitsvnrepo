## Create view service.view service configuration details

drop view if exists  service.vw_service_config_details_new;

CREATE VIEW service.vw_service_config_details_new AS
    SELECT 
        scd.company AS company,
        scd.service AS service,
        scd.vendor AS vendor,
        scd.mode AS mode,
        scd.name AS name,
        scd.value AS value,
        scd.encrFlag AS encrFlag
    FROM
        (service.service_master sm
        JOIN service.service_config_details scd ON (((sm.company = scd.company)
            AND (sm.vendor = scd.vendor)
            AND (sm.service = scd.service))))
    WHERE
        (sm.status = 'A')
    ORDER BY scd.company , scd.service , scd.vendor , scd.mode , scd.name;