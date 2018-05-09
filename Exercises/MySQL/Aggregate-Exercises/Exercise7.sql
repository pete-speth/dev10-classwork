/*
	Find the total sales by supplier 
	ordered from most to least.
*/

USE Northwind;

select
	sum(od.UnitPrice*od.Quantity) TotalSales, s.CompanyName
from Suppliers s
	inner join Products p on p.SupplierID = s.SupplierID
    inner join Order_Details od on p.ProductID = od.ProductID
group by (s.CompanyName)
order by TotalSales DESC;