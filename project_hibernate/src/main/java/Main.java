
import menu.Menu;
import util.EntityManagerFactoryProvider;
import java.sql.SQLException;
public class Main {
    public static void main(String[] args) throws SQLException {
      //  EntityManagerFactoryProvider.getEntityManagerFactory().createEntityManager();
        new Menu().firstMenu();
    }
}
