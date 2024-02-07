CREATE TABLE users (
    id int PRIMARY KEY,
    balance INT
);

CREATE TABLE base_operation (
    id_operation int PRIMARY KEY,
    id_user INT REFERENCES users(id),
    type_operation INT,
    amount INT,
    timeOperation DATE
);

