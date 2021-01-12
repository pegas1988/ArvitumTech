package sample.options;

import Controller.DBController;
import ExceptionAr.ExceptionWindow;
import sample.Main;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;

public class ImproveWithLeftAndRightLetterClass {

    private static File leftScans = null;
    private static File rightScans = null;
    private static String strLineForLeft;
    private static String strLineForRight;
    private static String tempNameOfScan;
    private static String result;

    public static String improveMethod(String array [], File directory, String podopitniy) {
        leftScans = new File(directory + "\\" + "_left.log");
        rightScans = new File(directory + "\\" + "_right.log");

        FileInputStream fstreamForLeft = null;
        try {
            fstreamForLeft = new FileInputStream(leftScans);
        } catch (FileNotFoundException e) {
            ExceptionWindow.problem.setText("log file with left scans were not found");
            ExceptionWindow.window("Error!");
            e.printStackTrace();
        }
        try {
            BufferedReader brForLeft = new BufferedReader(new InputStreamReader(fstreamForLeft));
            while ((strLineForLeft = brForLeft.readLine()) != null) {
                tempNameOfScan = strLineForLeft.substring(36, strLineForLeft.length() - 5);
                if (tempNameOfScan.equals(podopitniy)) {
                    return result = tempNameOfScan.substring(0, tempNameOfScan.length() - 4) + "L.DAT";
                }
            }

        } catch (IOException | NullPointerException | StringIndexOutOfBoundsException e) {
            //e.printStackTrace();
        }

        try {
            fstreamForLeft.close();
        } catch (IOException | NullPointerException e) {
        }

        FileInputStream fstreamForRight = null;
        try {
            fstreamForRight = new FileInputStream(rightScans);
        } catch (FileNotFoundException e) {
            ExceptionWindow.problem.setText("log file with left scans were not found");
            ExceptionWindow.window("Error!");
            e.printStackTrace();
        }
        try {
            BufferedReader brForRight = new BufferedReader(new InputStreamReader(fstreamForRight));
            while ((strLineForRight = brForRight.readLine()) != null) {
                tempNameOfScan = strLineForRight.substring(36, strLineForRight.length() - 5);
                if (tempNameOfScan.equals(podopitniy)) {
                    return result = tempNameOfScan.substring(0, tempNameOfScan.length() - 4) + "R.DAT";
                }
                else
                    result = podopitniy;
            }

        } catch (IOException | NullPointerException | StringIndexOutOfBoundsException e) {
            //e.printStackTrace();
        }

        try {
            fstreamForRight.close();
        } catch (IOException | NullPointerException e) {
        }
        return result;
        }

        public static void modifyScansWithLAndR (String originScan, String modifyed) throws SQLException, IOException {
            File originFile = new File(DBController.getFromDBScanFolder() + "\\" + Main.easyDateGetting + "\\" + originScan);
            File pathFile = new File(DBController.getFromDBScanFolder() + "\\" + Main.easyDateGetting + "\\" + modifyed);
            Files.copy(originFile.toPath(), pathFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
        }
    }


