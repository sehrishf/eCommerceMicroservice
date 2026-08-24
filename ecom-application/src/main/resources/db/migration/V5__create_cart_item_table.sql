CREATE TABLE cart_item_table (
    id BIGINT IDENTITY(1,1) PRIMARY KEY,
    product_id BIGINT,
    cart_id BIGINT,
    order_id BIGINT,
    quantity INT,

    CONSTRAINT fk_cart_item_product
        FOREIGN KEY (product_id)
        REFERENCES product_table(id),

    CONSTRAINT fk_cart_item_cart
        FOREIGN KEY (cart_id)
        REFERENCES cart_table(id),

    CONSTRAINT fk_cart_item_order
        FOREIGN KEY (order_id)
        REFERENCES orders_table(id)
);
