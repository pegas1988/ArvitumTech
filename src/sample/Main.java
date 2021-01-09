package sample;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.sql.*;

import Controller.ConverterClass;
import Controller.DBController;
import Controller.SearchScanClass;
import ExceptionAr.ExceptionWindow;
import View.buttons.*;
import View.labels.*;
import javafx.application.Application;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import View.DatePicker.DatePicker;
import Model.objects.Scans;
import sample.options.HelpClass;
import sample.options.ImproveWithLeftAndRightLetterClass;
import sample.options.ScanTimeClass;
import sample.options.SettingsClass;
import Model.objects.Client;
import View.tables.TableClients;
import View.tables.TableScans;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import static View.buttons.ButtonDate.buttonDate;
import static View.labels.LabelFIO.fio;
import static View.tables.TableClients.clientTable;
import static View.tables.TableClients.selectedItems;

public class Main extends Application {
    public static int manualChecker = 0;
    public static String easyDateGetting;
    public static String easyDateGettingConvert;
    public static int count = 0;
    public static String preName;
    public static String firstNameForScans = null;
    public static String temp;
    public  static String leftScan = "_left.DAT";
    public  static String rightScan = "_right.DAT";
    public static File dir = null;
    public static String [] scans = null;
    public static String headNameForOrderlst;
    public static String tempScan;

    public static void pause(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            System.err.format("IOException: %s%n", e);
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception{

        CheckBox checker = new CheckBox("Manual choosing");
        checker.setSelected(false);

        primaryStage.setTitle("ArvitumTech");

        Label info = new Label("You have chosen the client:");
        info.setPrefWidth(260);
        info.setAlignment(Pos.CENTER);


        VBox vBox = new VBox();
        vBox.setPadding(new Insets(20, 20, 20, 20));

        LabelDate.date.setPrefWidth(260);
        buttonDate.setPrefWidth(350);

        GridPane middleBar = new GridPane();
        middleBar.setAlignment(Pos.CENTER);
        middleBar.setVgap(10);
        middleBar.setHgap(2);

        middleBar.add(DatePicker.datePicker, 3, 1);
        middleBar.add(buttonDate, 8, 1);
        middleBar.add(LabelDate.date, 3, 2);
        middleBar.add(checker, 8 ,2);

        GridPane rootNode = new GridPane();
        rootNode.setAlignment(Pos.CENTER);
        rootNode.setHgap(2);
        rootNode.setVgap(10);

        primaryStage.setScene(new Scene(vBox));
        primaryStage.setResizable(true);

        MenuBar mb = new MenuBar();
        mb.setStyle("-fx-background-color: rgba(42,255,40,0);");
        Menu fileMenu = new Menu("Options");
        MenuItem help = new MenuItem("Help");
        MenuItem settings = new MenuItem("Settings");
        fileMenu.getItems().addAll(help,settings);

        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(10, 10,10,10));
        borderPane.setRight(LabelGreeting.greetings);
        borderPane.setLeft(mb);

        VBox borderPaneForConvertButton = new VBox();
        borderPaneForConvertButton.setSpacing(20);
        borderPaneForConvertButton.setPadding(new Insets(10,10,10,10));
        borderPaneForConvertButton.setAlignment(Pos.CENTER);
        borderPaneForConvertButton.getChildren().add(ButtonConvert.buttonConvert);
        borderPaneForConvertButton.getChildren().add(LabelProcess.process);

        TableClients.clientTable.setPlaceholder(new Label("Any data to show yet!"));
        TableClients.selectionModelClient.setSelectionMode(SelectionMode.SINGLE);
        TableClients.firstNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("firstname"));
        TableClients.firstNameTableColumn.setMinWidth(130);
        TableClients.lastNameTableColumn.setMinWidth(130);
        TableClients.lastNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        TableClients.clientTable.getColumns().add(TableClients.firstNameTableColumn);
        TableClients.clientTable.getColumns().add(TableClients.lastNameTableColumn);


