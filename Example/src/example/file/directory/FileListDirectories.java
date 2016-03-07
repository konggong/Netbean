package example.file.directory;

import java.io.File;
import java.io.IOException;

public class FileListDirectories {

    public String separator = File.separator;

    public void listDirectories(String address) throws IOException {
        File dir = new File(address);
        System.out.println(dir.getCanonicalPath() + separator);
        File listDir[] = dir.listFiles();
        for (int i = 0; i < listDir.length; i++) {
            if (listDir[i].isDirectory()) {
                System.out.println(listDir[i].getName());
            }
        }
    }

    public static void main(String[] args) throws IOException {
        FileListDirectories dir = new FileListDirectories();
        dir.listDirectories(".");
    }
}
