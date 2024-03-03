CREATE TABLE factory (
    id INT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    city VARCHAR(50) NOT NULL
);

CREATE TABLE product(
    id INT PRIMARY KEY,
    name VARCHAR NOT NULL ,
    quantity INT NOT NULL
)