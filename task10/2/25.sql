SELECT DISTINCT Product.maker
FROM Product
WHERE Product.maker IN 
(
	SELECT Product.maker 
	FROM Product
	WHERE Product.type ='Printer'
)
	AND Product.model IN
(
	SELECT PC.model
	FROM PC
	WHERE PC.ram = 
	(
		SELECT 	MIN(PC.ram)
		FROM PC
        WHERE Product.model = PC.model 
	)
    AND PC.speed =
	(
		SELECT MAX(PC.speed)
		FROM PC
		WHERE Product.model = PC.model
    )
)
;