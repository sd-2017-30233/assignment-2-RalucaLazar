package factory;

import model.Report;

/**
 * Created by Raluca on 17.04.2017.
 */

public class Factory {

    ReportFactory rf;

    public Report getFactory(String type, String name) {
        if (type.equals("PDF")) {
            rf = new PDFReportFactory(name);
            return rf.generateReport();
        } else if (type.equals("CSV")) {
            rf = new CSVReportFactory(name);
            return rf.generateReport();
        } else return null;
    }
}
