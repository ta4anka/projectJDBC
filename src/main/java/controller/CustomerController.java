package controller;

import model.Customer;
import repository.CustomerRepository;
import repository.impl.CustomerRepositoryImpl;

import java.sql.SQLException;
import java.util.List;

public class CustomerController{
    CustomerRepository customerRepository = new CustomerRepositoryImpl();

    public void save(Customer customer) throws SQLException, ClassNotFoundException {
        if(customer == null){
            throw new IllegalArgumentException();
        }else {
            customerRepository.save(customer);
        }

    }

    public List<Customer> findAll() throws SQLException, ClassNotFoundException {
        return customerRepository.findAll();
    }

    public void update(Customer customer) throws SQLException, ClassNotFoundException {
        if(customer == null){
            throw  new IllegalArgumentException();
        }else customerRepository.update(customer);

    }

    public void delete(Integer id) throws SQLException, ClassNotFoundException {
        if(id == null || id < 0){
            throw new IllegalArgumentException();
        }else {
            customerRepository.delete(id);
        }

    }


    public Customer getById(Integer id) throws SQLException, ClassNotFoundException {
        if(id == null || id < 0){
            throw new IllegalArgumentException();
        }else {

        }return customerRepository.getById(id);
    }
}
