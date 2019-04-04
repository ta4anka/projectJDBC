package controller;

import model.Project;
import repository.ProjectRepository;
import repository.impl.ProjectRepositoryImpl;

import java.sql.SQLException;
import java.util.List;

public class ProjectController{
    ProjectRepository projectRepository = new ProjectRepositoryImpl();

    public void save(Project project) throws SQLException, ClassNotFoundException {
        if(project == null){
            throw new IllegalArgumentException();
        }else {
            projectRepository.save(project);
        }
    }

    public List<Project> findAll() throws SQLException, ClassNotFoundException {
        return projectRepository.findAll();
    }


    public void update(Project project) throws SQLException, ClassNotFoundException {
        if(project == null){
            throw  new IllegalArgumentException();
        }else projectRepository.update(project);
    }


    public void delete(Integer id) throws SQLException, ClassNotFoundException {
        if(id == null || id < 0){
            throw new IllegalArgumentException();
        }else {
            projectRepository.delete(id);
        }
    }

    public Project getById(Integer id) throws SQLException, ClassNotFoundException {
        if(id == null || id < 0){
            throw new IllegalArgumentException();
        }else {

        }return projectRepository.getById(id);
    }
}

