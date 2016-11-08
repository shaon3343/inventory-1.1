select t0.id c0, t0.sl_no c1, t0.account_number c2, t0.original_account_number c3,
 t0.create_date c4, t0.create_time c5, t0.customer_name c6, t0.amount c7, t0.original_amount c8, 
 t0.pay_mode c9, t0.original_pay_mode c10, t0.envelop_number c11, t0.payment_mode c12,
 t0.reference_id c13, t0.payment_desc c14, t0.cdm_id c15, t0.fetch_date c16, 
 t0.fetch_mode c17,t0.remarks c18, t0.modify_remarks c19, t0.MODIFY_DATE c20, t0.payment_status c21,
 t0.status c22, t0.maker_id c23, t0.checker_id c24, t0.cdm_area c25, t0.particular c26,
 t0.account_type c27, t0.session_id c28, t0.GL_NAME c29, t1.payment_type pymt_text, t1.pai_id pay_id_text,
 t2.short_desc pay_desc_short, t2.long_desc pay_desc_long , t3.cdm_location cdm_name, t3.cdm_id cdm_id_text
 from cdm_log t0
 inner join payment_type t1 on t1.id = T0.PAYMENT_MODE 
 inner join payment_description t2 on t2.id = T0.PAYMENT_DESC
 inner join cdm_registration t3 on t3.id = T0.CDM_ID
 where t0.SESSION_ID = '10590208161217'  
 and t0.payment_status = 1 
 and t0.CREATE_TIME between  TO_DATE('2016-08-02 00:00:00', 'YYYY-MM-DD HH24:MI:SS') and TO_DATE('2016-08-03 23:59:59', 'YYYY-MM-DD HH24:MI:SS')
 and t0.CDM_ID in (91, 92, 85, 93, 95, 87, 90, 89, 384, 381, 382, 383 )  and t0.cdm_area = 4

select * from cdm_log where session_id = '10740408161106'


Delete From LOG_SCHEDULE WHERE LOG_SCHEDULE.TIME between TO_DATE('2016-09-25 00:00:00', 'YYYY-MM-DD HH24:MI:SS') and TO_DATE('2016-09-25 23:59:59', 'YYYY-MM-DD HH24:MI:SS')

DELETE FROM CDM_LOG_TEMP WHERE CDM_LOG_TEMP.FETCH_DATE between  TO_DATE('2016-08-04 00:00:00', 'YYYY-MM-DD HH24:MI:SS') and TO_DATE('2016-08-04 23:59:59', 'YYYY-MM-DD HH24:MI:SS')

DELETE FROM CDM_LOG WHERE CDM_LOG.FETCH_DATE between  TO_DATE('2016-09-25 00:00:00', 'YYYY-MM-DD HH24:MI:SS') and TO_DATE('2016-09-25 23:59:59', 'YYYY-MM-DD HH24:MI:SS')

SELECT * FROM CDM_LOG WHERE CDM_LOG.CREATE_TIME between  TO_DATE('2016-08-03 17:00:00', 'YYYY-MM-DD HH24:MI:SS') and TO_DATE('2016-08-04 23:59:59', 'YYYY-MM-DD HH24:MI:SS')



select t0.id c0, t0.sl_no c1, t0.account_number c2, t0.original_account_number c3, 
t0.create_date c4, t0.create_time c5, t0.customer_name c6, t0.amount c7, t0.original_amount c8, 
t0.pay_mode c9, t0.original_pay_mode c10, t0.envelop_number c11, t0.payment_mode c12,
 t0.reference_id c13, t0.payment_desc c14, t0.cdm_id c15, t0.fetch_date c16,
  t0.fetch_mode c17,t0.remarks c18, t0.modify_remarks c19, t0.MODIFY_DATE c20,
   t0.payment_status c21, t0.status c22, t0.maker_id c23, t0.checker_id c24, t0.cdm_area c25,
    t0.particular c26, t0.account_type c27, t0.session_id c28, t0.GL_NAME c29
from cdm_log_temp t0 where t0.sl_no = '2'  and t0.account_number = '0000050450500012'  and 
t0.envelop_number = '807363'  and t0.amount = '2000'  and t0.cdm_id = 88 
 and t0.create_time = TO_DATE('2016-08-03 16:24:39', 'YYYY-MM-DD HH24:MI:SS')


update cdm_registration set log_location='10.5.18.247' , cdm_ftpusername='cdmftpuser' , cdm_ftppassword='abcd1234.' where 



