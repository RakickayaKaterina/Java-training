SELECT DISTINCT maker
FROM Product
WHERE Product.type = 'PC' AND Product.maker NOT IN (
	SELECT laptop_maker.maker
    FROM Product as laptop_maker
    WHERE laptop_maker.type = 'Laptop'
)
;