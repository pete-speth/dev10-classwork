/*
	Find the gross total (sum of quantity * unit price) for 
	all orders placed by B's Beverages and Chop-suey Chinese.
*/

USE Northwind;

select
	sum(od.Quantity*od.UnitPrice)
from Orders o
	inner join Order_Details od on o.OrderID = od.OrderID
    inner join Customers c on c.CustomerID = o.CustomerID
where c.CompanyName = 'B\'s Beverages' 
	or c.CompanyName = 'Chop-suey Chinese';