/*
	Find the average freight paid for orders 
	placed by companies in the USA
*/

USE Northwind;

select
	avg(o.Freight)
from Orders o
	inner join Customers c on o.CustomerID = c.CustomerID
where c.Country = 'USA';