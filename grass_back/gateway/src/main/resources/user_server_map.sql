create database db_chat;
use chat;
drop table t_chat_gateway_user_server_mapping;
create table t_chat_gateway_user_server_mapping
(
    user_id            bigint not null primary key,
    user_username      varchar(32)  not null unique ,
    server_id          int not null 
);
create index ind_username on t_chat_gateway_user_server_mapping(user_username);
create index ind_email on t_chat_gateway_user_server_mapping(server_id);



