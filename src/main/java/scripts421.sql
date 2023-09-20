 --Возраст студента не может быть меньше 16 лет.
 --Имена студентов должны быть уникальными и не равны нулю.
 --Пара “значение названия” - “цвет факультета” должна быть уникальной.
 --При создании студента без возраста ему автоматически должно присваиваться 20 лет.


--Task1
select *from student s;


alter table student
add constraint age_pass check(student.age>16);

alter table student
add constraint unique_student unique("name");

alter table student
alter column "name" set not null  ;

alter table faculty
add constraint unique_faculty_name unique("name", "color");

alter table student
alter column age set default 20;

--Task 2 scripts422.sql.
create table person(
  id serial primary key,
  name text,
  age varchar,
  license boolean default false,
  car_id serial references car(id)
)

create table car(
  id serial primary key,
  car_name text,
  model text,
  car_price varchar,

)

insert into person(car_id, name, age, license) values (1,'Igor', 22, true);
insert into person(car_id, name, age) values (2,'Oleg', 20);
insert into person(car_id, name, age, license) values (3,'Valera', 32, true);

insert into car( car_name, model, car_price) values ( 'Toyota', '365X', 3500000);
insert into car( car_name, model, car_price) values ( 'Audi', 'R8', 13500000);
insert into car( car_name, model, car_price) values ( 'Tesla', 'X', 23500000);

--Task 3 scripts423.sql.
select student.name, student.age , student.faculty_id , faculty.color
from student
inner join faculty  on student.faculty_id  = faculty.id  ;

select student.id, student.name
from student
inner join avatar on student.id  = avatar.student_id  ;

