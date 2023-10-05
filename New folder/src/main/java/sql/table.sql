CREATE TABLE IF NOT EXISTS cart
(
    id       serial primary key,
    firstname     varchar(50),
    lastname varchar(30),
    username varchar(30),
    password varchar(30)
    );

CREATE TABLE IF NOT EXISTS CART(
    id SERIAL PRIMARY KEY ,
    count varchar(50) ,
    product_Id int ,
    UserId int references users(id)




)