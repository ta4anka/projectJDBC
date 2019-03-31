import connection.ConnectionToDB;
import model.Skill;
import repository.SkillRepository;
import repository.impl.SkillRepositoryImpl;

import java.sql.SQLException;

public class MainTest {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        SkillRepository sr = new SkillRepositoryImpl();
        //Skill skill1 = new Skill(0,"TestSkill");
        //sr.save(skill1);
        System.out.println(sr.getById(12));
        System.out.println(sr.getById(6));

    }
}
