create table TOPIC (
    id bigint not null,
    title varchar(50) not null,
    message varchar(300) not null,
    createdAt datetime not null,
    status varchar(20) not null,
    training_id bigint not null,
    users_id bigint not null,
    primary key(id),
    foreign key(training_id) references TRAINING(id),
    foreign key(users_id) references USERS(id)
);