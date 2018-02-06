SELECT  DISTINCT maker 
FROM Product RIGHT JOIN PC ON Product.model = PC.model
WHERE speed >= 450
;