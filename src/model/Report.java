package model;

import java.util.Date;
import java.util.List;

/**
 * Created by Raluca on 17.04.2017.
 */
public abstract class Report {
    protected String name;
    protected Date date;

    public Report(String name){
        this.name = name;
        this.date = new Date();
    }

    public abstract void generateReport();

}
