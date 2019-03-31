package repository.impl;

import connection.ConnectionToDB;
import model.Skill;
import repository.SkillRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SkillRepositoryImpl implements SkillRepository {
    @Override
    public void save(Skill skill) throws SQLException, ClassNotFoundException {
        String sqlQuery = "INSERT INTO skills(name) VALUES (?)";

        try(Connection conn = ConnectionToDB.getConnectionToDB();
            PreparedStatement prepdStatement = conn.prepareStatement(sqlQuery)){
            prepdStatement.setString(1,skill.getName());
            prepdStatement.execute();
        }

    }

    @Override
    public List<Skill> findAll() {
        return null;
    }

    @Override
    public void update(Skill skill) {

    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public Skill getById(Integer id) throws SQLException, ClassNotFoundException {
        String sqlQuery = "SELECT * FROM skills WHERE id = ?";

        try(Connection conn = ConnectionToDB.getConnectionToDB();
        PreparedStatement prepStatement = conn.prepareStatement(sqlQuery)){
            prepStatement.setInt(1,id);
            ResultSet resultSet = prepStatement.executeQuery();

            if(resultSet.next()) {
                return new Skill(resultSet.getInt("id"), resultSet.getString("name"));
            }
        }return  null;
    }
}
