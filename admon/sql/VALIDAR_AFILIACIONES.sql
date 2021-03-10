--TABLAS TAASGO_VENTAS
SELECT * FROM TAASGO_VENTAS.AFILIACION_SANTANDER;
SELECT * FROM TAASGO_VENTAS.AFILIACION_PARCIALIZACION;
SELECT * FROM TAASGO_VENTAS.AFILIACION_BANAMEX WHERE AFILIACION  ='2';--10 organizacion 20
SELECT * FROM TAASGO_VENTAS.AFILIACION_BANAMEX_PLAN WHERE AFILIACION  ='2';
SELECT * FROM TAASGO_VENTAS.AFILIACION_BANAMEX_TERMINAL ORDER BY BANAMEX_TERMINAL_ID DESC;--SI TIENE ID
SELECT * FROM TAASGO_VENTAS.CONFIG_FORMA_PAGO;
SELECT * FROM TAASGO_VENTAS.CONFIG_FORMA_PAGO_AFILIACION;
SELECT * FROM TAASGO_VENTAS.EVENTO_FORMA_PAGO;
SELECT * FROM TAASGO_VENTAS.AFILIACION_CANAL_VENTA;--4,2
describe TAASGO_VENTAS.AFILIACION_CANAL_VENTA;
--SECUENCIAS TAASGO
DROP SEQUENCE ADMON.AFILIACION_SANTANDER_SEQ; 
DROP SEQUENCE ADMON.PARCIALIZACION_SANTANDER_SEQ;
DROP SEQUENCE ADMON.AFILIACION_BANAMEX_SEQ;
DROP SEQUENCE ADMON.AFILIACION_BANAMEX_PLAN_SEQ;
DROP SEQUENCE ADMON.AFILIACION_BMEX_TERMINAL_SEQ;
DROP SEQUENCE ADMON.CONFIG_FORMA_PAGO_ID_SEQ;
DELETE ADMON.AFILIACION_BANAMEX;
DELETE ADMON.AFILIACION_BANAMEX_PLAN;
DELETE ADMON.AFILIACION_BANAMEX_TERMINAL;
DELETE ADMON.AFILIACION_SANTANDER;
DELETE ADMON.PARCIALIZACION_SANTANDER;
DELETE ADMON.CONFIG_FORMA_PAGO;
DELETE ADMON.CONFIG_FORMA_PAGO_AFILIACION;
DELETE ADMON.EVENTO_FORMA_PAGO;


SELECT LAST_NUMBER FROM ALL_SEQUENCES WHERE SEQUENCE_OWNER = 'ADMON' AND SEQUENCE_NAME = 'AFILIACION_SANTANDER_SEQ'; 
SELECT LAST_NUMBER FROM ALL_SEQUENCES WHERE SEQUENCE_OWNER = 'ADMON' AND SEQUENCE_NAME = 'PARCIALIZACION_SANTANDER_SEQ';
SELECT LAST_NUMBER FROM ALL_SEQUENCES WHERE SEQUENCE_OWNER = 'ADMON' AND SEQUENCE_NAME = 'AFILIACION_BANAMEX_SEQ';
SELECT LAST_NUMBER FROM ALL_SEQUENCES WHERE SEQUENCE_OWNER = 'ADMON' AND SEQUENCE_NAME = 'AFILIACION_BANAMEX_PLAN_SEQ';
SELECT LAST_NUMBER FROM ALL_SEQUENCES WHERE SEQUENCE_OWNER = 'ADMON' AND SEQUENCE_NAME = 'AFILIACION_BMEX_TERMINAL_SEQ';
SELECT LAST_NUMBER FROM ALL_SEQUENCES WHERE SEQUENCE_OWNER = 'ADMON' AND SEQUENCE_NAME = 'CONFIG_FORMA_PAGO_ID_SEQ';
--TABLAS TAASGO
SELECT * FROM ADMON.AFILIACION_SANTANDER WHERE AFILIACION_SANTANDER_ID =7;
SELECT * FROM ADMON.PARCIALIZACION_SANTANDER;
SELECT * FROM ADMON.AFILIACION_BANAMEX WHERE AFILIACION_BANAMEX_ID =7;
SELECT * FROM ADMON.AFILIACION_BANAMEX_PLAN;
SELECT * FROM ADMON.AFILIACION_BANAMEX_TERMINAL;
SELECT * FROM ADMON.CONFIG_FORMA_PAGO;
SELECT * FROM ADMON.CONFIG_FORMA_PAGO_AFILIACION;

