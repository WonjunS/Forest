-- 테이블 생성 --
CREATE TABLE BLACK_LIST 
(
  ID NUMBER(10, 0) NOT NULL 
, USER_ID NUMBER(10, 0) NOT NULL 
, IP_ADDRESS VARCHAR2(20 BYTE) 
, BOARD_ID NUMBER(10, 0) 
, CONSTRAINT BLOCK_LIST_PK PRIMARY KEY 
  (
    ID 
  )
  USING INDEX 
  (
      CREATE UNIQUE INDEX BLOCK_LIST_PK ON BLACK_LIST (ID ASC) 
      LOGGING 
      TABLESPACE USERS 
      PCTFREE 10 
      INITRANS 2 
      STORAGE 
      ( 
        BUFFER_POOL DEFAULT 
      ) 
      NOPARALLEL 
  )
  ENABLE 
) 
LOGGING 
TABLESPACE USERS 
PCTFREE 10 
INITRANS 1 
STORAGE 
( 
  BUFFER_POOL DEFAULT 
) 
NOCOMPRESS 
NO INMEMORY 
NOPARALLEL;

CREATE SEQUENCE BLACK_LIST_SEQ;

CREATE OR REPLACE TRIGGER BLACK_LIST_TRG 
BEFORE INSERT ON BLACK_LIST 
FOR EACH ROW 
BEGIN
  <<COLUMN_SEQUENCES>>
  BEGIN
    IF INSERTING AND :NEW.ID IS NULL THEN
      SELECT BLACK_LIST_SEQ.NEXTVAL INTO :NEW.ID FROM SYS.DUAL;
    END IF;
  END COLUMN_SEQUENCES;
END;
/


---------------------------------------

CREATE TABLE BLOG 
(
  ID NUMBER(10, 0) NOT NULL 
, POST_PRIVATE NUMBER(1, 0) 
, REPLY_PRIVATE NUMBER(1, 0) 
, CREATED_TIME TIMESTAMP(6) DEFAULT systimestamp 
, MODIFIED_TIME TIMESTAMP(6) DEFAULT systimestamp 
, USER_ID NUMBER(10, 0) 
, CONSTRAINT BLOG_PK PRIMARY KEY 
  (
    ID 
  )
  USING INDEX 
  (
      CREATE UNIQUE INDEX BLOG_PK ON BLOG (ID ASC) 
      LOGGING 
      TABLESPACE USERS 
      PCTFREE 10 
      INITRANS 2 
      STORAGE 
      ( 
        BUFFER_POOL DEFAULT 
      ) 
      NOPARALLEL 
  )
  ENABLE 
) 
LOGGING 
TABLESPACE USERS 
PCTFREE 10 
INITRANS 1 
STORAGE 
( 
  BUFFER_POOL DEFAULT 
) 
NOCOMPRESS 
NO INMEMORY 
NOPARALLEL;

CREATE SEQUENCE BLOG_SEQ;

CREATE OR REPLACE TRIGGER BLOG_TRG 
BEFORE INSERT ON BLOG 
FOR EACH ROW 
BEGIN
  <<COLUMN_SEQUENCES>>
  BEGIN
    IF INSERTING AND :NEW.ID IS NULL THEN
      SELECT BLOG_SEQ.NEXTVAL INTO :NEW.ID FROM SYS.DUAL;
    END IF;
  END COLUMN_SEQUENCES;
END;
/


------------------------------------------

CREATE TABLE BOARD 
(
  ID NUMBER(10, 0) NOT NULL 
, BOARD_CATEGORY VARCHAR2(20 CHAR) 
, BOARD_NAME VARCHAR2(20 CHAR)
, BOARD_INFO VARCHAR2(100 CHAR)
, BOARD_GRADE VARCHAR2(20 CHAR) 
, IS_APPROVED NUMBER(1, 0) 
, CREATED_TIME TIMESTAMP(6) DEFAULT systimestamp 
, MODIFIED_TIME TIMESTAMP(6) DEFAULT systimestamp 
, USER_ID NUMBER(10, 0) 
, CONSTRAINT BOARD_PK PRIMARY KEY 
  (
    ID 
  )
  USING INDEX 
  (
      CREATE UNIQUE INDEX BOARD_PK ON BOARD (ID ASC) 
      LOGGING 
      TABLESPACE USERS 
      PCTFREE 10 
      INITRANS 2 
      STORAGE 
      ( 
        BUFFER_POOL DEFAULT 
      ) 
      NOPARALLEL 
  )
  ENABLE 
) 
LOGGING 
TABLESPACE USERS 
PCTFREE 10 
INITRANS 1 
STORAGE 
( 
  BUFFER_POOL DEFAULT 
) 
NOCOMPRESS 
NO INMEMORY 
NOPARALLEL;

