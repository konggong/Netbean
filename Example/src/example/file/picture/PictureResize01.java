package example.file.picture;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class PictureResize01 {

    /**
     * Resizes an image to a absolute width and height (the image may not be
     * proportional)
     *
     * @param inputImagePath Path of the original image
     * @param outputImagePath Path to save the resized image
     * @param scaledWidth absolute width in pixels
     * @param scaledHeight absolute height in pixels
     * @throws IOException
     */
    public static void resize(String inputImagePath, String outputImagePath, int scaledWidth, int scaledHeight) throws IOException {
        // reads input image
        File inputFile = new File(inputImagePath);
        BufferedImage inputImage = ImageIO.read(inputFile);
        
        System.out.println("inputFile = " + inputFile.getCanonicalPath());

        // creates output image
        BufferedImage outputImage = new BufferedImage(scaledWidth, scaledHeight, inputImage.getType());
        
        System.out.println("size     [Width x Height] = [" + inputImage.getWidth() + " x " + inputImage.getHeight() + "]");
        System.out.println("NEW size [Width x Height] = [" + scaledWidth + " x " + scaledHeight + "]");

        // scales the input image to the output image
        Graphics2D g2d = outputImage.createGraphics();
        g2d.drawImage(inputImage, 0, 0, scaledWidth, scaledHeight, null);
        g2d.dispose();

        // extracts extension of output file
        String formatName = outputImagePath.substring(outputImagePath.lastIndexOf(".") + 1);

        // writes to output file
        File outputFile = new File(outputImagePath);
        ImageIO.write(outputImage, formatName, outputFile);
        
        System.out.println("outputFile = " + outputFile.getAbsolutePath());
        System.out.println("SUCCESSFUL!!");
    }

    /**
     * Resizes an image by a percentage of original size (proportional).
     *
     * @param inputImagePath Path of the original image
     * @param outputImagePath Path to save the resized image
     * @param percent a double number specifies percentage of the output image
     * over the input image.
     * @throws IOException
     */
    public static void resize(String inputImagePath, String outputImagePath, double percent) throws IOException {
        File inputFile = new File(inputImagePath);
        BufferedImage inputImage = ImageIO.read(inputFile);
        int scaledWidth = (int) (inputImage.getWidth() * percent);
        int scaledHeight = (int) (inputImage.getHeight() * percent);
        resize(inputImagePath, outputImagePath, scaledWidth, scaledHeight);
    }

    /**
     * Resizes an image to a absolute width (the image be proportional)
     *
     * @param inputImagePath Path of the original image
     * @param outputImagePath Path to save the resized image
     * @param scaledWidth absolute width in pixels
     * @throws IOException
     */    
    public static void resize(String inputImagePath, String outputImagePath, int scaledWidth) throws IOException {
        // reads input image
        File inputFile = new File(inputImagePath);
        BufferedImage inputImage = ImageIO.read(inputFile);
        
        System.out.println("inputFile = " + inputFile.getCanonicalPath());
        
        //set resize image 
        int ratioSize = gcd(inputImage.getWidth(), inputImage.getHeight());
        int ratioWidth = inputImage.getWidth() / ratioSize;
        int ratioHeight = inputImage.getHeight() / ratioSize;
        int newScaledWidth =  scaledWidth / ratioWidth * ratioWidth;
        int newScaledHeight = scaledWidth / ratioWidth * ratioHeight;
        
        System.out.println("size     [Width x Height] = [" + inputImage.getWidth() + " x " + inputImage.getHeight() + "]");
        System.out.println("NEW size [Width x Height] = [" + newScaledWidth + " x " + newScaledHeight + "]");

        //creates output image
        BufferedImage outputImage = new BufferedImage(newScaledWidth,newScaledHeight,inputImage.getType());

        // scales the input image to the output image
        Graphics2D g2d = outputImage.createGraphics();
        g2d.drawImage(inputImage, 0, 0, newScaledWidth, newScaledHeight, null);
        g2d.dispose();

        // extracts extension of output file
        String formatName = outputImagePath.substring(outputImagePath.lastIndexOf(".") + 1);

        // writes to output file
        File outputFile = new File(outputImagePath);
        ImageIO.write(outputImage, formatName, outputFile);
        
        System.out.println("outputFile = "+outputFile.getAbsolutePath());
        System.out.println("SUCCESSFUL!!");
    }

    public static int gcd(int x, int y) {
        if (x % y == 0) {
            return y;
        }
        return gcd(y, x % y);
    }
    
    /**
     * Test resizing images
     */
    public static void main(String[] args) {
        
        
        String inputImagePath = "./picture/resize/menu4.png";
        String outputImagePath = "./picture/resize/menu4-mini.png"; 
        int scaledWidth = 25;
        int scaledHeight = 25;
        try {
            double percent = 1;
            //PictureResize01.resize(inputImagePath, outputImagePath, scaledWidth, scaledHeight);
            PictureResize01.resize(inputImagePath, outputImagePath, percent);            
        } catch (IOException ex) {
            System.out.println("Error resizing the image. " + ex.getMessage());
        }
        
        /*
        String inputImagePath = "./picture/resize/001.jpg";
        String outputImagePath1 = "./picture/resize/001_FixedX.jpg";
        String outputImagePath2 = "./picture/resize/001_FixedXY.jpg";
        String outputImagePath3 = "./picture/resize/001_Smaller.jpg";
        String outputImagePath4 = "./picture/resize/001_Bigger.jpg";
        int scaledWidth;
        int scaledHeight;
       

        try {
            // resize to a fixed width (proportional)
            scaledWidth = 240;
            PictureResize01.resize(inputImagePath, outputImagePath1, scaledWidth);

            // resize to a fixed width (not proportional)
            scaledWidth = 1024;
            scaledHeight = 768;
            PictureResize01.resize(inputImagePath, outputImagePath2, scaledWidth, scaledHeight);

            // resize smaller by 50%
            double percent = 0.5;
            PictureResize01.resize(inputImagePath, outputImagePath3, percent);

            // resize bigger by 50%
            percent = 1.5;
            PictureResize01.resize(inputImagePath, outputImagePath4, percent);

        } catch (IOException ex) {
            System.out.println("Error resizing the image. " + ex.getMessage());
        }
        */
    }
}