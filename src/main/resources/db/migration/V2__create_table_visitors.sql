CREATE TABLE IF NOT EXISTS members (
    id varchar(255) PRIMARY KEY,
    id_card_number varchar(20) NOT NULL UNIQUE,
    name varchar(50) NOT NULL
);