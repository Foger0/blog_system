create database if not exists blog_system charset utf8;
use blog_system;

drop table if exists user;
drop table if exists blog;

create table blog(
blogId int primary key auto_increment,
title varchar(128),
content varchar(4096),
postTime datetime,
userId int
);

create table user(
     userId int primary key auto_increment,
     username varchar(20) UNIQUE,
     password varchar(20)
);
insert into blog values(1,'hello','hello everyone',now(),1);

insert into blog values(2,'hello2','hello everyone',now(),1);