CREATE TABLE tb_products_promotions (

    product_id BIGINT NOT NULL,
    promotion_id BIGINT NOT NULL,

    PRIMARY KEY (product_id, promotion_id),

    CONSTRAINT fk_products_promotions_product
    FOREIGN KEY(product_id)
    REFERENCES tb_products(id),

    CONSTRAINT fk_products_promotions_promotion
    FOREIGN KEY(promotion_id)
    REFERENCES tb_promotions(id)
);

CREATE INDEX idx_products_promotions_promotion_id ON tb_products_promotions(promotion_id);

