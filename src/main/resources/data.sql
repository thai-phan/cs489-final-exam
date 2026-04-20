-- Seed only one complete data set: address + apartment + tenant + lease.


-- 2. Insert Address Data
INSERT INTO addresses (apartment_number, street, city, state, zip_code)
VALUES ('W1100', '40 W Burlington Avenue', 'Chicago', 'IL', '66543'),
       ('J1109', '9 John Wayne Street', 'Akron', 'OH', '44132'),
       ('Y819', '7501 MLK Boulevard', 'Chicago', 'IL', '66031');

-- 3. Insert Apartment Data
INSERT INTO apartments (apartment_number, property_name, floor_no, size, number_of_rooms, address_id)
VALUES ('W1100', 'Bells Court', 14, 1050, 2, 1),
       ('J1109', 'The Galleria', 1, 960, 1, 2),
       ('Y819', 'Bells Court', 18, 2100, 2, 3);


INSERT INTO tenants (first_name, last_name, phone_number, email)
VALUES ('Ben', 'Klein', '(480) 123-1355', NULL),
       ('Jackie', 'Oatley', '(414) 998-0112', 'joatley@mail.com');



INSERT INTO leases (lease_number, start_date, end_date, monthly_rental_rate, apartment_id, tenant_id)
VALUES ('D0187-18775', '2021-10-01', '2022-09-30', 2750.00, 1, 1),
       ('W1736-16542', '2022-08-15', '2024-02-14', 1700.00, 2, 2),
       ('DD001-14420', '2022-10-01', '2023-09-30', 2975.00, 1, 1),
       ('PZ162-00179', '2023-10-01', '2024-09-30', 2575.00, 1, 1);


