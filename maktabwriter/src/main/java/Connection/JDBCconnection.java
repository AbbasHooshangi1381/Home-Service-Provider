package Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCconnection {
    Connection connection= DriverManager.getConnection
            ("jdbc:postgresql://localhost:5432/postgres","postgres","abbas1381.aa");

    public JDBCconnection() throws SQLException {
    }
}
