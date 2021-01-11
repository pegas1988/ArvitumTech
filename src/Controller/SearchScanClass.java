package Controller;

import java.io.*;

public class SearchScanClass {
    static String [] result;
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

        String[] files2 = directory.list(new FilenameFilter(){

            @Override
            public boolean accept(File dir, String name) {
                // TODO Auto-generated method stub
                return name.endsWith(".DAT");
            }

        });
        result = new String [files.length + files2.length];
        for (int e = 0; e < files.length; e ++) {
            result [e] = files [e];
        }

        for ( int e = files.length; e < result.length; e ++ ) {
            result [e] = files2 [e - files.length];
        }

        return result;
    }
}
