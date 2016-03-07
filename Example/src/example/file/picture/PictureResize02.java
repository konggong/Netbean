package example.file.picture;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import org.imgscalr.Scalr;
import org.imgscalr.Scalr.Mode;

public class PictureResize02 {

    public static void main(String[] args) {
        try {
            BufferedImage img = ImageIO.read(new File("./picture/resize/001.jpg"));
            BufferedImage scaledImg = Scalr.resize(img, Mode.AUTOMATIC, 640, 480);
            File destFile = new File("./picture/resize/001_Mode.AUTOMATIC.jpg");
            ImageIO.write(scaledImg, "jpg", destFile);
            System.out.println("Done resizing");
        } catch (IOException ex) {
            Logger.getLogger(PictureResize02.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
