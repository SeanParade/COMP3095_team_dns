# mySql version 5.7
DROP DATABASE IF EXISTS COMP3095;

CREATE DATABASE IF NOT EXISTS COMP3095;
USE COMP3095;
grant all on COMP3095.* to 'admin'@'localhost' identified by 'admin'; 

CREATE TABLE DEPARTMENT
(
	id int(11) AUTO_INCREMENT PRIMARY KEY,
	departmentName varchar(255) NOT NULL UNIQUE,
	location varchar(255)
	);
    
INSERT INTO DEPARTMENT(departmentName, location)
VALUES
("Accounting", "Room 1B"),
("Administration", "Room 1A"),
("Marketing", "Room 2B"),
("IT", "Room B01"),
("Human Resources", "Room 1D");


CREATE TABLE EGROUP
(
	id int(11) AUTO_INCREMENT PRIMARY KEY,
	groupName varchar(255) UNIQUE,
	departmentId int(11)
	
);

CREATE TABLE EMPLOYEE
(
	id int(11) PRIMARY KEY,
	firstName varchar(255) NOT NULL,
	lastName varchar(255) NOT NULL,
	email varchar(255) NOT NULL,
    username varchar(255) UNIQUE,
	hireYear varchar(255),
	departmentId int(11),
	groupId int(11),
    role varchar(255),
    password varchar(30),
    token varchar(255),
    UNIQUE(firstName, lastname, email),
	
	
	INDEX(departmentId),

	FOREIGN KEY(departmentId)
		REFERENCES department(id)
		ON UPDATE CASCADE ON DELETE RESTRICT

);

INSERT INTO EMPLOYEE(id, firstName, lastName, email, hireYear, role, departmentId, username, password)
VALUES
(9999, "Sergio", "Santilli", "admin@domain.ca", "2015", "Administrator", 4, "admin", "admin"),
#accounting
(1001,"Rich", "Gregory", "rich.gregory@gmail.com", "2017", "Accountant", 1, "rgregory", "password"),
(1002, "Nita", "Cochrane", "nita.cochrane@gmail.com", "2017", "Accountant", 1, "ncochrane", "password"),
#admin
(2001, "Simon", "Martin", "simon.martin@gmail.com", "2017", "Receptionist", 2, "smartin", "password"),
(2002, "Mindy", "Kelly", "mindy.kelly@gmail.com", "2017", "Office Administrator", 2, "mkelly", "password"),
#marketing
(3001, "Magaret", "Oneill", "magaret.oneill@gmail.com", "2017", "Sales", 3, "moneill", "password"),
(3002, "Donnie", "Zhang", "donnie.zhang@gmail.com", "2017", "Sales", 3, "dzhang",  "password"),
#it
(4001, "Hyon", "Barnhill", "hyon.barnhill@gmail.com", "2017", "Network Admin", 4, "hbarnhill", "password"),
(4002, "Jazmine", "Velasquez", "jazmine.velasquez@gmail.com", "2017", "Database Admin", 4, "jvelasquez", "password"),
(4003, "Deb", "Jude", "deb.jude@gmail.com", "2017", "Web Developer", 4, "djude", "password"),
#human resources
(5001, "Sui", "Layne", "sui.layne@gmail.com", "2017", "HR Manager", 5, "slayne", "password"),
(5002, "Leota", "Luckett", "leota.luckett@gmail.com", "2017", "HR Advisor", 5, "lluckett", "password");

CREATE TABLE REPORT_TEMPLATE
(
	id int(11) AUTO_INCREMENT PRIMARY KEY,
	templateName varchar(30) NOT NULL,
	departmentId int(11) NOT NULL,
	sec1_title varchar(30) NOT NULL,
	sec2_title varchar(30) NOT NULL,
	sec3_title varchar(30) NOT NULL,
	sec1_criteria varchar(255) NOT NULL,
	sec2_criteria varchar(255) NOT NULL,
	sec3_criteria varchar(255) NOT NULL,
	
	UNIQUE(templateName, departmentId),
	
	INDEX(departmentId),
	
	FOREIGN KEY(departmentId)
		REFERENCES department(id)
		ON UPDATE CASCADE ON DELETE RESTRICT
);

INSERT INTO REPORT_TEMPLATE (templateName, departmentId, sec1_title, sec2_title, sec3_title, 
                             sec1_criteria, sec2_criteria, sec3_criteria) VALUES 
("Sample Report", 4, "Contribution", "Responsibility", "Value", 
"Research and Gathering,4,Sharing Information,4,Using Time Wisely,4,Ready to Work,5",
"Fulfill Teams Role,4,Sharing Work Equally,5,Helping Team Members,4",
"Listens to others,5,Include Teammates,4,Make fair decisions,3");

CREATE TABLE REPORT
(
	id int(11) AUTO_INCREMENT PRIMARY KEY,
	templateId int(11) NOT NULL,
	title varchar(255) NOT NULL,
    reportType varchar(15) NOT NULL,
    sec1_evaluation varchar(255) NOT NULL,
	sec2_evaluation varchar(255) NOT NULL,
	sec3_evaluation varchar(255) NOT NULL,
	date DATE NOT NULL,
	departmentId int(11) NOT NULL,
    comment1 varchar(500),
    comment2 varchar(500),
    comment3 varchar(500),
	groupId int(11),
	employeeId int(11),
	totalEvaluation int(2),
    
		UNIQUE(templateId, title),
		INDEX(templateId), INDEX(departmentId), INDEX(groupId), INDEX(employeeId),

		FOREIGN KEY(departmentId)
			REFERENCES department(id)
			ON UPDATE CASCADE ON DELETE RESTRICT,
            
		FOREIGN KEY(templateId)
			REFERENCES report_template(id)
            ON UPDATE CASCADE ON DELETE RESTRICT,

		FOREIGN KEY(groupId)
			REFERENCES egroup(id)
			ON UPDATE CASCADE ON DELETE RESTRICT,

		FOREIGN KEY(employeeId)
			REFERENCES employee(id)
			ON UPDATE CASCADE ON DELETE RESTRICT	
);

INSERT INTO REPORT (templateId, title, reportType, sec1_evaluation, sec2_evaluation, sec3_evaluation, date, departmentId, comment1, comment2, comment3, employeeId, totalEvaluation)
VALUES (1, "Sample Employee Report", "employee", "Research and Gathering,4,Sharing Information,4,Using Time Wisely,4,Ready to Work,5",
"Fulfill Teams Role,4,Sharing Work Equally,5,Helping Team Members,4",
"Listens to others,5,Include Teammates,4,Make fair decisions,3",CURDATE(),4,"Fantastic. Keep it up","This was better last year.", "Great.", 9999, 42);

CREATE TABLE EMPLOYEE_ATTENDANCE
(
	id int(11) AUTO_INCREMENT PRIMARY KEY,
	date DATE NOT NULL,
	present BOOLEAN NOT NULL,
	employeeId int(11) NOT NULL,
	UNIQUE(date, employeeId),

	INDEX(employeeId),
	
	FOREIGN KEY(employeeId)
		REFERENCES employee(id)
		ON UPDATE CASCADE ON DELETE RESTRICT
);

