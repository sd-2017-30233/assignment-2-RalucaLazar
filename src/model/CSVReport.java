package model;

import service.BookService;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Raluca on 17.04.2017.
 */
public class CSVReport extends Report {

    BookService bs;

    public CSVReport(String name){
        super(name);
        bs = new BookService();
    }

    public void generateReport(){
        List<Book> books = bs.getBooksOutOfStock();
        System.out.println(books.size());
        try {
            FileWriter writer = new FileWriter(this.name+".csv");
            for(int i=0;i<books.size();i++) {
                Book b = books.get(i);
                CSVUtils.writeLine(writer, Arrays.asList(""+b.getId(), b.getTitle(), b.getAuthor(),b.getGenre(),""+b.getPrice()));
            }
            writer.flush();
            writer.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

}
