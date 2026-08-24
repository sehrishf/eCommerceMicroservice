CREATE TABLE product_table (
    id BIGINT IDENTITY(1,1) PRIMARY KEY,
    name VARCHAR(255),
    description VARCHAR(MAX),
    price DECIMAL(10,2),
    stock INT
);
