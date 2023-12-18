import menu.Menu;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
/*    EntityManagerFactory emf=Persistence.createEntityManagerFactory("default");
    EntityManager entityManager=emf.createEntityManager();*/
    new Menu().fistMenu();
    }
}
