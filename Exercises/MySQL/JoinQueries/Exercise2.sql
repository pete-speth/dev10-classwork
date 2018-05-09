/*
	Get the Company Name, Order Date, and each order details 
	Product name for USA customers only.
*/

USE Northwind;

select
	c.CompanyName,
    o.OrderDate,
    p.ProductName
from Orders o
	inner join Order_Details od on o.OrderID = od.OrderID
    inner join Customers c on o.CustomerID = c.CustomerID
    inner join Products p on od.ProductID = p.ProductID
where c.Country = 'USA';