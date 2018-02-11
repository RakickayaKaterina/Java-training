SELECT DISTINCT pc1.hd 
FROM PC as pc1 JOIN PC as pc2 ON pc1.code > pc2.code AND pc1.hd = pc2.hd