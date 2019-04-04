package controller;

import model.Team;
import repository.TeamRepository;
import repository.impl.TeamRepositoryImpl;

import java.sql.SQLException;
import java.util.List;

public class TeamController{

    TeamRepository teamRepository = new TeamRepositoryImpl();

    public void save(Team team) throws SQLException, ClassNotFoundException {
        if(team == null){
            throw new IllegalArgumentException();
        }else {
            teamRepository.save(team);
        }

    }


    public List<Team> findAll() throws SQLException, ClassNotFoundException {
        return teamRepository.findAll();
    }


    public void update(Team team) throws SQLException, ClassNotFoundException {
        if(team== null){
            throw  new IllegalArgumentException();
        }else teamRepository.update(team);

    }


    public void delete(Integer id) throws SQLException, ClassNotFoundException {
        if(id == null || id < 0){
            throw new IllegalArgumentException();
        }else {
            teamRepository.delete(id);
        }

    }


    public Team getById(Integer id) throws SQLException, ClassNotFoundException {
        if(id == null || id < 0){
            throw new IllegalArgumentException();
        }else {

        }return teamRepository.getById(id);
    }
}
