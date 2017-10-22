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

	INDEX(employeeId),
	
	FOREIGN KEY(employeeId)
		REFERENCES employee(id)

	ON UPDATE CASCADE ON DELETE RESTRICT
);

INSERT INTO USER (firstName, lastName, email, role, username, password, employeeId) 
VALUES (NULL, NULL, "admin@domain.ca", NULL, "admin", "admin", NULL);
