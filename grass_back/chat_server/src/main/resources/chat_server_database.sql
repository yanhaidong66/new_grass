drop database db_chat_server;
create database db_chat_server;
use db_chat_server;

create table t_conversation(
    conversation_id bigint  primary key auto_increment,
    conversation_sender_id bigint  not null ,
    conversation_receiver_id bigint not null,
    conversation_create_time bigint not null ,
    conversation_modify_time bigint not null ,
    index(conversation_sender_id) ,
    index(conversation_receiver_id),
    index(conversation_modify_time)

);
create table t_message(
    message_id bigint  primary key auto_increment,
    message_conversation_id bigint  not null ,
    message_create_time bigint not null ,
    message_sender_id bigint  not null ,
    message_receiver_id bigint not null ,
    message_content varchar(512) not null ,
    message_read tinyint not null ,
    foreign key (message_conversation_id)
                      references t_conversation(conversation_id)
                      on delete cascade,
    index (message_conversation_id),
    index(message_create_time)
);


