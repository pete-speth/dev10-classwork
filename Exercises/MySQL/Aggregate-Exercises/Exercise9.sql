/*
	Challenge: 
	Show the total amount of orders by
	year and country.  Data should be ordered 
	by year ascending and total descending.
	
	TotalSales    Year     Country
	41907.80      1996     USA
	37804.60      1996     Germany
	etc...
	
	Hint: Research the DatePart() function
*/

USE Northwind;

select
	sum(od.Quantity*od.UnitPrice) TotalSales, year(o.OrderDate) SalesYear, c.Country
from Orders o
	inner join Order_Details od on o.OrderID = od.OrderID
    inner join Customers c on c.CustomerID = o.CustomerID
group by c.Country, year(o.OrderDate)
order by SalesYear, TotalSales desc;