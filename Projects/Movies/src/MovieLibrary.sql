drop database if exists MovieLibrary;
create database MovieLibrary;

use MovieLibrary;

create table Movie (
	MovieID int auto_increment primary key,
    Title varchar(50) not null,
    ReleaseDate date null,
    RatingMPAA char(5) null,
    DirectorName varchar(30) null,
    Studio varchar(30) null,
    UserNote varchar(150) null
);

insert into Movie (Title, ReleaseDate, RatingMPAA, DirectorName, Studio, UserNote)
values ('UP','2009/05/29','PG','Pete Docter, Bob Peterson','Disney Pixar','Good movie for kids!'),
	('Good Will Hunting','1997/12/05','R','Gus Van Sant','Miramax','Great movie!'),
    ('Ghostbusters','1984/06/08','PG','Ivan Reitman','Columbia Pictures', 'Classic');

