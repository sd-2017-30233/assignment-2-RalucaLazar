package service;

import model.Administrator;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.io.IOException;

/**
 * Created by Raluca on 18.04.2017.
 */
public class AdminService {

    File inputFile = new File("admins.xml");
    DocumentBuilderFactory dbFactory;
    DocumentBuilder dBuilder;
    Document doc;
    XPath xPath;
    String expression = "/jaxbAdminsList/list";
    NodeList nodeList;

    public AdminService(){
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

}
