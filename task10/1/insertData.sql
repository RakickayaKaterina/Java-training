START TRANSACTION;
INSERT INTO Product(marker, model, type) values
	('HP', 'Pavilion', 'Laptop'),
    ('HP', 'Chromebook', 'Laptop'),
    ('ASUS', 'VivoBook', 'Laptop'),
    ('ACER', 'Aspire', 'Laptop'),
    ('LENOVO', 'Legion', 'Laptop'),
    ('APPLE', 'MacBook Air', 'Laptop'),
    ('DELL', 'Inspiron', 'Laptop'),
    ('APPLE', 'MacBook Pro', 'Laptop'),
    ('ASUS', 'ZenBook', 'Laptop'),
    ('ASUS', 'ROG', 'Laptop'),
    
    
    ('INTEL', 'Stick', 'PC'),
    ('JET', 'MultiGame', 'PC'),
    ('IRWIN', 'VEGA', 'PC'),
    ('APPLE', 'Mac mini', 'PC'),
    ('HAFF', 'Maxima AREA G4', 'PC'),
    ('APPLE', 'Mac Pro', 'PC'),
    ('IRWIN', 'VEGA G4B', 'PC'),
    ('BVK', 'Extreme', 'PC'),
    ('BVK', 'Advanced', 'PC'),
    ('INTEL', 'NUC6i7KYK', 'PC'),
    
    
    ('CANON', 'MF3010', 'Printer'),
    ('CANON', 'MF237w', 'Printer'),
    ('CANON', 'PIXMA iP7240', 'Printer'),
	('EPSON', 'L366', 'Printer'),
    ('EPSON', 'L805', 'Printer'),
    ('EPSON', 'L222', 'Printer'),
    ('SAMSUNG', 'SL-M2070', 'Printer'),
    ('SAMSUNG', 'SL-C430W', 'Printer'),
    ('HP', 'LaserJet', 'Printer'),
    ('HP', 'DeskJet', 'Printer');
    
INSERT INTO PC(model, speed, ram, hd, cd, price) values
	('Stick', 1700, 4, 500,'12x', 350),
    ('MultiGame', 2700, 16, 2000,'24x', 1350),
    ('VEGA', 2300, 8, 1000,'12x', 850),
    ('Mac mini', 2200, 6, 500,'24x', 1350),
    ('Maxima AREA G4', 2700, 4, 500,'4x', 350),
    ('Mac Pro', 3100, 16, 1000,'16x', 660),
    ('VEGA G4B', 1700, 8, 500,'12x', 550),
    ('Extreme', 3300, 4, 500,'4x', 550),
    ('Advanced', 2700, 16, 500,'12x', 250),
    ('NUC6i7KYK', 2500, 8, 500,'4x', 750);

INSERT INTO Laptop(model, speed, ram, hd, price, screen) values
	 ('Pavilion', 1700, 4, 1000, 324, 16),
	 ('Chromebook', 1600, 2, 500, 350, 16),
     ('VivoBook', 2700, 8, 250, 251, 16),
     ('Aspire', 2200, 4, 500, 524, 16),
     ('Legion', 2600, 8, 500, 1245, 16),
     ('MacBook Air', 2700, 8, 500, 1244, 16),
     ('Inspiron', 2800, 8, 500, 700, 16),
     ('MacBook Pro', 3200, 16, 1000, 2000, 16),
     ('ZenBook', 3300, 16, 1000, 1000, 16),
     ('Aspire', 2500, 16, 1000, 800, 19),
	 ('Aspire', 100, 16, 1, 10, 11);

INSERT INTO Printer(model, color, type, price) values
	('MF3010', 'n', 'Laser', 250),
    ('MF237w', 'n', 'Jet', 350),
    ('PIXMA iP7240', 'y', 'Laser', 550),
    ('SL-M2070', 'n', 'Matrix', 370),
    ('L366', 'y', 'Matrix', 350),
    ('L805', 'y', 'Matrix', 450),
    ('L222', 'y', 'Laser', 650),
    ('SL-C430W', 'y', 'Jet', 250),
    ('LaserJet', 'y', 'Laser', 1250),
    ('DeskJet', 'n', 'Laser', 650);
COMMIT;
