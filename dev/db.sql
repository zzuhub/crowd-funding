#创建权限模块数据表
#删除权限模块数据表
DROP DATABASE IF EXISTS db_wcj   ;
CREATE DATABASE db_wcj   ;
USE db_wcj   ;
DROP TABLE IF EXISTS t_permission ;
CREATE TABLE t_permission(
    permission_id  INT(12)   AUTO_INCREMENT,
    name           VARCHAR(100)   ,
    note           VARCHAR(100)    ,
    CONSTRAINT pk_permission_id PRIMARY KEY(permission_id)
) ;
INSERT INTO t_permission(name,note)VALUES('User:SELECT','用户查询');
INSERT INTO t_permission(name,note)VALUES('User:INSERT','用户增加');
INSERT INTO t_permission(name,note)VALUES('User:UPDATE','用户修改');
INSERT INTO t_permission(name,note)VALUES('User:DELETE','用户删除');
COMMIT ;
SELECT * FROM t_permission   ;

DROP TABLE IF EXISTS t_role   ;
CREATE TABLE t_role(
    role_id    INT(12)   AUTO_INCREMENT,
    name       VARCHAR(100)   ,
    note       VARCHAR(100)   ,
    CONSTRAINT pk_role_id PRIMARY KEY(role_id)
)  ;
INSERT INTO t_role(name,note)VALUES('admin','超级管理员') ;
INSERT INTO t_role(name,note)VALUES('boss','总裁') ;
INSERT INTO t_role(name,note)VALUES('manager','经理') ;
INSERT INTO t_role(name,note)VALUES('clerk','业务员') ;
INSERT INTO t_role(name,note)VALUES('user','用户') ;
SELECT * FROM t_role ;

DROP TABLE IF EXISTS t_user  ;
CREATE TABLE t_user(
     user_id  INT(12)  AUTO_INCREMENT ,
     name     VARCHAR(100)   ,
     password VARCHAR(50)    ,
     last_login DATE   ,
     status    INT(2)   ,
     login_err   INT(2)   ,
     create_time TIMESTAMP  ,
     last_ip   VARCHAR(100)  ,
     CONSTRAINT pk_user_id PRIMARY KEY(user_id)
)  ;
INSERT INTO t_user(name,password,last_login,status,login_err,create_time,last_ip)
            VALUES('wcj','wcj1234',NULL,1,0,NOW(),'125.41.71.33')   ;
COMMIT  ;
SELECT * FROM t_user ;       
     
DROP TABLE IF EXISTS t_role_permission  ;
CREATE TABLE t_role_permission(
     id  INT(12)  AUTO_INCREMENT   ,
     role_id  INT(12)   ,
     permission_id INT(12) ,
     CONSTRAINT pk_role_permission_id PRIMARY KEY(id)  ,
     CONSTRAINT fk_rp_role_id  FOREIGN KEY(role_id) REFERENCES t_role(role_id) ,
     CONSTRAINT fk_rp_permission_id FOREIGN KEY(permission_id) REFERENCES t_permission(permission_id)
)   ;
INSERT INTO t_role_permission(role_id,permission_id)VALUES(2,1);
INSERT INTO t_role_permission(role_id,permission_id)VALUES(2,2);
INSERT INTO t_role_permission(role_id,permission_id)VALUES(2,3);
INSERT INTO t_role_permission(role_id,permission_id)VALUES(2,4);

INSERT INTO t_role_permission(role_id,permission_id)VALUES(4,1);
INSERT INTO t_role_permission(role_id,permission_id)VALUES(4,2);

SELECT * FROM t_role_permission  ;

DROP TABLE IF EXISTS t_user_role   ;
CREATE TABLE t_user_role(
       id        INT(12)    AUTO_INCREMENT,
       user_id   INT(12)     ,
       role_id   INT(12)     ,
       CONSTRAINT pk_user_role_id PRIMARY KEY(id)
)   ;
INSERT INTO t_user_role(user_id,role_id)VALUES(1,1) ;
INSERT INTO t_user_role(user_id,role_id)VALUES(1,2) ;
INSERT INTO t_user_role(user_id,role_id)VALUES(1,3) ;
INSERT INTO t_user_role(user_id,role_id)VALUES(1,4) ;
INSERT INTO t_user_role(user_id,role_id)VALUES(1,5) ;
SELECT * FROM t_user_role ;















