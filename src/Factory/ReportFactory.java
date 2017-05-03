package Factory;

import model.Report;

/**
 * Created by Raluca on 29.04.2017.
 */
public abstract class ReportFactory {
    public String name;
    public abstract Report generateReport();

}
