/*****************************************************************/
/* Copyright 2009 avajava.com                                    */
/* This code may be freely used and distributed in any project.  */
/* However, please do not remove this credit if you publish this */
/* code in paper or electronic form, such as on a web site.      */
/*****************************************************************/

package example.file.pdf;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.List;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
//import java.awt.Color;

public class ITextWritePdfFile {

    public static void main(String[] args) {
        try {
            File file = new File("./pdf/ITextWritePdfFile.pdf");
            FileOutputStream fileout = new FileOutputStream(file);
            Document document = new Document();
            PdfWriter.getInstance(document, fileout);
            document.addAuthor("Me");
            document.addTitle("My iText Test");

            document.open();

            Chunk chunk = new Chunk("iText Test");
            Font font = new Font(Font.getFamily("Tahoma"));
            font.setStyle(Font.UNDERLINE);
            font.setStyle(Font.ITALIC);
            chunk.setFont(font);
            //chunk.setBackground(Color.CYAN);
            document.add(chunk);

            Paragraph paragraph = new Paragraph();
            paragraph.add("Hello World");
            paragraph.setAlignment(Element.ALIGN_CENTER);
            document.add(paragraph);

            Image image;
            try {
                image = Image.getInstance("./picture/trip.png");
                image.setAlignment(Image.MIDDLE);
                document.add(image);
            } catch (MalformedURLException e) {
                System.out.println("@Error : " + e.getMessage());
            } catch (IOException e) {
                System.out.println("@Error : " + e.getMessage());
            }

            List list = new List(true, 15);
            list.add("ABC");
            list.add("DEF");
            document.add(list);

            document.close();
        } catch (FileNotFoundException | DocumentException e) {
            System.out.println("@Error : " + e.getMessage());
        }
    }
}
