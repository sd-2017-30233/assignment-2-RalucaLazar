package service;

import model.Book;
import model.Employee;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Raluca on 12.04.2017.
 */
public class EmployeeService {

    File inputFile = new File("employees.xml");
    DocumentBuilderFactory dbFactory;
    DocumentBuilder dBuilder;
    Document doc;
    XPath xPath;
    String expression = "/jaxbEmployeesList/list";
    NodeList nodeList;

    public EmployeeService(){
        try {

            dbFactory = DocumentBuilderFactory.newInstance();
            dBuilder = dbFactory.newDocumentBuilder();
            doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            xPath = XPathFactory.newInstance().newXPath();
            nodeList = (NodeList) xPath.compile(expression).evaluate(doc, XPathConstants.NODESET);

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
    }

    public void addEmployee(String name, String username, String password) {
        try {
            nodeList = (NodeList) xPath.compile("/jaxbEmployeesList").evaluate(doc, XPathConstants.NODESET);
            Node nNode = nodeList.item(0);
            Element eElement = (Element) nNode;
            int id = Integer.parseInt(eElement.getLastChild().getAttributes().item(0).getTextContent());
            Element e = doc.createElement("list");
            id++;
            e.setAttribute("id",""+id);
            nNode.appendChild(e);

            Element a = doc.createElement("name");
            a.appendChild(doc.createTextNode(name));
            e.appendChild(a);

            Element p = doc.createElement("password");
            p.appendChild(doc.createTextNode(password));
            e.appendChild(p);

            Element g = doc.createElement("username");
            g.appendChild(doc.createTextNode(username));
            e.appendChild(g);

            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(new DOMSource(doc), new StreamResult(new File("employees.xml")));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public List<Employee> viewEmployees(){
        List<Employee> employees = new ArrayList<>();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node nNode = nodeList.item(i);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                Employee e = new Employee();
                e.setId(Integer.parseInt(eElement.getAttribute("id")));
                e.setName(eElement.getElementsByTagName("name").item(0).getTextContent());
                e.setUsername(eElement.getElementsByTagName("username").item(0).getTextContent());
                e.setPassword(eElement.getElementsByTagName("password").item(0).getTextContent());
                employees.add(e);
            }
        }
        return employees;
    }

    public Employee viewEmployee(String id){
        Employee e = new Employee();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node nNode = nodeList.item(i);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                if (eElement.getAttribute("id").equals(id)) {
                    e.setId(Integer.parseInt(eElement.getAttribute("id")));
                    e.setName(eElement.getElementsByTagName("name").item(0).getTextContent());
                    e.setUsername(eElement.getElementsByTagName("username").item(0).getTextContent());
                    e.setPassword(eElement.getElementsByTagName("password").item(0).getTextContent());
                }
            }
        }
        return e;
    }

    public void updateEmployee(String id,String username,String password){
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node nNode = nodeList.item(i);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                String elId = eElement.getAttribute("id");
                if (elId.equals(id)) {
                    try {
                        changeNodeText(doc, "/jaxbEmployeesList/list/username", username,i);
                        changeNodeText(doc, "/jaxbEmployeesList/list/password", password,i);
                        Transformer transformer = TransformerFactory.newInstance().newTransformer();
                        transformer.transform(new DOMSource(doc), new StreamResult(new File("employees.xml")));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public void deleteEmployee(String id){
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node nNode = nodeList.item(i);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                String elId = eElement.getAttribute("id");
                if (elId.equals(id)) {
                    try {
                        nNode.getParentNode().removeChild(nNode);
                        Transformer transformer = TransformerFactory.newInstance().newTransformer();
                        transformer.transform(new DOMSource(doc), new StreamResult(new File("employees.xml")));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public void changeNodeText(Node context, String xpath, String value, int i) {
        try {
            XPathFactory xFactory = XPathFactory.newInstance();
            XPath xPath = xFactory.newXPath();
            XPathExpression expression = xPath.compile(xpath);
            NodeList nodes = (NodeList) expression.evaluate(context, XPathConstants.NODESET);
            Node node = nodes.item(i);
            node.setTextContent(value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean verify(String username, String password){
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node nNode = nodeList.item(i);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                String thisUsername = eElement
                        .getElementsByTagName("username")
                        .item(0)
                        .getTextContent();
                String thisPassword = eElement
                        .getElementsByTagName("password")
                        .item(0)
                        .getTextContent();
                if (username.equals(thisUsername) && password.equals(thisPassword))
                    return true;
            }
        }
        return false;
    }

    public boolean exists(String id,String name){
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node nNode = nodeList.item(i);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                String thisName = eElement
                        .getElementsByTagName("name")
                        .item(0)
                        .getTextContent();
                String thisId =  eElement.getAttribute("id");
                if (thisId.equals(id) && name.equals(thisName))
                    return true;
            }
        }
        return false;
    }

    public boolean exists(String id){
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node nNode = nodeList.item(i);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                String thisId =  eElement.getAttribute("id");
                if (thisId.equals(id))
                    return true;
            }
        }
        return false;
    }

    public void createTable(List<Employee> employees){
        String[] columnNames = {"Id","Name","Username"};
        String[][] data = new String[50][3];

        for(int i=0;i<employees.size();i++){
            data[i][0] = ""+employees.get(i).getId();
            data[i][1] = employees.get(i).getName();
            data[i][2] = employees.get(i).getUsername();
        }

        DefaultTableModel model=new DefaultTableModel(data,columnNames);
        JTable t=new JTable(model);
        t.setEnabled(false);

        JDialog dialog=new JDialog();
        //dialog.setBounds(0,0,400,400);
        dialog.setLocationRelativeTo(null);
        dialog.setTitle("Employees");

        JScrollPane scrollPane3 = new JScrollPane(t);
        scrollPane3.setBounds(10, 50, 950, 120);
        scrollPane3.setEnabled(false);

        dialog.add(scrollPane3);
        dialog.pack();
        dialog.setVisible(true);
    }

}
