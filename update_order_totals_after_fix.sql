-- ============================================
-- Update Order Totals After Consolidation
-- ============================================
-- Run this AFTER consolidating duplicate order items
-- to ensure order totals match the sum of order items
-- ============================================

-- STEP 1: Preview which orders will be updated
SELECT
    o.id as order_id,
    o.order_number,
    o.subtotal as current_subtotal,
    o.total_amount as current_total,
    COALESCE(SUM(oi.line_total), 0) as calculated_subtotal,
    COALESCE(SUM(oi.line_total), 0) + COALESCE(o.tax_amount, 0) - COALESCE(o.discount_amount, 0) as calculated_total,
    o.subtotal - COALESCE(SUM(oi.line_total), 0) as subtotal_difference,
    o.total_amount - (COALESCE(SUM(oi.line_total), 0) + COALESCE(o.tax_amount, 0) - COALESCE(o.discount_amount, 0)) as total_difference
FROM orders o
LEFT JOIN order_items oi ON oi.order_id = o.id
GROUP BY o.id, o.order_number, o.subtotal, o.total_amount, o.tax_amount, o.discount_amount
HAVING
    ABS(o.subtotal - COALESCE(SUM(oi.line_total), 0)) > 0.01
    OR ABS(o.total_amount - (COALESCE(SUM(oi.line_total), 0) + COALESCE(o.tax_amount, 0) - COALESCE(o.discount_amount, 0))) > 0.01
ORDER BY ABS(o.total_amount - (COALESCE(SUM(oi.line_total), 0) + COALESCE(o.tax_amount, 0) - COALESCE(o.discount_amount, 0))) DESC;

-- ============================================
-- STEP 2: Update order totals (UNCOMMENT TO EXECUTE)
-- ============================================

/*
BEGIN;

-- Update order subtotals and totals based on consolidated order items
UPDATE orders o
SET
    subtotal = COALESCE(item_totals.subtotal, 0),
    total_amount = COALESCE(item_totals.subtotal, 0) + COALESCE(o.tax_amount, 0) - COALESCE(o.discount_amount, 0),
    updated_at = CURRENT_TIMESTAMP
FROM (
    SELECT
        order_id,
        SUM(line_total) as subtotal
    FROM order_items
    GROUP BY order_id
) item_totals
WHERE o.id = item_totals.order_id
AND (
    ABS(o.subtotal - item_totals.subtotal) > 0.01
    OR ABS(o.total_amount - (item_totals.subtotal + COALESCE(o.tax_amount, 0) - COALESCE(o.discount_amount, 0))) > 0.01
);

-- Show how many orders were updated
SELECT COUNT(*) as orders_updated FROM orders WHERE updated_at = CURRENT_TIMESTAMP;

COMMIT;
*/

-- ============================================
-- STEP 3: Verify all orders now match their items
-- ============================================

-- Should return 0 rows if everything is correct
SELECT
    o.id as order_id,
    o.order_number,
    o.subtotal,
    o.total_amount,
    COALESCE(SUM(oi.line_total), 0) as items_subtotal,
    COALESCE(SUM(oi.line_total), 0) + COALESCE(o.tax_amount, 0) - COALESCE(o.discount_amount, 0) as calculated_total
FROM orders o
LEFT JOIN order_items oi ON oi.order_id = o.id
GROUP BY o.id, o.order_number, o.subtotal, o.total_amount, o.tax_amount, o.discount_amount
HAVING
    ABS(o.subtotal - COALESCE(SUM(oi.line_total), 0)) > 0.01
    OR ABS(o.total_amount - (COALESCE(SUM(oi.line_total), 0) + COALESCE(o.tax_amount, 0) - COALESCE(o.discount_amount, 0))) > 0.01;
