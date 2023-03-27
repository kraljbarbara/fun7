create database funseven;
use funseven;

grant all privileges on funseven.* TO 'user'@'%' identified by 'password';
flush privileges;

create table USER (
    ID bigint primary key auto_increment,
    UUID varchar(32) not null unique,
    ACTIVE bit not null default b'1',
    CREATED datetime not null default NOW(),
    UPDATED datetime not null default NOW()
);

create table AUDIT_LOG (
    ID bigint primary key auto_increment,
    ACTION tinyint not null,
    ACTION_BY_USER_ID bigint not null references USER(ID),
    AFFECTED_USER_ID bigint references USER(ID),
    CREATED datetime not null default NOW()
);