--DELETE FROM ADMON.AFILIACION_BANAMEX;
--DELETE FROM ADMON.AFILIACION_BANAMEX_PLAN;
--DELETE FROM ADMON.AFILIACION_BANAMEX_TERMINAL;
 CREATE SEQUENCE ADMON.AFILIACION_SANTANDER_SEQ START WITH 1
                               INCREMENT BY 1
                               MAXVALUE 9999999999999999999999999999
                               NOMINVALUE
                               ORDER
                               NOCYCLE
                               NOCACHE;
 CREATE SEQUENCE ADMON.PARCIALIZACION_SANTANDER_SEQ START WITH 1
                               INCREMENT BY 1
                               MAXVALUE 9999999999999999999999999999
                               NOMINVALUE
                               ORDER
                               NOCYCLE
                               NOCACHE;
CREATE SEQUENCE ADMON.AFILIACION_BANAMEX_SEQ START WITH 1
                               INCREMENT BY 1
                               MAXVALUE 9999999999999999999999999999
                               NOMINVALUE
                               ORDER
                               NOCYCLE
                               NOCACHE;
CREATE SEQUENCE ADMON.AFILIACION_BANAMEX_PLAN_SEQ START WITH 1
                               INCREMENT BY 1
                               MAXVALUE 9999999999999999999999999999
                               NOMINVALUE
                               ORDER
                               NOCYCLE
                               NOCACHE;
declare 
	v_secuencia NUMBER:=1;
begin
	select (MAX(BANAMEX_TERMINAL_ID) + 1) into v_secuencia from TAASGO_VENTAS.AFILIACION_BANAMEX_TERMINAL;
    IF v_secuencia IS NULL THEN
        v_secuencia:=1;
    END IF;
    execute immediate 'CREATE SEQUENCE  ADMON.AFILIACION_BMEX_TERMINAL_SEQ  MINVALUE 1 MAXVALUE 999999999999999999 INCREMENT BY 1 START WITH '||v_secuencia||' CACHE 20 ORDER  NOCYCLE' ;

exception when others then
	dbms_output.put_line(dbms_utility.format_error_backtrace());
