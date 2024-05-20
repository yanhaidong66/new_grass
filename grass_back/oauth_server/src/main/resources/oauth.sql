drop database oauth;
create database oauth;
use oauth;
create table t_user
(
    user_id            bigint auto_increment not null primary key,
    user_username      varchar(32)        not null unique ,
    user_password      varchar(32)        not null,
    user_emile         varchar(64)        not null unique ,
    user_authorities        varchar(128)       not null,
    user_create_time   bigint  not null ,
    user_modified_time bigint  not null
);
CREATE TABLE t_friends
(
    user_id bigint not null,
    friend_id bigint not null,
    friend_name varchar(32) not null,
    PRIMARY KEY (user_id,friend_id),
    CONSTRAINT fk_t_friends_user_id FOREIGN KEY (user_id) REFERENCES t_user(user_id) ON DELETE CASCADE,
    CONSTRAINT fk_t_friends_friend_id FOREIGN KEY (friend_id) REFERENCES t_user(user_id)
);

create index ind_friend_id on t_friends(friend_id);
create index ind_friend_name on t_friends(friend_name);
create index ind_user_username on t_user(user_username);
create index ind_user_email on t_user(user_emile);



