create table users(
    id bigserial primary key,
    email varchar(255) not null unique,
    name varchar(255) not null,
    created_at timestamp not null,
    updated_at timestamp not null
);
insert into users(id, email, name, created_at, updated_at)
values (1,"test@mail.com","test User",now(),now());