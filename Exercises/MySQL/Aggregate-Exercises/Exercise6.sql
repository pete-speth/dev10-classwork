/*
	Get the count of how many unique countries
	are represented by our suppliers.
*/

USE Northwind;

select
	count(distinct(country))
from Suppliers;