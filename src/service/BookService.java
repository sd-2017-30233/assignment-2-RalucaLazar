package service;

import model.Book;
import org.w3c.dom.*;
import org.xml.sax.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.xml.parsers.*;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Raluca on 12.04.2017.
 */
public class BookService {

    File inputFile = new File("books.xml");
    DocumentBuilderFactory dbFactory;
    DocumentBuilder dBuilder;
    Document doc;
    XPath xPath;
    String expression = "/jaxbBooksList/list";
    NodeList nodeList;

    public BookService() {
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

    public List<Book> searchBooksByGenre(String genre) {
        List<Book> books = new ArrayList<Book>();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node nNode = nodeList.item(i);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                if (eElement.getElementsByTagName("genre").item(0).getTextContent().equals(genre)) {
                    Book b = new Book();
                    b.setId(Integer.parseInt(eElement.getAttribute("id")));
                    b.setTitle(eElement.getElementsByTagName("title").item(0).getTextContent());
                    b.setAuthor(eElement.getElementsByTagName("author").item(0).getTextContent());
                    b.setGenre(eElement.getElementsByTagName("genre").item(0).getTextContent());
                    b.setQuantity(Integer.parseInt(eElement.getElementsByTagName("quantity").item(0).getTextContent()));
                    b.setPrice(Float.parseFloat(eElement.getElementsByTagName("price").item(0).getTextContent()));
                    books.add(b);
                    System.out.println(books.size());
                }
            }
        }
        return books;
    }

    public List<Book> searchBooksByTitle(String title) {
        List<Book> books = new ArrayList<Book>();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node nNode = nodeList.item(i);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                if (eElement.getElementsByTagName("title").item(0).getTextContent().equals(title)) {
                    Book b1 = new Book();
                    b1.setId(Integer.parseInt(eElement.getAttribute("id")));
                    b1.setTitle(eElement.getElementsByTagName("title").item(0).getTextContent());
                    b1.setAuthor(eElement.getElementsByTagName("author").item(0).getTextContent());
                    b1.setGenre(eElement.getElementsByTagName("genre").item(0).getTextContent());
                    b1.setQuantity(Integer.parseInt(eElement.getElementsByTagName("quantity").item(0).getTextContent()));
                    b1.setPrice(Float.parseFloat(eElement.getElementsByTagName("price").item(0).getTextContent()));
                    books.add(b1);
                    System.out.println(books.size());
                }
            }
        }
        return books;
    }

    public List<Book> searchBooksByAuthor(String author) {
        List<Book> books = new ArrayList<Book>();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node nNode = nodeList.item(i);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                if (eElement.getElementsByTagName("author").item(0).getTextContent().equals(author)) {
                    Book b2 = new Book();
                    b2.setId(Integer.parseInt(eElement.getAttribute("id")));
                    b2.setTitle(eElement.getElementsByTagName("title").item(0).getTextContent());
                    b2.setAuthor(eElement.getElementsByTagName("author").item(0).getTextContent());
                    b2.setGenre(eElement.getElementsByTagName("genre").item(0).getTextContent());
                    b2.setQuantity(Integer.parseInt(eElement.getElementsByTagName("quantity").item(0).getTextContent()));
                    b2.setPrice(Float.parseFloat(eElement.getElementsByTagName("price").item(0).getTextContent()));
                    books.add(b2);
                    System.out.println(books.size());
                }
            }
        }
        return books;
    }

    public void sellBook(String title, String author) {
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node nNode = nodeList.item(i);
            System.out.println(nNode.toString());
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                System.out.println(eElement.toString());
                String t = eElement.getElementsByTagName("title").item(0).getTextContent();
                String a = eElement.getElementsByTagName("author").item(0).getTextContent();
                int q = Integer.parseInt(eElement.getElementsByTagName("quantity").item(0).getTextContent());
                if (t.equals(title) && a.equals(author)) {
                    try {
                        q -= 1;
                        changeNodeText(doc, "/jaxbBooksList/list/quantity", "" + q, i);
                        Transformer transformer = TransformerFactory.newInstance().newTransformer();
                        transformer.transform(new DOMSource(doc), new StreamResult(new File("books.xml")));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public void addBook(String title, String author, String genre, String quantity, String price) {
        //Book book = new Book(10, title, author, genre, Integer.parseInt(quantity), Float.parseFloat(price));

        try {
            nodeList = (NodeList) xPath.compile("/jaxbBooksList").evaluate(doc, XPathConstants.NODESET);
            Node nNode = nodeList.item(0);
            Element eElement = (Element) nNode;
            int id = Integer.parseInt(eElement.getLastChild().getAttributes().item(0).getTextContent());
            Element e = doc.createElement("list");
            id++;
            e.setAttribute("id",""+id);
            nNode.appendChild(e);

            Element a = doc.createElement("author");
            a.appendChild(doc.createTextNode(author));
            e.appendChild(a);

            Element g = doc.createElement("genre");
            g.appendChild(doc.createTextNode(genre));
            e.appendChild(g);

            Element p = doc.createElement("price");
            p.appendChild(doc.createTextNode(price));
            e.appendChild(p);

            Element q = doc.createElement("quantity");
            q.appendChild(doc.createTextNode(quantity));
            e.appendChild(q);

            Element t = doc.createElement("title");
            t.appendChild(doc.createTextNode(title));
            e.appendChild(t);

            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(new DOMSource(doc), new StreamResult(new File("books.xml")));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public List<Book> viewBooks(){
        List<Book> books = new ArrayList<>();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node nNode = nodeList.item(i);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                Book b = new Book();
                b.setId(Integer.parseInt(eElement.getAttribute("id")));
                b.setTitle(eElement.getElementsByTagName("title").item(0).getTextContent());
                b.setAuthor(eElement.getElementsByTagName("author").item(0).getTextContent());
                b.setGenre(eElement.getElementsByTagName("genre").item(0).getTextContent());
                b.setPrice(Float.parseFloat(eElement.getElementsByTagName("price").item(0).getTextContent()));
                b.setQuantity(Integer.parseInt(eElement.getElementsByTagName("quantity").item(0).getTextContent()));
                books.add(b);
            }
        }
        return books;
    }

    public Book viewBook(String id){
        Book b = new Book();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node nNode = nodeList.item(i);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                if (eElement.getAttribute("id").equals(id)) {
                    b.setId(Integer.parseInt(eElement.getAttribute("id")));
                    b.setTitle(eElement.getElementsByTagName("title").item(0).getTextContent());
                    b.setAuthor(eElement.getElementsByTagName("author").item(0).getTextContent());
                    b.setGenre(eElement.getElementsByTagName("genre").item(0).getTextContent());
                    b.setPrice(Float.parseFloat(eElement.getElementsByTagName("price").item(0).getTextContent()));
                    b.setQuantity(Integer.parseInt(eElement.getElementsByTagName("quantity").item(0).getTextContent()));
                }
            }
        }
        return b;
    }

    public void updateBook(String id,String quantity,String price){
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node nNode = nodeList.item(i);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                String elId = eElement.getAttribute("id");
                if (elId.equals(id)) {
                    try {
                        changeNodeText(doc, "/jaxbBooksList/list/quantity", quantity,i);
                        changeNodeText(doc, "/jaxbBooksList/list/price", price,i);
                        Transformer transformer = TransformerFactory.newInstance().newTransformer();
                        transformer.transform(new DOMSource(doc), new StreamResult(new File("books.xml")));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public void deleteBook(String id){
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node nNode = nodeList.item(i);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                String elId = eElement.getAttribute("id");
                if (elId.equals(id)) {
                    try {
                        nNode.getParentNode().removeChild(nNode);
                        Transformer transformer = TransformerFactory.newInstance().newTransformer();
                        transformer.transform(new DOMSource(doc), new StreamResult(new File("books.xml")));
                        String title = eElement.getElementsByTagName("title").item(0).getTextContent();
                        String author = eElement.getElementsByTagName("author").item(0).getTextContent();
                        JOptionPane.showConfirmDialog(null, "Removed book "+title+", "+author+".",
                                "", JOptionPane.OK_CANCEL_OPTION);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public List<Book> getBooksOutOfStock(){
        List<Book> books = new ArrayList<Book>();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node nNode = nodeList.item(i);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                String q = eElement.getElementsByTagName("quantity").item(0).getTextContent();
                if(q.equals("0")){
                    Book b = new Book();
                    b.setId(Integer.parseInt(eElement.getAttribute("id")));
                    b.setTitle(eElement.getElementsByTagName("title").item(0).getTextContent());
                    b.setAuthor(eElement.getElementsByTagName("author").item(0).getTextContent());
                    b.setGenre(eElement.getElementsByTagName("genre").item(0).getTextContent());
                    b.setPrice(Float.parseFloat(eElement.getElementsByTagName("price").item(0).getTextContent()));
                    b.setQuantity(0);
                    books.add(b);
                }
            }
        }
        return books;
    }

    public boolean exists(String title, String author){
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node nNode = nodeList.item(i);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                String thisTitle = eElement
                        .getElementsByTagName("title")
                        .item(0)
                        .getTextContent();
                String thisAuthor = eElement
                        .getElementsByTagName("author")
                        .item(0)
                        .getTextContent();
                if (title.equals(thisTitle) && author.equals(thisAuthor))
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
                String thisId = eElement.getAttribute("id");
                if (id.equals(thisId))
                    return true;
            }
        }
        return false;
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

    public void createTable(List<Book> books){
        String[] columnNames = {"Id","Title","Author","Genre","Quantity","Price"};
        String[][] data = new String[50][6];

        for(int i=0;i<books.size();i++){
            data[i][0] = ""+books.get(i).getId();
            data[i][1] = books.get(i).getTitle();
            data[i][2] = books.get(i).getAuthor();
            data[i][3] = books.get(i).getGenre();
            data[i][4] = ""+books.get(i).getQuantity();
            data[i][5] = ""+books.get(i).getPrice();
        }

        DefaultTableModel model=new DefaultTableModel(data,columnNames);
        JTable t=new JTable(model);
        t.setEnabled(false);

        JDialog dialog=new JDialog();
        //dialog.setBounds(0,0,400,400);
        dialog.setLocationRelativeTo(null);
        dialog.setTitle("Books");

        JScrollPane scrollPane3 = new JScrollPane(t);
        scrollPane3.setBounds(10, 50, 950, 120);
        scrollPane3.setEnabled(false);

        dialog.add(scrollPane3);
        dialog.pack();
        dialog.setVisible(true);
    }

}