-- RTGS  TESTING 

 select t0.id c0, t0.cbs_trnxn_id c1, t0.mt_trnxn_id c2, t0.trnxn_id c3, t0.msg_return_id c4, t0.sender_original_trnxn_id c5, t0.from_account_number c6,
  t0.from_account_name c7, t0.to_account_number c8, t0.to_account_name c9, t0.recipient_count c10, t0.trnxn_amount c11, t0.value_date c12, t0.priority c13,
   t0.debit_credit_flag c14, t0.is_incoming c15, t0.remarks c16, t0.particulars c17, t0.create_date c18, t0.instr_id c19, t0.end_to_end_id c20, 
   t0.biz_msg_idr c21, t0.tx_id c22, t0.reason_for_rejection c23, t0.edit_count c24, t1.type_id c25, t0.source_entity_id c26, t0.from_bank_id c27,
    t0.from_branch_id c28, t0.to_bank_id c29, t0.to_branch_id c30, t2.cp_type c31, t0.currency_id c32, t3.type_id c33, t0.ttc_id c34, t4.type_id c35,
     t0.status_id c36, t5.type_id c37, t0.outward_action_id c38, t0.trnxn_manual_entry_id c39, t6.cp_type c40, t0.user_group_id c41 
     from trnxn_details t0 left outer join app_data t1 on t1.id = t0.source_entity_id and t1.type_id = 'SourceEntity' 
      left outer join cpnv t2 on t2.id = t0.currency_id and t2.cp_type = 'Currency'  
      left outer join app_data t3 on t3.id = t0.ttc_id and t3.type_id = 'TrnxnType' 
       left outer join app_data t4 on t4.id = t0.status_id and t4.type_id = 'TrnxnStatus' 
        left outer join app_data t5 on t5.id = t0.outward_action_id and t5.type_id = 'TrnxnLimitAction' 
         left outer join cpnv t6 on t6.id = t0.user_group_id and t6.cp_type = 'UserGroup'  where t0.currency_id =31
           and t0.value_date between   TO_DATE('2016-09-27 00:00:00', 'YYYY-MM-DD HH24:MI:SS') and  TO_DATE('2016-09-27 14:21:47', 'YYYY-MM-DD HH24:MI:SS') 
            and t0.user_group_id = 2  order by t0.id desc; -- user group id 2 for core banking  (it can be found at that Drop down select User Group ID
            
            
            
            
 select A.bank_id ,A.IS_INCOMING, A.bank_name, A.bank_code,SUM(A.amount) amount ,A.trnxn_type
  from(
      select   t.id ,t.TO_BANK_ID bank_id ,t.IS_INCOMING, b.name bank_name,  b.code bank_code, app.id trnxn_type, t.trnxn_amount amount
          from trnxn_details t   inner join bank b on t.to_bank_id = b.id
          inner join app_data app on t.ttc_id=app.id      where t.is_incoming =0         AND t.currency_id = 31
          AND t.VALUE_DATE BETWEEN TO_DATE('2016-10-09 00:00:00', 'YYYY-MM-DD HH24:MI:SS') and TO_DATE('2016-10-10 23:59:59', 'YYYY-MM-DD HH24:MI:SS')   
      union
    select  t.id, t.FROM_BANK_ID bank_id,t.IS_INCOMING,b.name bank_name,
       b.code bank_code,app.id  trnxn_type,    t.trnxn_amount amount
       from trnxn_details t    inner join bank b on t.from_bank_id = b.id
       inner join app_data app on t.ttc_id=app.id
       where t.is_incoming = 1         AND t.currency_id = 31   AND t.VALUE_DATE BETWEEN TO_DATE('2016-10-09 00:00:00', 'YYYY-MM-DD HH24:MI:SS') and TO_DATE('2016-10-10 23:59:59', 'YYYY-MM-DD HH24:MI:SS')
     )A
         GROUP BY A.bank_id ,A.IS_INCOMING, A.bank_name, A.bank_code,A.trnxn_type 
         order by bank_id ASC;
          --bind(false, 31, Sun Oct 09 00:00:00 BDT 2016, Sun Oct 09 00:00:00 BDT 2016, true, 31, Sun Oct 09 00:00:00 BDT 2016, Sun Oct 09 00:00:00 BDT 2016,)
          
          
    
     select A.source_id ,A.IS_INCOMING, A.source_name, A.source_code,SUM(A.AMOUNT) amount ,A.trnxn_type
     from
        (   select t.IS_INCOMING , t.TRNXN_AMOUNT amount, t.SOURCE_ENTITY_ID source_id,     t.TTC_ID trnxn_type, 
            se.NAME source_name,se.SHORT_CODE source_code
            FROM TRNXN_DETAILS t, APP_DATA se, APP_DATA tt
              WHERE t.SOURCE_ENTITY_ID=se.ID and t.TTC_ID=tt.id     AND t.is_incoming = 0 AND t.currency_id = 31 
                 AND t.VALUE_DATE BETWEEN TO_DATE('2016-10-09 00:00:00', 'YYYY-MM-DD HH24:MI:SS') and TO_DATE('2016-10-10 23:59:59', 'YYYY-MM-DD HH24:MI:SS')  
             union all
             select t.IS_INCOMING, t.TRNXN_AMOUNT amount, t.SOURCE_ENTITY_ID source_id,    t.TTC_ID trnxn_type, 
             se.NAME source_name,se.SHORT_CODE source_code  
             FROM TRNXN_DETAILS t, APP_DATA se, APP_DATA tt
             WHERE t.SOURCE_ENTITY_ID=se.ID and t.TTC_ID=tt.id     AND t.is_incoming = 1 AND t.currency_id = 31
                 AND t.VALUE_DATE BETWEEN TO_DATE('2016-10-09 00:00:00', 'YYYY-MM-DD HH24:MI:SS') and TO_DATE('2016-10-10 23:59:59', 'YYYY-MM-DD HH24:MI:SS')   ) A 
                 GROUP BY  A.source_id ,A.IS_INCOMING, A.source_name, A.source_code,A.trnxn_type 
                    order by source_id ASC;
                    
                     --bind(false, 31, Sun Oct 09 00:00:00 BDT 2016, Mon Oct 10 00:00:00 BDT 2016, true, 31, Sun Oct 09 00:00:00 BDT 2016, Mon Oct 10 00:00:00 BDT 2016
, )

  

 select t0.id c0, t0.cbs_trnxn_id c1, t0.mt_trnxn_id c2, t0.trnxn_id c3, t0.msg_return_id c4, t0.sender_original_trnxn_id c5,
 t0.from_account_number c6, t0.from_account_name c7, t0.to_account_number c8, t0.to_account_name c9, t0.recipient_count c10, t0.trnxn_amount c11,
 t0.value_date c12, t0.priority c13, t0.debit_credit_flag c14, t0.is_incoming c15, t0.remarks c16, t0.particulars c17, t0.create_date c18, t0.instr_id c19,
 t0.end_to_end_id c20, t0.biz_msg_idr c21, t0.tx_id c22, t0.reason_for_rejection c23, t0.edit_count c24, t1.type_id c25, t0.source_entity_id c26, 
 t0.from_bank_id c27, t0.from_branch_id c28, t0.to_bank_id c29, t0.to_branch_id c30, t2.cp_type c31, t0.currency_id c32, t3.type_id c33, t0.ttc_id c34, 
 t4.type_id c35, t0.status_id c36, t5.type_id c37, t0.outward_action_id c38, t0.trnxn_manual_entry_id c39, t6.cp_type c40, t0.user_group_id c41
  from trnxn_details t0 left outer join app_data t1 on t1.id = t0.source_entity_id and t1.type_id = 'SourceEntity' 
   left outer join cpnv t2 on t2.id = t0.currency_id and t2.cp_type = 'Currency' 
   left outer join app_data t3 on t3.id = t0.ttc_id and t3.type_id = 'TrnxnType'  
   left outer join app_data t4 on t4.id = t0.status_id and t4.type_id = 'TrnxnStatus'  
   left outer join app_data t5 on t5.id = t0.outward_action_id and t5.type_id = 'TrnxnLimitAction'
   left outer join cpnv t6 on t6.id = t0.user_group_id and t6.cp_type = 'UserGroup'
    where t0.ttc_id = 301 
     and t0.currency_id = 31
     and t0.status_id in (204 )  
     and t0.value_date between  TO_DATE('2016-10-09 00:00:00', 'YYYY-MM-DD HH24:MI:SS') and TO_DATE('2016-10-10 23:59:59', 'YYYY-MM-DD HH24:MI:SS') ;
     
      --bind(301,31,204,Mon Oct 10 00:00:00 BDT 2016,Mon Oct 10 16:23:04 BDT 2016)


select A.bank_id ,A.IS_INCOMING, A.bank_name, A.bank_code,SUM(A.amount) amount ,A.trnxn_type
  from(    select   t.TO_BANK_ID bank_id ,t.IS_INCOMING, b.name bank_name,  b.code bank_code,       app.id trnxn_type, t.trnxn_amount amount 
          from trnxn_details t   inner join bank b on t.to_bank_id = b.id      
          inner join app_data app on t.ttc_id=app.id      where t.is_incoming = 0         AND t.currency_id = 31 
            AND t.VALUE_DATE BETWEEN TO_DATE('2016-10-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS') AND TO_DATE('2016-10-26 23:59:59', 'YYYY-MM-DD HH24:MI:SS')  
            union all
            select  t.FROM_BANK_ID bank_id,t.IS_INCOMING,b.name bank_name,          b.code bank_code,app.id  trnxn_type,    t.trnxn_amount amount 
              from trnxn_details t    inner join bank b on t.from_bank_id = b.id  
              inner join app_data app on t.ttc_id=app.id   
              where t.is_incoming = 1         AND t.currency_id = 31 
              AND t.VALUE_DATE BETWEEN TO_DATE('2016-10-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS') AND TO_DATE('2016-10-26 23:59:59', 'YYYY-MM-DD HH24:MI:SS')   
            )A  GROUP BY A.bank_id ,A.IS_INCOMING, A.bank_name, A.bank_code,A.trnxn_type   order by bank_id ASC;


select * from trnxn_details

