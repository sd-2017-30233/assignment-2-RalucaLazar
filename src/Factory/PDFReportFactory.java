package Factory;

import model.PDFReport;
import model.Report;

/**
 * Created by Raluca on 29.04.2017.
 */
public class PDFReportFactory extends ReportFactory {

    public PDFReportFactory(String name){
        this.name = name;
    }

    public Report generateReport(){
        PDFReport report = new PDFReport(name);
        return report;
    }

}
