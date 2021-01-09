package Controller;

import java.io.*;

public class SearchScanClass {
    public static String[] getTargetFiles(File directory){
        if(directory == null){
            return null;
        }

        String[] files = directory.list(new FilenameFilter(){

            @Override
            public boolean accept(File dir, String name) {
                // TODO Auto-generated method stub
                return name.endsWith(".dat");
            }

        });

        return files;
    }
}
