package repository.impl;

import connection.ConnectionToDB;
import model.Skill;
import repository.SkillRepository;

import java.sql.*;
import java.util.ArrayList;
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
    public List<Skill> findAll() throws SQLException, ClassNotFoundException {
        List<Skill> skillsList = new ArrayList<>();
        String sqlQuery = "SELECT * FROM skills";
        try(Connection conn = ConnectionToDB.getConnectionToDB();
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlQuery)){
            while(resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                Skill skill = new Skill(id,name);
                skillsList.add(skill);
            }
        }
        return skillsList;
    }

    @Override
    public void update(Skill skill) throws SQLException, ClassNotFoundException {
        String sqlQuery = "UPDATE skills SET name = ? WHERE id = ?";

        try(Connection conn = ConnectionToDB.getConnectionToDB();
            PreparedStatement prepStatement = conn.prepareStatement(sqlQuery)){
            prepStatement.setString(1,skill.getName());
            prepStatement.setInt(2,skill.getId());
            prepStatement.executeUpdate();
        }
    }


    @Override
    public void delete(Integer id) throws SQLException, ClassNotFoundException {
        String sqlQuery = "DELETE FROM skills WHERE id = ?";

        try(Connection conn = ConnectionToDB.getConnectionToDB();
        PreparedStatement prepStatement = conn.prepareStatement(sqlQuery)){
            prepStatement.setInt(1,id);
            prepStatement.execute();
        }
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
