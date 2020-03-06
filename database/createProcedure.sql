CREATE VIEW `stock-market`.`top_10_records_each_isin` AS
SELECT sp.idstockprice, s.Name, sp.buyout, sp.sellout, sp.date 
FROM (SELECT sp.*, ROW_NUMBER() OVER (PARTITION BY sp.`ISIN` ORDER BY sp.`date` DESC) as "rn" FROM `stock-market`.stockprice sp) sp
INNER JOIN stock s USING (`ISIN`)
WHERE sp.rn <=10