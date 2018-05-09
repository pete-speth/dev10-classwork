/*
	Get all the order information for any order where Chai was sold.
*/

USE Northwind;

select
	p.ProductName,
	o.*
from Orders o
	inner join Order_Details od on o.OrderID = od.OrderID
    inner join Products p on od.ProductID = p.ProductID
where p.ProductName = 'Chai';