import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/airline_reservation";
    private static final String USER = "root";
    private static final String PASSWORD = "YASH2813";

    public static Connection getConnection() {
        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Database connection established.");
            return connection;
        } catch (SQLException e) {
            System.err.println("Database connection failed.");
            e.printStackTrace();
            return null;
        }
    }
}
