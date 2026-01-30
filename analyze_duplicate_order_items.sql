-- ============================================
-- Analyze Duplicate Order Items
-- ============================================
-- Run this first to understand the extent of duplicate data
-- ============================================

-- 1. Count total duplicates
SELECT
    COUNT(*) as total_duplicate_groups,
    SUM(cnt - 1) as total_extra_records,
    SUM(cnt) as total_affected_records
FROM (
    SELECT order_id, product_id, COUNT(*) as cnt
    FROM order_items
    GROUP BY order_id, product_id
    HAVING COUNT(*) > 1
) sub;

-- 2. Show top 20 duplicate cases
SELECT
    oi.order_id,
    oi.product_id,
    COUNT(*) as duplicate_count,
    STRING_AGG(oi.id::text || ' (qty: ' || oi.quantity || ')', ', ' ORDER BY oi.id) as records_detail,
    SUM(oi.quantity) as total_quantity_if_merged,
    SUM(oi.line_total) as total_line_total
FROM order_items oi
GROUP BY oi.order_id, oi.product_id
HAVING COUNT(*) > 1
ORDER BY COUNT(*) DESC, oi.order_id, oi.product_id
LIMIT 20;

-- 3. Show duplicate distribution (how many have 2, 3, 4+ duplicates)
SELECT
    cnt as number_of_duplicates,
    COUNT(*) as how_many_groups
FROM (
    SELECT order_id, product_id, COUNT(*) as cnt
    FROM order_items
    GROUP BY order_id, product_id
    HAVING COUNT(*) > 1
) sub
GROUP BY cnt
ORDER BY cnt;

-- 4. Show affected orders (orders with duplicate items)
SELECT
    o.id as order_id,
    o.order_number,
    o.status,
    o.order_date,
    COUNT(DISTINCT oi.product_id) as unique_products,
    COUNT(oi.id) as total_item_records,
    COUNT(oi.id) - COUNT(DISTINCT oi.product_id) as extra_duplicate_records
FROM orders o
JOIN order_items oi ON oi.order_id = o.id
GROUP BY o.id, o.order_number, o.status, o.order_date
HAVING COUNT(oi.id) > COUNT(DISTINCT oi.product_id)
ORDER BY extra_duplicate_records DESC
LIMIT 20;

-- 5. Check if order totals will be affected
-- This shows if consolidating duplicates will require order total updates
SELECT
    oi.order_id,
    o.total_amount as current_order_total,
    SUM(oi.line_total) as current_items_total,
    SUM(calculated_new_total) as items_total_after_consolidation,
    SUM(calculated_new_total) - SUM(oi.line_total) as difference
FROM order_items oi
JOIN orders o ON o.id = oi.order_id
JOIN (
    SELECT
        order_id,
        product_id,
        SUM(quantity) * MAX(unit_price) as calculated_new_total
    FROM order_items
    GROUP BY order_id, product_id
) consolidated ON consolidated.order_id = oi.order_id AND consolidated.product_id = oi.product_id
WHERE oi.order_id IN (
    SELECT order_id
    FROM order_items
    GROUP BY order_id, product_id
    HAVING COUNT(*) > 1
)
GROUP BY oi.order_id, o.total_amount
HAVING SUM(oi.line_total) != SUM(calculated_new_total)
ORDER BY difference DESC;

-- 6. Sample of duplicate records (actual data for review)
SELECT
    oi.id,
    oi.order_id,
    oi.product_id,
    oi.quantity,
    oi.unit_price,
    oi.line_total,
    oi.created_at,
    oi.updated_at
FROM order_items oi
WHERE (oi.order_id, oi.product_id) IN (
    SELECT order_id, product_id
    FROM order_items
    GROUP BY order_id, product_id
    HAVING COUNT(*) > 1
)
ORDER BY oi.order_id, oi.product_id, oi.id
LIMIT 50;
