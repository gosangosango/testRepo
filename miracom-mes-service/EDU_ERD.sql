
/* Drop Tables */

<<<<<<< HEAD
DROP TABLE IF EXISTS MA13FLWOPR;
DROP TABLE IF EXISTS MA13FLWDEF;
DROP TABLE IF EXISTS MA13OPRDEF;
=======
DROP TABLE IF EXISTS MA07FLWOPR;
DROP TABLE IF EXISTS MA07FLWDEF;
DROP TABLE IF EXISTS MA07OPRDEF;
>>>>>>> branch 'master' of ssh://admin@192.168.20.14:29418/miracom-mes-service.git

<<<<<<< HEAD
DROP TABLE IF EXISTS "MA13FLWOPR";
DROP TABLE IF EXISTS "MA13FLWDEF";
DROP TABLE IF EXISTS "MA13OPRDEF";
=======
DROP TABLE IF EXISTS "MA07FLWOPR";
DROP TABLE IF EXISTS "MA07FLWDEF";
DROP TABLE IF EXISTS "MA07OPRDEF";
>>>>>>> branch 'master' of ssh://admin@192.168.20.14:29418/miracom-mes-service.git



/* Create Tables */

<<<<<<< HEAD
CREATE TABLE MA13FLWDEF
=======
CREATE TABLE MA07FLWDEF
>>>>>>> branch 'master' of ssh://admin@192.168.20.14:29418/miracom-mes-service.git
(
	FLOW_CODE varchar(20) NOT NULL,
	FLOW_DESC varchar(100),
	CREATE_USER_ID varchar(20),
	CREATE_TIME timestamp with time zone,
	UPDATE_USER_ID varchar(20),
	UPDATE_TIME timestamp with time zone,
<<<<<<< HEAD
	CONSTRAINT MA13FLWDEF_PK PRIMARY KEY (FLOW_CODE)
=======
	CONSTRAINT MA07FLWDEF_PK PRIMARY KEY (FLOW_CODE)
>>>>>>> branch 'master' of ssh://admin@192.168.20.14:29418/miracom-mes-service.git
) WITHOUT OIDS;


<<<<<<< HEAD
CREATE TABLE MA13FLWOPR
=======
CREATE TABLE MA07FLWOPR
>>>>>>> branch 'master' of ssh://admin@192.168.20.14:29418/miracom-mes-service.git
(
	FLOW_CODE varchar(20) NOT NULL,
	OPER_CODE varchar(20) NOT NULL,
	SEQ_NO numeric(2),
	CREATE_USER_ID varchar(20),
	CREATE_TIME timestamp with time zone,
	UPDATE_USER_ID varchar(20),
	UPDATE_TIME timestamp with time zone
) WITHOUT OIDS;


<<<<<<< HEAD
CREATE TABLE MA13OPRDEF
=======
CREATE TABLE MA07OPRDEF
>>>>>>> branch 'master' of ssh://admin@192.168.20.14:29418/miracom-mes-service.git
(
	OPER_CODE varchar(20) NOT NULL,
	OPER_DESC varchar(100),
	CREATE_USER_ID varchar(20),
	CREATE_TIME timestamp with time zone,
	UPDATE_USER_ID varchar(20),
	UPDATE_TIME timestamp with time zone,
<<<<<<< HEAD
	CONSTRAINT MA13OPRDEF_PK PRIMARY KEY (OPER_CODE)
=======
	CONSTRAINT MA07OPRDEF_PK PRIMARY KEY (OPER_CODE)
>>>>>>> branch 'master' of ssh://admin@192.168.20.14:29418/miracom-mes-service.git
) WITHOUT OIDS;



/* Create Foreign Keys */

<<<<<<< HEAD
ALTER TABLE MA13FLWOPR
=======
ALTER TABLE MA07FLWOPR
>>>>>>> branch 'master' of ssh://admin@192.168.20.14:29418/miracom-mes-service.git
	ADD FOREIGN KEY (FLOW_CODE)
<<<<<<< HEAD
	REFERENCES MA13FLWDEF (FLOW_CODE)
=======
	REFERENCES MA07FLWDEF (FLOW_CODE)
>>>>>>> branch 'master' of ssh://admin@192.168.20.14:29418/miracom-mes-service.git
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


<<<<<<< HEAD
ALTER TABLE MA13FLWOPR
=======
ALTER TABLE MA07FLWOPR
>>>>>>> branch 'master' of ssh://admin@192.168.20.14:29418/miracom-mes-service.git
	ADD FOREIGN KEY (OPER_CODE)
<<<<<<< HEAD
	REFERENCES MA13OPRDEF (OPER_CODE)
=======
	REFERENCES MA07OPRDEF (OPER_CODE)
>>>>>>> branch 'master' of ssh://admin@192.168.20.14:29418/miracom-mes-service.git
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;

