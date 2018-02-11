SELECT @min_price_printer:= MIN(Printer.price)
FROM Printer
WHERE Printer.color = 'y'
;
SELECT Product.maker, Printer.price
FROM Printer JOIN Product ON Printer.model = Product.model
WHERE Printer.price = @min_price_printer AND Printer.color = 'y'
GROUP BY Product.maker
;