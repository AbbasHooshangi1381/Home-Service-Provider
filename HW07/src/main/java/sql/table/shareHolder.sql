CREATE TABLE IF NOT EXISTS shareHolder(
    id SERIAL PRIMARY KEY ,
    name VARCHAR (50) NOT NULL,
    phoneNumber VARCHAR (50) NOT NULL ,
    NationalCode VARCHAR (50) NOT NULL UNIQUE

    )