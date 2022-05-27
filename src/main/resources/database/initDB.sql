/*создание таблиц*/
CREATE TABLE IF NOT EXISTS users
(
    id  INTEGER  PRIMARY KEY ,
    name VARCHAR(254),
    surname VARCHAR(254),
    patronymic VARCHAR(254),
    age INTEGER,
    salary INTEGER,
    mail VARCHAR(254),
    workplace VARCHAR(254)
);