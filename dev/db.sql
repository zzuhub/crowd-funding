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