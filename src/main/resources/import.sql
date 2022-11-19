/*Poblando usuarios*/

INSERT INTO clients (name, lastname, email, created_at, photo) VALUES ('Izhar', 'Baeza', 'ibaezar@outlook.com', '2022-11-10', '');
INSERT INTO clients (name, lastname, email, created_at, photo) VALUES ('Liam', 'Baeza', 'liam.baeza@gmail.com', '2022-11-10', '');
INSERT INTO clients (name, lastname, email, created_at, photo) VALUES ('Valentina', 'García', 'v.garcia@gmail.com', '2022-11-10', '');
INSERT INTO clients (name, lastname, email, created_at, photo) VALUES ('Victor', 'Baeza', 'victorb@gmail.com', '2022-11-10', '');
INSERT INTO clients (name, lastname, email, created_at, photo) VALUES ('Levi', 'Baeza', 'levi.b.r@gmail.com', '2022-11-10', '');

INSERT INTO clients (name, lastname, email, created_at, photo) VALUES ('Fabiola', 'reyes', 'freyesp.75@gmail.com', '2022-11-16', '');
INSERT INTO clients (name, lastname, email, created_at, photo) VALUES ('Manuel', 'Reyes', 'mreyesp.73@gmail.com', '2022-11-16', '');
INSERT INTO clients (name, lastname, email, created_at, photo) VALUES ('Alex', 'Fuentes', 'afuentesy.8@gmail.com', '2022-11-16', '');
INSERT INTO clients (name, lastname, email, created_at, photo) VALUES ('Belén', 'Baeza', 'bbaezab@gmail.com', '2022-11-16', '');
INSERT INTO clients (name, lastname, email, created_at, photo) VALUES ('Kirk', 'hammet', 'khammet@gmail.com', '2022-11-16', '');

INSERT INTO clients (name, lastname, email, created_at, photo) VALUES ('Javiera', 'Meza', 'jmeza@outlook.com', '2022-11-16', '');
INSERT INTO clients (name, lastname, email, created_at, photo) VALUES ('Hugo', 'Sanchez', 'hsanchez@gmail.com', '2022-11-16', '');
INSERT INTO clients (name, lastname, email, created_at, photo) VALUES ('Franco', 'Rojas', 'frojas@gmail.com', '2022-11-16', '');
INSERT INTO clients (name, lastname, email, created_at, photo) VALUES ('Juan', 'Pérez', 'jperez@gmail.com', '2022-11-16', '');
INSERT INTO clients (name, lastname, email, created_at, photo) VALUES ('Rodrigo', 'Gallardo', 'rgallardo@gmail.com', '2022-11-16', '');

INSERT INTO clients (name, lastname, email, created_at, photo) VALUES ('Moisés', 'Sepúlveda', 'msepulveda@outlook.com', '2022-11-16', '');
INSERT INTO clients (name, lastname, email, created_at, photo) VALUES ('Lionel', 'Sanchez', 'liosanchez@gmail.com', '2022-11-16', '');
INSERT INTO clients (name, lastname, email, created_at, photo) VALUES ('Tamara', 'vasquez', 'tvasquez@gmail.com', '2022-11-16', '');
INSERT INTO clients (name, lastname, email, created_at, photo) VALUES ('ninoska', 'lara', 'nlara@gmail.com', '2022-11-16', '');
INSERT INTO clients (name, lastname, email, created_at, photo) VALUES ('Sebastian', 'Carcamo', 'scarcamo@gmail.com', '2022-11-16', '');

INSERT INTO clients (name, lastname, email, created_at, photo) VALUES ('Lourdes', 'Pacheco', 'lpacheco@outlook.com', '2022-11-16', '');
INSERT INTO clients (name, lastname, email, created_at, photo) VALUES ('Marc', 'Inostroza', 'minostroza@gmail.com', '2022-11-16', '');
INSERT INTO clients (name, lastname, email, created_at, photo) VALUES ('Caleb', 'Reyes', 'creyes@gmail.com', '2022-11-16', '');
INSERT INTO clients (name, lastname, email, created_at, photo) VALUES ('Hernán', 'Meza', 'hmeza@gmail.com', '2022-11-16', '');
INSERT INTO clients (name, lastname, email, created_at, photo) VALUES ('Sofía', 'Castro', 'scastro@gmail.com', '2022-11-16', '');

