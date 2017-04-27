package model;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by Raluca on 12.04.2017.
 */
@XmlRootElement
public class JaxbEmployeesList {
    public List<Employee> list;

    public JaxbEmployeesList(){}

    public JaxbEmployeesList(List list){
        this.list=list;
    }

    public List getList(){
        return list;
    }
}
