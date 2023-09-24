    CREATE TABLE IF NOT EXISTS product(
    id SERIAL PRIMARY KEY ,
    name VARCHAR (50) NOT NULL,
    createDate DATE NOT NULL ,
    category_id INTEGER NOT NULL,
    brand_id INTEGER NOT NULL,
    FOREIGN KEY (category_id) REFERENCES category(id),
    FOREIGN KEY (brand_id) REFERENCES brand(id)

    )