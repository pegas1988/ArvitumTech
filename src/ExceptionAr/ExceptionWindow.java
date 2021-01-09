package ExceptionAr;

import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.FlowPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ExceptionWindow {
    public static Label problem = new Label();
    public static String message = "Huston, we have got a problem!";
    public static void window(String title) {

        Stage primaryStage = new Stage();
        primaryStage.initModality(Modality.APPLICATION_MODAL);
        FlowPane kompa = new FlowPane(10, 10);
        kompa.setOrientation(Orientation.VERTICAL);
        kompa.setAlignment(Pos.CENTER);

        primaryStage.setTitle(title);
        primaryStage.setScene(new Scene(kompa, 300, 100 ));
        primaryStage.setResizable(false);

        Label text = new Label(message);
        Separator separ = new Separator();

        kompa.getChildren().addAll(problem);
        primaryStage.showAndWait();
    }
}
