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
     last_login TIMESTAMP   ,
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

DROP TABLE IF EXISTS t_category ;
CREATE TABLE t_category(
    id   INT(12)  AUTO_INCREMENT,
    name VARCHAR(100)  ,
    CONSTRAINT pk_category_id PRIMARY KEY(id)
) ;
INSERT INTO t_category(name)VALUES('测试类别') ;
SELECT * FROM t_category ;

DROP TABLE IF EXISTS t_project ;
CREATE TABLE t_project(
      project_id INT(12) AUTO_INCREMENT  ,
      title      VARCHAR(100)   ,
      category_id INT(12)    ,
      user_id    INT(12)     ,
      expect_money DOUBLE(12,2) ,
      real_money  DOUBLE(12,2) ,
      note        VARCHAR(20000)  ,
      tel         VARCHAR(12)   ,
      status      INT(2) ,
      CONSTRAINT pk_project_id PRIMARY KEY(project_id) ,
      CONSTRAINT fk_catagory_id FOREIGN KEY(category_id) REFERENCES t_category(id) ,
      CONSTRAINT fk_user_id FOREIGN KEY(user_id) REFERENCES t_user(user_id)
)   ;
INSERT INTO t_project(title,category_id,user_id,expect_money,real_money,note,tel,status)
            VALUES('HelloWorld-Title',1,1,9999.99,8888.88,'HelloWorld','110',1)  ;
SELECT * FROM t_project ;            



DROP TABLE IF EXISTS t_approve ;
CREATE TABLE t_approve(
    approve_id INT(12) AUTO_INCREMENT  ,
    project_id INT(12)  ,
    tel        VARCHAR(12)  ,
    name       VARCHAR(100)  ,
    money      DOUBLE(12,2)  ,
    approve_time  DATE   ,
    CONSTRAINT pk_approve_id PRIMARY KEY(approve_id)   ,
    CONSTRAINT FOREIGN KEY(project_id) REFERENCES t_project(project_id)
);
INSERT INTO t_approve(project_id,tel,name,money,approve_time)
            VALUES(1,'119','Jerry',0.01,NOW())   ;
COMMIT ;
SELECT * FROM t_approve ;

DROP TABLE IF EXISTS t_photo  ;
CREATE TABLE t_photo(
    photo_id INT(12) AUTO_INCREMENT ,
    project_id INT(12)   ,
    name  VARCHAR(100)   ,
    CONSTRAINT pk_photo_id PRIMARY KEY(photo_id)  ,
    CONSTRAINT fk_project_id FOREIGN KEY(project_id) REFERENCES  t_project(project_id)
)  ;
INSERT INTO t_photo(project_id,name)VALUES(1,'wcj.jpg');
COMMIT ;
SELECT * FROM t_photo ;

DROP TABLE IF EXISTS t_comment ;
CREATE TABLE t_comment(
        comment_id INT(12) AUTO_INCREMENT ,
        user_id INT(12)   ,
        project_id INT(12)  ,
        content VARCHAR(20000) ,
        CONSTRAINT pk_comment_id PRIMARY KEY(comment_id)  ,
        CONSTRAINT fk_user_id_c  FOREIGN KEY(user_id) REFERENCES t_user(user_id)  ,
        CONSTRAINT fk_project_id_c FOREIGN KEY(project_id) REFERENCES t_project(project_id)
)  ;
INSERT INTO t_comment(user_id,project_id,content)VALUES(1,1,'逗比键') ;
COMMIT ;
SELECT * FROM t_comment ;


DROP TABLE IF EXISTS t_log   ;
CREATE TABLE t_log(
   id  INT(12)  AUTO_INCREMENT ,
   user_id INT(12)  ,
   operate VARCHAR(2000),
   CONSTRAINT pk_log_id PRIMARY KEY(id)  ,
   CONSTRAINT fk_user_id_l FOREIGN KEY(user_id) REFERENCES t_user(user_id)
)  ;
INSERT INTO t_log(user_id,operate)VALUES(1,'操作') ;
COMMIT ;
SELECT * FROM t_log ;

























