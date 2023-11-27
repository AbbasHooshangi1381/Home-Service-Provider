package application;

import menu.Menu;

import java.sql.SQLException;

public class ApplicationContext {

    private static Menu menu;

    public static Menu menu() throws SQLException {
        if (menu==null){
            menu=new Menu();
        }
        return menu;
    }


}
