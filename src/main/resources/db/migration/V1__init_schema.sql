CREATE TABLE users(
    id bigserial primary key,
    email varchar(255) not null unique,
    name varchar(255) not null,
    created_at timestamp  NOT NULL DEFAULT now(),
    updated_at timestamp  NOT NULL DEFAULT now()
);
INSERT INTO users(id, email, name, created_at, updated_at)
VALUES (1,'test1mail.com','user1',now(),now()),
       (2,'test2mail.com','user2',now(),now()),
       (3,'test3mail.com','user3',now(),now());




