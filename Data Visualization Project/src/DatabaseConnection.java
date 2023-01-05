import java.sql.*;

public class DatabaseConnection {
    private final String url = "jdbc:postgresql://localhost/AutoDag";
    private final String user = "postgres";
    private final String password = "12345";

    //Connection to the database
    public Connection connect() { // Method for establishing connection to the database, returns a database object
        Connection connection; // Connection variable
        try { // Starts a connection with database
            connection = DriverManager.getConnection(url,user,password); // Start a connection
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return  connection; // Returns the connection object
    }
}
