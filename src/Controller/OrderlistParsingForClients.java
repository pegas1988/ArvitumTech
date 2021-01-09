package Controller;

import java.io.*;

public class OrderlistParsingForClients {
    FileInputStream fstream = new FileInputStream("textfile.txt");
    BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

    String strLine;


    public OrderlistParsingForClients() throws FileNotFoundException {
    }
}
