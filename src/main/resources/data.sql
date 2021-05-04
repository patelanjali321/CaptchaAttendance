INSERT INTO ROLES(role_id, name) values(100, 'ROLE_STUDENT');
INSERT INTO ROLES(role_id, name) values(101, 'ROLE_FACULTY');
INSERT INTO ROLES(role_id, name) values(102, 'ROLE_ADMIN');

INSERT INTO USERS(user_id, username, password, name, enrolledon, enabled)
values(0,'admin', '$2a$10$ENABLED /cvgr.qmUGsg5UmwkLvMLOwGr4yJPBC', 'admin', '2021-01-01', 'true');
INSERT INTO USERS_ROLES(USER_ID, ROLE_ID) VALUES(0,102);

INSERT INTO USERS(user_id, username, password, name, enrolledon, enabled)
values(1,'faculty1', '$2a$10$ENABLED /cvgr.qmUGsg5UmwkLvMLOwGr4yJPBC', 'faculty1', '2021-01-01', 'true');
INSERT INTO USERS_ROLES(USER_ID, ROLE_ID) VALUES(1,101);

INSERT INTO USERS(user_id, username, password, name, enrolledon, enabled)
values(2,'student1', '$2a$10$ENABLED /cvgr.qmUGsg5UmwkLvMLOwGr4yJPBC', 'student1', '2021-01-01', 'true');
INSERT INTO USERS_ROLES(USER_ID, ROLE_ID) VALUES(2,100);

INSERT INTO USERS(user_id, username, password, name, enrolledon, enabled)
values(3,'student2', '$2a$10$ENABLED /cvgr.qmUGsg5UmwkLvMLOwGr4yJPBC', 'student2', '2021-01-01', 'true');
INSERT INTO USERS_ROLES(USER_ID, ROLE_ID) VALUES(3,100);

INSERT INTO ARECORD(ARECORD_ID,TIME, USER_ID) VALUES(11, '2021-05-02 08:40:00.000', 2);
INSERT INTO ARECORD(ARECORD_ID,TIME, USER_ID) VALUES(12, '2021-05-02 08:40:00.000', 2);
INSERT INTO ARECORD(ARECORD_ID,TIME, USER_ID) VALUES(13, '2021-05-02 08:40:00.000', 2);

INSERT INTO ARECORD(ARECORD_ID,TIME, USER_ID) VALUES(14, '2021-05-02 09:40:00.000', 2);
INSERT INTO ARECORD(ARECORD_ID,TIME, USER_ID) VALUES(15, '2021-05-02 09:40:00.000', 2);
INSERT INTO ARECORD(ARECORD_ID,TIME, USER_ID) VALUES(16, '2021-05-02 09:40:00.000', 2);

INSERT INTO ARECORD(ARECORD_ID,TIME, USER_ID) VALUES(17, '2021-05-02 10:40:00.000', 2);
INSERT INTO ARECORD(ARECORD_ID,TIME, USER_ID) VALUES(18, '2021-05-02 10:40:00.000', 2);

INSERT INTO ARECORD(ARECORD_ID,TIME, USER_ID) VALUES(19, '2021-05-02 11:40:00.000', 2);
INSERT INTO ARECORD(ARECORD_ID,TIME, USER_ID) VALUES(20, '2021-05-02 11:40:00.000', 2);
INSERT INTO ARECORD(ARECORD_ID,TIME, USER_ID) VALUES(21, '2021-05-02 11:40:00.000', 2);

INSERT INTO ARECORD(ARECORD_ID,TIME, USER_ID) VALUES(22, '2021-05-02 13:40:00.000', 2);
INSERT INTO ARECORD(ARECORD_ID,TIME, USER_ID) VALUES(23, '2021-05-02 13:40:00.000', 2);

INSERT INTO ARECORD(ARECORD_ID,TIME, USER_ID) VALUES(24, '2021-05-02 14:40:00.000', 2);
INSERT INTO ARECORD(ARECORD_ID,TIME, USER_ID) VALUES(25, '2021-05-02 14:40:00.000', 2);
INSERT INTO ARECORD(ARECORD_ID,TIME, USER_ID) VALUES(26, '2021-05-02 14:40:00.000', 2);

