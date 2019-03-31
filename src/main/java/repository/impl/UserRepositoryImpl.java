package repository.impl;

import connection.ConnectionToDB;
import model.Skill;
import model.User;
import repository.UserRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {

    @Override
    public void save(User user) throws SQLException, ClassNotFoundException {
        String sqlQuery = "INSERT INTO users(name,surname) VALUES (?,?)";
        try(Connection conn = ConnectionToDB.getConnectionToDB();
            PreparedStatement prepStatement = conn.prepareStatement(sqlQuery)) {
            prepStatement.setString(1,user.getName());
            prepStatement.setString(2,user.getSurname());
            prepStatement.execute();

            // many to many --> "users_skills":
                //1) getting id of user:
            ResultSet resultSet = prepStatement.getGeneratedKeys();
            int userId = resultSet.getInt(1);

                //2)filling "users_skills":
            for( Skill skill : user.getSkills()){
                prepStatement.setInt(1,userId);
                prepStatement.setInt(2,skill.getId());
            }
        }
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public void update(User user) {

    }

    @Override
    public void delete(Integer integer) {

    }

    @Override
    public User getById(Integer integer) {
        return null;
    }
}
