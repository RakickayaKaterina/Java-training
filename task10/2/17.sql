SELECT @min_speed_pc:= MIN(PC.speed)
FROM PC
;
SELECT Product.type, Laptop.model, Laptop.speed
FROM Laptop JOIN Product ON Laptop.model = Product.model
WHERE Laptop.speed < @min_speed_pc
;