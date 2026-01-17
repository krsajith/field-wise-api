-- Sample data for Kerala automobile parts distributor (skip vw_* views)

INSERT INTO roles (id, name, description) VALUES
  (1, 'Admin', 'System administrator'),
  (2, 'Sales Rep', 'Field sales representative');

INSERT INTO permissions (id, name, resource, action, description) VALUES
  (1, 'Orders Read', 'orders', 'read', 'View orders'),
  (2, 'Orders Write', 'orders', 'write', 'Create/update orders'),
  (3, 'Products Read', 'products', 'read', 'View products');

INSERT INTO role_permissions (id, role_id, permission_id) VALUES
  (1, 1, 1),
  (2, 1, 2),
  (3, 2, 1),
  (4, 2, 3);

INSERT INTO users (id, phone, first_name, last_name, role_id, employee_code, is_active, is_verified) VALUES
  (1, '9990001111', 'Anil', 'Menon', 1, 'EMP-ADM-001', true, true),
  (2, '9990002222', 'Deepak', 'Nair', 2, 'EMP-SLS-002', true, true);

INSERT INTO user_devices (id, user_id, device_token, device_name, os_name, os_version, app_version, is_active) VALUES
  (1, 2, 'android-kerala-001', 'Galaxy A52', 'Android', '13', '1.0.0', true);

INSERT INTO states (id, name, code, is_active) VALUES
  (1, 'Kerala', 'KL', true);

INSERT INTO districts (id, state_id, name, code, is_active) VALUES
  (1, 1, 'Ernakulam', 'EKM', true);

INSERT INTO places (id, district_id, name, pincode, is_active) VALUES
  (1, 1, 'Kochi', '682001', true),
  (2, 1, 'Aluva', '683101', true);

INSERT INTO routes (id, name, code, district_id, description, is_active) VALUES
  (1, 'Kochi North', 'KNR', 1, 'Kochi North distributor route', true),
  (2, 'Ernakulam City', 'ECY', 1, 'Central city retailers route', true);

INSERT INTO shop_types (id, name, code, description, is_active) VALUES
  (1, 'Distributor', 'DIST', 'Primary distributor', true),
  (2, 'Retailer', 'RET', 'Retail outlet', true);

INSERT INTO banks (id, name, code, is_active) VALUES
  (1, 'State Bank of India', 'SBI', true);

INSERT INTO payment_terms (id, name, code, days, description, is_active) VALUES
  (1, 'Net 30', 'N30', 30, 'Payment due in 30 days', true),
  (2, 'Cash on Delivery', 'COD', 0, 'Pay on delivery', true);

INSERT INTO transportation_modes (id, name, code, description, is_active) VALUES
  (1, 'Truck', 'TRK', 'Primary delivery truck', true),
  (2, 'Tempo', 'TMP', 'Light commercial vehicle', true);

INSERT INTO shops (
  id, name, code, shop_type_id, contact_person, phone, email, address_line1, place_id, district_id, state_id,
  pincode, route_id, bank_id, bank_account_number, bank_ifsc_code, payment_term_id, credit_limit, is_active,
  is_verified, created_by, verified_by
) VALUES
  (1, 'Kochi Auto Spares', 'KAS001', 1, 'Suresh Kumar', '0484-123456', 'kas@kochiauto.com',
   'MG Road, Kochi', 1, 1, 1, '682001', 1, 1, '1234567890', 'SBIN0001234', 1, 250000.00, true, true, 1, 1),
  (2, 'City Wheels', 'CW002', 2, 'Ravi Das', '0484-654321', 'citywheels@ekm.in',
   'Broadway, Ernakulam', 2, 1, 1, '683101', 2, 1, '9876543210', 'SBIN0001234', 2, 50000.00, true, true, 1, 1);

INSERT INTO product_categories (id, name, code, description, is_active) VALUES
  (1, 'Brake Components', 'BRAKE', 'Brake parts and accessories', true),
  (2, 'Filters', 'FILTER', 'Oil, air, and fuel filters', true);

