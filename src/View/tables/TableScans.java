package View.tables;

import Model.objects.Scans;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import Model.objects.Client;

public class TableScans {
    public static TableView scanTable = new TableView();

    public static TableColumn<String, Scans> scanName = new TableColumn<>("Scan Name");
    public static TableColumn<String, Scans> scanDate = new TableColumn<>("Scan Date");
    public static TableView.TableViewSelectionModel selectionModelScan = scanTable.getSelectionModel();
    public static ObservableList selectedItemsScans = selectionModelScan.getSelectedItems();


}
