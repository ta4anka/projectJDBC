import connection.ConnectionToDB;

import java.sql.SQLException;

public class MainTest {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        ConnectionToDB.getConnectionToDB();
    }
}
