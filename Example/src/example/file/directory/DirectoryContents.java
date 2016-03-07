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
import java.io.FileFilter;
import java.io.IOException;

public class DirectoryContents {

    public static void main(String[] args) throws IOException {

        File f = new File("."); // current directory

        FileFilter directoryFilter = new FileFilter() {
            @Override
            public boolean accept(File file) {
                return file.isDirectory();
            }
        };

        File[] files = f.listFiles(directoryFilter);
        for (File file : files) {
            if (file.isDirectory()) {
                System.out.print("directory:");
            } else {
                System.out.print("     file:");
            }
            System.out.println(file.getCanonicalPath());
        }

    }
}