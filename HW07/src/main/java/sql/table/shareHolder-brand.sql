CREATE TABLE IF NOT EXISTS shareHoldebrand
(
shareHolder_id INTEGER REFERENCES shareHolder(id),
brand_id INTEGER REFERENCES brand(id),
    PRIMARY KEY (shareHolder_id,brand_id)

)