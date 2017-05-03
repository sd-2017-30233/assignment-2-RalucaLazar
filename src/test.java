import GUI.Controller;
import GUI.View;
import model.*;
import service.BookService;
import service.EmployeeService;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Raluca on 12.04.2017.
 */
public class test {

    public static void main(String[] args) {
        //Serializing ...
        /*
        List<Book> books = new ArrayList<Book>();

        List<Employee> employees = new ArrayList<Employee>();
        List<Administrator> admins = new ArrayList<Administrator>();

        Book b1 = new Book(1,"Pride and prejudice","Jane Austen","novel, romance",10,20);
        Book b2 = new Book(2,"Jane Eyre","Charlotte Brontë","novel, romance",5,25);
        Book b3 = new Book(3,"Far from the Madding Crowd","Thomas Hardy","novel, romance",15,30);

        books.add(b1);
        books.add(b2);
        books.add(b3);

        Employee e1 = new Employee(1,"Popescu Alexandru","alexandru","alexandru");
        Employee e2 = new Employee(2,"Pop Andreea","andreea","andreea");
        Employee e3 = new Employee(3,"Popa Ana","ana","ana");

        employees.add(e1);
        employees.add(e2);
        employees.add(e3);

        Administrator a1 = new Administrator(1,"Ionescu Ion","ionescu");
        Administrator a2 = new Administrator(2,"Marinescu Ana","marinescu");

        admins.add(a1);
        admins.add(a2);

        JaxbBooksList jaxbBooks = new JaxbBooksList(books);
        JaxbEmployeesList jaxbEmployees = new JaxbEmployeesList(employees);
        JaxbAdminsList jaxbAdmins = new JaxbAdminsList(admins);

        try {
            File booksFile = new File("books.xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(JaxbBooksList.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(jaxbBooks, booksFile);
            //jaxbMarshaller.marshal(jaxbBooks, System.out);

            File employeesFile = new File("employees.xml");
            JAXBContext jaxbContext1 = JAXBContext.newInstance(JaxbEmployeesList.class);
            Marshaller jaxbMarshaller1 = jaxbContext1.createMarshaller();
            jaxbMarshaller1.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller1.marshal(jaxbEmployees, employeesFile);

            File adminsFile = new File("admins.xml");
            JAXBContext jaxbContext2 = JAXBContext.newInstance(JaxbAdminsList.class);
            Marshaller jaxbMarshaller2 = jaxbContext2.createMarshaller();
            jaxbMarshaller2.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller2.marshal(jaxbAdmins, adminsFile);

        } catch (JAXBException e) {
            e.printStackTrace();
        }
        */
        View view = new View();
        Controller controller = new Controller(view);
    }

}