CREATE SEQUENCE BOARD_SEQ;

CREATE OR REPLACE TRIGGER BOARD_TRG 
BEFORE INSERT ON BOARD 
FOR EACH ROW 
BEGIN
  <<COLUMN_SEQUENCES>>
  BEGIN
    IF INSERTING AND :NEW.ID IS NULL THEN
      SELECT BOARD_SEQ.NEXTVAL INTO :NEW.ID FROM SYS.DUAL;
    END IF;
  END COLUMN_SEQUENCES;
END;
/


---------------------------------------------------------

CREATE TABLE DONATION 
(
  ID NUMBER(10, 0) NOT NULL 
, DONATOR VARCHAR2(20 CHAR)
, IMP_UID VARCHAR2(50 CHAR)
, MERCHANT_UID VARCHAR2(50 CHAR)
, CREATED_TIME TIMESTAMP
, MODIFIED_TIME TIMESTAMP
, CONSTRAINT DONATION_PK PRIMARY KEY 
  (
    ID 
  )
  USING INDEX 
  (
      CREATE UNIQUE INDEX DONATION_PK ON DONATION (ID ASC) 
      LOGGING 
      TABLESPACE USERS 
      PCTFREE 10 
      INITRANS 2 
      STORAGE 
      ( 
        BUFFER_POOL DEFAULT 
      ) 
      NOPARALLEL 
  )
  ENABLE 
) 
LOGGING 
TABLESPACE USERS 
PCTFREE 10 
INITRANS 1 
STORAGE 
( 
  BUFFER_POOL DEFAULT 
) 
NOCOMPRESS 
NO INMEMORY 
NOPARALLEL;

CREATE SEQUENCE DONATION_SEQ;

CREATE OR REPLACE TRIGGER DONATION_TRG 
BEFORE INSERT ON DONATION 
FOR EACH ROW 
BEGIN
  <<COLUMN_SEQUENCES>>
  BEGIN
    IF INSERTING AND :NEW.ID IS NULL THEN
      SELECT DONATION_SEQ.NEXTVAL INTO :NEW.ID FROM SYS.DUAL;
    END IF;
  END COLUMN_SEQUENCES;
END;
/


---------------------------------------------------------

CREATE TABLE IMAGE_FILES 
(
  ID NUMBER(10, 0) NOT NULL 
, ORIGINAL_NAME VARCHAR2(100 CHAR) 
, FILE_NAME VARCHAR2(100 CHAR) 
, EXT VARCHAR2(20 CHAR) 
, UUID VARCHAR2(100 CHAR) 
, UPLOAD_PATH VARCHAR2(250 CHAR) 
, CREATED_TIME TIMESTAMP(6) DEFAULT systimestamp 
, MODIFIED_TIME TIMESTAMP(6) DEFAULT systimestamp 
, POST_ID NUMBER(10, 0) 
, BOARD_ID NUMBER(10, 0)
, CONSTRAINT IMAGE_FILES_PK PRIMARY KEY 
  (
    ID 
  )
  USING INDEX 
  (
      CREATE UNIQUE INDEX IMAGE_FILES_PK ON IMAGE_FILES (ID ASC) 
      LOGGING 
      TABLESPACE USERS 
      PCTFREE 10 
      INITRANS 2 
      STORAGE 
      ( 
        BUFFER_POOL DEFAULT 
      ) 
      NOPARALLEL 
  )
  ENABLE 
) 
LOGGING 
TABLESPACE USERS 
PCTFREE 10 
INITRANS 1 
STORAGE 
( 
  BUFFER_POOL DEFAULT 
) 
NOCOMPRESS 
NO INMEMORY 
NOPARALLEL;

CREATE SEQUENCE IMAGE_FILES_SEQ;

