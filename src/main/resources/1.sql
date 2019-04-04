USE testDB;
-- Adding a field 'cost' in the 'projects' table.
/* 
ALTER TABLE table
ADD [COLUMN] column_name column_definition [FIRST|AFTER existing_column];
*/

ALTER TABLE projects
 ADD COLUMN cost DECIMAL(10,2) NOT NULL AFTER name;

UPDATE projects 
SET cost  = 745000 WHERE id =1;

UPDATE projects 
SET cost  = 20000 WHERE id =2; 

UPDATE projects 
SET cost  = 30000 WHERE id =3; 

UPDATE projects 
SET cost  = 40000 WHERE id =4; 

UPDATE projects 
SET cost  = 50000 WHERE id =5; 

UPDATE projects 
SET cost  = 60000 WHERE id =6; 
 
-- checking:
SELECT * FROM projects;

/*
+----+-----------+-----------+
| id | name      | cost      |
+----+-----------+-----------+
|  1 | project_1 | 745000.00 |
|  2 | project_2 |  20000.00 |
|  3 | project_3 |  30000.00 |
|  4 | project_4 |  40000.00 |
|  5 | project_5 |  50000.00 |
|  6 | project_6 |  60000.00 |
+----+-----------+-----------+
*/