SELECT Product.maker, MAX(PC.price)
FROM Product JOIN PC ON Product.model = PC.model
GROUP BY Product.maker