CREATE TABLE IF NOT EXISTS author
(
    id        SERIAL PRIMARY KEY,
    firstname VARCHAR(50) NOT NULL,
    lastname  VARCHAR(50) NOT NULL,
    age       INTEGER     NOT NULL
)