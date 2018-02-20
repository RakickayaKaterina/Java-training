SELECT @max_all := MAX(price)
FROM (
SELECT PC.model, price
FROM Product JOIN PC ON Product.model = PC.model 
UNION 
SELECT Laptop.model, price
FROM Product JOIN Laptop ON Product.model = Laptop.model 
UNION 
SELECT Printer.model, price
FROM Product JOIN Printer ON Product.model = Printer.model 
) as products
;
SELECT products.model
FROM (
SELECT PC.model, price
FROM Product JOIN PC ON Product.model = PC.model 
UNION 
SELECT Laptop.model, price
FROM Product JOIN Laptop ON Product.model = Laptop.model 
UNION 
SELECT Printer.model, price
FROM Product JOIN Printer ON Product.model = Printer.model 
) as products 
WHERE products.price = @max_all
;