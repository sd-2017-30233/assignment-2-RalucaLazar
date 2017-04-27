package model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Raluca on 12.04.2017.
 */
@XmlRootElement(name = "book")
public class Book {
    private int id;
    private String title;
    private String author;
    private String genre;
    private int quantity;
    private float price;

    public Book(){}

    public Book(int id,String title, String author, String genre, int quantity, float price) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.quantity = quantity;
        this.price = price;
        this.id = id;
    }

    @XmlAttribute
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @XmlElement
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @XmlElement
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @XmlElement
    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @XmlElement
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @XmlElement
    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public String toString(){
        return("Id: "+id+System.lineSeparator()+"Title: "+title+System.lineSeparator()+"Author: "+author+System.lineSeparator()+
        "Genre: "+genre+System.lineSeparator()+"Quantity: "+quantity+System.lineSeparator()+"Price: "+price+System.lineSeparator());
    }
}
