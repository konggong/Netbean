package example.file.readWriteObject;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Deserializer {

    public static void main(String args[]) {

        Deserializer deserializer = new Deserializer();
        Address address = deserializer.deserialzeAddress();
        System.out.println(address);
    }

    public Address deserialzeAddress() {

        Address address;

        try {

            FileInputStream fin = new FileInputStream("./output/Serializer.ser");
            try (ObjectInputStream ois = new ObjectInputStream(fin)) {
                address = (Address) ois.readObject();
            }

            return address;

        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