INSERT INTO ARECORD(ARECORD_ID,TIME, USER_ID) VALUES(27, '2021-05-02 15:40:00.000', 2);
INSERT INTO ARECORD(ARECORD_ID,TIME, USER_ID) VALUES(28, '2021-05-02 15:40:00.000', 2);
INSERT INTO ARECORD(ARECORD_ID,TIME, USER_ID) VALUES(29, '2021-05-02 15:40:00.000', 2);

INSERT INTO ARECORD(ARECORD_ID,TIME, USER_ID) VALUES(30, '2021-05-02 16:40:00.000', 2);
INSERT INTO ARECORD(ARECORD_ID,TIME, USER_ID) VALUES(32, '2021-05-02 16:40:00.000', 2);
INSERT INTO ARECORD(ARECORD_ID,TIME, USER_ID) VALUES(33, '2021-05-02 16:40:00.000', 2);


INSERT INTO ARECORD(ARECORD_ID,TIME, USER_ID) VALUES(34, '2021-05-02 08:40:00.000', 3);
INSERT INTO ARECORD(ARECORD_ID,TIME, USER_ID) VALUES(35, '2021-05-02 08:40:00.000', 3);

INSERT INTO ARECORD(ARECORD_ID,TIME, USER_ID) VALUES(36, '2021-05-02 09:40:00.000', 3);
INSERT INTO ARECORD(ARECORD_ID,TIME, USER_ID) VALUES(37, '2021-05-02 09:40:00.000', 3);
INSERT INTO ARECORD(ARECORD_ID,TIME, USER_ID) VALUES(38, '2021-05-02 09:40:00.000', 3);

INSERT INTO ARECORD(ARECORD_ID,TIME, USER_ID) VALUES(39, '2021-05-02 10:40:00.000', 3);
INSERT INTO ARECORD(ARECORD_ID,TIME, USER_ID) VALUES(40, '2021-05-02 10:40:00.000', 3);
INSERT INTO ARECORD(ARECORD_ID,TIME, USER_ID) VALUES(41, '2021-05-02 10:40:00.000', 3);

INSERT INTO ARECORD(ARECORD_ID,TIME, USER_ID) VALUES(42, '2021-05-02 11:40:00.000', 3);
INSERT INTO ARECORD(ARECORD_ID,TIME, USER_ID) VALUES(43, '2021-05-02 11:40:00.000', 3);

INSERT INTO ARECORD(ARECORD_ID,TIME, USER_ID) VALUES(44, '2021-05-02 13:40:00.000', 3);
INSERT INTO ARECORD(ARECORD_ID,TIME, USER_ID) VALUES(45, '2021-05-02 13:40:00.000', 3);

INSERT INTO ARECORD(ARECORD_ID,TIME, USER_ID) VALUES(46, '2021-05-02 14:40:00.000', 3);
INSERT INTO ARECORD(ARECORD_ID,TIME, USER_ID) VALUES(47, '2021-05-02 14:40:00.000', 3);
INSERT INTO ARECORD(ARECORD_ID,TIME, USER_ID) VALUES(48, '2021-05-02 14:40:00.000', 3);

INSERT INTO ARECORD(ARECORD_ID,TIME, USER_ID) VALUES(49, '2021-05-02 15:40:00.000', 3);
INSERT INTO ARECORD(ARECORD_ID,TIME, USER_ID) VALUES(50, '2021-05-02 15:40:00.000', 3);

INSERT INTO ARECORD(ARECORD_ID,TIME, USER_ID) VALUES(52, '2021-05-02 16:40:00.000', 3);
INSERT INTO ARECORD(ARECORD_ID,TIME, USER_ID) VALUES(53, '2021-05-02 16:40:00.000', 3);
INSERT INTO ARECORD(ARECORD_ID,TIME, USER_ID) VALUES(54, '2021-05-02 16:40:00.000', 3);

commit;