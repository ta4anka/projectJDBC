package repository.impl;

import connection.ConnectionToDB;
import model.Customer;
import model.Project;
import repository.CustomerRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepositoryImpl implements CustomerRepository {
    @Override
    public void save(Customer customer) throws SQLException, ClassNotFoundException {
        String sqlQueryCustomer = "INSERT INTO customers(name) VALUES (?)";
        String sqlQueryCustomersProjects = "INSERT INTO customers_projects(customer_id,project_id) VALUES (?,?)";

        int customerId = 0;

        try(Connection conn = ConnectionToDB.getConnectionToDB();
            PreparedStatement prepStatementCustomer = conn.prepareStatement(sqlQueryCustomer,Statement.RETURN_GENERATED_KEYS);

            PreparedStatement prepdStatementCustomersProjects = conn.prepareStatement(sqlQueryCustomersProjects)) {

            prepStatementCustomer.setString(1,customer.getName());
            prepStatementCustomer.executeUpdate();


            // many to many --> "customers_projects":
            //1) getting id of team:
            try(ResultSet resultSet = prepStatementCustomer.getGeneratedKeys()){
                if(resultSet.next()){customerId = resultSet.getInt(1);}
            }

            //2)filling "customers_projects":
            for( Project project: customer.getProjects()){
                prepdStatementCustomersProjects.setInt(1,customerId);
                prepdStatementCustomersProjects.setInt(2,project.getId());
                prepdStatementCustomersProjects.executeUpdate();
            }
        }
    }

    @Override
    public List<Customer> findAll() throws SQLException, ClassNotFoundException {
        List<Customer> list = new ArrayList<>();
        String sqlQuery = "SELECT * FROM customers";
        try(Connection conn = ConnectionToDB.getConnectionToDB();
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlQuery)){
            while(resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                Customer customer = new Customer(id,name);
                list.add(customer);
            }
        }
        return list;
    }

    @Override
    public void update(Customer customer) throws SQLException, ClassNotFoundException {
        String sqlQuery = "UPDATE customers SET name = ? WHERE id = ?";

        try(Connection conn = ConnectionToDB.getConnectionToDB();
            PreparedStatement prepStatement = conn.prepareStatement(sqlQuery)){
            prepStatement.setString(1,customer.getName());
            prepStatement.setInt(2,customer.getId());
            prepStatement.executeUpdate();
        }

    }

    @Override
    public void delete(Integer id) throws SQLException, ClassNotFoundException {
        String sqlQuery = "DELETE FROM customers WHERE id = ?";

        try(Connection conn = ConnectionToDB.getConnectionToDB();
            PreparedStatement prepStatement = conn.prepareStatement(sqlQuery)){
            prepStatement.setInt(1,id);
            prepStatement.execute();
        }


    }

    @Override
    public Customer getById(Integer id) throws SQLException, ClassNotFoundException {
        String sqlQuery = "SELECT * FROM customers WHERE id = ?";

        try(Connection conn = ConnectionToDB.getConnectionToDB();
            PreparedStatement prepStatement = conn.prepareStatement(sqlQuery)){
            prepStatement.setInt(1,id);
            ResultSet resultSet = prepStatement.executeQuery();

            if(resultSet.next()) {
                return new Customer(resultSet.getInt("id"), resultSet.getString("name"));
            }
        }return  null;
    }
}

