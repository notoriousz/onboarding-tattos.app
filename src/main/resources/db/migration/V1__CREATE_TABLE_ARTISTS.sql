CREATE TABLE artist(
    id int auto_increment primary key,
    name varchar(255) not null,
    email varchar(255) not null unique,
    address varchar(255) not null,
    created_at DATETIME not null
);