INSERT INTO product_sub_categories (id, category_id, name, code, description, is_active) VALUES
  (1, 1, 'Brake Pads', 'BPAD', 'Brake pad sets', true),
  (2, 1, 'Brake Discs', 'BDISC', 'Brake discs and rotors', true);

INSERT INTO products (
  id, name, code, category_id, sub_category_id, description, unit, hsn_code, price, mrp, tax_rate, is_active
) VALUES
  (1, 'Brake Pad Set - Swift', 'BP-SWIFT', 1, 1, 'Front brake pad set', 'set', '870830', 850.00, 950.00, 18.00, true),
  (2, 'Brake Disc - WagonR', 'BD-WAGONR', 1, 2, 'Front brake disc', 'pc', '870830', 1350.00, 1500.00, 18.00, true),
  (3, 'Oil Filter - Alto', 'OF-ALTO', 2, NULL, 'Oil filter', 'pc', '842123', 220.00, 250.00, 18.00, true);

INSERT INTO product_stock (id, product_id, shop_id, quantity) VALUES
  (1, 1, 1, 120),
  (2, 2, 1, 60),
  (3, 3, 2, 200);

INSERT INTO shop_visits (
  id, user_id, shop_id, route_id, check_in_time, check_in_latitude, check_in_longitude, visit_purpose, notes, is_productive
) VALUES
  (1, 2, 1, 1, TIMESTAMP '2026-01-15 10:05:00', 9.93120000, 76.26730000, 'Order', 'Routine visit', true),
  (2, 2, 2, 2, TIMESTAMP '2026-01-15 14:30:00', 10.10760000, 76.35160000, 'Collection', NULL, true);

INSERT INTO orders (
  id, order_number, shop_id, user_id, shop_visit_id, order_date, delivery_date, delivery_mode, payment_mode_id,
  transportation_mode_id, subtotal, tax_amount, discount_amount, total_amount, status, latitude, longitude, remarks
) VALUES
  (1, 'ORD-KL-0001', 1, 2, 1, DATE '2026-01-15', DATE '2026-01-17', 'By truck', 1, 1, 3000.00, 300.00, 0.00, 3300.00,
   'PENDING', 9.93120000, 76.26730000, 'Deliver in morning'),
  (2, 'ORD-KL-0002', 2, 2, NULL, DATE '2026-01-15', DATE '2026-01-18', 'By tempo', 2, 2, 2200.00, 0.00, 0.00, 2200.00,
   'APPROVED', 10.10760000, 76.35160000, 'Call before delivery');

INSERT INTO order_items (
  id, order_id, product_id, quantity, unit_price, tax_rate, tax_amount, discount_percentage, discount_amount, line_total
) VALUES
  (1, 1, 1, 2, 850.00, 18.00, 306.00, 0.00, 0.00, 1700.00),
  (2, 1, 2, 1, 1300.00, 18.00, 234.00, 0.00, 0.00, 1300.00),
  (3, 2, 3, 10, 220.00, 18.00, 396.00, 0.00, 0.00, 2200.00);

INSERT INTO collections (
  id, collection_number, shop_id, user_id, order_id, collection_date, amount, payment_mode, status, remarks
) VALUES
  (1, 'COL-0001', 1, 2, 1, DATE '2026-01-16', 3300.00, 'CASH', 'RECEIVED', 'Collected at shop');

INSERT INTO attendance (id, user_id, punch_in_time, punch_in_location_address, is_verified_biometric) VALUES
  (1, 2, TIMESTAMP '2026-01-15 09:00:00', 'Kochi depot', true);

INSERT INTO location_logs (id, user_id, latitude, longitude, accuracy, speed, battery_level, "timestamp") VALUES
  (1, 2, 9.93120000, 76.26730000, 5.00, 0.00, 85, TIMESTAMP '2026-01-15 09:15:00');

INSERT INTO activities (id, user_id, shop_id, activity_type, title, description, scheduled_date, scheduled_time, status) VALUES
  (1, 2, 1, 'VISIT', 'Kochi Auto Spares visit', 'Monthly stock check', DATE '2026-01-15', TIME '10:00:00', 'SCHEDULED');

