package Factory;

import model.Book;
import model.CSVReport;
import model.PDFReport;
import model.Report;

import java.util.List;

/**
 * Created by Raluca on 17.04.2017.
 */

public class ReportFactory {

    public Report makeReport(String type,String name){
        if(type.equals("PDF"))
            return new PDFReport(name);
        else if(type.equals("CSV"))
            return new CSVReport(name);
        else return null;
    }

}
