CREATE TABLE IF NOT EXISTS users(
    id smallint PRIMARY KEY UNIQUE NOT NULL,
    username varchar(50) NOT NULL UNIQUE,
    password varchar(50) NOT NULL,
    role varchar(50) NOT NULL
);