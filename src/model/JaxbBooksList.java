package model;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
public class JaxbBooksList {
    public List<Book> list;

    public JaxbBooksList(){}

    public JaxbBooksList(List list){
        this.list=list;
    }

    public List getList(){
        return list;
    }
}