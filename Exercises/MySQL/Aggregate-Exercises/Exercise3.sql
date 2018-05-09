/*
	Find the gross total of all orders (sum of quantity * unit price) 
	for each customer, order it in descending order by the total.
*/

USE Northwind;

select
	sum(od.Quantity*od.UnitPrice) GrossTotal, c.CompanyName
from Orders o
	inner join Order_Details od on o.OrderID = od.OrderID
    inner join Customers c on c.CustomerID = o.CustomerID
group by (c.CompanyName);