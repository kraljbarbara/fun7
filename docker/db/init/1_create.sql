create database funseven;
use funseven;

grant all privileges on funseven.* TO 'user'@'%' identified by 'password';
flush privileges;

create table USER (
    ID bigint primary key auto_increment,
    UUID varchar(32) not null unique,
    USERNAME varchar(50) not null,
    ACTIVE bit not null default b'1',
    CREATED datetime not null default CURRENT_TIMESTAMP,
    UPDATED datetime not null on update CURRENT_TIMESTAMP
);
create index USER_UUID on USER(UUID);

create table AUDIT_LOG (
    ID bigint primary key auto_increment,
    ACTION tinyint not null,
    USER_UUID varchar(32),
    CREATED datetime not null default CURRENT_TIMESTAMP
);
create index AUDIT_LOG_USER_UUID on AUDIT_LOG(USER_UUID);

insert into USER values (default, 'uuid1', 'baraba', default, default, default);
insert into USER values (default, 'uuid2', 'taliban', default, default, default);
insert into USER values (default, 'uuid3', 'martin_krpan', default, default, default);
insert into USER values (default, 'uuid4', 'kekec', default, default, default);
insert into USER values (default, 'uuid5', 'bedanec', default, default, default);
