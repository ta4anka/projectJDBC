USE testDB;

INSERT INTO users(name,surname)
VALUES
('Brian','Kernighan'),
('Dennis','Ritchie'),
('Bjarne','Stroustrup'),
('James','Gosling'),
('Steve','Wozniak'),
('Linus','Torvalds'),
('Guido','vanRossum'),
('Brendan','Eich'),
('Brad','Cox');


INSERT INTO skills(name)
VALUES
('Java'),
('C'),
('C++'),
('Python'),
('Objective-c'),
('SQL'),
('HTML'),
('JavaScript'),
('Go'),
('R'),
('Math');


/* -- one combination:
INSERT INTO users_skills(user_id,skill_id)  
SELECT u.id, s.id FROM users AS u
CROSS JOIN skills AS s
WHERE u.name = 'Brian'
AND s.name = 'C';
*/

/* -- all the combinations:
INSERT INTO users_skills(user_id,skill_id)
SELECT u.id, s.id FROM users AS u
CROSS JOIN skills AS s;*/

INSERT INTO users_skills(user_id,skill_id)
VALUES
(1,1),
(1,2), 
(1,3),
(1,11),
(2,1),
(2,3), 
(2,11),

(3,3),
(3,1),
(3,11),

(4,1),
(4,4),
(4,11),

(5,1),
(5,2),
(5,3),
(5,11),
(6,1),
(6,11),
(7,6),
(7,7),

(8,5),
(8,9),

(9,7),
(9,8);

INSERT INTO teams(name)
VALUES
('team_1'),
('team_2'),
('team_3'),
('team_4');

INSERT INTO projects(name)
VALUES
('project_1'),
('project_2'),
('project_3'),
('project_4'),
('project_5'),
('project_6');

INSERT INTO teams_projects(team_id,project_id)
VALUES
(1,4),
(1,5),
(1,6),

(2,2),
(2,3),

(3,3),
(3,4),

(4,1),
(4,2),
(4,3);

INSERT INTO teams_users( team_id, user_id)
VALUES 
(1,1),
(1,2),
(2,3),
(2,4),
(3,5),
(3,6),
(4,7),
(4,8),
(4,9);

INSERT INTO customers (name)
VALUES
('customer_1'),
('customer_2'),
('customer_3'),
('customer_4');

INSERT INTO customers_projects (customer_id,project_id)
VALUES
(1,1),
(2,2),
(2,3),
(3,4),
(3,6),
(4,5),
(4,6);


