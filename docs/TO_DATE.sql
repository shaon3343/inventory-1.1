select count(*) total_count, SUM(TO_NUMBER(AMOUNT)) total_amount
from CDM_LOG t0 where t0.CDM_ID 
    in (88, 56, 108, 109, 110, 113, 114, 58, 118, 141, 111, 112, 117, 123, 124, 57, 54, 106, 115, 116, 122, 142, 143, 161, 364, 281, 365, 341, 362, 363, 366, 301, 342, 361 )
    --in(100, 101, 102, 103, 104, 401, 402, 403, 404, 82 )
 and FETCH_DATE between  TO_DATE('2016-08-04 00:00:00', 'YYYY-MM-DD HH24:MI:SS') and TO_DATE('2016-08-07 23:59:59', 'YYYY-MM-DD HH24:MI:SS')


Select A.CDM_ID ,cdm_registration.cdm_location ,Total
From 
(
select cdm_log.CDM_ID , SUM(TO_NUMBER(cdm_log.AMOUNT)) as Total
    from CDM_LOG
  --  inner join cdm_registration on CDM_LOG.cdm_id = cdm_registration.id
    where 
    cdm_log.CDM_ID 
   in (100, 101, 102, 103, 104, 401, 402, 403, 404, 82 )
   --in (88, 56, 108, 109, 110, 113, 114, 58, 118, 141, 111, 112, 117, 123, 124, 57, 54, 106, 115, 116, 122, 142, 143, 161, 364, 281, 365, 341, 362, 363, 366, 301, 342, 361 )
    and FETCH_DATE between  TO_DATE('2016-08-07 00:00:00', 'YYYY-MM-DD HH24:MI:SS') and TO_DATE('2016-08-07 23:59:59', 'YYYY-MM-DD HH24:MI:SS')
     group by cdm_log.CDM_ID
     order by Total DESC
     
)A inner join cdm_registration on A.cdm_id = cdm_registration.id
;

select * from cdm_log where cdm_id='109'