CREATE OR REPLACE TRIGGER IMAGE_FILES_TRG 
BEFORE INSERT ON IMAGE_FILES 
FOR EACH ROW 
BEGIN
  <<COLUMN_SEQUENCES>>
  BEGIN
    IF INSERTING AND :NEW.ID IS NULL THEN
      SELECT IMAGE_FILES_SEQ.NEXTVAL INTO :NEW.ID FROM SYS.DUAL;
    END IF;
  END COLUMN_SEQUENCES;
END;
/


------------------------------------------------------

CREATE TABLE LIKES 
(
  ID NUMBER(10, 0) NOT NULL 
, LIKE_DISLIKE NUMBER(1, 0) 
, CREATED_TIME TIMESTAMP(6) DEFAULT systimestamp 
, MODIFIED_TIME TIMESTAMP(6) DEFAULT systimestamp 
, USER_ID NUMBER(10, 0) 
, POST_ID NUMBER(10, 0) 
, CONSTRAINT LIKES_PK PRIMARY KEY 
  (
    ID 
  )
  USING INDEX 
  (
      CREATE UNIQUE INDEX LIKES_PK ON LIKES (ID ASC) 
      LOGGING 
      TABLESPACE USERS 
      PCTFREE 10 
      INITRANS 2 
      STORAGE 
      ( 
        BUFFER_POOL DEFAULT 
      ) 
      NOPARALLEL 
  )
  ENABLE 
) 
LOGGING 
TABLESPACE USERS 
PCTFREE 10 
INITRANS 1 
STORAGE 
( 
  BUFFER_POOL DEFAULT 
) 
NOCOMPRESS 
NO INMEMORY 
NOPARALLEL;

CREATE SEQUENCE LIKES_SEQ;

CREATE OR REPLACE TRIGGER LIKES_TRG 
BEFORE INSERT ON LIKES 
FOR EACH ROW 
BEGIN
  <<COLUMN_SEQUENCES>>
  BEGIN
    IF INSERTING AND :NEW.ID IS NULL THEN
      SELECT LIKES_SEQ.NEXTVAL INTO :NEW.ID FROM SYS.DUAL;
    END IF;
  END COLUMN_SEQUENCES;
END;
/

--------------------------------------------------

CREATE TABLE POSTS 
(
  ID NUMBER(10, 0) NOT NULL 
, POST_TITLE VARCHAR2(50 CHAR) 
, POST_TYPE VARCHAR2(20 CHAR) 
, POST_NICKNAME VARCHAR2(20 CHAR) 
, POST_IP VARCHAR2(20 CHAR) 
, POST_PASSWORD VARCHAR2(100 CHAR) 
, POST_CONTENT VARCHAR2(1000 CHAR) 
, POST_VIEWS NUMBER(10, 0) 
, CREATED_TIME TIMESTAMP(6) DEFAULT systimestamp 
, MODIFIED_TIME TIMESTAMP(6) DEFAULT systimestamp 
, USER_ID NUMBER(10, 0) 
, BOARD_ID NUMBER(10, 0) 
, CONSTRAINT POSTS_PK PRIMARY KEY 
  (
    ID 
  )
  USING INDEX 
  (
      CREATE UNIQUE INDEX POSTS_PK ON POSTS (ID ASC) 
      LOGGING 
      TABLESPACE USERS 
      PCTFREE 10 
      INITRANS 2 
      STORAGE 
      ( 
        BUFFER_POOL DEFAULT 
      ) 
      NOPARALLEL 
  )
  ENABLE 
) 
LOGGING 
TABLESPACE USERS 
PCTFREE 10 
INITRANS 1 
STORAGE 
( 
  BUFFER_POOL DEFAULT 
) 
NOCOMPRESS 
NO INMEMORY 
NOPARALLEL;

-- 새 CLOB 컬럼을 추가합니다.
ALTER TABLE POSTS ADD (TMP_CONTENTS CLOB);

-- 데이터를 복사합니다.
UPDATE POSTS SET TMP_CONTENTS = POST_CONTENT;
UPDATE POSTS SET POST_CONTENT = NULL;
COMMIT;

-- 기존 컬럼을 삭제합니다.
ALTER TABLE POSTS DROP COLUMN POST_CONTENT;

