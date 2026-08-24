CREATE TABLE user_table (
    id BIGINT IDENTITY(1,1) PRIMARY KEY,
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    email VARCHAR(255),
    phone VARCHAR(50),
    role VARCHAR(50),
    address_id BIGINT,
    created_at DATETIME2,
    updated_at DATETIME2,

    CONSTRAINT fk_user_address
        FOREIGN KEY (address_id)
        REFERENCES address_table(id)
);
