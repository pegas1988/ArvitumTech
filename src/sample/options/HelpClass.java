package sample.options;

import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class HelpClass {
    public static void window(String title) {

        Stage primaryStage = new Stage();
        primaryStage.initModality(Modality.APPLICATION_MODAL);
        VBox kompa = new VBox();
        kompa.setSpacing(20);
        kompa.setPadding(new Insets(10, 10,10,10));
        primaryStage.setTitle(title);
        primaryStage.setScene(new Scene(kompa, 500, 650));
        primaryStage.setResizable(false);

        Label simpleInstructionHead = new Label();
        simpleInstructionHead.setText("                     Dear user, here you can find information how to use this software" +"\n" + "\n" + "\n" +
                "           Simple path for one client - 2D scanning - dynamical scanning - converter." + "\n" + "\n" + "\n" +
                "You can use this program in two ways:" + "\n" +
                "   - as an automatic converter (just chose a client) " + "\n" +
                "   - as manual converter (you have to choose also scans for both legs)" + "\n" +
                "Auto converter is recommend, but it will works ONLY if you start converting directly after " + "\n" +
                "dynamical scanning" + "\n" +
                "Manual converting is the right choice if you forget to make converting just after 2D scanning");
        Label firstStep = new Label();
        firstStep.setText("     First step you should make is to set paths to main folders. " + "\n" +
                "   - On the top left corner push the button <Options> and there <Settings>" + "\n" +
                "   - Write your name to the textfield" + "\n" +
                "   - Choose the folder with clients file" + "\n" +
                "   - Choose the folder with clients scans" + "\n" +
                "   - Choose the folder where to save converted files" + "\n" +
                "   - Close the window (this makes soft go back), now you can work with the program");
        Label secondStep = new Label();
        secondStep.setText("    1.For average scans no needs to choose scans," + "\n" +
                "       so the flag in checkbox <Manual choosing> shouldn`t be placed" +"\n" +
                "   - The date is always automatically will be the current day" + "\n" +
                "   - Press the button <Check clients> and chose the one you need" + "\n" +
                "   - Press the big button <Convert> on the bottom side of the window" + "\n" +
                "   - Everything is done now!");

        Label thirdStep = new Label();
        thirdStep.setText("     2.For manual converting " + "\n" +
                "   - Place a flag in checkbox <Manual converting>, table with scans and additional buttons" + "\n" +
                " became active" + "\n" +
                "   - chose a client as in automatic mode" + "\n" +
                "   - chose a scan for left foot and press the button <SetLeft>" + "\n" +
                "   - chose a scan for right foot and press the button <SetRight>" + "\n" +
                "   - Press the big button <Convert> on the bottom side of the window" + "\n" +
                "   - Everything is done now!");

        kompa.getChildren().addAll(simpleInstructionHead, firstStep, secondStep, thirdStep);
        primaryStage.showAndWait();
    }
}
