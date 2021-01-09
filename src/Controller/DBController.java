package Controller;

import View.labels.LabelFIO;
import View.labels.LabelGreeting;
import sample.Main;

import java.sql.*;

public class DBController {

    public static String folderWithScans = null;
    public static String folderWithClients = null;
    public static String folderConvert = null;

    public static String getFromDBName() throws SQLException {
        Connection connection = null;
        Statement statement = null;
        String query = "SELECT * FROM users";
        ResultSet rs = null;
        String result = null;

        //"jdbc:sqlite:D:\\MyApps\\ArvitumTech\\DataBase\\Arvitum.db"
        //"jdbc:sqlite:E:\\ArvitumTech\\DataBase\\Arvitum.db"

        try {
            connection = DriverManager.getConnection("jdbc:sqlite:D:\\MyApps\\ArvitumTech\\DataBase\\Arvitum.db");
            statement = connection.createStatement();
            rs = statement.executeQuery(query);
            result = rs.getString("name");
        } catch (SQLException e) {
            LabelFIO.fio.setText("connection doesnt open");
        }
        finally {
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                LabelFIO.fio.setText("connection doesnt close");
            }
        }
        return result;
    }
    public static void setNewName(String newName) throws SQLException {
        Connection connection = null;
        Statement statement = null;
        String queryNewOne = "INSERT INTO users (name) VALUES ('" + newName + "')";
        String queryUpdateName = "UPDATE users SET name = '" + newName + "' where id = 1";

        try {
            connection = DriverManager.getConnection("jdbc:sqlite:D:\\MyApps\\ArvitumTech\\DataBase\\Arvitum.db");
            statement = connection.createStatement();
            if (!DBController.getFromDBName().equals(null)) {
                statement.executeUpdate(queryUpdateName);
                LabelGreeting.greetings.setText("Hello " + DBController.getFromDBName() + "!");
            }
            else {
                statement.executeUpdate(queryNewOne);
                LabelGreeting.greetings.setText("Hello " + DBController.getFromDBName() + "!");
            }
        } catch (SQLException e) {
            LabelFIO.fio.setText("connection doesnt open");
        }
        finally {
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                LabelFIO.fio.setText("connection doesnt close");
            }
        }

    }

    public static String getFromDBScanFolder() throws SQLException {
        Connection connection = null;
        Statement statement = null;
        String query = "SELECT * FROM fields1";
        ResultSet rs = null;
        String result = null;


        try {
            connection = DriverManager.getConnection("jdbc:sqlite:D:\\MyApps\\ArvitumTech\\DataBase\\Arvitum.db");
            statement = connection.createStatement();
            rs = statement.executeQuery(query);
            result = rs.getString("fieldForScans");
        } catch (SQLException e) {
            System.out.println(result);
            System.out.println("problem");
        }
        finally {
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                System.out.println("problem22");
            }
        }
        return result;
    }

    public static void setFolderScans(String newFolder) throws SQLException {
        Connection connection = null;
        Statement statement = null;
        String queryNewOne = "INSERT INTO fields1 (fieldForScans) VALUES ('" + newFolder + "');";
        String queryUpdateFolderScan = "UPDATE fields1 SET fieldForScans = '" + newFolder + "'  WHERE id = 1";

        try {
            connection = DriverManager.getConnection("jdbc:sqlite:D:\\MyApps\\ArvitumTech\\DataBase\\Arvitum.db");
            statement = connection.createStatement();
            if (!DBController.getFromDBScanFolder().equals(null)) {
                statement.executeUpdate(queryUpdateFolderScan);
                folderWithScans = getFromDBScanFolder();
            }
            else {
                statement.executeUpdate(queryNewOne);
                folderWithScans = getFromDBScanFolder();
            }
        } catch (SQLException e) {
            LabelFIO.fio.setText("connection doesnt open");
        }
        finally {
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                LabelFIO.fio.setText("connection doesnt close");
            }
        }
    }

    public static String getFromDBClientFolder() throws SQLException {
        Connection connection = null;
        Statement statement = null;
        String query = "SELECT * FROM fields1";
        ResultSet rs = null;
        String result = null;


        try {
            connection = DriverManager.getConnection("jdbc:sqlite:D:\\MyApps\\ArvitumTech\\DataBase\\Arvitum.db");
            statement = connection.createStatement();
            rs = statement.executeQuery(query);
            result = rs.getString("fieldForClients");
        } catch (SQLException e) {
            System.out.println(result);
            System.out.println("problem");
        }
        finally {
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                System.out.println("problem22");
            }
        }
        return result;
    }

    public static void setFolderWithClients(String newFolder) throws SQLException {
        Connection connection = null;
        Statement statement = null;
        String queryNewOne = "INSERT INTO fields1 (fieldForClients) VALUES ('" + newFolder + "');";
        String queryUpdateFolderClient = "UPDATE fields1 SET fieldForClients = '" + newFolder + "'  WHERE id = 1";

        try {
            connection = DriverManager.getConnection("jdbc:sqlite:D:\\MyApps\\ArvitumTech\\DataBase\\Arvitum.db");
            statement = connection.createStatement();
            if (!getFromDBClientFolder().equals(null)) {
                System.out.println("are we here");
                statement.executeUpdate(queryUpdateFolderClient);
                folderWithClients = getFromDBClientFolder();
            }
            else {
                System.out.println("trying to set query");
                statement.executeUpdate(queryNewOne);
                System.out.println(getFromDBClientFolder());
                folderWithClients = getFromDBClientFolder();
            }
        } catch (SQLException e) {
            LabelFIO.fio.setText("connection doesnt open");
        }
        finally {
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                LabelFIO.fio.setText("connection doesnt close");
            }
        }
    }

    public static String getFromDBConvertFolder() throws SQLException {
        Connection connection = null;
        Statement statement = null;
        String query = "SELECT * FROM fields1";
        ResultSet rs = null;
        String result = null;


        try {
            connection = DriverManager.getConnection("jdbc:sqlite:D:\\MyApps\\ArvitumTech\\DataBase\\Arvitum.db");
            statement = connection.createStatement();
            rs = statement.executeQuery(query);
            result = rs.getString("fieldForConverter");
        } catch (SQLException e) {
            System.out.println(result);
            System.out.println("problem");
        }
        finally {
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                System.out.println("problem22");
            }
        }
        return result;
    }

    public static void setFolderConvert(String newFolder) throws SQLException {
        Connection connection = null;
        Statement statement = null;
        String queryNewOne = "UPDATE fields1 SET fieldsForConverter = 'D:\\MyApps' WHERE id = 1;";
        String queryUpdateFolderConvert = "UPDATE fields1 SET fieldForConverter = '" + newFolder + "'  WHERE id = 1";

        try {
            connection = DriverManager.getConnection("jdbc:sqlite:D:\\MyApps\\ArvitumTech\\DataBase\\Arvitum.db");
            statement = connection.createStatement();
            if (!DBController.getFromDBConvertFolder().equals(null)) {
                statement.executeUpdate(queryUpdateFolderConvert);
                folderConvert = getFromDBConvertFolder();
            }
            else {
                statement.executeUpdate(queryNewOne);
                folderConvert = getFromDBConvertFolder();
            }
        } catch (SQLException e) {
            LabelFIO.fio.setText("connection doesnt open");
        }
        finally {
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                LabelFIO.fio.setText("connection doesnt close");
            }
        }
    }

}
