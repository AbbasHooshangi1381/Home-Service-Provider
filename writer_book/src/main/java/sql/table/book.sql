CREATE TABLE IF NOT EXISTS Books (
id INTEGER PRIMARY KEY,
title VARCHAR (50),
publication_year INTEGER ,
author_id INTEGER ,
FOREIGN KEY (author_id) REFERENCES author(id)
)