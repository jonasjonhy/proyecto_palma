package util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.html.simpleparser.HTMLWorker;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.StringReader;

public class GeneratePDF {

    public static void main(String[] args) {
        try {
            String k = "<html><body> This is my Project </body></html>";
            OutputStream file = new FileOutputStream(new File("Test.pdf"));
            Document document = new Document();
            PdfWriter.getInstance(document, file);
            document.open();
            HTMLWorker htmlWorker = new HTMLWorker(document);
            htmlWorker.parse(new StringReader(k));
            document.close();
            file.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