        ButtonConfirmRightScan.confirmRightScan.setPrefWidth(260);
        ButtonConfirmLeftScan.confirmLeftScan.setPrefWidth(260);

        ButtonConvert.buttonConvert.setPrefWidth(620);
        ButtonConvert.buttonConvert.setPrefHeight(70);

        if (manualChecker == 0)
            TableScans.scanTable.setDisable(true);
        else
            TableScans.scanTable.setDisable(false);

        TableScans.scanTable.setPlaceholder(new Label("Any data to show yet!"));
        TableScans.selectionModelScan.setSelectionMode(SelectionMode.SINGLE);
        TableScans.scanName.setCellValueFactory(new PropertyValueFactory<>("scanname"));
        TableScans.scanDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        TableScans.scanName.setMinWidth(200);
        TableScans.scanDate.setMinWidth(150);
        TableScans.scanName.setResizable(false);
        TableScans.scanTable.getColumns().add(TableScans.scanName);
        TableScans.scanTable.getColumns().add(TableScans.scanDate);



        mb.getMenus().addAll(fileMenu);
        mb.setMinSize(20,10);

        settings.setOnAction((make)->{
            primaryStage.hide();
            SettingsClass.window("Settings");
            primaryStage.show();
        });
        help.setOnAction((make)->{
            primaryStage.hide();
            HelpClass.window("Help");
            primaryStage.show();
        });

        ButtonClient.buttonClient.setPrefWidth(260);
        ButtonScans.buttonScans.setPrefWidth(350);
        fio.setPrefWidth(350);
        fio.setAlignment(Pos.CENTER);
        LabelRightScan.rightScan.setAlignment(Pos.CENTER);
        LabelRightScan.rightScan.setPrefWidth(350);
        LabelLeftScan.leftScan.setAlignment(Pos.CENTER);
        LabelLeftScan.leftScan.setPrefWidth(350);
        DatePicker.datePicker.setValue(LocalDate.now());




