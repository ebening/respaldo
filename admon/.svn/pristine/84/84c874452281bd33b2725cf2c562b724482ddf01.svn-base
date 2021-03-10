DECLARE
v_catalogoIdB  NUMBER ;
v_catalogoIdS  NUMBER ;
v_catalogo     NUMBER ;
v_existe NUMBER;
BEGIN
    --AFILIACION SANTANDER
    SELECT ( SELECT CATALOGO_ID 
                        FROM   TAASGO.CATALOGO 
                        WHERE  CLAVE='AFILIACION_SANTANDER') 
                INTO   v_catalogoIdS 
                FROM   DUAL; 
    IF v_catalogoIdS IS  NULL THEN
        DBMS_OUTPUT.PUT_LINE('SE INSERTO SANTANDER' );
        INSERT INTO TAASGO.CATALOGO (CATALOGO_ID, ORGANIZACION_ID, NOMBRE, CLAVE, SELECCIONA_COLOR,
                DESCRIPCION, CATALOGO_PADRE_ID, ES_DEPENDIENTE, PARAMETRO_DEPENDIENTE_ID, GENERICO,
                ESTATUS_ID, ELIMINADO_ID, CREACION_FECHA, CREACION_USUARIO, MODIFICACION_FECHA,
                MODIFICACION_USUARIO) VALUES (TAASGO.CATALOGO_SEQ.NEXTVAL,1, 'Afiliación Santander', 'AFILIACION_SANTANDER', 4,
                'Valores default para la pantalla Afiliación Santander', null, null, null, 0,
                1,0,SYSDATE,1,SYSDATE,1);
    END IF;
    SELECT CATALOGO_ID INTO v_catalogoIdS FROM TAASGO.CATALOGO WHERE CLAVE='AFILIACION_SANTANDER';
    IF(v_catalogoIdS IS  NOT NULL) THEN
            DBMS_OUTPUT.PUT_LINE('SE INSERTARAN PARÁMETROS' );
            SELECT ( SELECT CATALOGO_ID 
                            FROM   TAASGO.CATALOGO_PARAMETRO 
                            WHERE  CLAVE='URL' AND CATALOGO_ID = v_catalogoIdS) 
                    INTO   v_existe 
                    FROM   DUAL; 
            IF(v_existe IS NULL) THEN 
                INSERT INTO TAASGO.CATALOGO_PARAMETRO( CATALOGO_PARAMETRO_ID, CATALOGO_ID, ORGANIZACION_ID,
                    CLAVE, VALOR, ORDEN, VISIBLE, DESCRIPCION, ESTATUS_ID, 
                    ELIMINADO_ID, CREACION_FECHA, CREACION_USUARIO, MODIFICACION_FECHA,
                    MODIFICACION_USUARIO
                ) VALUES (
                    TAASGO.CATALOGO_PARAMETRO_SEQ.NEXTVAL, v_catalogoIdS, 1, 'URL','https://qa3.mitec.com.mx', 0, 1,'Url para Santander',1,
                    0, SYSDATE, 1,SYSDATE,1
                );
            END IF;   
        
            SELECT ( SELECT CATALOGO_ID 
                                    FROM   TAASGO.CATALOGO_PARAMETRO 
                                    WHERE  CLAVE='BASE_COBRO' AND CATALOGO_ID = v_catalogoIdS) 
                            INTO   v_existe 
                            FROM   DUAL; 
            IF(v_existe IS NULL) THEN          
                INSERT INTO TAASGO.CATALOGO_PARAMETRO( CATALOGO_PARAMETRO_ID, CATALOGO_ID, ORGANIZACION_ID,
                    CLAVE, VALOR, ORDEN, VISIBLE, DESCRIPCION, ESTATUS_ID, 
                    ELIMINADO_ID, CREACION_FECHA, CREACION_USUARIO, MODIFICACION_FECHA,
                    MODIFICACION_USUARIO
                ) VALUES (
                    TAASGO.CATALOGO_PARAMETRO_SEQ.NEXTVAL,v_catalogoIdS, 1, 'BASE_COBRO','/pgs/cobroXml', 0, 1,'Base Cobro',1,
                    0, SYSDATE, 1,SYSDATE,1
                );
            END IF;
        
            SELECT ( SELECT CATALOGO_ID 
                                    FROM   TAASGO.CATALOGO_PARAMETRO 
                                    WHERE  CLAVE='BASE_CANCELACION' AND CATALOGO_ID = v_catalogoIdS) 
                            INTO   v_existe 
                            FROM   DUAL; 
            IF(v_existe IS NULL) THEN  
                INSERT INTO TAASGO.CATALOGO_PARAMETRO( CATALOGO_PARAMETRO_ID, CATALOGO_ID, ORGANIZACION_ID,
                    CLAVE, VALOR, ORDEN, VISIBLE, DESCRIPCION, ESTATUS_ID, 
                    ELIMINADO_ID, CREACION_FECHA, CREACION_USUARIO, MODIFICACION_FECHA,
                    MODIFICACION_USUARIO
                ) VALUES (
                    TAASGO.CATALOGO_PARAMETRO_SEQ.NEXTVAL,v_catalogoIdS, 1, 'BASE_CANCELACION','/pgs/CancelacionXml', 0, 1,'Base Cancelación',1,
                    0, SYSDATE, 1,SYSDATE,1
                );
            END IF;
        
            SELECT ( SELECT CATALOGO_ID 
                                    FROM   TAASGO.CATALOGO_PARAMETRO 
                                    WHERE  CLAVE='BASE_3DS' AND CATALOGO_ID = v_catalogoIdS) 
                            INTO   v_existe 
                            FROM   DUAL; 
            IF(v_existe IS NULL) THEN     
                INSERT INTO TAASGO.CATALOGO_PARAMETRO ( CATALOGO_PARAMETRO_ID, CATALOGO_ID, ORGANIZACION_ID,
                    CLAVE, VALOR, ORDEN, VISIBLE, DESCRIPCION, ESTATUS_ID, 
                    ELIMINADO_ID, CREACION_FECHA, CREACION_USUARIO, MODIFICACION_FECHA,
                    MODIFICACION_USUARIO 
                ) VALUES (
                    TAASGO.CATALOGO_PARAMETRO_SEQ.NEXTVAL, v_catalogoIdS, 1, 'BASE_3DS','/ws3dsecure/Auth3dsecure', 0, 1,'Solo si se ha seleccionado validar con 3DS',1,
                    0, SYSDATE, 1,SYSDATE,1
                );
            END IF;
        
            SELECT ( SELECT CATALOGO_ID 
                                    FROM   TAASGO.CATALOGO_PARAMETRO 
                                    WHERE  CLAVE='TYPEC' AND CATALOGO_ID = v_catalogoIdS) 
                            INTO   v_existe 
                            FROM   DUAL; 
            IF(v_existe IS NULL) THEN   
                INSERT INTO TAASGO.CATALOGO_PARAMETRO (  CATALOGO_PARAMETRO_ID, CATALOGO_ID, ORGANIZACION_ID,
                    CLAVE, VALOR, ORDEN, VISIBLE, DESCRIPCION, ESTATUS_ID, 
                    ELIMINADO_ID, CREACION_FECHA, CREACION_USUARIO, MODIFICACION_FECHA,
                    MODIFICACION_USUARIO  
                ) VALUES (
                    TAASGO.CATALOGO_PARAMETRO_SEQ.NEXTVAL, v_catalogoIdS, 1, 'TYPEC',' V/MC', 0, 1,'Se llena automático con Visa/Mastercard',1,
                    0, SYSDATE, 1,SYSDATE,1
                );
           END IF;
        
            SELECT ( SELECT CATALOGO_ID 
                                    FROM   TAASGO.CATALOGO_PARAMETRO 
                                    WHERE  CLAVE='EMPRESA_AFILIACION_ID' AND CATALOGO_ID = v_catalogoIdS) 
                            INTO   v_existe 
                            FROM   DUAL; 
            IF(v_existe IS NULL) THEN    
                INSERT INTO TAASGO.CATALOGO_PARAMETRO ( CATALOGO_PARAMETRO_ID, CATALOGO_ID, ORGANIZACION_ID,
                    CLAVE, VALOR, ORDEN, VISIBLE, DESCRIPCION, ESTATUS_ID, 
                    ELIMINADO_ID, CREACION_FECHA, CREACION_USUARIO, MODIFICACION_FECHA,
                    MODIFICACION_USUARIO  
                ) VALUES (
                    TAASGO.CATALOGO_PARAMETRO_SEQ.NEXTVAL, v_catalogoIdS, 1, 'EMPRESA_AFILIACION_ID',' 1', 0, 1,'Se crea un consecutivo automático en aumento de 1',1,
                    0, SYSDATE, 1,SYSDATE,1
                );
            END IF;
        
            SELECT ( SELECT CATALOGO_ID 
                                    FROM   TAASGO.CATALOGO_PARAMETRO 
                                    WHERE  CLAVE='CLAVE_INSTITUCION_BANCARIA' AND CATALOGO_ID = v_catalogoIdS) 
                            INTO   v_existe 
                            FROM   DUAL; 
            IF(v_existe IS NULL) THEN    
                INSERT INTO TAASGO.CATALOGO_PARAMETRO ( CATALOGO_PARAMETRO_ID, CATALOGO_ID, ORGANIZACION_ID,
                    CLAVE, VALOR, ORDEN, VISIBLE, DESCRIPCION, ESTATUS_ID, 
                    ELIMINADO_ID, CREACION_FECHA, CREACION_USUARIO, MODIFICACION_FECHA,
                    MODIFICACION_USUARIO  
                ) VALUES (
                    TAASGO.CATALOGO_PARAMETRO_SEQ.NEXTVAL, v_catalogoIdS, 1, 'CLAVE_INSTITUCION_BANCARIA',' SANTANDER', 0, 1,'Clave Bancaria de Santander',1,
                    0, SYSDATE, 1,SYSDATE,1
                );
            END IF;
    END IF;
    
    --AFILIACION BANAMEX
    SELECT ( SELECT CATALOGO_ID 
                        FROM   TAASGO.CATALOGO 
                        WHERE  CLAVE='AFILIACION_BANAMEX') 
                INTO   v_catalogoIdB 
                FROM   DUAL; 
    IF v_catalogoIdB IS  NULL THEN
        DBMS_OUTPUT.PUT_LINE('SE INSERTO BANAMEX' );
         INSERT INTO TAASGO.CATALOGO (CATALOGO_ID, ORGANIZACION_ID, NOMBRE, CLAVE, SELECCIONA_COLOR,
                    DESCRIPCION, CATALOGO_PADRE_ID, ES_DEPENDIENTE, PARAMETRO_DEPENDIENTE_ID, GENERICO,
                    ESTATUS_ID, ELIMINADO_ID, CREACION_FECHA, CREACION_USUARIO, MODIFICACION_FECHA,
                    MODIFICACION_USUARIO) VALUES (TAASGO.CATALOGO_SEQ.NEXTVAL,1, 'Afiliación Banamex', 'AFILIACION_BANAMEX', 4,
                    'Valores default para la pantalla Afiliación Banamex', null, null, null, 1,
                    1,0,SYSDATE,1,SYSDATE,1);
    END IF;
    SELECT CATALOGO_ID INTO v_catalogoIdB FROM TAASGO.CATALOGO WHERE CLAVE='AFILIACION_BANAMEX';
    IF(v_catalogoIdB IS  NOT NULL) THEN
        DBMS_OUTPUT.PUT_LINE('SE INSERTARAN PARÁMETROS BANAMEX' );
        SELECT ( SELECT CATALOGO_ID 
                        FROM   TAASGO.CATALOGO_PARAMETRO 
                        WHERE  CLAVE='URL' AND CATALOGO_ID = v_catalogoIdB) 
                INTO   v_existe 
                FROM   DUAL; 
        IF(v_existe IS NULL) THEN 
            INSERT INTO TAASGO.CATALOGO_PARAMETRO ( CATALOGO_PARAMETRO_ID, CATALOGO_ID, ORGANIZACION_ID,
                CLAVE, VALOR, ORDEN, VISIBLE, DESCRIPCION, ESTATUS_ID, 
                ELIMINADO_ID, CREACION_FECHA, CREACION_USUARIO, MODIFICACION_FECHA,
                MODIFICACION_USUARIO
            ) VALUES ( TAASGO.CATALOGO_PARAMETRO_SEQ.NEXTVAL,  v_catalogoIdB,1, 'URL','https://banamex.dialectpayments.com/api/rest/version/40/merchant/{merchantId}/session', 0, 1,'Url que deberá llenarse automáticamente para Web',1,
                        0, SYSDATE, 1,SYSDATE,1 );
        END IF;
        
        SELECT ( SELECT CATALOGO_ID 
                        FROM   TAASGO.CATALOGO_PARAMETRO 
                        WHERE  CLAVE='COMMAND' AND CATALOGO_ID = v_catalogoIdB) 
                INTO   v_existe 
                FROM   DUAL; 
        IF(v_existe IS NULL) THEN 
            INSERT INTO TAASGO.CATALOGO_PARAMETRO (CATALOGO_PARAMETRO_ID, CATALOGO_ID, ORGANIZACION_ID,
                CLAVE, VALOR, ORDEN, VISIBLE, DESCRIPCION, ESTATUS_ID, 
                ELIMINADO_ID, CREACION_FECHA, CREACION_USUARIO, MODIFICACION_FECHA,
                MODIFICACION_USUARIO
            ) VALUES (
                TAASGO.CATALOGO_PARAMETRO_SEQ.NEXTVAL,v_catalogoIdB,1, 'COMMAND','pay', 0, 1,
                'Command que deberá llenarse automáticamente para Web',1,
                0, SYSDATE, 1,SYSDATE,1
            );
        END IF;
        
        SELECT ( SELECT CATALOGO_ID 
                        FROM   TAASGO.CATALOGO_PARAMETRO 
                        WHERE  CLAVE='RETURN_URL' AND CATALOGO_ID = v_catalogoIdB) 
                INTO   v_existe 
                FROM   DUAL; 
        IF(v_existe IS NULL) THEN 
            INSERT INTO TAASGO.CATALOGO_PARAMETRO (CATALOGO_PARAMETRO_ID, CATALOGO_ID, ORGANIZACION_ID,
                CLAVE, VALOR, ORDEN, VISIBLE, DESCRIPCION, ESTATUS_ID, 
                ELIMINADO_ID, CREACION_FECHA, CREACION_USUARIO, MODIFICACION_FECHA,
                MODIFICACION_USUARIO
            ) VALUES (
                TAASGO.CATALOGO_PARAMETRO_SEQ.NEXTVAL, v_catalogoIdB,1, 'RETURN_URL','/taquilla/w/v/pagoBanamex', 0, 1,'Return Url',1,
                0, SYSDATE, 1,SYSDATE,1
            );
        END IF;
        SELECT ( SELECT CATALOGO_ID 
                        FROM   TAASGO.CATALOGO_PARAMETRO 
                        WHERE  CLAVE='ACTION_URL' AND CATALOGO_ID = v_catalogoIdB) 
                INTO   v_existe 
                FROM   DUAL; 
        IF(v_existe IS NULL) THEN
            INSERT INTO TAASGO.CATALOGO_PARAMETRO (  CATALOGO_PARAMETRO_ID, CATALOGO_ID, ORGANIZACION_ID,
                CLAVE, VALOR, ORDEN, VISIBLE, DESCRIPCION, ESTATUS_ID, 
                ELIMINADO_ID, CREACION_FECHA, CREACION_USUARIO, MODIFICACION_FECHA,
                MODIFICACION_USUARIO
            ) VALUES (
                TAASGO.CATALOGO_PARAMETRO_SEQ.NEXTVAL, v_catalogoIdB,1, 'ACTION_URL','http://localhost:8081/upay/', 0, 1,'Action Url',1,
                0, SYSDATE, 1,SYSDATE,1
            );
        END IF;
        
        SELECT ( SELECT CATALOGO_ID 
                        FROM   TAASGO.CATALOGO_PARAMETRO 
                        WHERE  CLAVE='CLAVE_INSTITUCION_BANCARIA' AND CATALOGO_ID = v_catalogoIdB) 
                INTO   v_existe 
                FROM   DUAL; 
        IF(v_existe IS NULL) THEN
            INSERT INTO TAASGO.CATALOGO_PARAMETRO (  CATALOGO_PARAMETRO_ID, CATALOGO_ID, ORGANIZACION_ID,
                CLAVE, VALOR, ORDEN, VISIBLE, DESCRIPCION, ESTATUS_ID, 
                ELIMINADO_ID, CREACION_FECHA, CREACION_USUARIO, MODIFICACION_FECHA,
                MODIFICACION_USUARIO
            ) VALUES (
                TAASGO.CATALOGO_PARAMETRO_SEQ.NEXTVAL, v_catalogoIdB,1, 'CLAVE_INSTITUCION_BANCARIA','BANAMEX', 0, 1,'Clave Institucion Bancaria',1,
                0, SYSDATE, 1,SYSDATE,1
            );
        END IF;
        
        SELECT ( SELECT CATALOGO_ID 
                        FROM   TAASGO.CATALOGO_PARAMETRO 
                        WHERE  CLAVE='VALIDA_BANAMEX' AND CATALOGO_ID = v_catalogoIdB) 
                INTO   v_existe 
                FROM   DUAL;
        IF(v_existe IS NULL) THEN
            INSERT INTO TAASGO.CATALOGO_PARAMETRO (  CATALOGO_PARAMETRO_ID, CATALOGO_ID, ORGANIZACION_ID,
                CLAVE, VALOR, ORDEN, VISIBLE, DESCRIPCION, ESTATUS_ID, 
                ELIMINADO_ID, CREACION_FECHA, CREACION_USUARIO, MODIFICACION_FECHA,
                MODIFICACION_USUARIO
            ) VALUES (
                TAASGO.CATALOGO_PARAMETRO_SEQ.NEXTVAL, v_catalogoIdB,1, 'VALIDA_BANAMEX','1', 0, 1,'Valida Banamex',1,
                0, SYSDATE, 1,SYSDATE,1
            );
        END IF;
        
        SELECT ( SELECT CATALOGO_ID 
                        FROM   TAASGO.CATALOGO_PARAMETRO 
                        WHERE  CLAVE='URL_CANCELACION' AND CATALOGO_ID = v_catalogoIdB) 
                INTO   v_existe 
                FROM   DUAL;
        IF(v_existe IS NULL) THEN
            INSERT INTO TAASGO.CATALOGO_PARAMETRO (  CATALOGO_PARAMETRO_ID, CATALOGO_ID, ORGANIZACION_ID,
                CLAVE, VALOR, ORDEN, VISIBLE, DESCRIPCION, ESTATUS_ID, 
                ELIMINADO_ID, CREACION_FECHA, CREACION_USUARIO, MODIFICACION_FECHA,
                MODIFICACION_USUARIO
            ) VALUES (
                TAASGO.CATALOGO_PARAMETRO_SEQ.NEXTVAL, v_catalogoIdB,1, 'URL_CANCELACION','/taquilla/w/v/cancelacionPagoBanamex', 0, 1,'Url Cancelación',1,
                0, SYSDATE, 1,SYSDATE,1
            );
        END IF;
        
        SELECT ( SELECT CATALOGO_ID 
                        FROM   TAASGO.CATALOGO_PARAMETRO 
                        WHERE  CLAVE='URL_DEVOLUCION' AND CATALOGO_ID = v_catalogoIdB) 
                INTO   v_existe 
                FROM   DUAL;
        IF(v_existe IS NULL) THEN
            INSERT INTO TAASGO.CATALOGO_PARAMETRO (  CATALOGO_PARAMETRO_ID, CATALOGO_ID, ORGANIZACION_ID,
                CLAVE, VALOR, ORDEN, VISIBLE, DESCRIPCION, ESTATUS_ID, 
                ELIMINADO_ID, CREACION_FECHA, CREACION_USUARIO, MODIFICACION_FECHA,
                MODIFICACION_USUARIO
            ) VALUES (
                TAASGO.CATALOGO_PARAMETRO_SEQ.NEXTVAL, v_catalogoIdB,1, 'URL_DEVOLUCION','/taquilla/w/v/DevolucionPagoBanamex', 0, 1,'Url Devolución',1,
                0, SYSDATE, 1,SYSDATE,1
            );
        END IF;

        SELECT ( SELECT CATALOGO_ID 
                        FROM   TAASGO.CATALOGO_PARAMETRO 
                        WHERE  CLAVE='TRN_CUR_ID1' AND CATALOGO_ID = v_catalogoIdB) 
                INTO   v_existe 
                FROM   DUAL;
        IF(v_existe IS NULL) THEN
            INSERT INTO TAASGO.CATALOGO_PARAMETRO (  CATALOGO_PARAMETRO_ID, CATALOGO_ID, ORGANIZACION_ID,
                CLAVE, VALOR, ORDEN, VISIBLE, DESCRIPCION, ESTATUS_ID, 
                ELIMINADO_ID, CREACION_FECHA, CREACION_USUARIO, MODIFICACION_FECHA,
                MODIFICACION_USUARIO
            ) VALUES (
                TAASGO.CATALOGO_PARAMETRO_SEQ.NEXTVAL, v_catalogoIdB,1, 'TRN_CUR_ID1','484', 0, 1,'TRN_CUR_ID1',1,
                0, SYSDATE, 1,SYSDATE,1
            );
        END IF;
    END IF;

     --CANAL VENTA
    SELECT ( SELECT CATALOGO_ID 
                        FROM   TAASGO.CATALOGO 
                        WHERE  CLAVE='CANAL_VENTA') 
                INTO   v_catalogo
                FROM   DUAL; 
    IF v_catalogo IS  NULL THEN
        INSERT INTO TAASGO.CATALOGO (CATALOGO_ID, ORGANIZACION_ID, NOMBRE, CLAVE, SELECCIONA_COLOR,
                DESCRIPCION, CATALOGO_PADRE_ID, ES_DEPENDIENTE, PARAMETRO_DEPENDIENTE_ID, GENERICO,
                ESTATUS_ID, ELIMINADO_ID, CREACION_FECHA, CREACION_USUARIO, MODIFICACION_FECHA,
                MODIFICACION_USUARIO) VALUES (TAASGO.CATALOGO_SEQ.NEXTVAL,1, 'Canal Venta', 'CANAL_VENTA', 4,
                'Valores para canales de venta', null, null, null, 0,
                1,0,SYSDATE,1,SYSDATE,1);
    END IF;
    SELECT CATALOGO_ID INTO v_catalogo FROM TAASGO.CATALOGO WHERE CLAVE='CANAL_VENTA';
    IF(v_catalogo IS  NOT NULL) THEN
            SELECT ( SELECT CATALOGO_ID 
                            FROM   TAASGO.CATALOGO_PARAMETRO 
                            WHERE  CLAVE='PRESENCIAL' AND CATALOGO_ID = v_catalogo) 
                    INTO   v_existe 
                    FROM   DUAL; 
            IF(v_existe IS NULL) THEN 
                INSERT INTO TAASGO.CATALOGO_PARAMETRO ( CATALOGO_PARAMETRO_ID, CATALOGO_ID, ORGANIZACION_ID,
                    CLAVE, VALOR, ORDEN, VISIBLE, DESCRIPCION, ESTATUS_ID, 
                    ELIMINADO_ID, CREACION_FECHA, CREACION_USUARIO, MODIFICACION_FECHA,
                    MODIFICACION_USUARIO
                ) VALUES (
                    TAASGO.CATALOGO_PARAMETRO_SEQ.NEXTVAL, v_catalogo, 1, 'PRESENCIAL','PRESENCIAL', 0, 1,'Canal de venta',1,
                    0, SYSDATE, 1,SYSDATE,1
                );
            END IF;  

            SELECT ( SELECT CATALOGO_ID 
                            FROM   TAASGO.CATALOGO_PARAMETRO 
                            WHERE  CLAVE='NO_PRESENCIAL' AND CATALOGO_ID = v_catalogo) 
                    INTO   v_existe 
                    FROM   DUAL; 
            IF(v_existe IS NULL) THEN 
                INSERT INTO TAASGO.CATALOGO_PARAMETRO ( CATALOGO_PARAMETRO_ID, CATALOGO_ID, ORGANIZACION_ID,
                    CLAVE, VALOR, ORDEN, VISIBLE, DESCRIPCION, ESTATUS_ID, 
                    ELIMINADO_ID, CREACION_FECHA, CREACION_USUARIO, MODIFICACION_FECHA,
                    MODIFICACION_USUARIO
                ) VALUES (
                    TAASGO.CATALOGO_PARAMETRO_SEQ.NEXTVAL, v_catalogo, 1, 'NO_PRESENCIAL','NO PRESENCIAL', 0, 1,'Canal de venta',1,
                    0, SYSDATE, 1,SYSDATE,1
                );
            END IF;  
    END IF; 

    --TIPO DE MONEDA AFILIACIONES
    SELECT ( SELECT CATALOGO_ID 
                        FROM   TAASGO.CATALOGO 
                        WHERE  CLAVE='TIPO_MONEDA_AFILIACIONES') 
                INTO   v_catalogo
                FROM   DUAL; 
    IF v_catalogo IS  NULL THEN
        INSERT INTO TAASGO.CATALOGO (CATALOGO_ID, ORGANIZACION_ID, NOMBRE, CLAVE, SELECCIONA_COLOR,
                DESCRIPCION, CATALOGO_PADRE_ID, ES_DEPENDIENTE, PARAMETRO_DEPENDIENTE_ID, GENERICO,
                ESTATUS_ID, ELIMINADO_ID, CREACION_FECHA, CREACION_USUARIO, MODIFICACION_FECHA,
                MODIFICACION_USUARIO) VALUES (TAASGO.CATALOGO_SEQ.NEXTVAL,1, 'Tipo Moneda Afiliaciones', 'TIPO_MONEDA_AFILIACIONES', 4,
                'Tipos de Moneda Afiliaciones', null, null, null, 0,
                1,0,SYSDATE,1,SYSDATE,1);
    END IF;
    SELECT CATALOGO_ID INTO v_catalogo FROM TAASGO.CATALOGO WHERE CLAVE='TIPO_MONEDA_AFILIACIONES';
    IF(v_catalogo IS  NOT NULL) THEN
            SELECT ( SELECT CATALOGO_ID 
                            FROM   TAASGO.CATALOGO_PARAMETRO 
                            WHERE  CLAVE='MXN' AND CATALOGO_ID = v_catalogo) 
                    INTO   v_existe 
                    FROM   DUAL; 
            IF(v_existe IS NULL) THEN 
                INSERT INTO TAASGO.CATALOGO_PARAMETRO ( CATALOGO_PARAMETRO_ID, CATALOGO_ID, ORGANIZACION_ID,
                    CLAVE, VALOR, ORDEN, VISIBLE, DESCRIPCION, ESTATUS_ID, 
                    ELIMINADO_ID, CREACION_FECHA, CREACION_USUARIO, MODIFICACION_FECHA,
                    MODIFICACION_USUARIO
                ) VALUES (
                    TAASGO.CATALOGO_PARAMETRO_SEQ.NEXTVAL, v_catalogo, 1, 'MXN','MXN', 0, 1,'Moneda Nacional',1,
                    0, SYSDATE, 1,SYSDATE,1
                );
            END IF;  
    END IF; 

     --VERSION AFILIACIONES
    SELECT ( SELECT CATALOGO_ID 
                        FROM   TAASGO.CATALOGO 
                        WHERE  CLAVE='VERSION_AFILIACIONES') 
                INTO   v_catalogo
                FROM   DUAL; 
    IF v_catalogo IS  NULL THEN
        INSERT INTO TAASGO.CATALOGO (CATALOGO_ID, ORGANIZACION_ID, NOMBRE, CLAVE, SELECCIONA_COLOR,
                DESCRIPCION, CATALOGO_PADRE_ID, ES_DEPENDIENTE, PARAMETRO_DEPENDIENTE_ID, GENERICO,
                ESTATUS_ID, ELIMINADO_ID, CREACION_FECHA, CREACION_USUARIO, MODIFICACION_FECHA,
                MODIFICACION_USUARIO) VALUES (TAASGO.CATALOGO_SEQ.NEXTVAL,1, 'Tipo Versión Afiliaciones', 'VERSION_AFILIACIONES', 4,
                'Tipo de Versión Afiliaciones', null, null, null, 0,
                1,0,SYSDATE,1,SYSDATE,1);
    END IF;
    SELECT CATALOGO_ID INTO v_catalogo FROM TAASGO.CATALOGO WHERE CLAVE='VERSION_AFILIACIONES';
    IF(v_catalogo IS  NOT NULL) THEN
            SELECT ( SELECT CATALOGO_ID 
                            FROM   TAASGO.CATALOGO_PARAMETRO 
                            WHERE  CLAVE='VERSION_1' AND CATALOGO_ID = v_catalogo) 
                    INTO   v_existe 
                    FROM   DUAL; 
            IF(v_existe IS NULL) THEN 
                INSERT INTO TAASGO.CATALOGO_PARAMETRO ( CATALOGO_PARAMETRO_ID, CATALOGO_ID, ORGANIZACION_ID,
                    CLAVE, VALOR, ORDEN, VISIBLE, DESCRIPCION, ESTATUS_ID, 
                    ELIMINADO_ID, CREACION_FECHA, CREACION_USUARIO, MODIFICACION_FECHA,
                    MODIFICACION_USUARIO
                ) VALUES (
                    TAASGO.CATALOGO_PARAMETRO_SEQ.NEXTVAL, v_catalogo, 1, 'VERSION_1','1', 0, 1,'Versión de Afiliación',1,
                    0, SYSDATE, 1,SYSDATE,1
                );
            END IF;  
             SELECT ( SELECT CATALOGO_ID 
                            FROM   TAASGO.CATALOGO_PARAMETRO 
                            WHERE  CLAVE='VERSION_1' AND CATALOGO_ID = v_catalogo) 
                    INTO   v_existe 
                    FROM   DUAL; 
            IF(v_existe IS NULL) THEN 
            INSERT INTO TAASGO.CATALOGO_PARAMETRO ( CATALOGO_PARAMETRO_ID, CATALOGO_ID, ORGANIZACION_ID,
                    CLAVE, VALOR, ORDEN, VISIBLE, DESCRIPCION, ESTATUS_ID, 
                    ELIMINADO_ID, CREACION_FECHA, CREACION_USUARIO, MODIFICACION_FECHA,
                    MODIFICACION_USUARIO
                ) VALUES (
                    TAASGO.CATALOGO_PARAMETRO_SEQ.NEXTVAL, v_catalogo, 1, 'VERSION_2','2', 0, 1,'Versión de Afiliación',1,
                    0, SYSDATE, 1,SYSDATE,1
                );
            END IF;  
    END IF; 
    --CRYPTO AFILIACION SANTANDER
    SELECT ( SELECT CATALOGO_ID 
                        FROM   TAASGO.CATALOGO 
                        WHERE  CLAVE='CRYPTO_AFILIACIONES_SANTANDER') 
                INTO   v_catalogo
                FROM   DUAL; 
    IF v_catalogo IS  NULL THEN
        INSERT INTO TAASGO.CATALOGO (CATALOGO_ID, ORGANIZACION_ID, NOMBRE, CLAVE, SELECCIONA_COLOR,
                DESCRIPCION, CATALOGO_PADRE_ID, ES_DEPENDIENTE, PARAMETRO_DEPENDIENTE_ID, GENERICO,
                ESTATUS_ID, ELIMINADO_ID, CREACION_FECHA, CREACION_USUARIO, MODIFICACION_FECHA,
                MODIFICACION_USUARIO) VALUES (TAASGO.CATALOGO_SEQ.NEXTVAL,1, 'Crypto', 'CRYPTO_AFILIACIONES_SANTANDER', 4,
                'Si el tipo de canal de venta es presencial el valor es 0, de lo contrario es 3', null, null, null, 0,
                1,0,SYSDATE,1,SYSDATE,1);
    END IF;
    SELECT CATALOGO_ID INTO v_catalogo FROM TAASGO.CATALOGO WHERE CLAVE='CRYPTO_AFILIACIONES_SANTANDER';
    IF(v_catalogo IS  NOT NULL) THEN
            SELECT ( SELECT CATALOGO_ID 
                            FROM   TAASGO.CATALOGO_PARAMETRO 
                            WHERE  CLAVE='CRYPTO_0' AND CATALOGO_ID = v_catalogo) 
                    INTO   v_existe 
                    FROM   DUAL; 
            IF(v_existe IS NULL) THEN 
                INSERT INTO TAASGO.CATALOGO_PARAMETRO ( CATALOGO_PARAMETRO_ID, CATALOGO_ID, ORGANIZACION_ID,
                    CLAVE, VALOR, ORDEN, VISIBLE, DESCRIPCION, ESTATUS_ID, 
                    ELIMINADO_ID, CREACION_FECHA, CREACION_USUARIO, MODIFICACION_FECHA,
                    MODIFICACION_USUARIO
                ) VALUES (
                    TAASGO.CATALOGO_PARAMETRO_SEQ.NEXTVAL, v_catalogo, 1, 'CRYPTO_0','0', 0, 1,'Crypto para tipo canal Presencial',1,
                    0, SYSDATE, 1,SYSDATE,1
                );
            END IF;  
            SELECT ( SELECT CATALOGO_ID 
                            FROM   TAASGO.CATALOGO_PARAMETRO 
                            WHERE  CLAVE='CRYPTO_3' AND CATALOGO_ID = v_catalogo) 
                    INTO   v_existe 
                    FROM   DUAL; 
            IF(v_existe IS NULL) THEN 
                INSERT INTO TAASGO.CATALOGO_PARAMETRO ( CATALOGO_PARAMETRO_ID, CATALOGO_ID, ORGANIZACION_ID,
                    CLAVE, VALOR, ORDEN, VISIBLE, DESCRIPCION, ESTATUS_ID, 
                    ELIMINADO_ID, CREACION_FECHA, CREACION_USUARIO, MODIFICACION_FECHA,
                    MODIFICACION_USUARIO
                ) VALUES (
                    TAASGO.CATALOGO_PARAMETRO_SEQ.NEXTVAL, v_catalogo, 1, 'CRYPTO_3','3', 0, 1,'Crypto para tipo canal No Presencial',1,
                    0, SYSDATE, 1,SYSDATE,1
                );
            END IF;  
    END IF;
    --FORMA DE PAGO
    SELECT ( SELECT CATALOGO_ID 
                        FROM   TAASGO.CATALOGO 
                        WHERE  CLAVE='AFILIACIONES_FORMA_PAGO') 
                INTO   v_catalogo
                FROM   DUAL; 
    IF v_catalogo IS  NULL THEN
        INSERT INTO TAASGO.CATALOGO (CATALOGO_ID, ORGANIZACION_ID, NOMBRE, CLAVE, SELECCIONA_COLOR,
                DESCRIPCION, CATALOGO_PADRE_ID, ES_DEPENDIENTE, PARAMETRO_DEPENDIENTE_ID, GENERICO,
                ESTATUS_ID, ELIMINADO_ID, CREACION_FECHA, CREACION_USUARIO, MODIFICACION_FECHA,
                MODIFICACION_USUARIO) VALUES (TAASGO.CATALOGO_SEQ.NEXTVAL,1, 'Forma de Pago', 'AFILIACIONES_FORMA_PAGO', 4,
                'Formas de Pago de Afiliaciones Paynet Paypal', null, null, null, 0,
                1,0,SYSDATE,1,SYSDATE,1);
    END IF;
    SELECT CATALOGO_ID INTO v_catalogo FROM TAASGO.CATALOGO WHERE CLAVE='AFILIACIONES_FORMA_PAGO';
    IF(v_catalogo IS  NOT NULL) THEN
            SELECT ( SELECT CATALOGO_ID 
                            FROM   TAASGO.CATALOGO_PARAMETRO 
                            WHERE  CLAVE='PAYPAL' AND CATALOGO_ID = v_catalogo) 
                    INTO   v_existe 
                    FROM   DUAL; 
            IF(v_existe IS NULL) THEN 
                INSERT INTO TAASGO.CATALOGO_PARAMETRO ( CATALOGO_PARAMETRO_ID, CATALOGO_ID, ORGANIZACION_ID,
                    CLAVE, VALOR, ORDEN, VISIBLE, DESCRIPCION, ESTATUS_ID, 
                    ELIMINADO_ID, CREACION_FECHA, CREACION_USUARIO, MODIFICACION_FECHA,
                    MODIFICACION_USUARIO
                ) VALUES (
                    TAASGO.CATALOGO_PARAMETRO_SEQ.NEXTVAL, v_catalogo, 1, 'PAYPAL','PAYPAL', 0, 1,'Forma de Pago Paypal en Afiliaciones',1,
                    0, SYSDATE, 1,SYSDATE,1
                );
            END IF;  
            SELECT ( SELECT CATALOGO_ID 
                            FROM   TAASGO.CATALOGO_PARAMETRO 
                            WHERE  CLAVE='PAYNET' AND CATALOGO_ID = v_catalogo) 
                    INTO   v_existe 
                    FROM   DUAL; 
            IF(v_existe IS NULL) THEN 
                INSERT INTO TAASGO.CATALOGO_PARAMETRO ( CATALOGO_PARAMETRO_ID, CATALOGO_ID, ORGANIZACION_ID,
                    CLAVE, VALOR, ORDEN, VISIBLE, DESCRIPCION, ESTATUS_ID, 
                    ELIMINADO_ID, CREACION_FECHA, CREACION_USUARIO, MODIFICACION_FECHA,
                    MODIFICACION_USUARIO
                ) VALUES (
                    TAASGO.CATALOGO_PARAMETRO_SEQ.NEXTVAL, v_catalogo, 1, 'PAYNET','PAYNET', 0, 1,'Forma de Pago Paynet en Afiliaciones',1,
                    0, SYSDATE, 1,SYSDATE,1
                );
            END IF;  
    END IF;
    SELECT CATALOGO_ID INTO v_catalogo FROM TAASGO.CATALOGO WHERE CLAVE='T_CANAL_VENTA';
    IF(v_catalogo IS  NOT NULL) THEN
            SELECT ( SELECT CATALOGO_ID 
                            FROM   TAASGO.CATALOGO_PARAMETRO 
                            WHERE  CLAVE='CALL_CENTER' AND CATALOGO_ID = v_catalogo) 
                    INTO   v_existe 
                    FROM   DUAL; 
            IF(v_existe IS NULL) THEN 
                INSERT INTO TAASGO.CATALOGO_PARAMETRO ( CATALOGO_PARAMETRO_ID, CATALOGO_ID, ORGANIZACION_ID,
                    CLAVE, VALOR, ORDEN, VISIBLE, DESCRIPCION, ESTATUS_ID, 
                    ELIMINADO_ID, CREACION_FECHA, CREACION_USUARIO, MODIFICACION_FECHA,
                    MODIFICACION_USUARIO
                ) VALUES (
                    TAASGO.CATALOGO_PARAMETRO_SEQ.NEXTVAL, v_catalogo, 1, 'CALL_CENTER','CALL_CENTER', 4, 1,'Canal de venta Call Center',1,
                    0, SYSDATE, 1,SYSDATE,1
                );
            END IF;  
            SELECT ( SELECT CATALOGO_ID 
                            FROM   TAASGO.CATALOGO_PARAMETRO 
                            WHERE  CLAVE='TICKET_SALES' AND CATALOGO_ID = v_catalogo) 
                    INTO   v_existe 
                    FROM   DUAL; 
            IF(v_existe IS NULL) THEN 
                INSERT INTO TAASGO.CATALOGO_PARAMETRO( CATALOGO_PARAMETRO_ID, CATALOGO_ID, ORGANIZACION_ID,
                    CLAVE, VALOR, ORDEN, VISIBLE, DESCRIPCION, ESTATUS_ID, 
                    ELIMINADO_ID, CREACION_FECHA, CREACION_USUARIO, MODIFICACION_FECHA,
                    MODIFICACION_USUARIO
                ) VALUES (
                    TAASGO.CATALOGO_PARAMETRO_SEQ.NEXTVAL, v_catalogo, 1, 'TICKET_SALES','TICKET_SALES', 5, 1,'Canal de venta Ticket Sales',1,
                    0, SYSDATE, 1,SYSDATE,1
                );
            END IF;  
    END IF;

    --AFILIACION BANAMEX
    SELECT ( SELECT CATALOGO_ID 
                        FROM   TAASGO.CATALOGO 
                        WHERE  CLAVE='AFILIACION_OTROS') 
                INTO   v_catalogoIdB 
                FROM   DUAL; 
    IF v_catalogoIdB IS  NULL THEN
         INSERT INTO TAASGO.CATALOGO (CATALOGO_ID, ORGANIZACION_ID, NOMBRE, CLAVE, SELECCIONA_COLOR,
                    DESCRIPCION, CATALOGO_PADRE_ID, ES_DEPENDIENTE, PARAMETRO_DEPENDIENTE_ID, GENERICO,
                    ESTATUS_ID, ELIMINADO_ID, CREACION_FECHA, CREACION_USUARIO, MODIFICACION_FECHA,
                    MODIFICACION_USUARIO) VALUES (TAASGO.CATALOGO_SEQ.NEXTVAL,1, 'Afiliación Otros', 'AFILIACION_OTROS', 4,
                    'Valores default para la pantalla Afiliación PayNet y PayPal', null, null, null, 1,
                    1,0,SYSDATE,1,SYSDATE,1);
    END IF;
    SELECT CATALOGO_ID INTO v_catalogoIdB FROM TAASGO.CATALOGO WHERE CLAVE='AFILIACION_OTROS';
    IF(v_catalogoIdB IS  NOT NULL) THEN
        SELECT ( SELECT CATALOGO_ID 
                        FROM   TAASGO.CATALOGO_PARAMETRO 
                        WHERE  CLAVE='URL_SITE' AND CATALOGO_ID = v_catalogoIdB) 
                INTO   v_existe 
                FROM   DUAL; 
        IF(v_existe IS NULL) THEN 
            INSERT INTO TAASGO.CATALOGO_PARAMETRO( CATALOGO_PARAMETRO_ID, CATALOGO_ID, ORGANIZACION_ID,
                CLAVE, VALOR, ORDEN, VISIBLE, DESCRIPCION, ESTATUS_ID, 
                ELIMINADO_ID, CREACION_FECHA, CREACION_USUARIO, MODIFICACION_FECHA,
                MODIFICACION_USUARIO
            ) VALUES ( TAASGO.CATALOGO_PARAMETRO_SEQ.NEXTVAL,  v_catalogoIdB,1, 'URL_SITE','https://www.sandbox.paypal.com/checkoutnow?token=', 0, 1,'Url que deberá llenarse automáticamente',1,
                        0, SYSDATE, 1,SYSDATE,1 );
        END IF;
        
        SELECT ( SELECT CATALOGO_ID 
                        FROM   TAASGO.CATALOGO_PARAMETRO 
                        WHERE  CLAVE='URL_RETURN' AND CATALOGO_ID = v_catalogoIdB) 
                INTO   v_existe 
                FROM   DUAL; 
        IF(v_existe IS NULL) THEN 
            INSERT INTO TAASGO.CATALOGO_PARAMETRO(CATALOGO_PARAMETRO_ID, CATALOGO_ID, ORGANIZACION_ID,
                CLAVE, VALOR, ORDEN, VISIBLE, DESCRIPCION, ESTATUS_ID, 
                ELIMINADO_ID, CREACION_FECHA, CREACION_USUARIO, MODIFICACION_FECHA,
                MODIFICACION_USUARIO
            ) VALUES (
                TAASGO.CATALOGO_PARAMETRO_SEQ.NEXTVAL,v_catalogoIdB,1, 'URL_RETURN','http://qa2.taasgo.mx/Web/w/v/pago/procesaPaypal?cancel_paypal=true', 0, 1,
                'URL_RETURN deberá llenarse automáticamente',1,
                0, SYSDATE, 1,SYSDATE,1
            );
        END IF;
        
        SELECT ( SELECT CATALOGO_ID 
                        FROM   TAASGO.CATALOGO_PARAMETRO 
                        WHERE  CLAVE='URL_CANCEL' AND CATALOGO_ID = v_catalogoIdB) 
                INTO   v_existe 
                FROM   DUAL; 
        IF(v_existe IS NULL) THEN
            INSERT INTO TAASGO.CATALOGO_PARAMETRO(  CATALOGO_PARAMETRO_ID, CATALOGO_ID, ORGANIZACION_ID,
                CLAVE, VALOR, ORDEN, VISIBLE, DESCRIPCION, ESTATUS_ID, 
                ELIMINADO_ID, CREACION_FECHA, CREACION_USUARIO, MODIFICACION_FECHA,
                MODIFICACION_USUARIO
            ) VALUES (
                TAASGO.CATALOGO_PARAMETRO_SEQ.NEXTVAL, v_catalogoIdB,1, 'URL_CANCEL','http://qa2.taasgo.mx/Web/w/v/pago/procesaPaypal?cancel_paypal=false', 0, 1,'Cancel Url',1,
                0, SYSDATE, 1,SYSDATE,1
            );
        END IF;
        
        SELECT ( SELECT CATALOGO_ID 
                        FROM   TAASGO.CATALOGO_PARAMETRO 
                        WHERE  CLAVE='CONNECTION_MODE' AND CATALOGO_ID = v_catalogoIdB) 
                INTO   v_existe 
                FROM   DUAL; 
        IF(v_existe IS NULL) THEN
            INSERT INTO TAASGO.CATALOGO_PARAMETRO(  CATALOGO_PARAMETRO_ID, CATALOGO_ID, ORGANIZACION_ID,
                CLAVE, VALOR, ORDEN, VISIBLE, DESCRIPCION, ESTATUS_ID, 
                ELIMINADO_ID, CREACION_FECHA, CREACION_USUARIO, MODIFICACION_FECHA,
                MODIFICACION_USUARIO
            ) VALUES (
                TAASGO.CATALOGO_PARAMETRO_SEQ.NEXTVAL, v_catalogoIdB,1, 'CONNECTION_MODE','sandbox', 0, 1,'Connection Mode',1,
                0, SYSDATE, 1,SYSDATE,1
            );
        END IF;
        
        SELECT ( SELECT CATALOGO_ID 
                        FROM   TAASGO.CATALOGO_PARAMETRO 
                        WHERE  CLAVE='CURRENCY' AND CATALOGO_ID = v_catalogoIdB) 
                INTO   v_existe 
                FROM   DUAL;
        IF(v_existe IS NULL) THEN
            INSERT INTO TAASGO.CATALOGO_PARAMETRO(  CATALOGO_PARAMETRO_ID, CATALOGO_ID, ORGANIZACION_ID,
                CLAVE, VALOR, ORDEN, VISIBLE, DESCRIPCION, ESTATUS_ID, 
                ELIMINADO_ID, CREACION_FECHA, CREACION_USUARIO, MODIFICACION_FECHA,
                MODIFICACION_USUARIO
            ) VALUES (
                TAASGO.CATALOGO_PARAMETRO_SEQ.NEXTVAL, v_catalogoIdB,1, 'CURRENCY','MXN', 0, 1,'Currency',1,
                0, SYSDATE, 1,SYSDATE,1
            );
        END IF;
        
    END IF;
