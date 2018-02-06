SELECT maker, speed
FROM Product RIGHT JOIN Laptop ON Product.model = Laptop.model
WHERE hd >= 10
;