CREATE TABLE portfolio(
    id int auto_increment primary key,
    created_at DATETIME not null,
    type varchar(100),
    artist_id int not null,
    FOREIGN KEY (artist_id) REFERENCES artist(id)
);