--AFILIACION_PAYPAL
    SELECT ( SELECT CATALOGO_ID 
                        FROM   TAASGO.CATALOGO 
                        WHERE  CLAVE='AFILIACION_PAYPAL') 
                INTO   v_catalogoIdB 
                FROM   DUAL; 
    IF v_catalogoIdB IS  NULL THEN
         INSERT INTO TAASGO.CATALOGO (CATALOGO_ID, ORGANIZACION_ID, NOMBRE, CLAVE, SELECCIONA_COLOR,
                    DESCRIPCION, CATALOGO_PADRE_ID, ES_DEPENDIENTE, PARAMETRO_DEPENDIENTE_ID, GENERICO,
                    ESTATUS_ID, ELIMINADO_ID, CREACION_FECHA, CREACION_USUARIO, MODIFICACION_FECHA,
                    MODIFICACION_USUARIO) VALUES (TAASGO.CATALOGO_SEQ.NEXTVAL,1, 'Afiliación Otros', 'AFILIACION_PAYPAL', 4,
                    'Valores default para la pantalla Afiliación PayPal', null, null, null, 1,
                    1,0,SYSDATE,1,SYSDATE,1);
    END IF;
    SELECT CATALOGO_ID INTO v_catalogoIdB FROM TAASGO.CATALOGO WHERE CLAVE='AFILIACION_PAYPAL';
    IF(v_catalogoIdB IS  NOT NULL) THEN
        SELECT ( SELECT CATALOGO_ID 
                        FROM   TAASGO.CATALOGO_PARAMETRO 
                        WHERE  CLAVE='URL_SITE' AND CATALOGO_ID = v_catalogoIdB) 
                INTO   v_existe 
                FROM   DUAL; 
        IF(v_existe IS NULL) THEN 
            INSERT INTO TAASGO.CATALOGO_PARAMETRO( CATALOGO_PARAMETRO_ID, CATALOGO_ID, ORGANIZACION_ID,
                CLAVE, VALOR, ORDEN, VISIBLE, DESCRIPCION, ESTATUS_ID, 
                ELIMINADO_ID, CREACION_FECHA, CREACION_USUARIO, MODIFICACION_FECHA,
                MODIFICACION_USUARIO
            ) VALUES ( TAASGO.CATALOGO_PARAMETRO_SEQ.NEXTVAL,  v_catalogoIdB,1, 'URL_SITE','https://www.sandbox.paypal.com/checkoutnow?token=', 0, 1,'Url que deberá llenarse automáticamente',1,
                        0, SYSDATE, 1,SYSDATE,1 );
        END IF;
        
        SELECT ( SELECT CATALOGO_ID 
                        FROM   TAASGO.CATALOGO_PARAMETRO 
                        WHERE  CLAVE='URL_RETURN' AND CATALOGO_ID = v_catalogoIdB) 
                INTO   v_existe 
                FROM   DUAL; 
        IF(v_existe IS NULL) THEN 
            INSERT INTO TAASGO.CATALOGO_PARAMETRO(CATALOGO_PARAMETRO_ID, CATALOGO_ID, ORGANIZACION_ID,
                CLAVE, VALOR, ORDEN, VISIBLE, DESCRIPCION, ESTATUS_ID, 
                ELIMINADO_ID, CREACION_FECHA, CREACION_USUARIO, MODIFICACION_FECHA,
                MODIFICACION_USUARIO
            ) VALUES (
                TAASGO.CATALOGO_PARAMETRO_SEQ.NEXTVAL,v_catalogoIdB,1, 'URL_RETURN','http://qa2.taasgo.mx/Web/w/v/pago/procesaPaypal?cancel_paypal=true', 0, 1,
                'URL_RETURN deberá llenarse automáticamente',1,
                0, SYSDATE, 1,SYSDATE,1
            );
        END IF;
        
        SELECT ( SELECT CATALOGO_ID 
                        FROM   TAASGO.CATALOGO_PARAMETRO 
                        WHERE  CLAVE='URL_CANCEL' AND CATALOGO_ID = v_catalogoIdB) 
                INTO   v_existe 
                FROM   DUAL; 
        IF(v_existe IS NULL) THEN
            INSERT INTO TAASGO.CATALOGO_PARAMETRO(  CATALOGO_PARAMETRO_ID, CATALOGO_ID, ORGANIZACION_ID,
                CLAVE, VALOR, ORDEN, VISIBLE, DESCRIPCION, ESTATUS_ID, 
                ELIMINADO_ID, CREACION_FECHA, CREACION_USUARIO, MODIFICACION_FECHA,
                MODIFICACION_USUARIO
            ) VALUES (
                TAASGO.CATALOGO_PARAMETRO_SEQ.NEXTVAL, v_catalogoIdB,1, 'URL_CANCEL','http://qa2.taasgo.mx/Web/w/v/pago/procesaPaypal?cancel_paypal=false', 0, 1,'Cancel Url',1,
                0, SYSDATE, 1,SYSDATE,1
            );
        END IF;
        
        SELECT ( SELECT CATALOGO_ID 
                        FROM   TAASGO.CATALOGO_PARAMETRO 
                        WHERE  CLAVE='CONNECTION_MODE' AND CATALOGO_ID = v_catalogoIdB) 
                INTO   v_existe 
                FROM   DUAL; 
        IF(v_existe IS NULL) THEN
            INSERT INTO TAASGO.CATALOGO_PARAMETRO(  CATALOGO_PARAMETRO_ID, CATALOGO_ID, ORGANIZACION_ID,
                CLAVE, VALOR, ORDEN, VISIBLE, DESCRIPCION, ESTATUS_ID, 
                ELIMINADO_ID, CREACION_FECHA, CREACION_USUARIO, MODIFICACION_FECHA,
                MODIFICACION_USUARIO
            ) VALUES (
                TAASGO.CATALOGO_PARAMETRO_SEQ.NEXTVAL, v_catalogoIdB,1, 'CONNECTION_MODE','sandbox', 0, 1,'Connection Mode',1,
                0, SYSDATE, 1,SYSDATE,1
            );
        END IF;
        
        SELECT ( SELECT CATALOGO_ID 
                        FROM   TAASGO.CATALOGO_PARAMETRO 
                        WHERE  CLAVE='CURRENCY' AND CATALOGO_ID = v_catalogoIdB) 
                INTO   v_existe 
                FROM   DUAL;
        IF(v_existe IS NULL) THEN
            INSERT INTO TAASGO.CATALOGO_PARAMETRO(  CATALOGO_PARAMETRO_ID, CATALOGO_ID, ORGANIZACION_ID,
                CLAVE, VALOR, ORDEN, VISIBLE, DESCRIPCION, ESTATUS_ID, 
                ELIMINADO_ID, CREACION_FECHA, CREACION_USUARIO, MODIFICACION_FECHA,
                MODIFICACION_USUARIO
            ) VALUES (
                TAASGO.CATALOGO_PARAMETRO_SEQ.NEXTVAL, v_catalogoIdB,1, 'CURRENCY','MXN', 0, 1,'Currency',1,
                0, SYSDATE, 1,SYSDATE,1
            );
        END IF;
        
    END IF;
    
    --AFILIACION_PAYNET
    SELECT ( SELECT CATALOGO_ID 
                        FROM   TAASGO.CATALOGO 
                        WHERE  CLAVE='AFILIACION_PAYNET') 
                INTO   v_catalogoIdB 
                FROM   DUAL; 
    IF v_catalogoIdB IS  NULL THEN
         INSERT INTO TAASGO.CATALOGO (CATALOGO_ID, ORGANIZACION_ID, NOMBRE, CLAVE, SELECCIONA_COLOR,
                    DESCRIPCION, CATALOGO_PADRE_ID, ES_DEPENDIENTE, PARAMETRO_DEPENDIENTE_ID, GENERICO,
                    ESTATUS_ID, ELIMINADO_ID, CREACION_FECHA, CREACION_USUARIO, MODIFICACION_FECHA,
                    MODIFICACION_USUARIO) VALUES (TAASGO.CATALOGO_SEQ.NEXTVAL,1, 'Afiliación Otros', 'AFILIACION_PAYNET', 4,
                    'Valores default para la pantalla Afiliación PayNet ', null, null, null, 1,
                    1,0,SYSDATE,1,SYSDATE,1);
    END IF;
    SELECT CATALOGO_ID INTO v_catalogoIdB FROM TAASGO.CATALOGO WHERE CLAVE='AFILIACION_PAYNET';
    IF(v_catalogoIdB IS  NOT NULL) THEN
        SELECT ( SELECT CATALOGO_ID 
                        FROM   TAASGO.CATALOGO_PARAMETRO 
                        WHERE  CLAVE='URL_SITE' AND CATALOGO_ID = v_catalogoIdB) 
                INTO   v_existe 
                FROM   DUAL; 
        IF(v_existe IS NULL) THEN 
            INSERT INTO TAASGO.CATALOGO_PARAMETRO( CATALOGO_PARAMETRO_ID, CATALOGO_ID, ORGANIZACION_ID,
                CLAVE, VALOR, ORDEN, VISIBLE, DESCRIPCION, ESTATUS_ID, 
                ELIMINADO_ID, CREACION_FECHA, CREACION_USUARIO, MODIFICACION_FECHA,
                MODIFICACION_USUARIO
            ) VALUES ( TAASGO.CATALOGO_PARAMETRO_SEQ.NEXTVAL,  v_catalogoIdB,1, 'URL_SITE','https://www.sandbox.paynet.com/checkoutnow?token=', 0, 1,'Url que deberá llenarse automáticamente',1,
                        0, SYSDATE, 1,SYSDATE,1 );
        END IF;
        
        SELECT ( SELECT CATALOGO_ID 
                        FROM   TAASGO.CATALOGO_PARAMETRO 
                        WHERE  CLAVE='URL_RETURN' AND CATALOGO_ID = v_catalogoIdB) 
                INTO   v_existe 
                FROM   DUAL; 
        IF(v_existe IS NULL) THEN 
            INSERT INTO TAASGO.CATALOGO_PARAMETRO(CATALOGO_PARAMETRO_ID, CATALOGO_ID, ORGANIZACION_ID,
                CLAVE, VALOR, ORDEN, VISIBLE, DESCRIPCION, ESTATUS_ID, 
                ELIMINADO_ID, CREACION_FECHA, CREACION_USUARIO, MODIFICACION_FECHA,
                MODIFICACION_USUARIO
            ) VALUES (
                TAASGO.CATALOGO_PARAMETRO_SEQ.NEXTVAL,v_catalogoIdB,1, 'URL_RETURN','http://qa2.taasgo.mx/Web/w/v/pago/procesaPaynet?cancel_paypal=true', 0, 1,
                'URL_RETURN deberá llenarse automáticamente',1,
                0, SYSDATE, 1,SYSDATE,1
            );
        END IF;
        
        SELECT ( SELECT CATALOGO_ID 
                        FROM   TAASGO.CATALOGO_PARAMETRO 
                        WHERE  CLAVE='URL_CANCEL' AND CATALOGO_ID = v_catalogoIdB) 
                INTO   v_existe 
                FROM   DUAL; 
        IF(v_existe IS NULL) THEN
            INSERT INTO TAASGO.CATALOGO_PARAMETRO(  CATALOGO_PARAMETRO_ID, CATALOGO_ID, ORGANIZACION_ID,
                CLAVE, VALOR, ORDEN, VISIBLE, DESCRIPCION, ESTATUS_ID, 
                ELIMINADO_ID, CREACION_FECHA, CREACION_USUARIO, MODIFICACION_FECHA,
                MODIFICACION_USUARIO
            ) VALUES (
                TAASGO.CATALOGO_PARAMETRO_SEQ.NEXTVAL, v_catalogoIdB,1, 'URL_CANCEL','http://qa2.taasgo.mx/Web/w/v/pago/procesaPaynet?cancel_paypal=false', 0, 1,'Cancel Url',1,
                0, SYSDATE, 1,SYSDATE,1
            );
        END IF;
        
        SELECT ( SELECT CATALOGO_ID 
                        FROM   TAASGO.CATALOGO_PARAMETRO 
                        WHERE  CLAVE='CONNECTION_MODE' AND CATALOGO_ID = v_catalogoIdB) 
                INTO   v_existe 
                FROM   DUAL; 
        IF(v_existe IS NULL) THEN
            INSERT INTO TAASGO.CATALOGO_PARAMETRO(  CATALOGO_PARAMETRO_ID, CATALOGO_ID, ORGANIZACION_ID,
                CLAVE, VALOR, ORDEN, VISIBLE, DESCRIPCION, ESTATUS_ID, 
                ELIMINADO_ID, CREACION_FECHA, CREACION_USUARIO, MODIFICACION_FECHA,
                MODIFICACION_USUARIO
            ) VALUES (
                TAASGO.CATALOGO_PARAMETRO_SEQ.NEXTVAL, v_catalogoIdB,1, 'CONNECTION_MODE','sandnet', 0, 1,'Connection Mode',1,
                0, SYSDATE, 1,SYSDATE,1
            );
        END IF;
        
        SELECT ( SELECT CATALOGO_ID 
                        FROM   TAASGO.CATALOGO_PARAMETRO 
                        WHERE  CLAVE='CURRENCY' AND CATALOGO_ID = v_catalogoIdB) 
                INTO   v_existe 
                FROM   DUAL;
        IF(v_existe IS NULL) THEN
            INSERT INTO TAASGO.CATALOGO_PARAMETRO(  CATALOGO_PARAMETRO_ID, CATALOGO_ID, ORGANIZACION_ID,
                CLAVE, VALOR, ORDEN, VISIBLE, DESCRIPCION, ESTATUS_ID, 
                ELIMINADO_ID, CREACION_FECHA, CREACION_USUARIO, MODIFICACION_FECHA,
                MODIFICACION_USUARIO
            ) VALUES (
                TAASGO.CATALOGO_PARAMETRO_SEQ.NEXTVAL, v_catalogoIdB,1, 'CURRENCY','MXN', 0, 1,'Currency',1,
                0, SYSDATE, 1,SYSDATE,1
            );
        END IF;
        
    END IF;


COMMIT;  
END;
