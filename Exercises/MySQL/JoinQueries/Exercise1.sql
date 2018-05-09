/*
	Get a list of each employee first name and lastname
	and the territory names they are associated with
*/

USE Northwind;

select
	e.FirstName,
    e.LastName,
    t.TerritoryDescription
from Employees e
	inner join EmployeeTerritories et on e.EmployeeID = et.EmployeeID
    inner join Territories t on et.TerritoryID = t.TerritoryID;