-- 새로 추가한 임시 컬럼의 이름을 변경합니다.
ALTER TABLE POSTS RENAME COLUMN TMP_CONTENTS TO POST_CONTENT;

CREATE SEQUENCE POSTS_SEQ;

CREATE OR REPLACE TRIGGER POSTS_TRG 
BEFORE INSERT ON POSTS 
FOR EACH ROW 
BEGIN
  <<COLUMN_SEQUENCES>>
  BEGIN
    IF INSERTING AND :NEW.ID IS NULL THEN
      SELECT POSTS_SEQ.NEXTVAL INTO :NEW.ID FROM SYS.DUAL;
    END IF;
  END COLUMN_SEQUENCES;
END;
/


--------------------------------------------------

CREATE TABLE RE_REPLIES 
(
  ID NUMBER(10, 0) NOT NULL 
, REPLY_TEXT2 VARCHAR2(100 CHAR) 
, REPLY_NICKNAME2 VARCHAR2(20 CHAR) 
, REPLY_IP2 VARCHAR2(20 CHAR) 
, REPLY_PASSWORD2 VARCHAR2(100 CHAR) 
, CREATED_TIME TIMESTAMP(6) DEFAULT systimestamp 
, MODIFIED_TIME TIMESTAMP(6) DEFAULT systimestamp 
, REPLY_ID NUMBER(10, 0) 
, USER_ID NUMBER(10, 0) 
, CONSTRAINT RE_REPLIES_PK PRIMARY KEY 
  (
    ID 
  )
  USING INDEX 
  (
      CREATE UNIQUE INDEX RE_REPLIES_PK ON RE_REPLIES (ID ASC) 
      LOGGING 
      TABLESPACE USERS 
      PCTFREE 10 
      INITRANS 2 
      STORAGE 
      ( 
        BUFFER_POOL DEFAULT 
      ) 
      NOPARALLEL 
  )
  ENABLE 
) 
LOGGING 
TABLESPACE USERS 
PCTFREE 10 
INITRANS 1 
STORAGE 
( 
  BUFFER_POOL DEFAULT 
) 
NOCOMPRESS 
NO INMEMORY 
NOPARALLEL;

CREATE SEQUENCE RE_REPLIES_SEQ;

CREATE OR REPLACE TRIGGER RE_REPLIES_TRG 
BEFORE INSERT ON RE_REPLIES 
FOR EACH ROW 
BEGIN
  <<COLUMN_SEQUENCES>>
  BEGIN
    IF INSERTING AND :NEW.ID IS NULL THEN
      SELECT RE_REPLIES_SEQ.NEXTVAL INTO :NEW.ID FROM SYS.DUAL;
    END IF;
  END COLUMN_SEQUENCES;
END;
/

--------------------------------------------------

CREATE TABLE REPLIES 
(
  ID NUMBER(10, 0) NOT NULL 
, REPLY_TEXT VARCHAR2(100 CHAR) 
, REPLY_NICKNAME VARCHAR2(20 CHAR) 
, REPLY_IP VARCHAR2(20 CHAR) 
, REPLY_PASSWORD VARCHAR2(100 CHAR) 
, CREATED_TIME TIMESTAMP(6) DEFAULT systimestamp 
, MODIFIED_TIME TIMESTAMP(6) DEFAULT systimestamp 
, USER_ID NUMBER(10, 0) 
, POST_ID NUMBER(10, 0) 
, CONSTRAINT REPLIES_PK PRIMARY KEY 
  (
    ID 
  )
  USING INDEX 
  (
      CREATE UNIQUE INDEX REPLIES_PK ON REPLIES (ID ASC) 
      LOGGING 
      TABLESPACE USERS 
      PCTFREE 10 
      INITRANS 2 
      STORAGE 
      ( 
        BUFFER_POOL DEFAULT 
      ) 
      NOPARALLEL 
  )
  ENABLE 
) 
LOGGING 
TABLESPACE USERS 
PCTFREE 10 
INITRANS 1 
STORAGE 
( 
  BUFFER_POOL DEFAULT 
) 
NOCOMPRESS 
NO INMEMORY 
NOPARALLEL;


CREATE SEQUENCE REPLIES_SEQ;

