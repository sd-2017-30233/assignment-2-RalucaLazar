package model;

import Factory.Factory;
import service.AdminService;
import service.BookService;
import service.EmployeeService;

import javax.swing.*;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

/**
 * Created by Raluca on 12.04.2017.
 */
public class Administrator {

    private int id;
    private String username;
    private String password;
    private BookService bs;
    private EmployeeService es;
    private AdminService as;

    public Administrator() {
        bs = new BookService();
        es = new EmployeeService();
        as = new AdminService();
    }

    public Administrator(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
        bs = new BookService();
        es = new EmployeeService();
        as = new AdminService();
    }

    @XmlAttribute
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public void addBook(String title, String author, String genre, String quantity, String price) {
        if (!bs.exists(title, author))
            bs.addBook(title, author, genre, quantity, price);
        else JOptionPane.showMessageDialog(null,
                "Book already exists.",
                "Warning",
                JOptionPane.WARNING_MESSAGE);
    }

    public void viewBooks() {
        bs.createTable(bs.viewBooks());
    }

    public void viewBook(String id) {
        Book b = new Book();
        if (bs.exists(id)) {
            b = bs.viewBook(id);
            JPanel myPanel = new JPanel();
            myPanel.setLayout(new BoxLayout(myPanel, BoxLayout.Y_AXIS));
            myPanel.add(new JLabel("Book no: "+b.getId()));
            myPanel.add(new JLabel("Title: "+b.getTitle()));
            myPanel.add(new JLabel("Author: "+b.getAuthor()));
            myPanel.add(new JLabel("Quantity: "+b.getQuantity()));
            myPanel.add(new JLabel("Genre: "+b.getGenre()));
            myPanel.add(new JLabel("Price: "+b.getPrice()));
            JOptionPane.showConfirmDialog(null, myPanel,
                    "", JOptionPane.OK_CANCEL_OPTION);
        }
        else JOptionPane.showMessageDialog(null,
                "Book does not exist.",
                "Warning",
                JOptionPane.WARNING_MESSAGE);
    }

    public void updateBook(String id, String quantity, String price) {
        if (bs.exists(id))
            bs.updateBook(id, quantity, price);
        else JOptionPane.showMessageDialog(null,
                "Book does not exist.",
                "Warning",
                JOptionPane.WARNING_MESSAGE);
    }

    public void deleteBook(String id) {
        if (bs.exists(id))
            bs.deleteBook(id);
        else JOptionPane.showMessageDialog(null,
                "Book does not exist.",
                "Warning",
                JOptionPane.WARNING_MESSAGE);
    }

    public void addEmployee(String name, String username, String password) {
        if (!es.exists(name))
            es.addEmployee(name, username, password);
        else JOptionPane.showMessageDialog(null,
                "Employee already exists.",
                "Warning",
                JOptionPane.WARNING_MESSAGE);
    }

    public void viewEmployees() {
        es.createTable(es.viewEmployees());
    }

    public void viewEmployee(String id) {
        Employee e;
        if (es.exists(id)) {
            e = es.viewEmployee(id);
            JPanel myPanel = new JPanel();
            myPanel.setLayout(new BoxLayout(myPanel, BoxLayout.Y_AXIS));
            myPanel.add(new JLabel("Employee no: "+e.getId()));
            myPanel.add(new JLabel("Name: "+e.getName()));
            myPanel.add(new JLabel("Username: "+e.getUsername()));
            JOptionPane.showConfirmDialog(null, myPanel,
                    "", JOptionPane.OK_CANCEL_OPTION);

        }
        else JOptionPane.showMessageDialog(null,
                "Employee does not exist.",
                "Warning",
                JOptionPane.WARNING_MESSAGE);
    }

    public void updateEmployee(String id, String username, String password) {
        if (es.exists(id))
            es.updateEmployee(id, username, password);
        else JOptionPane.showMessageDialog(null,
                "Employee does not exist.",
                "Warning",
                JOptionPane.WARNING_MESSAGE);
    }

    public void deleteEmployee(String id) {
        if(es.exists(id))
            es.deleteEmployee(id);
        else JOptionPane.showMessageDialog(null,
                "Employee does not exist.",
                "Warning",
                JOptionPane.WARNING_MESSAGE);
    }

    public void generateReport(String type,String name){
        Factory fr = new Factory();
        Report r = fr.getFactory(type,name);
        r.generateReport();
        JOptionPane.showConfirmDialog(null,type+ " report generated.",
                "", JOptionPane.OK_CANCEL_OPTION);
    }

    public boolean verify(String username, String password){
        return as.verify(username, password);
    }

    public boolean exists(String id){
        return as.exists(id);
    }

}