end;
Insert into AFILIACION_BANAMEX (AFILIACION,URL,VERSION,COMMAND,ACCESS_CODE,MERCHANT,RETURN_URL,CURRENCY,SECURE_SECRET,ACTION_URL,CLAVE_INSTITUCION_BANCARIA,VALIDA_BANAMEX,TRN_CASHBACK_AMOUNT,TRN_TIP_AMOUNT,CREACION_FECHA,CREACION_USUARIO,MODIFICACION_FECHA,MODIFICACION_USUARIO,ORGANIZACION_ID,URL_CANCELACION,URL_DEVOLUCION,TRN_CUR_ID1,NOMBRE_EMPRESA) values ('44','https://banamex.dialectpayments.com/api/rest/version/40/merchant/{merchantId}/session','2','pay','merchant.TEST1073490','TEST1073490',null,'MXN','951a87cf55e1fb5c5e7ac05de1912b5a',null,null,1,0,0,to_date('28/12/16','DD/MM/RR'),null,null,null,10,null,null,null,'WEB ST');
Insert into AFILIACION_BANAMEX (AFILIACION,URL,VERSION,COMMAND,ACCESS_CODE,MERCHANT,RETURN_URL,CURRENCY,SECURE_SECRET,ACTION_URL,CLAVE_INSTITUCION_BANCARIA,VALIDA_BANAMEX,TRN_CASHBACK_AMOUNT,TRN_TIP_AMOUNT,CREACION_FECHA,CREACION_USUARIO,MODIFICACION_FECHA,MODIFICACION_USUARIO,ORGANIZACION_ID,URL_CANCELACION,URL_DEVOLUCION,TRN_CUR_ID1,NOMBRE_EMPRESA) values ('7041800','/taquilla/w/v/pagoBanamex','1','T060S000','rojo2012','pruebad3','/taquilla/w/v/pagoBanamex',null,null,'http://localhost:8081/upay/','BANAMEX',1,0,0,to_date('28/12/16','DD/MM/RR'),null,null,null,44,'/taquilla/w/v/cancelacionPagoBanamex','/taquilla/w/v/DevolucionPagoBanamex','484','TAQUILLA XTICKET ');
Insert into AFILIACION_BANAMEX (AFILIACION,URL,VERSION,COMMAND,ACCESS_CODE,MERCHANT,RETURN_URL,CURRENCY,SECURE_SECRET,ACTION_URL,CLAVE_INSTITUCION_BANCARIA,VALIDA_BANAMEX,TRN_CASHBACK_AMOUNT,TRN_TIP_AMOUNT,CREACION_FECHA,CREACION_USUARIO,MODIFICACION_FECHA,MODIFICACION_USUARIO,ORGANIZACION_ID,URL_CANCELACION,URL_DEVOLUCION,TRN_CUR_ID1,NOMBRE_EMPRESA) values ('2','/taquilla/w/v/pagoBanamex','1','T060S000','rojo2012','QA000001-10','/taquilla/w/v/pagoBanamex',null,null,'http://localhost:8081/upay/','BANAMEX',1,0,0,to_date('28/12/16','DD/MM/RR'),null,null,null,10,'/taquilla/w/v/cancelacionPagoBanamex','/taquilla/w/v/DevolucionPagoBanamex','484','TAQUILLA ST');
Insert into AFILIACION_BANAMEX (AFILIACION,URL,VERSION,COMMAND,ACCESS_CODE,MERCHANT,RETURN_URL,CURRENCY,SECURE_SECRET,ACTION_URL,CLAVE_INSTITUCION_BANCARIA,VALIDA_BANAMEX,TRN_CASHBACK_AMOUNT,TRN_TIP_AMOUNT,CREACION_FECHA,CREACION_USUARIO,MODIFICACION_FECHA,MODIFICACION_USUARIO,ORGANIZACION_ID,URL_CANCELACION,URL_DEVOLUCION,TRN_CUR_ID1,NOMBRE_EMPRESA) values ('4','https://banamex.dialectpayments.com/api/rest/version/40/merchant/{merchantId}/session','2','pay','merchant.TEST1073490','TEST1073490',null,'MXN','951a87cf55e1fb5c5e7ac05de1912b5a',null,null,1,0,0,to_date('28/12/16','DD/MM/RR'),null,null,null,2,null,null,null,'WEB ST');
Insert into AFILIACION_BANAMEX (AFILIACION,URL,VERSION,COMMAND,ACCESS_CODE,MERCHANT,RETURN_URL,CURRENCY,SECURE_SECRET,ACTION_URL,CLAVE_INSTITUCION_BANCARIA,VALIDA_BANAMEX,TRN_CASHBACK_AMOUNT,TRN_TIP_AMOUNT,CREACION_FECHA,CREACION_USUARIO,MODIFICACION_FECHA,MODIFICACION_USUARIO,ORGANIZACION_ID,URL_CANCELACION,URL_DEVOLUCION,TRN_CUR_ID1,NOMBRE_EMPRESA) values ('5','https://banamex.dialectpayments.com/api/rest/version/40/merchant/{merchantId}/session','2','pay','merchant.1073490','1073490',null,'MXN','6430d33a2cbfbadcba272737d068fdcd',null,null,1,0,0,to_date('28/12/16','DD/MM/RR'),null,null,null,2,null,null,null,'WEB ST');
Insert into AFILIACION_BANAMEX (AFILIACION,URL,VERSION,COMMAND,ACCESS_CODE,MERCHANT,RETURN_URL,CURRENCY,SECURE_SECRET,ACTION_URL,CLAVE_INSTITUCION_BANCARIA,VALIDA_BANAMEX,TRN_CASHBACK_AMOUNT,TRN_TIP_AMOUNT,CREACION_FECHA,CREACION_USUARIO,MODIFICACION_FECHA,MODIFICACION_USUARIO,ORGANIZACION_ID,URL_CANCELACION,URL_DEVOLUCION,TRN_CUR_ID1,NOMBRE_EMPRESA) values ('02','/taquilla/w/v/pagoBanamex','1','T060S000','rojo2012','pruebacert','/taquilla/w/v/pagoBanamex',null,null,'http://localhost:8081/upay/','BANAMEX',1,0,0,to_date('28/12/16','DD/MM/RR'),null,null,null,44,'/taquilla/w/v/cancelacionPagoBanamex','/taquilla/w/v/DevolucionPagoBanamex','484','TAQUILLA ST');
Insert into AFILIACION_BANAMEX (AFILIACION,URL,VERSION,COMMAND,ACCESS_CODE,MERCHANT,RETURN_URL,CURRENCY,SECURE_SECRET,ACTION_URL,CLAVE_INSTITUCION_BANCARIA,VALIDA_BANAMEX,TRN_CASHBACK_AMOUNT,TRN_TIP_AMOUNT,CREACION_FECHA,CREACION_USUARIO,MODIFICACION_FECHA,MODIFICACION_USUARIO,ORGANIZACION_ID,URL_CANCELACION,URL_DEVOLUCION,TRN_CUR_ID1,NOMBRE_EMPRESA) values ('04','https://banamex.dialectpayments.com/api/rest/version/40/merchant/{merchantId}/session','2','pay','merchant.TEST1073490','TEST1073490',null,'MXN','951a87cf55e1fb5c5e7ac05de1912b5a',null,null,1,0,0,to_date('28/12/16','DD/MM/RR'),null,null,null,44,null,null,null,'WEB ST');
Insert into AFILIACION_BANAMEX (AFILIACION,URL,VERSION,COMMAND,ACCESS_CODE,MERCHANT,RETURN_URL,CURRENCY,SECURE_SECRET,ACTION_URL,CLAVE_INSTITUCION_BANCARIA,VALIDA_BANAMEX,TRN_CASHBACK_AMOUNT,TRN_TIP_AMOUNT,CREACION_FECHA,CREACION_USUARIO,MODIFICACION_FECHA,MODIFICACION_USUARIO,ORGANIZACION_ID,URL_CANCELACION,URL_DEVOLUCION,TRN_CUR_ID1,NOMBRE_EMPRESA) values ('22','/taquilla/w/v/pagoBanamex','1','T060S000','rojo2012','QA000001-09','/taquilla/w/v/pagoBanamex',null,null,'http://localhost:8081/upay/','BANAMEX',1,0,0,to_date('28/12/16','DD/MM/RR'),null,null,null,2,'/taquilla/w/v/cancelacionPagoBanamex','/taquilla/w/v/DevolucionPagoBanamex','484','TAQUILLA ST');
Insert into AFILIACION_BANAMEX (AFILIACION,URL,VERSION,COMMAND,ACCESS_CODE,MERCHANT,RETURN_URL,CURRENCY,SECURE_SECRET,ACTION_URL,CLAVE_INSTITUCION_BANCARIA,VALIDA_BANAMEX,TRN_CASHBACK_AMOUNT,TRN_TIP_AMOUNT,CREACION_FECHA,CREACION_USUARIO,MODIFICACION_FECHA,MODIFICACION_USUARIO,ORGANIZACION_ID,URL_CANCELACION,URL_DEVOLUCION,TRN_CUR_ID1,NOMBRE_EMPRESA) values ('1078829','/taquilla/w/v/pagoBanamex','1','T060S000','rojo2012','QA000001-09','/taquilla/w/v/pagoBanamex',null,null,'http://localhost:8081/upay/','BANAMEX',1,0,0,to_date('28/12/16','DD/MM/RR'),null,null,null,100,'/taquilla/w/v/cancelacionPagoBanamex','/taquilla/w/v/DevolucionPagoBanamex','484','NewTickets');


