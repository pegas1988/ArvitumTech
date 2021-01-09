package View.labels;

import Controller.DBController;
import javafx.scene.control.Label;

import java.sql.SQLException;


public class LabelGreeting {

    static String greeting;

    static {
        try {
            if (!DBController.getFromDBName().equals(null))
                greeting = "Hello " + DBController.getFromDBName() + "!";
            else
                greeting = "Please, set user name!";
        } catch (SQLException e) {
            //e.printStackTrace();
        }
    }

    public static Label greetings = new Label(greeting);

}
