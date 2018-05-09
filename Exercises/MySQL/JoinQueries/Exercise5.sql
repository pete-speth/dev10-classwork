/*
	Find a list of all the Employees who have never found a Grant
*/

USE SWCCorp;

select
*
from Employee e
	left outer join `Grant` g on e.EmpID = g.EmpID
where g.GrantID is null;