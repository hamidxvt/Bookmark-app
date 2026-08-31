INSERT INTO cities (name, latitude, longitude, geofence_radius, created_at, updated_at) VALUES
-- Quetta (10)
('Quetta - Cantonment', 30.1798, 67.0064, 500, NOW(), NOW()),
('Quetta - Aabadgar', 30.2106, 67.0281, 500, NOW(), NOW()),
('Quetta - Satellite Town', 30.1524, 67.0457, 500, NOW(), NOW()),
('Quetta - Arbab Khamoosh', 30.2094, 67.0102, 500, NOW(), NOW()),
('Quetta - Alamdar Road', 30.1923, 67.0223, 500, NOW(), NOW()),
('Quetta - Zarghoon Road', 30.1945, 67.0189, 500, NOW(), NOW()),
('Quetta - Jinnah Road', 30.1872, 67.0124, 500, NOW(), NOW()),
('Quetta - Model Town', 30.1650, 67.0321, 500, NOW(), NOW()),
('Quetta - Hazara Town', 30.1412, 66.9680, 500, NOW(), NOW()),
('Quetta - Samungli Road', 30.2240, 67.0015, 500, NOW(), NOW()),
-- Karachi (10)
('Karachi - Defence', 24.7936, 67.0521, 500, NOW(), NOW()),
('Karachi - Clifton', 24.7786, 67.0301, 500, NOW(), NOW()),
('Karachi - DHA', 24.8255, 67.0273, 500, NOW(), NOW()),
('Karachi - Downtown', 24.8516, 67.0095, 500, NOW(), NOW()),
('Karachi - Gulshan-e-Iqbal', 24.9253, 67.2406, 500, NOW(), NOW()),
('Karachi - North Nazimabad', 24.9372, 67.0423, 500, NOW(), NOW()),
('Karachi - PECHS', 24.8686, 67.0620, 500, NOW(), NOW()),
('Karachi - Bahadurabad', 24.8820, 67.0678, 500, NOW(), NOW()),
('Karachi - Tariq Road', 24.8715, 67.0599, 500, NOW(), NOW()),
('Karachi - Malir Cantonment', 24.9080, 67.2021, 500, NOW(), NOW())
ON CONFLICT (name) DO NOTHING;
