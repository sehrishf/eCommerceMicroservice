CREATE TABLE cart_table (
    id BIGINT IDENTITY(1,1) PRIMARY KEY,
    user_id BIGINT,

    CONSTRAINT fk_cart_user
        FOREIGN KEY (user_id)
        REFERENCES user_table(id)
);
