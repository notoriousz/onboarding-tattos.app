CREATE TABLE image(
    id int auto_increment primary key,
    file_name varchar(255) not null,
    file_path varchar(255) not null,
    status varchar(100),
    created_at DATETIME not null,
    portfolio_id int not null,
    FOREIGN KEY (portfolio_id) REFERENCES portfolio(id)
);