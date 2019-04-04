package view;



import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class ConsoleHelper {

    Scanner scanner = new Scanner(System.in);

    SkillView sv = new SkillView();
    UserView uv = new UserView();
    TeamView tv = new TeamView();
    ProjectView pv = new ProjectView();
    CustomerView cv = new CustomerView();


    public void start() throws SQLException, ClassNotFoundException {
        getMenu();
    }

    public static Set<Integer> inputToSetId(String input) {
        // 1) Separate user's input by comma:
        String [] arrayOfId = input.split(",");
        Set<String> idSetString = new HashSet<>(Arrays.asList(arrayOfId));
        //  2) Converting Set<String> to Set<Integer>
        Set<Integer> idSetInteger = new HashSet<>();
        for(String sId : idSetString){
            idSetInteger.add(Integer.parseInt(sId));
        }
        return idSetInteger;
    }

    public void getMenu() throws SQLException, ClassNotFoundException {
        System.out.println("JDBC PROJECT - console CRUD application");
        System.out.println("-------------------------------");
        System.out.println("The application started running at " + LocalDateTime.now());
        System.out.println("--------------------------------------------------------------------------------------------");
        System.out.println("MAIN MENU");
        System.out.println("Enter: 'S' - for Skill, 'U' - for User, 'T' - for Team,'P' - for Project, 'C' - for Customer");
        System.out.println("--------------------------------------------------------------------------------------------");
        String choosingEntity = scanner.next();

        switch(choosingEntity){
            case "S":
                sv.getSkillMenu();
                break;
            case "U":
                uv.getUserMenu();
                break;
            case "T":
                tv.getTeamMenu();
                break;
            case "P":
                pv.getProjectMenu();
                break;
            case "C":
                cv.getCustomerMenu();
                default:
                    System.out.println("Your choice is not right!");
                    choosingEntity = scanner.next();
        }
    }
}
