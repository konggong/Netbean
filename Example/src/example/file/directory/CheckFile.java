package example.file.directory;

import java.io.File;

public class CheckFile {

    public static void main(String[] args) {

        String INPUT_FILE = "./output/";
        String target_file = "";
        File folderToScan = new File(INPUT_FILE);
        File[] listOfFiles = folderToScan.listFiles();
        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                target_file = listOfFiles[i].getName();
                System.out.println("target_file = " + target_file);
                if (target_file.matches(".*LISTADDRESSINFO20131105.*")) {
                    System.out.println("Found !!");
                } else {
                    System.out.println("Not Found !!");
                }
                System.out.println("-----------------");
            }
        }
    }
}
