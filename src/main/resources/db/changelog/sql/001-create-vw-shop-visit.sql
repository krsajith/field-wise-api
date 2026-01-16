CREATE OR REPLACE VIEW vw_shop_visit AS
SELECT
    shop_visits.*,
    shops.name as shop_name,
    routes.name as route_name
FROM shop_visits
JOIN shops ON shop_visits.shop_id = shops.id
JOIN routes ON shop_visits.route_id = routes.id;
