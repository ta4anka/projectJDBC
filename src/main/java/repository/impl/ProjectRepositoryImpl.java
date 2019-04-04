package repository.impl;

import connection.ConnectionToDB;
import model.Project;
import model.Skill;
import model.Team;
import repository.ProjectRepository;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProjectRepositoryImpl implements ProjectRepository {
    @Override
    public void save(Project project) throws SQLException, ClassNotFoundException {

        String sqlQueryUser = "INSERT INTO projects(name,cost) VALUES (?,?)";
        String sqlQueryUsersSkills = "INSERT INTO teams_projects(team_id,project_id) VALUES (?,?)";
        int projectId = 0;
        try(Connection conn = ConnectionToDB.getConnectionToDB();
            PreparedStatement prepStatementProject = conn.prepareStatement(sqlQueryUser, Statement.RETURN_GENERATED_KEYS);
            PreparedStatement prepdStatementTeamsProjects = conn.prepareStatement(sqlQueryUsersSkills) ) {
            prepStatementProject.setString(1,project.getName());
            prepStatementProject.setBigDecimal(2,project.getCost());
            prepStatementProject.executeUpdate();



            // many to many --> "teams_projects":
            //1) getting id of project:
            try(ResultSet resultSet = prepStatementProject.getGeneratedKeys()){
                if(resultSet.next()){projectId = resultSet.getInt(1);}
            }

            //2)filling "teams_projects":
            for( Team team : project.getTeams()){
                prepdStatementTeamsProjects.setInt(1,team.getId());
                prepdStatementTeamsProjects.setInt(2,projectId);
                prepdStatementTeamsProjects.executeUpdate();
            }
        }

    }

    @Override
    public List<Project> findAll() throws SQLException, ClassNotFoundException {
        List<Project> projectsList = new ArrayList<>();
        String sqlQuery = "SELECT * FROM projects";
        try(Connection conn = ConnectionToDB.getConnectionToDB();
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlQuery)){
            while(resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                BigDecimal cost = resultSet.getBigDecimal("cost");
                Project project = new Project(id,name,cost);
                projectsList.add(project);
            }
        }
        return projectsList;
    }

    @Override
    public void update(Project project) throws SQLException, ClassNotFoundException {
        String sqlQuery = "UPDATE projects SET name = ?, cost = ? WHERE id = ?";

        try(Connection conn = ConnectionToDB.getConnectionToDB();
            PreparedStatement prepStatement = conn.prepareStatement(sqlQuery)){
            prepStatement.setString(1,project.getName());
            prepStatement.setBigDecimal(2,project.getCost());
            prepStatement.setInt(3,project.getId());
            prepStatement.executeUpdate();
        }

    }

    @Override //+++++++++++++++++++++++++++++
    public void delete(Integer id) throws SQLException, ClassNotFoundException {
        String sqlQuery = "DELETE FROM projects WHERE id = ?";

        try(Connection conn = ConnectionToDB.getConnectionToDB();
            PreparedStatement prepStatement = conn.prepareStatement(sqlQuery)){
            prepStatement.setInt(1,id);
            prepStatement.execute();
        }

    }

    @Override
    public Project getById(Integer id) throws SQLException, ClassNotFoundException {
        String sqlQuery = "SELECT * FROM projects WHERE id = ?";

        try(Connection conn = ConnectionToDB.getConnectionToDB();
            PreparedStatement prepStatement = conn.prepareStatement(sqlQuery)){
            prepStatement.setInt(1,id);
            ResultSet resultSet = prepStatement.executeQuery();

            if(resultSet.next()) {
                return new Project(resultSet.getInt("id"), resultSet.getString("name"),resultSet.getBigDecimal("cost"));
            }
        }return  null;
    }
}