Insert into AFILIACION_BANAMEX_TERMINAL (BANAMEX_TERMINAL_ID,AFILIACION,PUNTO_VENTA_TERMINAL_ID,MERCHANT,ACCESS_CODE,CREACION_FECHA,CREACION_USUARIO,MODIFICACION_FECHA,MODIFICACION_USUARIO,ORGANIZACION_ID,CANCELACION) values ('1','2',362,'QA000001-1','rojo2012',to_date('30/12/16','DD/MM/RR'),2,null,null,10,1);
Insert into AFILIACION_BANAMEX_TERMINAL (BANAMEX_TERMINAL_ID,AFILIACION,PUNTO_VENTA_TERMINAL_ID,MERCHANT,ACCESS_CODE,CREACION_FECHA,CREACION_USUARIO,MODIFICACION_FECHA,MODIFICACION_USUARIO,ORGANIZACION_ID,CANCELACION) values ('2','22',548,'QA000001-1','rojo2012',to_date('30/12/16','DD/MM/RR'),2,null,null,2,1);

Insert into CONFIG_FORMA_PAGO (CONFIG_FORMA_PAGO_ID,URL_SITE,URL_RETURN,URL_CANCEL,USERNAME,PASSWORD,SIGNATURE,CURRENCY,CONTRACT_ID,CONNECTION_MODE,CLAVE_FORMA_PAGO,ORGANIZACION_ID,SECRET,CLIENT_ID,URL_OAUTH,URL_STC,MERCHANT_ID) values (5,'https://sandbox-api.openpay.mx',null,null,'mav3jud5kdxu2ejvjai0',null,'sk_473b70f450734b3daf6ab61e5e78877d','MXN',null,'live','PAYNET',2,null,null,null,null,null);
Insert into CONFIG_FORMA_PAGO (CONFIG_FORMA_PAGO_ID,URL_SITE,URL_RETURN,URL_CANCEL,USERNAME,PASSWORD,SIGNATURE,CURRENCY,CONTRACT_ID,CONNECTION_MODE,CLAVE_FORMA_PAGO,ORGANIZACION_ID,SECRET,CLIENT_ID,URL_OAUTH,URL_STC,MERCHANT_ID) values (8,'https://www.sandbox.paypal.com/checkoutnow?token=','http://qa2.taasgo.mx/Web/w/v/pago/procesaPaypal?cancel_paypal=true','http://qa2.taasgo.mx/Web/w/v/pago/procesaPaypal?cancel_paypal=false','luis_achutegui-facilitator_api1.taasgo.com.mx','96VSUWKVBQKJA6HM','Ai1PaghZh5FmBLCDCTQpwG8jB264AKaTuEncGdC.oafiMrK3Nsu1h3S0','MXN',null,'sandbox','PAYPAL',4,'EN1MRW8uE6znhxZ60qAG3LVmG2cS4h2l4lo6X8BoOmYpfQ2dMzzFad0IfwFksFVq8ohWB_ypJFYCnts9','AdnzRKofVM-bIu2R6UciWIg8JHLBjVRl-3v1Y0YPMWEqDQTfQ3KRVgUxtJ0_5biJ1z_ceeeeOD00ki4Q','https://api.sandbox.paypal.com/v1/oauth2/token','https://api.sandbox.paypal.com/v1/risk/transaction-contexts/','979K2XCU58UGY');
Insert into CONFIG_FORMA_PAGO (CONFIG_FORMA_PAGO_ID,URL_SITE,URL_RETURN,URL_CANCEL,USERNAME,PASSWORD,SIGNATURE,CURRENCY,CONTRACT_ID,CONNECTION_MODE,CLAVE_FORMA_PAGO,ORGANIZACION_ID,SECRET,CLIENT_ID,URL_OAUTH,URL_STC,MERCHANT_ID) values (7,'https://www.sandbox.paypal.com/checkoutnow?token=','http://qa2.taasgo.mx/Web/w/v/pago/procesaPaypal?cancel_paypal=true','http://qa2.taasgo.mx/Web/w/v/pago/procesaPaypal?cancel_paypal=false','luis_achutegui-facilitator_api1.taasgo.com.mx','96VSUWKVBQKJA6HM','Ai1PaghZh5FmBLCDCTQpwG8jB264AKaTuEncGdC.oafiMrK3Nsu1h3S0','MXN',null,'sandbox','PAYPAL',3,'EN1MRW8uE6znhxZ60qAG3LVmG2cS4h2l4lo6X8BoOmYpfQ2dMzzFad0IfwFksFVq8ohWB_ypJFYCnts9','AdnzRKofVM-bIu2R6UciWIg8JHLBjVRl-3v1Y0YPMWEqDQTfQ3KRVgUxtJ0_5biJ1z_ceeeeOD00ki4Q','https://api.sandbox.paypal.com/v1/oauth2/token','https://api.sandbox.paypal.com/v1/risk/transaction-contexts/','979K2XCU58UGY');
Insert into CONFIG_FORMA_PAGO (CONFIG_FORMA_PAGO_ID,URL_SITE,URL_RETURN,URL_CANCEL,USERNAME,PASSWORD,SIGNATURE,CURRENCY,CONTRACT_ID,CONNECTION_MODE,CLAVE_FORMA_PAGO,ORGANIZACION_ID,SECRET,CLIENT_ID,URL_OAUTH,URL_STC,MERCHANT_ID) values (6,'https://www.sandbox.paypal.com/checkoutnow?token=','http://qa2.taasgo.mx/Web/w/v/pago/procesaPaypal?cancel_paypal=true','http://qa2.taasgo.mx/Web/w/v/pago/procesaPaypal?cancel_paypal=false','luis_achutegui-facilitator_api1.taasgo.com.mx','96VSUWKVBQKJA6HM','Ai1PaghZh5FmBLCDCTQpwG8jB264AKaTuEncGdC.oafiMrK3Nsu1h3S0','MXN',null,'sandbox','PAYPAL',2,'EN1MRW8uE6znhxZ60qAG3LVmG2cS4h2l4lo6X8BoOmYpfQ2dMzzFad0IfwFksFVq8ohWB_ypJFYCnts9','AdnzRKofVM-bIu2R6UciWIg8JHLBjVRl-3v1Y0YPMWEqDQTfQ3KRVgUxtJ0_5biJ1z_ceeeeOD00ki4Q','https://api.sandbox.paypal.com/v1/oauth2/token','https://api.sandbox.paypal.com/v1/risk/transaction-contexts/','979K2XCU58UGY');

