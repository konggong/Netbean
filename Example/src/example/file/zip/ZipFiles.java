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
package example.file.zip;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipFiles {

    public static void main(String[] args) {
        try {
            FileOutputStream fos = new FileOutputStream("./zip/ZipFiles.zip");
            ZipOutputStream zos = new ZipOutputStream(fos);

            // This sets the compression level to STORED, ie, uncompressed
            //zos.setLevel(ZipOutputStream.STORED);

            String file1Name = "./zip/0000.jpg";
            String file2Name = "./zip/0001.jpg";
            String file3Name = "./zip/0002.jpg";
            String file4Name = "./zip/0003.jpg";
            String file5Name = "./zip/0004.jpg";
            String file6Name = "./zip/0005.jpg";
            String file7Name = "./zip/0006.jpg";

            checkExists(new File(file1Name));

            addToZipFile(file1Name, zos);
            addToZipFile(file2Name, zos);
            addToZipFile(file3Name, zos);
            addToZipFile(file4Name, zos);
            addToZipFile(file5Name, zos);
            addToZipFile(file6Name, zos);
            addToZipFile(file7Name, zos);

            zos.close();
            fos.close();

        } catch (FileNotFoundException e) {
            System.out.println("@Error : " + e.getMessage());
        } catch (IOException e) {
            System.out.println("@Error : " + e.getMessage());
        }

    }

    public static void checkExists(File file) {
        System.out.println(file.getName() + " exists? " + file.exists());
    }

    public static void addToZipFile(String fileName, ZipOutputStream zos) throws FileNotFoundException, IOException {

        System.out.println("Writing '" + fileName + "' to zip file");

        File file = new File(fileName);
        try (FileInputStream fis = new FileInputStream(file)) {
            ZipEntry zipEntry = new ZipEntry(fileName);
            zos.putNextEntry(zipEntry);
            byte[] bytes = new byte[1024];
            int length;
            while ((length = fis.read(bytes)) >= 0) {
                zos.write(bytes, 0, length);
            }
            zos.closeEntry();
        }
    }
}