INSERT INTO expense_categories (id, name, code, max_amount, requires_approval, description, is_active) VALUES
  (1, 'Fuel', 'FUEL', 1500.00, true, 'Fuel reimbursement', true),
  (2, 'Meals', 'MEAL', 500.00, false, 'Food expenses', true);

INSERT INTO expenses (
  id, user_id, category_id, expense_date, amount, title, description, status, remarks
) VALUES
  (1, 2, 1, DATE '2026-01-15', 750.00, 'Fuel refill', 'Trip to Ernakulam', 'SUBMITTED', 'Paid by cash');

INSERT INTO leave_categories (id, name, code, max_days_per_year, requires_approval, description, is_active) VALUES
  (1, 'Casual Leave', 'CL', 12, true, 'Casual leave quota', true);

INSERT INTO leave_applications (
  id, user_id, category_id, from_date, to_date, leave_type, total_days, reason, status
) VALUES
  (1, 2, 1, DATE '2026-02-01', DATE '2026-02-02', 'FULL_DAY', 2, 'Family function', 'PENDING');

INSERT INTO targets (
  id, user_id, target_type, period_type, start_date, end_date, target_value, achieved_value, achievement_percentage, created_by
) VALUES
  (1, 2, 'SALES', 'MONTH', DATE '2026-01-01', DATE '2026-01-31', 500000.00, 0.00, 0.00, 1);

INSERT INTO messages (id, from_user_id, to_user_id, message_type, subject, body, is_read) VALUES
  (1, 1, 2, 'INFO', 'Monthly target', 'Please focus on brake components.', false);

INSERT INTO complaints (id, user_id, shop_id, complaint_type, subject, description, priority, status) VALUES
  (1, 2, 1, 'QUALITY', 'Brake pad noise', 'Customer reported noise after installation.', 'MEDIUM', 'OPEN');

INSERT INTO feedback (id, user_id, shop_id, feedback_type, subject, description, rating, status) VALUES
  (1, 2, 1, 'SERVICE', 'Fast delivery', 'Delivery within same day.', 5, 'RECORDED');

INSERT INTO enquiries (id, user_id, customer_name, phone, product_interest, status, priority) VALUES
  (1, 2, 'AutoCare Garage', '9847000001', 'Brake pads', 'OPEN', 'HIGH');

INSERT INTO push_notifications (id, user_id, title, body, notification_type, is_sent) VALUES
  (1, 2, 'New order assigned', 'Order ORD-KL-0001 created.', 'ORDER', true);

INSERT INTO audit_logs (id, user_id, table_name, record_id, action, ip_address, user_agent) VALUES
  (1, 1, 'orders', 1, 'CREATE', '10.0.0.10', 'seed-data');

INSERT INTO otp_verifications (id, phone, otp_code, purpose, is_verified, expires_at) VALUES
  (1, '9990002222', '123456', 'LOGIN', true, TIMESTAMP '2026-01-15 09:05:00');

INSERT INTO user_route_assignments (id, user_id, route_id, assigned_date, is_active) VALUES
  (1, 2, 1, DATE '2026-01-01', true);

INSERT INTO webauthn_credentials (id, user_id, signature_count, device_name, last_used_at) VALUES
  (1, 1, 1, 'Admin Laptop', TIMESTAMP '2026-01-10 12:00:00');

INSERT INTO system_settings (id, setting_key, setting_value, data_type, description, is_public, updated_by) VALUES
  (1, 'company_name', 'Kerala Auto Parts Distributor', 'string', 'Company display name', true, 1),
  (2, 'currency', 'INR', 'string', 'Default currency', true, 1);

INSERT INTO files (id, uploaded_by, file_name, file_size, file_type, storage_path, entity_type, entity_id) VALUES
  (1, 1, 'invoice_ord_0001.pdf', 128000, 'application/pdf', '/uploads/invoices/invoice_ord_0001.pdf', 'ORDER', 1);