Insert into CONFIG_FORMA_PAGO_AFILIACION (ORGANIZACION_ID,CONFIG_FORMA_PAGO_ID,EVENTO_ID,AFILIACION,CREACION_FECHA,CREACION_USUARIO,USER_NAME,PASSWORD,SIGNATURE) values (2,5,null,'000070',to_date('22/05/17','DD/MM/RR'),1,'mav3jud5kdxu2ejvjai0',null,'sk_473b70f450734b3daf6ab61e5e78877d');
Insert into CONFIG_FORMA_PAGO_AFILIACION (ORGANIZACION_ID,CONFIG_FORMA_PAGO_ID,EVENTO_ID,AFILIACION,CREACION_FECHA,CREACION_USUARIO,USER_NAME,PASSWORD,SIGNATURE) values (4,5,null,'000070',to_date('22/05/17','DD/MM/RR'),1,'mav3jud5kdxu2ejvjai0',null,'sk_473b70f450734b3daf6ab61e5e78877d');
Insert into CONFIG_FORMA_PAGO_AFILIACION (ORGANIZACION_ID,CONFIG_FORMA_PAGO_ID,EVENTO_ID,AFILIACION,CREACION_FECHA,CREACION_USUARIO,USER_NAME,PASSWORD,SIGNATURE) values (3,5,null,'000070',to_date('24/05/17','DD/MM/RR'),1,'mav3jud5kdxu2ejvjai0',null,'sk_473b70f450734b3daf6ab61e5e78877d');

