# Computer Parts List
This project was created as an assignment for JavaRush Internship program.
The Project was build as a single-page web application.
Technologies used:
  - Maven;
  - Spring-Boot;
  - Hibernate;
  - Tomcat;
  - MySql;
  - HTML;
  - ThymeLeaf;
  - Bootstrap;
  - jQuery.
  
MySQL DB configurations:

  - DB: spring.datasource.url=jdbc:mysql://localhost:3306/test;
  - Table: part.
  - Username: root, Password: root.
  
The table is set up with some data (38 parts) during the initial load of the application.

The Application runs on "http://localhost:8080/".

Basic functionality implimented:
  - Load all the Computer Parts from the DB to the main page with the pagination (10 parts per page);
  - Filter the parts by optionality;
  - Edit parts;
  - Delete parts;
  - Add parts;
  - Search parts by part name (or by fragment of the name);
  - Reset filters;
  - Reset all parameters;
  - Present the amount of Computers available to be build based on all the mandatory parts in the DB.
