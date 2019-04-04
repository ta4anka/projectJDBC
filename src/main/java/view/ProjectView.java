package view;

import controller.ProjectController;
import controller.TeamController;
import model.Project;
import model.Team;


import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;


public class ProjectView {
    TeamController teamController = new TeamController();
    ProjectController projectController = new ProjectController();

    static Scanner scanner = new Scanner(System.in);



    public void getProjectMenu() throws SQLException, ClassNotFoundException {
        System.out.println("Enter:" +"\n"+
                "'add' for adding new project" +"\n"+
                "'delete' for deleting project" +"\n"+
                "'show' for showing all the projects"+"\n"+
                "'get' for for showing a certain project"+"\n"+
                "'edit' for change a certain project"+"\n"+
                "'menu' for return to main menu" + "\n" +
                "'exit' for exit");

        String input = scanner.next();
        Project projectToSave = new Project();
        Integer id;
        BigDecimal cost;

        while(!input.equals("exit")){
            switch(input){
                case "add":

                    System.out.println("Enter the name:");
                    input = scanner.next();
                    projectToSave.setName(input);

                    System.out.println("Enter the cost:");
                    input = scanner.next();
                    cost = new BigDecimal(input);
                    projectToSave.setCost(cost);

                    //--------Set of team's logic---------------------------
                    System.out.println("Choose some teams from this list and enter its id by using ',' :" + "\n"
                            + teamController.findAll());
                    scanner.nextLine();
                    input = scanner.nextLine();

                    Set<Team> teamSet = new HashSet<>();
                    for(Integer sId: ConsoleHelper.inputToSetId(input)){
                        Team teamToSave = teamController.getById(sId);
                        teamSet.add(teamToSave);
                    }
                    projectToSave.setTeams(teamSet);
                    projectController.save(projectToSave);

                    System.out.println("New project was successfully added!");
                    System.out.println("What do you want to do now?");
                    input = scanner.next();
                    break;

                case "delete":
                    System.out.println("Enter id of deleting project:");
                    input = scanner.next();
                    id = Integer.parseInt(input);
                    projectController.delete(id);
                    System.out.println("project was successfully deleted!");
                    System.out.println("What do you want to do now?");
                    input = scanner.next();
                    break;

                case "show":
                    System.out.println(projectController.findAll());
                    System.out.println("What do you want to do now?");
                    input = scanner.next();
                    break;
                case "get":
                    System.out.println("Enter id of project:");
                    input = scanner.next();
                    id = Integer.parseInt(input);
                    projectController.getById(id);
                    input = scanner.next();
                    break;

                case "edit":
                    System.out.println("Enter id of updating project:");
                    input = scanner.next();
                    projectToSave.setId(Integer.parseInt(input));

                    System.out.println("Enter the new name:");
                    input = scanner.next();
                    projectToSave.setName(input);

                    System.out.println("Enter the new cost:");
                    input = scanner.next();
                    cost = new BigDecimal(input);
                    projectToSave.setCost(cost);

                    projectController.update(projectToSave);

                    System.out.println("project was successfully changed!");
                    System.out.println("What do you want to do now?");
                    input = scanner.next();
                    break;

                case "menu":
                    ConsoleHelper ch = new ConsoleHelper();
                    ch.getMenu();

                default:
                    System.out.println("Please,make your choice!");
                    input = scanner.next();
            }
        }

    }
}
