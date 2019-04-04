package repository.impl;

import connection.ConnectionToDB;
import model.Skill;
import model.Team;
import model.User;
import repository.TeamRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TeamRepositoryImpl implements TeamRepository {
    @Override
    public void save(Team team) throws SQLException, ClassNotFoundException {
        String sqlQueryTeam = "INSERT INTO teams(name) VALUES (?)";


        String sqlQueryTeamsUsers = "INSERT INTO teams_users(team_id,user_id) VALUES (?,?)";
        int teamId = 0;
        try(Connection conn = ConnectionToDB.getConnectionToDB();
            PreparedStatement prepStatementTeam = conn.prepareStatement(sqlQueryTeam , Statement.RETURN_GENERATED_KEYS);
            PreparedStatement prepdStatementTeamsUsers = conn.prepareStatement(sqlQueryTeamsUsers) ) {
            prepStatementTeam.setString(1,team.getName());

            prepStatementTeam.executeUpdate();


            // many to many --> "teams_users":
            //1) getting id of team:
            try(ResultSet resultSet = prepStatementTeam.getGeneratedKeys()){
                if(resultSet.next()){teamId = resultSet.getInt(1);}
            }

            //2)filling "teams_projects":
            for( User user: team.getUsers()){
                prepdStatementTeamsUsers.setInt(1,teamId);
                prepdStatementTeamsUsers.setInt(2,user.getId());

                prepdStatementTeamsUsers.executeUpdate();
            }
        }



    }

    @Override
    public List<Team> findAll() throws SQLException, ClassNotFoundException {
        List<Team> teamsList = new ArrayList<>();
        String sqlQuery = "SELECT * FROM teams";
        try(Connection conn = ConnectionToDB.getConnectionToDB();
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlQuery)){
            while(resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                Team team = new Team(id,name);
                teamsList.add(team);
            }
        }
        return teamsList;
    }

    @Override
    public void update(Team team) throws SQLException, ClassNotFoundException {
        String sqlQuery = "UPDATE teams SET name = ? WHERE id = ?";

        try(Connection conn = ConnectionToDB.getConnectionToDB();
            PreparedStatement prepStatement = conn.prepareStatement(sqlQuery)){
            prepStatement.setString(1,team.getName());
            prepStatement.setInt(2,team.getId());
            prepStatement.executeUpdate();
        }

    }

    @Override
    public void delete(Integer id) throws SQLException, ClassNotFoundException {
        String sqlQuery = "DELETE FROM teams WHERE id = ?";

        try(Connection conn = ConnectionToDB.getConnectionToDB();
            PreparedStatement prepStatement = conn.prepareStatement(sqlQuery)){
            prepStatement.setInt(1,id);
            prepStatement.execute();
        }

    }

    @Override
    public Team getById(Integer id) throws SQLException, ClassNotFoundException {
        String sqlQuery = "SELECT * FROM teams WHERE id = ?";

        try(Connection conn = ConnectionToDB.getConnectionToDB();
            PreparedStatement prepStatement = conn.prepareStatement(sqlQuery)){
            prepStatement.setInt(1,id);
            ResultSet resultSet = prepStatement.executeQuery();

            if(resultSet.next()) {
                return new Team(resultSet.getInt("id"), resultSet.getString("name"));
            }
        }return  null;
    }
}
