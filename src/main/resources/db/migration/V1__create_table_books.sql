CREATE TABLE IF NOT EXISTS books (
    id varchar(255) PRIMARY KEY,
    isbn varchar(20) NOT NULL UNIQUE,
    title varchar(255) NOT NULL
);