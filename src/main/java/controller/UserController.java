package controller;

import model.User;
import repository.UserRepository;
import repository.impl.UserRepositoryImpl;

import java.sql.SQLException;
import java.util.List;

public class UserController{
    UserRepository userRepository = new UserRepositoryImpl();


    public void save(User user) throws SQLException, ClassNotFoundException {
        if(user == null){
            throw new IllegalArgumentException();
        }else {
            userRepository.save(user);
        }
    }

    public List<User> findAll() throws SQLException, ClassNotFoundException {
        return userRepository.findAll();
    }

    public void update(User user) throws SQLException, ClassNotFoundException {
        if(user == null){
            throw  new IllegalArgumentException();
        }else userRepository.update(user);
    }


    public void delete(Integer id) throws SQLException, ClassNotFoundException {
        if(id == null || id < 0){
            throw new IllegalArgumentException();
        }else {
            userRepository.delete(id);
        }
    }

    public User getById(Integer id) throws SQLException, ClassNotFoundException {
        if(id == null || id < 0){
            throw new IllegalArgumentException();
        }else {

        }return userRepository.getById(id);
    }

}