CREATE OR REPLACE TRIGGER REPLIES_TRG 
BEFORE INSERT ON REPLIES 
FOR EACH ROW 
BEGIN
  <<COLUMN_SEQUENCES>>
  BEGIN
    IF INSERTING AND :NEW.ID IS NULL THEN
      SELECT REPLIES_SEQ.NEXTVAL INTO :NEW.ID FROM SYS.DUAL;
    END IF;
  END COLUMN_SEQUENCES;
END;
/

--------------------------------------------------

CREATE TABLE USERS 
(
  ID NUMBER(10, 0) NOT NULL 
, LOGIN_ID VARCHAR2(20 CHAR) 
, NICKNAME VARCHAR2(20 CHAR) NOT NULL 
, EMAIL VARCHAR2(50 CHAR) 
, PASSWORD VARCHAR2(100 CHAR) 
, ROLE VARCHAR2(20 CHAR) 
, CREATED_TIME TIMESTAMP(6) DEFAULT systimestamp 
, MODIFIED_TIME TIMESTAMP(6) DEFAULT systimestamp 
, IMAGE_FILE_ID NUMBER(10, 0) 
, CONSTRAINT USERS_PK PRIMARY KEY 
  (
    ID 
  )
  USING INDEX 
  (
      CREATE UNIQUE INDEX USERS_PK ON USERS (ID ASC) 
      LOGGING 
      TABLESPACE USERS 
      PCTFREE 10 
      INITRANS 2 
      STORAGE 
      ( 
        BUFFER_POOL DEFAULT 
      ) 
      NOPARALLEL 
  )
  ENABLE 
) 
LOGGING 
TABLESPACE USERS 
PCTFREE 10 
INITRANS 1 
STORAGE 
( 
  BUFFER_POOL DEFAULT 
) 
NOCOMPRESS 
NO INMEMORY 
NOPARALLEL;


CREATE SEQUENCE USERS_SEQ;

CREATE OR REPLACE TRIGGER USERS_TRG 
BEFORE INSERT ON USERS 
FOR EACH ROW 
BEGIN
  <<COLUMN_SEQUENCES>>
  BEGIN
    IF INSERTING AND :NEW.ID IS NULL THEN
      SELECT USERS_SEQ.NEXTVAL INTO :NEW.ID FROM SYS.DUAL;
    END IF;
  END COLUMN_SEQUENCES;
END;
/

--------------------------------------------------

CREATE TABLE CHAT_MESSAGES 
(
  ID NUMBER(10, 0) NOT NULL 
, CONTENT VARCHAR2(500 CHAR) 
, SENDER_ID NUMBER(10, 0) 
, ROOM_ID NUMBER(10, 0) 
, CREATED_TIME TIMESTAMP(6) DEFAULT systimestamp 
, MODIFIED_TIME TIMESTAMP(6) DEFAULT systimestamp 
, CONSTRAINT CHAT_MESSAGES_PK PRIMARY KEY 
  (
    ID 
  )
  USING INDEX 
  (
      CREATE UNIQUE INDEX CHAT_MESSAGES_PK ON CHAT_MESSAGES (ID ASC) 
      LOGGING 
      TABLESPACE USERS 
      PCTFREE 10 
      INITRANS 2 
      STORAGE 
      ( 
        INITIAL 65536 
        NEXT 1048576 
        MINEXTENTS 1 
        MAXEXTENTS UNLIMITED 
        BUFFER_POOL DEFAULT 
      ) 
      NOPARALLEL 
  )
  ENABLE 
) 
LOGGING 
TABLESPACE USERS 
PCTFREE 10 
INITRANS 1 
STORAGE 
( 
  INITIAL 65536 
  NEXT 1048576 
  MINEXTENTS 1 
  MAXEXTENTS UNLIMITED 
  BUFFER_POOL DEFAULT 
) 
NOCOMPRESS 
NO INMEMORY 
NOPARALLEL;

CREATE SEQUENCE CHAT_MESSAGES_SEQ;

CREATE OR REPLACE TRIGGER CHAT_MESSAGES_TRG 
BEFORE INSERT ON CHAT_MESSAGES 
FOR EACH ROW 
BEGIN
  <<COLUMN_SEQUENCES>>
  BEGIN
    IF INSERTING AND :NEW.ID IS NULL THEN
      SELECT CHAT_MESSAGES_SEQ.NEXTVAL INTO :NEW.ID FROM SYS.DUAL;
    END IF;
  END COLUMN_SEQUENCES;
