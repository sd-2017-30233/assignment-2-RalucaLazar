package factory;

import model.CSVReport;
import model.Report;

/**
 * Created by Raluca on 29.04.2017.
 */
public class CSVReportFactory extends ReportFactory {

    public CSVReportFactory(String name){
        this.name = name;
    }

    public Report generateReport(){
        CSVReport report = new CSVReport(name);
        return report;
    }

}