        ButtonScans.buttonScans.setOnAction((make) -> {
            File dirForScans = null;
            try {
                dirForScans = new File(DBController.getFromDBScanFolder() + "\\" + DateTimeFormatter.ofPattern("yyyyMMdd", Locale.ENGLISH).format(DatePicker.datePicker.getValue()));
            } catch (SQLException e) {
                ExceptionWindow.problem.setText("The folder was not found");
                ExceptionWindow.window("Error!");
            }

            File file;
            BasicFileAttributes bfa = null;
            try {
                scans = SearchScanClass.getTargetFiles(dirForScans);
            for (int e = 0; e < scans.length; e++) {
                if (count < scans.length) {
                    file = new File(dirForScans + "\\" + scans[e]);
                    Path path = file.toPath();
                    try {
                        bfa = Files.readAttributes(path, BasicFileAttributes.class);
                    } catch (IOException ex) {
                        ExceptionWindow.problem.setText("Wrong path to file!");
                        ExceptionWindow.window("Error!");
                        //ex.printStackTrace();
                    }
                    tempScan = scans [e];
                    scans [e] = ImproveWithLeftAndRightLetterClass.improveMethod(scans, dirForScans, scans [e]);
                    ImproveWithLeftAndRightLetterClass.modifyScansWithLAndR(tempScan, scans [e]);
                    TableScans.scanTable.getItems().add(new Scans((scans[e]),  bfa.creationTime().toInstant().atZone(ZoneId.systemDefault()).toString().substring(0,19).replace("T", "    ")));
                    count++;
                }
            }
            } catch (NullPointerException e) {
                ExceptionWindow.problem.setText("Scans were not found!");
                ExceptionWindow.window("Error!");
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        buttonDate.setOnAction(new EventHandler<ActionEvent>() {
              @Override
              public void handle(ActionEvent event) {
                  count = 0;
                  TableScans.scanTable.getItems().clear();
                  clientTable.getItems().removeAll(clientTable.getItems());
                  LabelDate.date.setText("Chosen date " + DateTimeFormatter.ofPattern("dd.MM.yyyy", Locale.ENGLISH).format(DatePicker.datePicker.getValue()));
                  easyDateGetting = DateTimeFormatter.ofPattern("yyyyMMdd", Locale.ENGLISH).format(DatePicker.datePicker.getValue());
                  easyDateGettingConvert = DateTimeFormatter.ofPattern("yyyy_MM_dd", Locale.ENGLISH).format(DatePicker.datePicker.getValue());
              }
        });

        rootNode.add(ButtonClient.buttonClient, 3,1);
        rootNode.add(TableClients.clientTable, 3,2);

        if (manualChecker == 0)
            ButtonScans.buttonScans.setDisable(true);
        else
            ButtonScans.buttonScans.setDisable(false);
        rootNode.add(ButtonScans.buttonScans,8,1);
        rootNode.add(TableScans.scanTable,8, 2);
        rootNode.add(fio, 8, 4);
        rootNode.add(info, 3 , 4);
        rootNode.add(ButtonConfirmLeftScan.confirmLeftScan, 3, 5 );
        rootNode.add(LabelLeftScan.leftScan, 8, 5);
        rootNode.add(ButtonConfirmRightScan.confirmRightScan, 3, 6);
        rootNode.add(LabelRightScan.rightScan, 8, 6);


//Start of client fio searhing

        ButtonClient.buttonClient.setOnAction((make) -> {
            clientTable.getItems().removeAll(clientTable.getItems());
            String [] clients;
            String strLine = null;
            String fio = null;
            String [] neededLine = null;
            try {
                dir = new File(DBController.getFromDBClientFolder() + "\\" + DateTimeFormatter.ofPattern("yyyy_MM_dd", Locale.ENGLISH).format(DatePicker.datePicker.getValue()));
            } catch (Exception e) {
                ExceptionWindow.problem.setText("Cant find " + "\\n" + "folder with scans!");
                ExceptionWindow.window("Error!");
                //e.printStackTrace();
            }
            FileInputStream fstream = null;
            try {
                fstream = new FileInputStream(dir + "\\orderlst.txt");
            } catch (FileNotFoundException e) {
                ExceptionWindow.problem.setText("Folder with clients was not found");
                ExceptionWindow.window("Error!");
                //e.printStackTrace();
            }
                try {
                    BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
                    while ((strLine = br.readLine()) != null)
                 {
                     neededLine = strLine.split(" ");
                     if (strLine.startsWith("%%") && strLine.endsWith("S")){
                         fio = neededLine [0] + neededLine[1];
                         TableClients.clientTable.getItems().add(new Client(neededLine[0].substring(2, neededLine[0].indexOf(",")), neededLine[1]));
                     }
                 }

                } catch (IOException | NullPointerException | StringIndexOutOfBoundsException e) {
                    //e.printStackTrace();
                }

            try {
                fstream.close();
            } catch (IOException | NullPointerException e) {
            }
        });

        // End of fio searching

        selectedItems.addListener(new ListChangeListener<Client>() {
            @Override
            public void onChanged(Change<? extends Client> c) {
                Client client = (Client)clientTable.getSelectionModel().getSelectedItem();
                preName = client.getFirstname() + " " + client.getLastname();
                headNameForOrderlst = client.getFirstname() + ", " + client.getLastname();
                fio.setText(preName + "          "+ ScanTimeClass.scanTimeViewing(dir, client.getFirstname()));
                firstNameForScans = client.getFirstname();
                LabelProcess.process.setText(LabelProcess.inAProcess);
            }

        });

        TableScans.selectedItemsScans.addListener(new ListChangeListener<Scans>() {
            @Override
            public void onChanged(Change<? extends Scans> c) {
                temp = (((Scans)TableScans.scanTable.getSelectionModel().getSelectedItem()).getScanname().replace(".dat", ".DAT"));
                LabelProcess.process.setText(LabelProcess.inAProcess);
            }

        });

        ButtonConvert.buttonConvert.setOnAction(make ->{
            try {
                if (manualChecker == 0) {
                    leftScan = "_left.DAT";
                    rightScan = "_right.DAT";
                }
                ConverterClass.updateOrderlst();
            } catch (IOException e) {
                ExceptionWindow.problem.setText("Cant convert file!");
                ExceptionWindow.window("Error!");
                e.printStackTrace();
            }
            try {
                if (manualChecker == 0){
                    leftScan = "_left.ave";
                    rightScan = "_right.ave";
                }

                File leftFileOrigin = new File(DBController.getFromDBScanFolder() + "\\" + easyDateGetting + "\\" + leftScan);
                File leftFilePath = new File(DBController.getFromDBConvertFolder() + "\\" + easyDateGettingConvert + "\\" + firstNameForScans + leftScan.replace(".ave", ".DAT"));
                Files.copy(leftFileOrigin.toPath(), leftFilePath.toPath(), StandardCopyOption.REPLACE_EXISTING);
                File rightFileOrigin = new File(DBController.getFromDBScanFolder() + "\\" + easyDateGetting + "\\" + rightScan);
                File rightFilePath = new File(DBController.getFromDBConvertFolder() + "\\" + easyDateGettingConvert + "\\" + firstNameForScans + rightScan.replace(".ave", ".DAT"));
                Files.copy(rightFileOrigin.toPath(), rightFilePath.toPath(), StandardCopyOption.REPLACE_EXISTING);
                LabelProcess.process.setText(LabelProcess.ready);
            } catch (SQLException | IOException e) {
                ExceptionWindow.problem.setText("Cant convert file!");
                ExceptionWindow.window("Error!");
                LabelProcess.process.setText("Oops...");
                e.printStackTrace();
            }
        });

        if (manualChecker == 0) {
            ButtonConfirmRightScan.confirmRightScan.setDisable(true);
            ButtonConfirmLeftScan.confirmLeftScan.setDisable(true);
        }
        else {
            ButtonConfirmRightScan.confirmRightScan.setDisable(false);
            ButtonConfirmLeftScan.confirmLeftScan.setDisable(false);
        }

        ButtonConfirmRightScan.confirmRightScan.setOnAction(make ->{
            rightScan = temp;
            LabelRightScan.rightScan.setText(rightScan);
            if (!rightScan.equals("_right.ave") && !leftScan.equals("_left.ave"))
                ButtonConvert.buttonConvert.setDisable(false);
        });
        ButtonConfirmLeftScan.confirmLeftScan.setOnAction(make ->{
            leftScan = temp;
            LabelLeftScan.leftScan.setText(leftScan);
            if (!rightScan.equals("_right.ave") && !leftScan.equals("_left.ave"))
                ButtonConvert.buttonConvert.setDisable(false);
        });


        vBox.getChildren().add(borderPane);
        vBox.getChildren().add(middleBar);
        vBox.getChildren().add(rootNode);
        vBox.getChildren().add(borderPaneForConvertButton);
        primaryStage.setResizable(false);


        checker.setOnAction((make)->{
            if (manualChecker == 0) {
                manualChecker = 1;
                ButtonScans.buttonScans.setDisable(false);
                TableScans.scanTable.setDisable(false);
                ButtonConfirmLeftScan.confirmLeftScan.setDisable(false);
                ButtonConfirmRightScan.confirmRightScan.setDisable(false);
                ButtonConvert.buttonConvert.setDisable(true);
                rightScan = "_right.ave";
                leftScan = "_left.ave";
            }
            else {
                manualChecker = 0;
                ButtonScans.buttonScans.setDisable(true);
                TableScans.scanTable.setDisable(true);
                ButtonConfirmLeftScan.confirmLeftScan.setDisable(true);
                ButtonConfirmRightScan.confirmRightScan.setDisable(true);
                LabelRightScan.rightScan.setText("_right.ave");
                LabelLeftScan.leftScan.setText("_left.ave");
                ButtonConvert.buttonConvert.setDisable(false);
                rightScan = "_right.ave";
                leftScan = "_left.ave";

            }
        });
        System.out.println("Hello GitHub!!!");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
