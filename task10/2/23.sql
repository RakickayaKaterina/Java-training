SELECT DISTINCT pc_makers.maker
FROM (
SELECT Product.maker
FROM Product JOIN PC ON Product.model = PC.model
WHERE PC.speed >= 750
) as pc_makers JOIN 
(
SELECT Product.maker
FROM Product JOIN Laptop ON Product.model = Laptop.model
WHERE Laptop.speed >= 750
) as laptop_makers ON pc_makers.maker = laptop_makers.maker
;
