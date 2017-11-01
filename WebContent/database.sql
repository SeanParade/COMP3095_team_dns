# mySql version 5.7
DROP DATABASE IF EXISTS COMP3095;

CREATE DATABASE IF NOT EXISTS COMP3095;
USE COMP3095;
grant all on COMP3095.* to 'admin'@'localhost' identified by 'admin'; 

CREATE TABLE DEPARTMENT
(
	id int(11) AUTO_INCREMENT PRIMARY KEY,
	departmentName varchar(255),
	location varchar(255)
	);
INSERT INTO DEPARTMENT(departmentName, location)
VALUES("Accounting", "Room 1B");

INSERT INTO DEPARTMENT(departmentName, location)
VALUES("Administration", "Room 1A");

INSERT INTO DEPARTMENT(departmentName, location)
VALUES("Marketing", "Room 2B");

INSERT INTO DEPARTMENT(departmentName, location)
VALUES("IT", "Room B01");

INSERT INTO DEPARTMENT(departmentName, location)
VALUES("Human Resources", "Room 1D");

CREATE TABLE EGROUP
(
	id int(11) AUTO_INCREMENT PRIMARY KEY,
	groupName varchar(255),
	departmentId int(11),

	INDEX(departmentId),

	FOREIGN KEY(departmentId)
	REFERENCES department(id)
	
ON UPDATE CASCADE
	ON DELETE RESTRICT
	
);
CREATE TABLE EMPLOYEE
(
	id int(11) PRIMARY KEY,
	firstName varchar(255),
	lastName varchar(255),
	email varchar(255),
	hireYear varchar(255),
	position varchar(255),
	departmentId int(11),
	groupId int(11),
	
	
	INDEX(departmentId),
	INDEX(groupId),

	FOREIGN KEY(departmentId)
		REFERENCES department(id)
		ON UPDATE CASCADE ON DELETE RESTRICT,

	FOREIGN KEY(groupId)
		REFERENCES egroup(id)
		ON UPDATE CASCADE ON DELETE RESTRICT

);
# 1 accounting
INSERT INTO EMPLOYEE(id, firstName, lastName, email, hireYear, position, departmentId)
VALUES(1001,"Rich", "Gregory", "rich.gregory@gmail.com", "2017", "Accountant", 1);

INSERT INTO EMPLOYEE(id, firstName, lastName, email, hireYear, position, departmentId)
VALUES(1002, "Nita", "Cochrane", "nita.cochrane@gmail.com", "2017", "Accountant", 1);

#admin
INSERT INTO EMPLOYEE(id, firstName, lastName, email, hireYear, position, departmentId)
VALUES(2001, "Simon", "Martin", "simon.martin@gmail.com", "2017", "Receptionist", 2);

INSERT INTO EMPLOYEE(id, firstName, lastName, email, hireYear, position, departmentId)
VALUES(2002, "Mindy", "Kelly", "mindy.kelly@gmail.com", "2017", "Office Administrator", 2);

#marketing
INSERT INTO EMPLOYEE(id, firstName, lastName, email, hireYear, position, departmentId)
VALUES(3001, "Magaret", "Oneill", "magaret.oneill@gmail.com", "2017", "Sales", 3);

INSERT INTO EMPLOYEE(id, firstName, lastName, email, hireYear, position, departmentId)
VALUES(3002, "Donnie", "Zhang", "donnie.zhang@gmail.com", "2017", "Sales", 3);

#it
INSERT INTO EMPLOYEE(id, firstName, lastName, email, hireYear, position, departmentId)
VALUES(4001, "Hyon", "Barnhill", "hyon.barnhill@gmail.com", "2017", "Network Admin", 4);

INSERT INTO EMPLOYEE(id, firstName, lastName, email, hireYear, position, departmentId)
VALUES(4002, "Jazmine", "Velasquez", "jazmine.velasquez@gmail.com", "2017", "Database Admin", 4);

INSERT INTO EMPLOYEE(id, firstName, lastName, email, hireYear, position, departmentId)
VALUES(4003, "Deb", "Jude", "deb.jude@gmail.com", "2017", "Web Developer", 4);

#human resources
INSERT INTO EMPLOYEE(id, firstName, lastName, email, hireYear, position, departmentId)
VALUES(5001, "Sui", "Layne", "sui.layne@gmail.com", "2017", "HR Manager", 5);

INSERT INTO EMPLOYEE(id, firstName, lastName, email, hireYear, position, departmentId)
VALUES(5002, "Leota", "Luckett", "leota.luckett@gmail.com", "2017", "HR Advisor", 5);


CREATE TABLE USER 
( 
	id int(11) AUTO_INCREMENT PRIMARY KEY, 
	firstName varchar(255),
	lastName varchar(255),
	email varchar(255), 
	role varchar(20),
	username varchar(20),
	password varchar(20),
	employeeId int(11),
	token varchar(255),

	INDEX(employeeId),
	
	FOREIGN KEY(employeeId)
		REFERENCES employee(id)

	ON UPDATE CASCADE ON DELETE RESTRICT
);

INSERT INTO USER (firstName, lastName, email, role, username, password, employeeId) 
VALUES ("admin", "!", "admin@domain.ca", "Administrator", "admin", "admin", 0);
