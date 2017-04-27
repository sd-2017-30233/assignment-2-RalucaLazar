package model;

import service.BookService;
import service.EmployeeService;

import javax.swing.*;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;

/**
 * Created by Raluca on 12.04.2017.
 */
public class Employee {

    private int id;
    private String name;
    private String username;
    private String password;
    private BookService bs;
    private EmployeeService es;

    public Employee(){
        bs = new BookService();
        es = new EmployeeService();
    }

    public Employee(int id,String name,String username, String password) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        bs = new BookService();
        es = new EmployeeService();
    }

    @XmlAttribute
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @XmlElement
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlElement
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @XmlElement
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public void searchBooksByGenre(String genre){
        bs.createTable(bs.searchBooksByGenre(genre));
    }

    public void searchBooksByTitle(String title){
         bs.createTable(bs.searchBooksByTitle(title));
    }

    public void searchBooksByAuthor(String author){
         bs.createTable(bs.searchBooksByAuthor(author));
    }

    public void sellBook(String title,String author){
        List<Book> books = bs.getBooksOutOfStock();
        boolean ok = false;
        if(bs.exists(title,author)) {
            for(int i=0;i<books.size();i++)
                if(books.get(i).getTitle().equals(title) && books.get(i).getAuthor().equals(author))
                    ok = true;
            if(ok)
                JOptionPane.showMessageDialog(null,
                            "Book out of stock.",
                            "Warning",
                            JOptionPane.WARNING_MESSAGE);
            else {
                bs.sellBook(title, author);
                JOptionPane.showMessageDialog(null,
                        "Sold book "+title+", "+author+".",
                        "Success",
                        JOptionPane.WARNING_MESSAGE);
            }
        }
        else JOptionPane.showMessageDialog(null,
                "Book does not exist.",
                "Warning",
                JOptionPane.WARNING_MESSAGE);
    }

    public boolean verify(String username, String password){
        if(es == null) System.out.println("es null");
       return es.verify(username, password);
    }

    public boolean exists(String id,String name){
        return es.exists(id, name);
    }

    public boolean exists(String id){
        return es.exists(id);
    }

}