Insert into EVENTO_FORMA_PAGO (CONFIG_FORMA_PAGO_ID,EVENTO_ID,CREACION_FECHA,CREACION_USUARIO) values (5,null,to_date('22/05/17','DD/MM/RR'),1);
Insert into EVENTO_FORMA_PAGO (CONFIG_FORMA_PAGO_ID,EVENTO_ID,CREACION_FECHA,CREACION_USUARIO) values (8,null,to_date('06/06/17','DD/MM/RR'),1);
Insert into EVENTO_FORMA_PAGO (CONFIG_FORMA_PAGO_ID,EVENTO_ID,CREACION_FECHA,CREACION_USUARIO) values (6,null,to_date('06/06/17','DD/MM/RR'),1);
Insert into EVENTO_FORMA_PAGO (CONFIG_FORMA_PAGO_ID,EVENTO_ID,CREACION_FECHA,CREACION_USUARIO) values (7,null,to_date('06/06/17','DD/MM/RR'),1);

SELECT TCP.VALOR FROM ADMON.CATALOGO TC
            JOIN ADMON.CATALOGO_PARAMETRO TCP ON (TC.CATALOGO_ID = TCP.CATALOGO_ID)
            WHERE TC.CLAVE ='TIPO_MONEDA_AFILIACIONES' AND TCP.CATALOGO_PARAMETRO_ID = TO_NUMBER('7025');
