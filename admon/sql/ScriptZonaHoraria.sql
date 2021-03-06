CREATE TABLE "ZONA_HORARIA" 
   (	"ZONA_ID" NUMBER(*,0)  NOT NULL ENABLE, 
	"ZONA_PARAMETRO_ID" NUMBER(*,0), 
	 CONSTRAINT "PK_ZONA_HORARIAS" PRIMARY KEY ("ZONA_ID")
   ); 
Insert into ZONA_HORARIA (ZONA_ID,ZONA_PARAMETRO_ID) values (1,1);
Insert into ZONA_HORARIA (ZONA_ID,ZONA_PARAMETRO_ID) values (2,1);
Insert into ZONA_HORARIA (ZONA_ID,ZONA_PARAMETRO_ID) values (3,2);
Insert into ZONA_HORARIA (ZONA_ID,ZONA_PARAMETRO_ID) values (4,2);
Insert into ZONA_HORARIA (ZONA_ID,ZONA_PARAMETRO_ID) values (5,3);
Insert into ZONA_HORARIA (ZONA_ID,ZONA_PARAMETRO_ID) values (6,3);
Insert into ZONA_HORARIA (ZONA_ID,ZONA_PARAMETRO_ID) values (7,4);
Insert into ZONA_HORARIA (ZONA_ID,ZONA_PARAMETRO_ID) values (8,4);


 CREATE TABLE "ZONA_HORARIA_LENGUAJE" 
   (	"ZONA_PARAMETRO_ID" NUMBER(*,0) NOT NULL ENABLE, 
	"LENGUAJE_ID" NUMBER(*,0), 
	"ZONA_ID" NUMBER(*,0), 
	"ZONA" VARCHAR2(10 CHAR), 
	"NOMBRE" VARCHAR2(250 CHAR), 
	 CONSTRAINT "PK_ZONA_HORARIA" PRIMARY KEY ("ZONA_PARAMETRO_ID")
   ); 
Insert into ZONA_HORARIA_LENGUAJE (ZONA_PARAMETRO_ID,LENGUAJE_ID,ZONA_ID,ZONA,NOMBRE) values (1,1,1,'-12:00','L?nea internacional de fecha del oeste');
Insert into ZONA_HORARIA_LENGUAJE (ZONA_PARAMETRO_ID,LENGUAJE_ID,ZONA_ID,ZONA,NOMBRE) values (2,2,1,'-12:00','International line of date of the west');
Insert into ZONA_HORARIA_LENGUAJE (ZONA_PARAMETRO_ID,LENGUAJE_ID,ZONA_ID,ZONA,NOMBRE) values (3,1,2,'-11:00','Isla Midway, Samoa');
Insert into ZONA_HORARIA_LENGUAJE (ZONA_PARAMETRO_ID,LENGUAJE_ID,ZONA_ID,ZONA,NOMBRE) values (4,2,2,'-11:00','Isla Midway, Samoa');
Insert into ZONA_HORARIA_LENGUAJE (ZONA_PARAMETRO_ID,LENGUAJE_ID,ZONA_ID,ZONA,NOMBRE) values (5,1,3,'-10:00','Hawai');
Insert into ZONA_HORARIA_LENGUAJE (ZONA_PARAMETRO_ID,LENGUAJE_ID,ZONA_ID,ZONA,NOMBRE) values (6,2,3,'-10:00','Hawaii');
Insert into ZONA_HORARIA_LENGUAJE (ZONA_PARAMETRO_ID,LENGUAJE_ID,ZONA_ID,ZONA,NOMBRE) values (7,1,4,'-09:00','Alaska');
Insert into ZONA_HORARIA_LENGUAJE (ZONA_PARAMETRO_ID,LENGUAJE_ID,ZONA_ID,ZONA,NOMBRE) values (8,2,4,'-09:00','Alaska');
Insert into ZONA_HORARIA_LENGUAJE (ZONA_PARAMETRO_ID,LENGUAJE_ID,ZONA_ID,ZONA,NOMBRE) values (9,1,5,'-08:00','Hora de Pac?fico (EE.UU. Y Canad?)');
Insert into ZONA_HORARIA_LENGUAJE (ZONA_PARAMETRO_ID,LENGUAJE_ID,ZONA_ID,ZONA,NOMBRE) values (10,2,5,'-08:00','Hour of Pac?fico (USA And Canada)');
Insert into ZONA_HORARIA_LENGUAJE (ZONA_PARAMETRO_ID,LENGUAJE_ID,ZONA_ID,ZONA,NOMBRE) values (11,1,6,'-07:00','Chihuahua, La Paz, Mazatl?n');
Insert into ZONA_HORARIA_LENGUAJE (ZONA_PARAMETRO_ID,LENGUAJE_ID,ZONA_ID,ZONA,NOMBRE) values (12,2,6,'-07:00','Chihuahua, La Paz, Mazatl?n');
Insert into ZONA_HORARIA_LENGUAJE (ZONA_PARAMETRO_ID,LENGUAJE_ID,ZONA_ID,ZONA,NOMBRE) values (13,1,7,'-06:00','Guadalajara, Ciudad de M?xico, Monterrey');
Insert into ZONA_HORARIA_LENGUAJE (ZONA_PARAMETRO_ID,LENGUAJE_ID,ZONA_ID,ZONA,NOMBRE) values (14,2,7,'-06:00','Guadalajara, Mexico City, Monterrey');
Insert into ZONA_HORARIA_LENGUAJE (ZONA_PARAMETRO_ID,LENGUAJE_ID,ZONA_ID,ZONA,NOMBRE) values (15,1,8,'-07:00','Tijuana, Baja California');
Insert into ZONA_HORARIA_LENGUAJE (ZONA_PARAMETRO_ID,LENGUAJE_ID,ZONA_ID,ZONA,NOMBRE) values (16,2,8,'-07:00','Tijuana, Lowers California');

CREATE OR REPLACE FORCE VIEW "ZONA_HORARIAS_V" ("ZONA_ID", "NOMBRE", "LENGUAJE_ID") AS 
  SELECT "ZHL"."ZONA_ID", '(GMT' || zona || ') ' || nombre AS "NOMBRE", "ZHL"."LENGUAJE_ID"
FROM "ZONA_HORARIA_LENGUAJE" "ZHL";
