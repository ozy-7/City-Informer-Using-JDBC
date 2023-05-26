City Population-Country Information App

---------------------------------------

Things to do before running:

1- You have to import "world.sql" file to your MySQL (MySQL Workbench -> (Log in) -> Server -> Data Import -> Import from Self-Contained File -> (Select "world.sql") -> Start Import).

2- Import the file "mysql-connector-j-8.0.32.jar" as an external library to your IDE.

3-Program should work now. If you are getting error "Error java.sql.SQLException: Access denied for user 'root'@'localhost' (using password: YES)", you have to edit "DBProperties.txt" according to your mysql user information.

-----------------------------------------------------

-This program is to get information about cities from a sql table.

-Type some characters into text field, and get cities that includes your character string in their names.

-If search button is pressed without nothing typed, then all cities will be shown on table.


----------------- INSERT, UPDATE, DELETE AND SORT BUTTONS WILL BE ADDED ON THE NEXT UPDATE -----------------
