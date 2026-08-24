CREATE TABLE address_table (
    id BIGINT IDENTITY(1,1) PRIMARY KEY,
    street VARCHAR(255),
    city VARCHAR(255),
    state VARCHAR(255),
    postal_code VARCHAR(50),
    country VARCHAR(100),
    zip_code VARCHAR(50)
);
