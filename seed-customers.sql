-- Insert or update cities
INSERT INTO cities (name, latitude, longitude, geofence_radius, created_at, updated_at)
VALUES 
  ('Quetta', 30.1798, 66.9750, 5000, NOW(), NOW()),
  ('Karachi', 24.8607, 67.0011, 5000, NOW(), NOW())
ON CONFLICT (name) DO NOTHING;

-- Get city IDs
WITH city_ids AS (
  SELECT id, name FROM cities WHERE name IN ('Quetta', 'Karachi')
)

-- Insert Quetta customers
INSERT INTO customers (name, address, customer_type, latitude, longitude, city_id, approval_status, phone, email, created_at, updated_at)
SELECT 
  'Bookmark Store Quetta', 'Zarghoon Road, Quetta', 'A+', 30.1850, 66.9700, (SELECT id FROM city_ids WHERE name='Quetta'), 'APPROVED', '+923001234567', 'bookmark.store.quetta@test.com', NOW(), NOW()
WHERE NOT EXISTS (SELECT 1 FROM customers WHERE name = 'Bookmark Store Quetta')
UNION ALL
SELECT 
  'Education Hub Quetta', 'Liaquat Road, Quetta', 'A', 30.1800, 66.9800, (SELECT id FROM city_ids WHERE name='Quetta'), 'APPROVED', '+923001234568', 'education.hub.quetta@test.com', NOW(), NOW()
WHERE NOT EXISTS (SELECT 1 FROM customers WHERE name = 'Education Hub Quetta')
UNION ALL
SELECT 
  'Student Corner Quetta', 'Shahbaz Road, Quetta', 'B', 30.1750, 66.9650, (SELECT id FROM city_ids WHERE name='Quetta'), 'APPROVED', '+923001234569', 'student.corner.quetta@test.com', NOW(), NOW()
WHERE NOT EXISTS (SELECT 1 FROM customers WHERE name = 'Student Corner Quetta')
UNION ALL
SELECT 
  'Knowledge Shop Quetta', 'Jinnah Road, Quetta', 'A+', 30.1900, 66.9850, (SELECT id FROM city_ids WHERE name='Quetta'), 'APPROVED', '+923001234570', 'knowledge.shop.quetta@test.com', NOW(), NOW()
WHERE NOT EXISTS (SELECT 1 FROM customers WHERE name = 'Knowledge Shop Quetta')
UNION ALL
SELECT 
  'School Book Distributor Quetta', 'Zarghoon Avenue, Quetta', 'B', 30.1780, 66.9750, (SELECT id FROM city_ids WHERE name='Quetta'), 'APPROVED', '+923001234571', 'school.book.quetta@test.com', NOW(), NOW()
WHERE NOT EXISTS (SELECT 1 FROM customers WHERE name = 'School Book Distributor Quetta')
UNION ALL
-- Insert Karachi customers
SELECT 
  'Bookmark Mega Store Karachi', 'Saddar, Karachi', 'A+', 24.8650, 67.0050, (SELECT id FROM city_ids WHERE name='Karachi'), 'APPROVED', '+923011234567', 'bookmark.mega.karachi@test.com', NOW(), NOW()
WHERE NOT EXISTS (SELECT 1 FROM customers WHERE name = 'Bookmark Mega Store Karachi')
UNION ALL
SELECT 
  'City Library Karachi', 'Clifton, Karachi', 'A', 24.7900, 67.0200, (SELECT id FROM city_ids WHERE name='Karachi'), 'APPROVED', '+923011234568', 'city.library.karachi@test.com', NOW(), NOW()
WHERE NOT EXISTS (SELECT 1 FROM customers WHERE name = 'City Library Karachi')
UNION ALL
SELECT 
  'Educational Books Karachi', 'Defence, Karachi', 'B', 24.8400, 67.0300, (SELECT id FROM city_ids WHERE name='Karachi'), 'APPROVED', '+923011234569', 'educational.books.karachi@test.com', NOW(), NOW()
WHERE NOT EXISTS (SELECT 1 FROM customers WHERE name = 'Educational Books Karachi')
UNION ALL
SELECT 
  'Student Supply Hub Karachi', 'Gulshan-e-Iqbal, Karachi', 'A+', 24.9000, 67.0500, (SELECT id FROM city_ids WHERE name='Karachi'), 'APPROVED', '+923011234570', 'student.supply.karachi@test.com', NOW(), NOW()
WHERE NOT EXISTS (SELECT 1 FROM customers WHERE name = 'Student Supply Hub Karachi')
UNION ALL
SELECT 
  'Academic Bookshop Karachi', 'F.B. Area, Karachi', 'B', 24.8500, 67.0100, (SELECT id FROM city_ids WHERE name='Karachi'), 'APPROVED', '+923011234571', 'academic.bookshop.karachi@test.com', NOW(), NOW()
WHERE NOT EXISTS (SELECT 1 FROM customers WHERE name = 'Academic Bookshop Karachi');
