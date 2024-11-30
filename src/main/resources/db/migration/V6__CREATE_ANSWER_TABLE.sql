create table ANSWER (
    id bigint not null auto_increment,
    message varchar(300) not null,
    created_at datetime not null,
    user_id bigint not null,
    topic_id bigint not null,
    solution int not null,
    primary key(id),
    foreign key(user_id) references USERS(id),
    foreign key(topic_id) references TOPIC(id)
);