END;
/


--------------------------------------------------

CREATE TABLE CHAT_ROOMS 
(
  ID NUMBER(10, 0) NOT NULL 
, NAME VARCHAR2(30 BYTE) 
, CREATOR_ID NUMBER(10, 0) 
, CREATED_TIME TIMESTAMP(6) DEFAULT systimestamp 
, MODIFIED_TIME TIMESTAMP(6) DEFAULT systimestamp 
, CONSTRAINT CHAT_ROOMS_PK PRIMARY KEY 
  (
    ID 
  )
  USING INDEX 
  (
      CREATE UNIQUE INDEX CHAT_ROOMS_PK ON CHAT_ROOMS (ID ASC) 
      LOGGING 
      TABLESPACE USERS 
      PCTFREE 10 
      INITRANS 2 
      STORAGE 
      ( 
        INITIAL 65536 
        NEXT 1048576 
        MINEXTENTS 1 
        MAXEXTENTS UNLIMITED 
        BUFFER_POOL DEFAULT 
      ) 
      NOPARALLEL 
  )
  ENABLE 
) 
LOGGING 
TABLESPACE USERS 
PCTFREE 10 
INITRANS 1 
STORAGE 
( 
  INITIAL 65536 
  NEXT 1048576 
  MINEXTENTS 1 
  MAXEXTENTS UNLIMITED 
  BUFFER_POOL DEFAULT 
) 
NOCOMPRESS 
NO INMEMORY 
NOPARALLEL;

CREATE SEQUENCE CHAT_ROOMS_SEQ;

CREATE OR REPLACE TRIGGER CHAT_ROOMS_TRG 
BEFORE INSERT ON CHAT_ROOMS 
FOR EACH ROW 
BEGIN
  <<COLUMN_SEQUENCES>>
  BEGIN
    IF INSERTING AND :NEW.ID IS NULL THEN
      SELECT CHAT_ROOMS_SEQ.NEXTVAL INTO :NEW.ID FROM SYS.DUAL;
    END IF;
  END COLUMN_SEQUENCES;
END;
/


--------------------------------------------------

-- USERS 테이블 외래키 설정 --
ALTER TABLE USERS
ADD CONSTRAINT USERS_FK_FILEID FOREIGN KEY(IMAGE_FILE_ID) REFERENCES IMAGE_FILES(ID);

-- BOARD 테이블 외래키 설정 --
ALTER TABLE BOARD
ADD CONSTRAINT BOARD_FK_USERID FOREIGN KEY(USER_ID) REFERENCES USERS(ID);

-- POSTS 테이블 외래키 설정 --
ALTER TABLE POSTS
ADD CONSTRAINT POSTS_FK_USERID FOREIGN KEY(USER_ID) REFERENCES USERS(ID);

ALTER TABLE POSTS
ADD CONSTRAINT POSTS_FK_BOARDID FOREIGN KEY(BOARD_ID) REFERENCES BOARD(ID);

-- BLOG 테이블 외래키 설정 --
ALTER TABLE BLOG
ADD CONSTRAINT BLOG_FK_USERID FOREIGN KEY(USER_ID) REFERENCES USERS(ID);

-- LIKES 테이블 외래키 설정 --
ALTER TABLE LIKES
ADD CONSTRAINT LIKES_FK_USERID FOREIGN KEY(USER_ID) REFERENCES USERS(ID);

ALTER TABLE LIKES
ADD CONSTRAINT LIKES_FK_POSTID FOREIGN KEY(POST_ID) REFERENCES POSTS(ID);

-- REPLIES 테이블 외래키 설정 --
ALTER TABLE REPLIES
ADD CONSTRAINT REPLIES_FK_POSTID FOREIGN KEY(POST_ID) REFERENCES POSTS(ID);

-- RE_REPLIES 테이블 외래키 설정 --
ALTER TABLE RE_REPLIES
ADD CONSTRAINT REREPLIES_FK_REPLYID FOREIGN KEY(REPLY_ID) REFERENCES REPLIES(ID);
