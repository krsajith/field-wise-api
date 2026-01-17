CREATE OR REPLACE VIEW v_orders AS
SELECT
    s.id,
    s.created_at,
    s.user_id,
    s.quantity,
    s.unit_price,
    s.order_date,
    p.name AS product,
    s1.name AS shop_name
FROM simple_orders s
INNER JOIN products p ON s.product_id = p.id
INNER JOIN shops s1 ON s.shop_id = s1.id;
