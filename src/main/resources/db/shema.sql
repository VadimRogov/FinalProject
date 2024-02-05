CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    balance INT
);

CREATE TABLE base_operation (
    id_operation SERIAL PRIMARY KEY,
    id_user INT REFERENCES users(id),
    type_operation INT,
    amount INT
);

