package GUI;

/**
 * Created by Raluca on 25.03.2017.
 */

import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class View extends JFrame {

    public JLabel background;
    public JLabel background1;
    public JLabel background2;
    public JLabel background3;
    public JLabel background4;
    public JLabel welcomeLabel;
    public JLabel signInLabel;
    public JLabel usernameLabel;
    public JLabel passwordLabel;
    public JLabel signInLabel1;
    public JLabel usernameLabel1;
    public JLabel passwordLabel1;
    public JLabel employeeLabel;
    public JLabel adminLabel;
    public JButton okButton;
    public JButton okButton1;
    public JButton okButton2;
    public JButton backButton1;
    public JButton backButton2;
    public JMenuBar jmenu;
    public JComboBox combo;
    public JPasswordField passwordField;
    public JTextField usernameTextField;
    public JPasswordField passwordField1;
    public JTextField usernameTextField1;

    public JButton performButton;
    public JRadioButton addB;
    public JRadioButton updateB;
    public JRadioButton viewB;
    public JRadioButton viewBs;
    public JRadioButton deleteB;
    public JRadioButton addE;
    public JRadioButton updateE;
    public JRadioButton viewE;
    public JRadioButton viewEs;
    public JRadioButton deleteE;
    public JRadioButton generatePDF;
    public JRadioButton generateCSV;
    public JLabel actionsLabel;
    ButtonGroup btnGroup;

    public JButton performButton1;
    public JRadioButton searchGenre;
    public JRadioButton searchTitle;
    public JRadioButton searchAuthor;
    public JRadioButton sellBook;
    public JLabel actionsLabel1;
    ButtonGroup btnGroup1;

    public JLabel invalid;
    public JLabel invalid1;

    public ImageIcon imageIcon;


    public View() {
        //construct preComponents
        imageIcon = new ImageIcon("book.jpeg");
        background=new JLabel(imageIcon);
        background1=new JLabel(imageIcon);
        background2=new JLabel(imageIcon);
        background3=new JLabel(imageIcon);
        background4=new JLabel(imageIcon);

        background1.setVisible (false);
        background2.setVisible (false);
        background3.setVisible (false);
        background4.setVisible(false);

        JMenu fileMenu = new JMenu ("File");
        JMenuItem exitItem = new JMenuItem ("Exit");
        fileMenu.add (exitItem);
        JMenu helpMenu = new JMenu ("Help");
        JMenuItem contentsItem = new JMenuItem ("Contents");
        helpMenu.add (contentsItem);
        JMenuItem aboutItem = new JMenuItem ("About");
        helpMenu.add (aboutItem);
        String[] comboItems = {"Employee", "Administrator"};

        Font font = new Font("Serif",Font.BOLD + Font.ITALIC,50);
        Font font1 = new Font("Serif",Font.ITALIC,30);

        ImageIcon image = new ImageIcon("image.png");
        welcomeLabel = new JLabel(image);

        //welcomeLabel = new JLabel ("Welcome");
        okButton = new JButton ("Ok");
        okButton1 = new JButton ("Ok");
        okButton2 = new JButton ("Ok");
        backButton1 = new JButton ("Back");
        backButton2 = new JButton ("Back");
        jmenu = new JMenuBar();
        jmenu.add (fileMenu);
        jmenu.add (helpMenu);
        combo = new JComboBox (comboItems);
        signInLabel = new JLabel ("Sign in");
        usernameLabel = new JLabel ("Username:");
        passwordLabel = new JLabel ("Password:");
        usernameTextField = new JTextField (5);
        passwordField = new JPasswordField ();
        signInLabel1 = new JLabel ("Sign in");
        usernameLabel1 = new JLabel ("Username:");
        passwordLabel1 = new JLabel ("Password:");
        usernameTextField1 = new JTextField (5);
        passwordField1 = new JPasswordField ();
        employeeLabel = new JLabel("--Employee--");
        adminLabel = new JLabel("--Administrator--");
        btnGroup = new ButtonGroup();

        performButton = new JButton ("Perform");
        addB = new JRadioButton("Add new book");
        viewB = new JRadioButton("View book information");
        viewBs = new JRadioButton("View list of books");
        updateB = new JRadioButton("Update book");
        deleteB = new JRadioButton("Remove book");
        addE = new JRadioButton("Add new employee");
        viewE = new JRadioButton("View employee information");
        viewEs = new JRadioButton("View list of employees");
        updateE = new JRadioButton("Update employee");
        deleteE = new JRadioButton("Remove employee");
        generatePDF = new JRadioButton("Generate report in PDF format");
        generateCSV = new JRadioButton("Generate report in CSV format");
        actionsLabel = new JLabel ("Actions");
        btnGroup1 = new ButtonGroup();

        invalid = new JLabel ("Invalid username or password.");
        invalid.setForeground(Color.red);
        invalid1 = new JLabel ("Invalid username or password.");
        invalid1.setForeground(Color.red);

        invalid.setVisible(false);
        invalid1.setVisible(false);

        btnGroup.add(addB);
        btnGroup.add(viewB);
        btnGroup.add(viewBs);
        btnGroup.add(updateB);
        btnGroup.add(deleteB);
        btnGroup.add(addE);
        btnGroup.add(viewE);
        btnGroup.add(viewEs);
        btnGroup.add(updateE);
        btnGroup.add(deleteE);
        btnGroup.add(generatePDF);
        btnGroup.add(generateCSV);

        performButton1 = new JButton ("Perform");
        searchGenre = new JRadioButton ("Search book by genre");
        searchTitle = new JRadioButton ("Search book by title");
        searchAuthor = new JRadioButton ("Search book by author");
        sellBook = new JRadioButton ("Sell book");

        actionsLabel1 = new JLabel ("Actions");
        btnGroup1.add(searchGenre);
        btnGroup1.add(searchTitle);
        btnGroup1.add(searchAuthor);
        btnGroup1.add(sellBook);

        //adjust size and set layout
        setPreferredSize (new Dimension (784, 454));
        setLayout (null);

        //add components
        background.add(welcomeLabel);
        background.add(combo);
        background.add(jmenu);
        background.add(okButton);

        background1.add (okButton1);
        background1.add (backButton1);
        background1.add(signInLabel);
        background1.add(usernameLabel);
        background1.add(passwordLabel);
        background1.add(usernameTextField);
        background1.add(passwordField);
        background1.add(employeeLabel);
        background1.add(invalid);

        background2.add (okButton2);
        background2.add (backButton2);
        background2.add(signInLabel1);
        background2.add(usernameLabel1);
        background2.add(passwordLabel1);
        background2.add(usernameTextField1);
        background2.add(passwordField1);
        background2.add(adminLabel);
        background2.add(invalid1);

        background3.add (performButton);
        background3.add (searchGenre);
        background3.add (searchTitle);
        background3.add (searchAuthor);
        background3.add (sellBook);
        background3.add (actionsLabel);

        background4.add (performButton1);
        background4.add (addB);
        background4.add (viewB);
        background4.add (viewBs);
        background4.add (updateB);
        background4.add (deleteB);
        background4.add (addE);
        background4.add (viewE);
        background4.add (viewEs);
        background4.add (updateE);
        background4.add (deleteE);
        background4.add(generatePDF);
        background4.add(generateCSV);
        background4.add (actionsLabel1);

        //set component bounds (only needed by Absolute Positioning)
        welcomeLabel.setBounds (0, 70, 800, 100);
        welcomeLabel.setForeground(Color.DARK_GRAY);
        welcomeLabel.setFont(font);

        okButton.setBounds (365, 320, 67, 29);
        okButton1.setBounds (445, 305, 70, 30);
        okButton2.setBounds (445, 305, 70, 30);
        backButton1.setBounds (335, 305, 70, 30);
        backButton2.setBounds (335, 305, 70, 30);
        jmenu.setBounds (0, 0, 785, 25);
        combo.setBounds (340, 250, 140, 25);


        signInLabel.setBounds (380, 70, 100, 50);
        signInLabel.setFont(font1);
        employeeLabel.setBounds (390, 100, 100, 50);
        usernameLabel.setBounds (300, 180, 100, 25);
        usernameTextField.setBounds (380, 180, 145, 25);
        passwordLabel.setBounds (300, 220, 100, 25);
        passwordField.setBounds (380, 220, 145, 25);
        invalid.setBounds (350, 260, 200, 25);

        signInLabel1.setBounds (380, 70, 100, 50);
        signInLabel1.setFont(font1);
        adminLabel.setBounds (380, 100, 100, 50);
        usernameLabel1.setBounds (300, 180, 100, 25);
        usernameTextField1.setBounds (380, 180, 145, 25);
        passwordLabel1.setBounds (300, 220, 100, 25);
        passwordField1.setBounds (380, 220, 145, 25);
        invalid1.setBounds (350, 260, 200, 25);

        performButton.setBounds (330, 370, 100, 25);
        searchGenre.setBounds (250, 115, 300, 25);
        searchTitle.setBounds (250, 150, 300, 25);
        searchAuthor.setBounds (250, 185, 300, 25);
        sellBook.setBounds(250,220,300,25);
        actionsLabel.setBounds (350, 40, 100, 25);

        actionsLabel1.setBounds (370, 40, 100, 25);
        addB.setBounds (250, 75, 300, 25);
        viewB.setBounds (250, 105, 300, 25);
        viewBs.setBounds (250, 135, 300, 25);
        updateB.setBounds (250, 165, 300, 25);
        deleteB.setBounds (250, 195, 300, 25);
        addE.setBounds (250, 225, 300, 25);
        viewE.setBounds (250, 255, 300, 25);
        viewEs.setBounds (250, 285, 300, 25);
        updateE.setBounds (250, 315, 300, 25);
        deleteE.setBounds(250,345,300,25);
        generatePDF.setBounds(250,375,300,25);
        generateCSV.setBounds(250,405,300,25);
        performButton1.setBounds (345, 445, 100, 25);


        this.setJMenuBar(jmenu);
        this.setTitle("Book store");
        this.setPreferredSize (new Dimension (784, 600));
        this.setResizable(false);
        this.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        this.setContentPane(background);
        this.pack();
        this.setVisible (true);
    }


    void addComboListener(ActionListener mal)
    {
        okButton.addActionListener(mal);
    }

    void addOk1Listener(ActionListener mal)
    {
        okButton1.addActionListener(mal);
    }

    void addOk2Listener(ActionListener mal)
    {
        okButton2.addActionListener(mal);
    }

    void addBack1Listener(ActionListener mal)
    {
        backButton1.addActionListener(mal);
    }


    void addBack2Listener(ActionListener mal)
    {
        backButton2.addActionListener(mal);
    }

    void addEmployeeBtnListener(ActionListener mal)
    {
        performButton.addActionListener(mal);
    }

    void addAdministratorBtnListener(ActionListener mal)
    {
        performButton1.addActionListener(mal);
    }

}
