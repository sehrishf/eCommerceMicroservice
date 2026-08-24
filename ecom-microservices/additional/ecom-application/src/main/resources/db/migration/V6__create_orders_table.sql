CREATE TABLE orders_table (
    id BIGINT IDENTITY(1,1) PRIMARY KEY,
    user_id BIGINT,
    total_amount DECIMAL(10,2),

    CONSTRAINT fk_order_user
        FOREIGN KEY (user_id)
        REFERENCES user_table(id)
);
