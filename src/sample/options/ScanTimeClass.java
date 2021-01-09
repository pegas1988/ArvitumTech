package sample.options;

import ExceptionAr.ExceptionWindow;
import Model.objects.Client;
import View.tables.TableClients;

import java.io.*;

public class ScanTimeClass {
        public static String strLine;
        public static String afterSymbol [];
        public static String addTime;

    public static String scanTimeViewing(File directry, String name){
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(directry + "\\orderlst.txt");
        } catch (FileNotFoundException e) {
            ExceptionWindow.problem.setText("Folder with clients was not found");
            ExceptionWindow.window("Error!");
        }
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(fileInputStream));
            while ((strLine = br.readLine()) != null)
                if (strLine.startsWith("%%") && strLine.contains(name)){
                    afterSymbol = strLine.split("\\(");
                    addTime = "Scanning time " + afterSymbol[1].substring(11,16).replace(".", " : ");
                    break;
                }

        } catch (IOException | NullPointerException | StringIndexOutOfBoundsException e) {
            //e.printStackTrace();
        }

        try {
            fileInputStream.close();
        } catch (IOException | NullPointerException e) {
        }
        return addTime;
    }
}
