package example.property;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class propertiesFileReader {

    private Properties prop;

    public propertiesFileReader(String fileName) {
        prop = new Properties();
        fileName = "./xml/" + fileName;
        try {
            prop.loadFromXML(new FileInputStream(fileName));
        } catch (FileNotFoundException ex) {
            System.out.println("Error [propertiesFile] FileNotFoundException : " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("Error [propertiesFile] IOException : " + ex.getMessage());
        }
    }

    public String getProperties(String valiable) {
        return prop.getProperty(valiable);
    }

    public static void main(String[] args) {
        propertiesFileReader propf = new propertiesFileReader("database.xml");
        System.out.println("url       = " + propf.getProperties("url"));
        System.out.println("utf8      = " + propf.getProperties("utf8"));
        System.out.println("userName  = " + propf.getProperties("username"));
        System.out.println("password  = " + propf.getProperties("password"));
        System.out.println("className = " + propf.getProperties("classname"));
    }
}
