-- ============================================
-- Fix Duplicate Order Items
-- ============================================
-- This script consolidates duplicate order items
-- (same order_id + product_id) by summing quantities
-- and keeping only one record per combination
-- ============================================

-- STEP 1: View duplicates before fixing (for verification)
-- Run this first to see what will be consolidated
SELECT
    order_id,
    product_id,
    COUNT(*) as duplicate_count,
    STRING_AGG(id::text, ', ' ORDER BY id) as item_ids,
    SUM(quantity) as total_quantity,
    AVG(unit_price) as avg_unit_price
FROM order_items
GROUP BY order_id, product_id
HAVING COUNT(*) > 1
ORDER BY order_id, product_id;

-- ============================================
-- STEP 2: Consolidate duplicates
-- ============================================

BEGIN;

-- Create a temporary table with consolidated data
CREATE TEMP TABLE consolidated_items AS
SELECT
    MIN(id) as keep_id,  -- Keep the first record
    order_id,
    product_id,
    SUM(quantity) as total_quantity,
    SUM(supplied_quantity) as total_supplied_quantity,
    MAX(unit_price) as unit_price,  -- Use the most recent unit price
    MAX(tax_rate) as tax_rate,
    SUM(tax_amount) as total_tax_amount,
    MAX(discount_percentage) as discount_percentage,
    SUM(discount_amount) as total_discount_amount,
    SUM(quantity * unit_price) as calculated_line_total,  -- Recalculate line total
    ARRAY_AGG(id ORDER BY id) as all_ids  -- All IDs in this group
FROM order_items
GROUP BY order_id, product_id
HAVING COUNT(*) > 1;

-- Update the records we're keeping with consolidated quantities
UPDATE order_items oi
SET
    quantity = ci.total_quantity,
    supplied_quantity = ci.total_supplied_quantity,
    line_total = ci.total_quantity * oi.unit_price,  -- Recalculate based on new quantity
    tax_amount = ci.total_tax_amount,
    discount_amount = ci.total_discount_amount,
    updated_at = CURRENT_TIMESTAMP  -- Update timestamp if this column exists
FROM consolidated_items ci
WHERE oi.id = ci.keep_id;

-- Delete duplicate records (keeping only the first one)
DELETE FROM order_items
WHERE id IN (
    SELECT unnest(all_ids[2:])  -- Remove first element, delete the rest
    FROM consolidated_items
);

-- Show summary of what was fixed
SELECT
    COUNT(*) as groups_consolidated,
    SUM(array_length(all_ids, 1) - 1) as records_deleted
FROM consolidated_items;

COMMIT;

-- ============================================
-- STEP 3: Add unique constraint to prevent future duplicates
-- ============================================

-- Add unique constraint on (order_id, product_id)
-- This will prevent the same issue from happening again
ALTER TABLE order_items
ADD CONSTRAINT uk_order_product UNIQUE (order_id, product_id);

-- ============================================
-- STEP 4: Verify the fix
-- ============================================

-- Check if any duplicates remain (should return 0 rows)
SELECT
    order_id,
    product_id,
    COUNT(*) as count
FROM order_items
GROUP BY order_id, product_id
HAVING COUNT(*) > 1;

-- Show sample of consolidated records
SELECT
    id,
    order_id,
    product_id,
    quantity,
    unit_price,
    line_total
FROM order_items
ORDER BY order_id, product_id
LIMIT 20;
