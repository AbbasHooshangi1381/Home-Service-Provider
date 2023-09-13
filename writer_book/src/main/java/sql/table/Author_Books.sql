CREATE TABLE Author_Books (
author_id INTEGER,
book_id INTEGER,
FOREIGN KEY (author_id) REFERENCES author(id),
FOREIGN KEY (book_id) REFERENCES Books(id)
);