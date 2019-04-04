package view;

import controller.SkillController;
import model.Skill;

import java.sql.SQLException;
import java.util.Scanner;

public class SkillView {

    SkillController skillController = new SkillController();
    static Scanner scanner = new Scanner(System.in);

    public void getSkillMenu() throws SQLException, ClassNotFoundException {
        System.out.println("Enter:" +"\n"+
                "'add' for adding new skill" +"\n"+
                "'delete' for deleting skill" +"\n"+
                "'show' for showing All the skills"+"\n"+
                "'get' for for showing a certain skill"+"\n"+
                "'edit' for change a certain skill"+"\n"+
                "'menu' for return to main menu" + "\n" +
                "'exit' for exit");

        String input = scanner.next();
        Skill skillToSave = new Skill();
        Integer id;

        while(!input.equals("exit")){
            switch(input){
                case "add":

                    System.out.println("Enter name of the skill:");
                    input = scanner.next();
                    skillToSave.setName(input);
                    skillController.save(skillToSave);

                    System.out.println("Your skill was successfully added!");
                    System.out.println("What do you want to do now?");
                    input = scanner.next();
                    break;

                case "delete":
                    System.out.println("Enter id of deleting skill:");
                    input = scanner.next();
                    id = Integer.parseInt(input);
                    skillController.delete(id);
                    System.out.println("Your skill was successfully deleted!");
                    System.out.println("What do you want to do now?");
                    input = scanner.next();
                    break;

                case "show":
                    System.out.println(skillController.findAll());
                    System.out.println("What do you want to do now?");
                    input = scanner.next();
                    break;
                case "get":
                    System.out.println("Enter id of skill:");
                    input = scanner.next();
                    Integer idskill = Integer.parseInt(input);
                    skillController.getById(idskill);
                    input = scanner.next();
                    break;

                case "edit":
                    System.out.println("Enter id of updating skill:");
                    input = scanner.next();
                    skillToSave.setId(Integer.parseInt(input));

                    System.out.println("Enter the new name of the skill:");
                    input = scanner.next();
                    skillToSave.setName(input);
                    skillController.update(skillToSave);
                    System.out.println("Your skill was successfully changed!");
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
