SELECT AVG(speed)
FROM Product RIGHT JOIN PC ON Product.model = PC.model
WHERE maker = 'INTEL'
;