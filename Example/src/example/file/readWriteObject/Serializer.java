package example.file.readWriteObject;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class Serializer {

    public static void main(String args[]) {

        Serializer serializer = new Serializer();
        serializer.serializeAddress("wall street", "united state");
    }

    public void serializeAddress(String street, String country) {

        Address address = new Address();
        address.setStreet(street);
        address.setCountry(country);

        try {

            FileOutputStream fout = new FileOutputStream("./output/Serializer.ser");
            try (ObjectOutputStream oos = new ObjectOutputStream(fout)) {
                oos.writeObject(address);
            }
            System.out.println("Done");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
