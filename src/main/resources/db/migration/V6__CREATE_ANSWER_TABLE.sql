create table ANSWER (
    id bigint not null,
    message varchar(300) not null,
    createdAt datetime not null,
    users_id bigint not null,
    topic_id bigint not null,
    solution int not null,
    primary key(id),
    foreign key(users_id) references USERS(id),
    foreign key(topic_id) references TOPIC(id)
);