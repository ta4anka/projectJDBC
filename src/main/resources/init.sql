CREATE DATABASE IF NOT EXISTS testDB DEFAULT CHARACTER SET UTF8;
USE testDB;

CREATE TABLE IF NOT EXISTS users (
  id INT(5) AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(64),
  surname VARCHAR(64)
  );

CREATE TABLE IF NOT EXISTS skills (
  id INT(5) AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(64) NOT NULL
);

--  many to many --> users + skills
CREATE TABLE IF NOT EXISTS users_skills(
  user_id INT NOT NULL ,
  skill_id INT NOT NULL ,
  PRIMARY KEY (user_id , skill_id),
  FOREIGN KEY (user_id) REFERENCES users(id),
  FOREIGN KEY (skill_id) REFERENCES skills(id)
);

CREATE TABLE IF NOT EXISTS teams (
  id INT(5) AUTO_INCREMENT PRIMARY KEY,
   name VARCHAR(64) NOT NULL
   );

CREATE TABLE IF NOT EXISTS projects (
  id INT(5) AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(64) NOT NULL
 );

 --  many to many --> project + teams
  CREATE TABLE IF NOT EXISTS teams_projects(
  team_id INT NOT NULL ,
  project_id INT NOT NULL ,
  PRIMARY KEY (team_id,project_id),
  FOREIGN KEY (team_id) REFERENCES teams(id),
  FOREIGN KEY (project_id) REFERENCES projects(id)
  );

  --  many to many --> teams + users

  CREATE TABLE IF NOT EXISTS teams_users(
  team_id INT NOT NULL ,
  user_id INT NOT NULL ,
  PRIMARY KEY (team_id,user_id),
  FOREIGN KEY (team_id) REFERENCES teams(id),
  FOREIGN KEY (user_id) REFERENCES users(id)
  );


CREATE TABLE IF NOT EXISTS customers (
  id INT(5) AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(64) NOT NULL
);

--  many to many --> customers + projects
CREATE TABLE IF NOT EXISTS customers_projects(
  customer_id INT NOT NULL ,
  project_id INT NOT NULL ,
  PRIMARY KEY (customer_id , project_id),
  FOREIGN KEY (customer_id) REFERENCES customers(id),
  FOREIGN KEY (project_id) REFERENCES projects(id)
);










