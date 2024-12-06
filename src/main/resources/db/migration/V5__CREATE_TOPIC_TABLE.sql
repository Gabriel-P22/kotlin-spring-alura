create table topic (
    id bigint auto_increment not null ,
    title varchar(50) not null,
    message varchar(300) not null,
    created_at datetime,
    status varchar(20),
    training_id bigint not null,
    user_id bigint not null,
    primary key(id),
    foreign key(training_id) references training(id),
    foreign key(user_id) references users(id)
);