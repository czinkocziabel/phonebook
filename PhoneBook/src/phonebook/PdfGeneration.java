package phonebook;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.GrayColor;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.util.Observable;
import javafx.collections.ObservableList;

public class PdfGeneration {

    public void pdfGeneration(String fileName, ObservableList<Person> text) {
        Document document = new Document();

        try {
            PdfWriter.getInstance(document, new FileOutputStream(fileName + ".pdf"));
            document.open();
            Rectangle rectangle = document.getPageSize();

            //Lógó
            Image image = Image.getInstance(getClass().getResource("/logo.png"));
            image.scaleToFit(rectangle.getWidth(), 114);
            image.setAbsolutePosition(0f, ((float) rectangle.getHeight()) - 114);
            document.add(image);

            //Táblázat
            float[] columnWidths = {3,3,4};
            PdfPTable table = new PdfPTable(columnWidths);
            table.setWidthPercentage(100);
            PdfPCell cell = new PdfPCell(new Phrase("Kontaktlista"));
            cell.setBackgroundColor(GrayColor.GRAYWHITE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(3);
            cell.setBorder(0);
            table.addCell(cell);
            
            table.getDefaultCell().setBackgroundColor(new GrayColor(0.75f));
            table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell("Vazetéknév");
            table.addCell("Keresztnév");
            table.addCell("Email cím");
            
            table.getDefaultCell().setBackgroundColor(GrayColor.GRAYWHITE);
            table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            
            for(int counter = 1; counter < 10; counter++){
            table.addCell(String.valueOf(counter));
            table.addCell("key" + counter);
            table.addCell("value" + counter);
            }
            document.add(table);
            
            //Aláírás
            Chunk signature = new Chunk("\n\nGenerálva a Telefonkönyv alkalmazás segítségével.");
            Paragraph sigParagraph = new Paragraph(signature);

            document.add(sigParagraph);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        document.close();

    }

}
