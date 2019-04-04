package view;


import controller.TeamController;
import controller.UserController;
import model.Team;
import model.User;


import java.sql.SQLException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class TeamView {

    UserController userController = new UserController();
    TeamController teamController = new TeamController();

    static Scanner scanner = new Scanner(System.in);



    public void getTeamMenu() throws SQLException, ClassNotFoundException {
        System.out.println("Enter:" +"\n"+
                "'add' for adding new team" +"\n"+
                "'delete' for deleting team" +"\n"+
                "'show' for showing all the teams"+"\n"+
                "'get' for for showing a certain team"+"\n"+
                "'edit' for change a certain team"+"\n"+
                "'menu' for return to main menu" + "\n" +
                "'exit' for exit");

        String input = scanner.next();
        Team teamToSave = new Team();
        Integer id;

        while(!input.equals("exit")){
            switch(input){
                case "add":

                    System.out.println("Enter the name:");
                    input = scanner.next();
                    teamToSave.setName(input);

                    //--------Set of user's logic---------------------------
                    System.out.println("Choose some users from this list and enter its id by using ',' :" + "\n"
                            + userController.findAll());
                    scanner.nextLine();
                    input = scanner.nextLine();

                    Set<User> userSet = new HashSet<>();
                    for(Integer sId: ConsoleHelper.inputToSetId(input)){
                        User userToSave = userController.getById(sId);
                        userSet.add(userToSave);
                    }
                    teamToSave.setUsers(userSet);
                    teamController.save(teamToSave);

                    System.out.println("New team was successfully added!");
                    System.out.println("What do you want to do now?");
                    input = scanner.next();
                    break;

                case "delete":
                    System.out.println("Enter id of deleting team:");
                    input = scanner.next();
                    id = Integer.parseInt(input);
                    teamController.delete(id);
                    System.out.println("Team was successfully deleted!");
                    System.out.println("What do you want to do now?");
                    input = scanner.next();
                    break;

                case "show":
                    System.out.println(teamController.findAll());
                    System.out.println("What do you want to do now?");
                    input = scanner.next();
                    break;
                case "get":
                    System.out.println("Enter id of team:");
                    input = scanner.next();
                    id = Integer.parseInt(input);
                    teamController.getById(id);
                    input = scanner.next();
                    break;

                case "edit":
                    System.out.println("Enter id of updating team:");
                    input = scanner.next();
                    teamToSave.setId(Integer.parseInt(input));

                    System.out.println("Enter the new name:");
                    input = scanner.next();
                    teamToSave.setName(input);



                    teamController.update(teamToSave);
                    System.out.println("Team was successfully changed!");
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
