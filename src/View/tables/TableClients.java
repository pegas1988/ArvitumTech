package View.tables;

import View.labels.LabelFIO;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import Model.objects.Client;
import javafx.stage.Stage;

public class TableClients {
    public static TableView clientTable = new TableView();
    public static TableColumn<String, Client> firstNameTableColumn = new TableColumn<>("First Name");
    public static TableColumn<String, Client> lastNameTableColumn = new TableColumn<>("Last Name");
    public static TableView.TableViewSelectionModel selectionModelClient = clientTable.getSelectionModel();
    public static ObservableList selectedItems = selectionModelClient.getSelectedItems();
}
