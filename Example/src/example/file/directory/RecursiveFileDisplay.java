/**
 * **************************************************************
 */
/* Copyright 2009 avajava.com                                    */
/* This code may be freely used and distributed in any project.  */
/* However, please do not remove this credit if you publish this */
/* code in paper or electronic form, such as on a web site.      */
/**
 * **************************************************************
 */
package example.file.directory;

import java.io.File;
import java.io.IOException;

public class RecursiveFileDisplay {

    public static void main(String[] args) {
        File currentDir = new File("."); // current directory
        displayDirectoryContents(currentDir);
    }

    public static void displayDirectoryContents(File dir) {
        try {
            File[] files = dir.listFiles();
            for (File file : files) {
                if (file.isDirectory()) {
                    System.out.println("directory : " + file.getCanonicalPath());
                    displayDirectoryContents(file);
                } else {
                    System.out.println("     file : " + file.getCanonicalPath());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