/*Poblando tabla productos*/
INSERT INTO products (name, amount, created_at) VALUES ('Apple iPhone 14 Pro 256 GB', 1349990, NOW());
INSERT INTO products (name, amount, created_at) VALUES ('Apple iPhone 13 Pro Max 128 GB', 1149990, NOW());
INSERT INTO products (name, amount, created_at) VALUES ('Smartphone Galaxy S22 Ultra 5G 256GB', 949990, NOW());
INSERT INTO products (name, amount, created_at) VALUES ('Samsung Galaxy S10 Plus 128GB 8GB RAM Negro', 299990, NOW());
INSERT INTO products (name, amount, created_at) VALUES ('QLED Samsung 50" Q65B 4K UHD Smart TV 2022', 359990, NOW());
INSERT INTO products (name, amount, created_at) VALUES ('LED LG 55'' 55UP7760 4K TV UHD TV Smart TV + Magic Remote', 339990, NOW());
INSERT INTO products (name, amount, created_at) VALUES ('Apple MacBook Pro (13" con Chip M2 CPU 8 núcleos y GPU 10 núcleos, 8GB RAM, 256GB SSD) - Plata', 1299990, NOW());
INSERT INTO products (name, amount, created_at) VALUES ('Notebook ThinkPad Z16 AMD Ryzen 7 PRO 32GB RAM 1TB SSD 16'' LENOVO', 2799990, NOW());
INSERT INTO products (name, amount, created_at) VALUES ('Consola Microsoft Xbox Series X - 1 Tb - Sniper', 799990, NOW());
INSERT INTO products (name, amount, created_at) VALUES ('PS5 Consola Playstation 5 Sony HW 1115 HFW Standard Bundle', 809990, NOW());
INSERT INTO products (name, amount, created_at) VALUES ('Airpods Pro con estuche Magsafe', 259990, NOW());
INSERT INTO products (name, amount, created_at) VALUES ('Audífonos Bluetooth Noise Cancelling WF-1000XM4 Negro', 199990, NOW());
INSERT INTO products (name, amount, created_at) VALUES ('Cámara Blackmagic Design Pocket Cinema 6K Pro - Canon EF', 4133990, NOW());
INSERT INTO products (name, amount, created_at) VALUES ('Cámara Panasonic Lumix DC-GH5 Micro 4/3 con Lente 12-60mm - Negro', 2271990, NOW());
INSERT INTO products (name, amount, created_at) VALUES ('Chasing M2 ROV Submarino Edición EnterpriseMaleta y E-reel 200 Metros 100WH', 4399990, NOW());
INSERT INTO products (name, amount, created_at) VALUES ('Drone dji mavic 3 fly more combo', 3365990, NOW());

/*Poblando tabla facturas e item facturas*/
INSERT INTO invoices (description, observation, client_id, created_at) VALUES ('Factura de compra tecnología', NULL, 1, NOW());
INSERT INTO invoice_items (quantity, invoice_id, product_id) VALUES (2, 1, 1);
INSERT INTO invoice_items (quantity, invoice_id, product_id) VALUES (1, 1, 7);
INSERT INTO invoice_items (quantity, invoice_id, product_id) VALUES (1, 1, 11);
INSERT INTO invoice_items (quantity, invoice_id, product_id) VALUES (2, 1, 16);

INSERT INTO invoices (description, observation, client_id, created_at) VALUES ('Factura equipos de estudio', NULL, 1, NOW());
INSERT INTO invoice_items (quantity, invoice_id, product_id) VALUES (1, 1, 7);
INSERT INTO invoice_items (quantity, invoice_id, product_id) VALUES (1, 1, 15);
INSERT INTO invoice_items (quantity, invoice_id, product_id) VALUES (1, 1, 11);

INSERT INTO invoices (description, observation, client_id, created_at) VALUES ('Factura equipos de entretención', 'Para menor de edad', 2, NOW());
INSERT INTO invoice_items (quantity, invoice_id, product_id) VALUES (1, 2, 10);
INSERT INTO invoice_items (quantity, invoice_id, product_id) VALUES (1, 2, 12);
INSERT INTO invoice_items (quantity, invoice_id, product_id) VALUES (1, 2, 4);

INSERT INTO invoices (description, observation, client_id, created_at) VALUES ('Factura equipos de estudio', NULL, 5, NOW());
INSERT INTO invoice_items (quantity, invoice_id, product_id) VALUES (1, 3, 7);
INSERT INTO invoice_items (quantity, invoice_id, product_id) VALUES (1, 3, 15);
INSERT INTO invoice_items (quantity, invoice_id, product_id) VALUES (1, 3, 11);
