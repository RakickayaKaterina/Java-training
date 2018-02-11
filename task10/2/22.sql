SELECT pc1.speed, AVG(pc1.price)
FROM PC as pc1 JOIN PC as pc2 ON pc1.code > pc2.code AND pc1.speed = pc2.speed
WHERE pc1.speed > 600
GROUP BY pc1.speed 
