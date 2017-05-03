package model;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import service.BookService;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Raluca on 17.04.2017.
 */
public class PDFReport extends Report {

    BookService bs;

    public PDFReport(String name){
        this.name = name;
        bs = new BookService();
    }

    public void generateReport(){
        Document document = new Document();
        List<Book> books = bs.getBooksOutOfStock();
        try
        {
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(this.name+".pdf"));
            document.open();
            for(int i=0;i<books.size();i++) {
                document.add(new Paragraph(books.get(i).toString()));
            }
            document.close();
            writer.close();
        } catch (DocumentException e)
        {
            e.printStackTrace();
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }

}
