package connection;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionToDB {

    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/firstDB";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "spring";

    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";


    public static Connection getConnectionToDB() throws SQLException, ClassNotFoundException {
        Class.forName(JDBC_DRIVER);
        return DriverManager.getConnection(DATABASE_URL,USERNAME,PASSWORD);
        }
}




