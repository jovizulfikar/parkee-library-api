CREATE TABLE IF NOT EXISTS book_lending (
    id varchar(255) PRIMARY KEY,
    book_id varchar(255) NOT NULL,
    member_id varchar(255) NOT NULL,
    due_date DATE NOT NULL,
    return_date DATE,
    FOREIGN KEY (book_id) REFERENCES books(id),
    FOREIGN KEY (member_id) REFERENCES members(id)
);