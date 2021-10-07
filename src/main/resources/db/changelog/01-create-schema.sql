drop table if exists employee;
drop table if exists department;
drop table if exists organization;
CREATE TABLE organization(id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100)
);
CREATE TABLE department(id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100),
    organization_id int,
    constraint FK_departmentorginization FOREIGN key(organization_id)
    REFERENCES Organization(id)
);
CREATE TABLE employee(id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(100),
    last_name varchar(100),
    position varchar(50),
    salary double,
    age int,
    department_id int,
    organization_id int,
    CONSTRAINT FK_employeedepartment FOREIGN key(department_id) REFERENCES department(id),
    constraint FK_employeeorganization foreign key(organization_id) references organization(id)
);
alter table department add column employee_id int;
alter table department add constraint FK_departmentemployee FOREIGN KEY(employee_id) references employee(id);
insert into organization (id, name) values (1, 'Test1');
insert into organization (id, name) values (2, 'Test2');
insert into organization (id, name) values (3, 'Test3');
insert into organization (id, name) values (4, 'Test4');
insert into organization (id, name) values (5, 'Test5');
insert into department (id, name, organization_id) values (1, 'Test1', 1);
insert into department (id, name, organization_id) values (2, 'Test2', 1);
insert into department (id, name, organization_id) values (3, 'Test3', 1);
insert into department (id, name, organization_id) values (4, 'Test4', 2);
insert into department (id, name, organization_id) values (5, 'Test5', 2);
insert into department (id, name, organization_id) values (6, 'Test6', 3);
insert into department (id, name, organization_id) values (7, 'Test7', 4);
insert into department (id, name, organization_id) values (8, 'Test8', 5);
insert into department (id, name, organization_id) values (9, 'Test9', 5);
insert into employee (id, first_name, last_name, position, salary, age, department_id, organization_id) values (1, 'John', 'Smith', 'Developer', 10000, 30, 1, 1);
insert into employee (id, first_name, last_name, position, salary, age, department_id, organization_id) values (2, 'Adam', 'Hamilton', 'Developer', 12000, 35, 1, 1);
insert into employee (id, first_name, last_name, position, salary, age, department_id, organization_id) values (3, 'Tracy', 'Smith', 'Architect', 15000, 40, 1, 1);
insert into employee (id, first_name, last_name, position, salary, age, department_id, organization_id) values (4, 'Lucy', 'Kim', 'Developer', 13000, 25, 2, 1);
insert into employee (id, first_name, last_name, position, salary, age, department_id, organization_id) values (5, 'Peter', 'Wright', 'Director', 50000, 50, 4, 2);
insert into employee (id, first_name, last_name, position, salary, age, department_id, organization_id) values (6, 'Alan', 'Murray', 'Developer', 20000, 37, 4, 2);
insert into employee (id, first_name, last_name, position, salary, age, department_id, organization_id) values (7, 'Pamela', 'Anderson', 'Analyst', 7000, 27, 4, 2);