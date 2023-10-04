package connection;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcConnection {

    private static Connection connection;
    static {

        try {
            connection   = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/postgres","postgres","abbas1381.aa"   );
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }


    public static Connection getConnection(){
        return connection;
    }
}
