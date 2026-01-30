-- ============================================
-- SAFE VERSION: Fix Duplicate Order Items
-- ============================================
-- This version creates a backup table first
-- and allows you to verify changes before committing
-- ============================================

-- STEP 1: Create backup of order_items table
CREATE TABLE order_items_backup_$(date +%Y%m%d) AS
SELECT * FROM order_items;

-- Verify backup was created
SELECT COUNT(*) as backup_count FROM order_items_backup_$(date +%Y%m%d);

-- STEP 2: View what will be changed (REVIEW THIS CAREFULLY)
WITH duplicates AS (
    SELECT
        order_id,
        product_id,
        COUNT(*) as duplicate_count,
        ARRAY_AGG(id ORDER BY id) as all_ids,
        SUM(quantity) as total_quantity,
        AVG(unit_price) as unit_price
    FROM order_items
    GROUP BY order_id, product_id
    HAVING COUNT(*) > 1
)
SELECT
    d.order_id,
    d.product_id,
    d.duplicate_count,
    d.all_ids[1] as id_to_keep,
    d.all_ids[2:] as ids_to_delete,
    d.total_quantity as new_quantity,
    oi.quantity as old_quantity_of_kept_record,
    (d.total_quantity - oi.quantity) as quantity_being_added
FROM duplicates d
JOIN order_items oi ON oi.id = d.all_ids[1]
ORDER BY d.order_id, d.product_id;

-- ============================================
-- STEP 3: Apply the fix (UNCOMMENT TO EXECUTE)
-- ============================================

-- Uncomment the lines below once you've reviewed the changes above

/*
BEGIN;

-- Consolidate duplicates
WITH duplicates AS (
    SELECT
        MIN(id) as keep_id,
        order_id,
        product_id,
        SUM(quantity) as total_quantity,
        SUM(COALESCE(supplied_quantity, 0)) as total_supplied_quantity,
        MAX(unit_price) as unit_price,
        MAX(tax_rate) as tax_rate,
        SUM(COALESCE(tax_amount, 0)) as total_tax_amount,
        MAX(discount_percentage) as discount_percentage,
        SUM(COALESCE(discount_amount, 0)) as total_discount_amount,
        ARRAY_AGG(id ORDER BY id) as all_ids
    FROM order_items
    GROUP BY order_id, product_id
    HAVING COUNT(*) > 1
)
UPDATE order_items oi
SET
    quantity = d.total_quantity,
    supplied_quantity = d.total_supplied_quantity,
    line_total = d.total_quantity * oi.unit_price,
    tax_amount = d.total_tax_amount,
    discount_amount = d.total_discount_amount,
    updated_at = CURRENT_TIMESTAMP
FROM duplicates d
WHERE oi.id = d.keep_id;

-- Delete duplicates
DELETE FROM order_items
WHERE id IN (
    SELECT unnest(all_ids[2:])
    FROM (
        SELECT ARRAY_AGG(id ORDER BY id) as all_ids
        FROM order_items
        GROUP BY order_id, product_id
        HAVING COUNT(*) > 1
    ) sub
);

-- Add unique constraint
ALTER TABLE order_items
ADD CONSTRAINT uk_order_product UNIQUE (order_id, product_id);

-- Show summary
SELECT 'Fix completed successfully' as status;

COMMIT;
*/

-- ============================================
-- ROLLBACK SCRIPT (if something goes wrong)
-- ============================================

/*
-- To restore from backup:
BEGIN;

-- Drop the current table
DROP TABLE order_items;

-- Restore from backup
ALTER TABLE order_items_backup_$(date +%Y%m%d) RENAME TO order_items;

COMMIT;
*/
