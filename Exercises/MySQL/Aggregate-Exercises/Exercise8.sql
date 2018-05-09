/*
	Show the number of orders placed by customers 
	from fewest to most provided the customer has 
	a minimum of 4 orders.
*/

USE Northwind;

select
	count(o.OrderID) NumOrders, c.CompanyName
from Customers c
	inner join Orders o on c.CustomerID = o.CustomerID
group by (c.CompanyName)
having count(o.OrderID) >= 4
order by NumOrders;

