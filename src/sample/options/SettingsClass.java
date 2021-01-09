package sample.options;

import Controller.DBController;
import ExceptionAr.ExceptionWindow;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import sample.Main;
import java.io.File;
import java.sql.SQLException;

public class SettingsClass {
    public static String user;
    public static String findClients;
    public static String findScans;
    public static String saveConvertedFolder;

    public static void window(String title) {

        Stage primaryStage = new Stage();
        VBox kompa = new VBox();
        kompa.setSpacing(20);
        kompa.setPadding(new Insets(10, 10, 10, 10));
        kompa.setAlignment(Pos.CENTER);
        primaryStage.setTitle(title);
        primaryStage.setScene(new Scene(kompa, 400, 650));
        primaryStage.setResizable(false);

        TextField userName = new TextField("Please, fill with your name");
        userName.setMaxWidth(350);

        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setInitialDirectory(new File("src"));

        Button chooseClientsFolder = new Button("Click here to set clients folder");
        chooseClientsFolder.setPrefSize(350, 50);
        Button chooseScansFolder = new Button("Click here to set scans folder");
        chooseScansFolder.setPrefSize(350, 50);
        Button chooseConvertFolder = new Button("Click here to set convert folder");
        chooseConvertFolder.setPrefSize(350, 50);

        Label clientsFolder = new Label("Here will be the path to folder with clients");
        clientsFolder.setPrefSize(350, 25);
        clientsFolder.setAlignment(Pos.CENTER);
        clientsFolder.setFont(Font.font("Arial"));

        Label scansFolder = new Label("Here will be the path to folder with scans");
        scansFolder.setPrefSize(350, 25);
        scansFolder.setAlignment(Pos.CENTER);
        scansFolder.setFont(Font.font("Arial"));

        Label convertFolder = new Label("Here will be the path to folder with converted files");
        convertFolder.setPrefSize(350, 25);
        convertFolder.setAlignment(Pos.CENTER);
        convertFolder.setFont(Font.font("Arial"));

        Label showUserName = new Label();
        showUserName.setPrefWidth(350);

        chooseScansFolder.setOnAction(e -> {
            System.out.println("wtf");
            File selectedDirectoryScans = directoryChooser.showDialog(primaryStage);
            System.out.println("omg");
            try {
                findScans = selectedDirectoryScans.getAbsolutePath();
                scansFolder.setText(findScans);
                DBController.setFolderScans(findScans);
            } catch (NullPointerException | SQLException ex) {
                ExceptionWindow.problem.setText("The path wasnt save" );
                ExceptionWindow.window("Error!");
                scansFolder.setText("Please choose the folder!");
                /*try {
                    scansFolder.setText(DBController.getFromDBScanFolder());
                } catch (SQLException exc) {
                    exc.printStackTrace();
                }*/
            }
            Main.count =0;
            Main.scans = null;
        });

        chooseClientsFolder.setOnAction(e -> {
            File selectedDirectoryClients = directoryChooser.showDialog(primaryStage);
            try {
                findClients = selectedDirectoryClients.getAbsolutePath();
                clientsFolder.setText(findClients);
                DBController.setFolderWithClients(findClients);
                System.out.println(findClients);
            } catch (NullPointerException | SQLException ex) {
                ExceptionWindow.problem.setText("The path wasnt save");
                ExceptionWindow.window("Error!");
                //clientsFolder.setStyle("-fx-text-fill: red; -fx-font-size: 14px;");
                clientsFolder.setText("Please choose the folder!");
            }
        });

        userName.setOnAction(e -> {
            user = userName.getText();
            userName.setDisable(true);
            try {
                DBController.setNewName(user);
            } catch (SQLException ex) {
                ExceptionWindow.problem.setText("The name wasnt save");
                ExceptionWindow.window("Error!");
                ex.printStackTrace();
            }
        });

        chooseConvertFolder.setOnAction(event -> {
            File selectedDirectoryConvert = directoryChooser.showDialog(primaryStage);
            try {
                saveConvertedFolder = selectedDirectoryConvert.getAbsolutePath();
                convertFolder.setText(saveConvertedFolder);
                DBController.setFolderConvert(saveConvertedFolder);
            } catch (SQLException | NullPointerException e) {
                ExceptionWindow.problem.setText("The path wasnt save");
                ExceptionWindow.window("Error!");
                convertFolder.setText("Please choose the folder!");
            }
        });

        kompa.getChildren().addAll(userName, showUserName, chooseClientsFolder, clientsFolder, chooseScansFolder, scansFolder, chooseConvertFolder, convertFolder);
        primaryStage.showAndWait();
    }

}
