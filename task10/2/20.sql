SELECT * 
FROM (
SELECT Product.maker, COUNT(Product.model) as models_count
FROM Product 
WHERE Product.model = 'PC'
GROUP BY Product.maker
) as maker_countpc
WHERE maker_countpc.models_count >=3
;