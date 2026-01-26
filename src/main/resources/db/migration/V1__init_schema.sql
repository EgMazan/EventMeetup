CREATE TABLE users(
    id bigserial primary key,
    email varchar(255) not null unique,
    name varchar(255) not null,
    created_at timestamptz  NOT NULL DEFAULT now(),
    updated_at timestamptz  NOT NULL DEFAULT now()
);
INSERT INTO users(email, name)
VALUES ('test1mail.com','user1'),
       ('test2mail.com','user2'),
       ('test3mail.com','user3');




