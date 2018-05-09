/*
	Write a query to show every combination of employee and location.
*/

USE SWCCorp;

select
*
from Employee e
	inner join Location l on e.LocationID = l.LocationID;