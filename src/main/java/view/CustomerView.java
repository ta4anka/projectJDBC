package view;

import controller.ProjectController;
import controller.CustomerController;
import model.Project;
import model.Customer;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class CustomerView {
    CustomerController customerController = new CustomerController();
    ProjectController projectController = new ProjectController();
    static Scanner scanner = new Scanner(System.in);


    public void getCustomerMenu() throws SQLException, ClassNotFoundException {
        System.out.println("Enter:" +"\n"+
                "'add' for adding new customer" +"\n"+
                "'delete' for deleting customer" +"\n"+
                "'show' for showing all the customers"+"\n"+
                "'get' for for showing a certain customer"+"\n"+
                "'edit' for change a certain customer"+"\n"+
                "'menu' for return to main menu" + "\n" +
                "'exit' for exit");

        String input = scanner.next();
        Customer customerToSave = new Customer();
        Integer id;

        while(!input.equals("exit")){
            switch(input){
                case "add":

                    System.out.println("Enter the name:");
                    input = scanner.next();
                    customerToSave.setName(input);

                   
                    //--------Set of projects' logic---------------------------
                    System.out.println("Choose some projects from this list and enter its id by using ',' :" + "\n"
                            + projectController.findAll());
                    scanner.nextLine();
                    input = scanner.nextLine();

                    Set<Project> projectSet = new HashSet<>();
                    for(Integer sId: ConsoleHelper.inputToSetId(input)){
                        Project projectToSave = projectController.getById(sId);
                        projectSet.add(projectToSave);
                    }
                    customerToSave.setProjects(projectSet);
                    customerController.save(customerToSave);

                    System.out.println("New customer was successfully added!");
                    System.out.println("What do you want to do now?");
                    input = scanner.next();
                    break;

                case "delete":
                    System.out.println("Enter id of deleting customer:");
                    input = scanner.next();
                    id = Integer.parseInt(input);
                    customerController.delete(id);
                    System.out.println("customer was successfully deleted!");
                    System.out.println("What do you want to do now?");
                    input = scanner.next();
                    break;

                case "show":
                    System.out.println(customerController.findAll());
                    System.out.println("What do you want to do now?");
                    input = scanner.next();
                    break;
                case "get":
                    System.out.println("Enter id of customer:");
                    input = scanner.next();
                    id = Integer.parseInt(input);
                    customerController.getById(id);
                    input = scanner.next();
                    break;

                case "edit":
                    System.out.println("Enter id of updating customer:");
                    input = scanner.next();
                    customerToSave.setId(Integer.parseInt(input));

                    System.out.println("Enter the new name:");
                    input = scanner.next();
                    customerToSave.setName(input);

                    customerController.update(customerToSave);
                    System.out.println("customer was successfully changed!");
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
