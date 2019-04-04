package repository.impl;

import connection.ConnectionToDB;
import model.Skill;
import model.User;
import repository.UserRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {

    @Override
    public void save(User user) throws SQLException, ClassNotFoundException {
        String sqlQueryUser = "INSERT INTO users(name,surname) VALUES (?,?)";
        String sqlQueryUsersSkills = "INSERT INTO users_skills(user_id,skill_id) VALUES (?,?)";
        int userId = 0;
        try(Connection conn = ConnectionToDB.getConnectionToDB();
            PreparedStatement prepStatementUser = conn.prepareStatement(sqlQueryUser, Statement.RETURN_GENERATED_KEYS);
            PreparedStatement prepdStatementUsersSkills = conn.prepareStatement(sqlQueryUsersSkills) ) {
            prepStatementUser.setString(1,user.getName());
            prepStatementUser.setString(2,user.getSurname());
            prepStatementUser.executeUpdate();

            // many to many --> "users_skills":
                //1) getting id of user:
            try(ResultSet resultSet = prepStatementUser.getGeneratedKeys()){
                if(resultSet.next()){userId = resultSet.getInt(1);}
            }

            //2)filling "users_skills":
            for( Skill skill : user.getSkills()){
                prepdStatementUsersSkills.setInt(1,userId);
                prepdStatementUsersSkills.setInt(2,skill.getId());
                prepdStatementUsersSkills.executeUpdate();
            }
        }
    }

    @Override
    public List<User> findAll() throws SQLException, ClassNotFoundException {

        List<User> usersList = new ArrayList<>();
        String sqlQuery = "SELECT * FROM users";
        try(Connection conn = ConnectionToDB.getConnectionToDB();
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlQuery)){
            while(resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                User user = new User(id,name,surname);
                usersList.add(user);
            }
        }
        return usersList;
    }

    @Override
    public void update(User user) throws SQLException, ClassNotFoundException {
        String sqlQuery = "UPDATE users SET name = ?,surname = ? WHERE id = ?";

        try(Connection conn = ConnectionToDB.getConnectionToDB();
            PreparedStatement prepStatement = conn.prepareStatement(sqlQuery)){
            prepStatement.setString(1,user.getName());
            prepStatement.setString(2,user.getSurname());
            prepStatement.setInt(3,user.getId());
            prepStatement.executeUpdate();
        }

    }

    @Override
    public void delete(Integer id) throws SQLException, ClassNotFoundException {
        String sqlQuery = "DELETE FROM users WHERE id = ?";
        try(Connection conn = ConnectionToDB.getConnectionToDB();
            PreparedStatement prepStatement = conn.prepareStatement(sqlQuery)){
            prepStatement.setInt(1,id);
            prepStatement.execute();
        }

    }

    @Override
    public User getById(Integer id) throws SQLException, ClassNotFoundException {
        String sqlQuery = "SELECT * FROM users WHERE id = ?";

        try(Connection conn = ConnectionToDB.getConnectionToDB();
            PreparedStatement prepStatement = conn.prepareStatement(sqlQuery)){
            prepStatement.setInt(1,id);
            ResultSet resultSet = prepStatement.executeQuery();
            if(resultSet.next()) {
                return new User(resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("surname")
                );
            }
        }return  null;
    }
}

