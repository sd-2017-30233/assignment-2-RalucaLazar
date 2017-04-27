package GUI;

import model.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * Created by Raluca on 25.03.2017.
 */
public class Controller {

    View view;
    Employee registeredEmployee;
    Administrator registeredAdmin;

    public Controller(View view){
        this.view = view;
        view.addComboListener(new ComboListener());
        view.addOk1Listener(new Ok1Listener());
        view.addOk2Listener(new Ok2Listener());
        view.addBack1Listener(new BackListener());
        view.addBack2Listener(new BackListener());
        view.addEmployeeBtnListener(new EmloyeeRBListener());
        view.addAdministratorBtnListener(new AdministratorRBListener());
    }

    class ComboListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String comboBox = (String) view.combo.getSelectedItem();
            switch (comboBox) {
                case "Employee": {
                    view.panel1.setVisible (true);
                    view.setContentPane(view.panel1);
                    view.pack();
                }
                break;
                case "Administrator": {
                    view.panel2.setVisible (true);
                    view.setContentPane(view.panel2);
                    view.pack();
                }
            }
        }
    }

    class Ok1Listener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String username = view.usernameTextField.getText();
            String password = view.passwordField.getText();
            registeredEmployee = new Employee();
            registeredEmployee.setUsername(username);
            registeredEmployee.setPassword(password);
            try {
                if(registeredEmployee.verify(username,password)){
                    view.panel3.setVisible(true);
                    view.setContentPane(view.panel3);
                    view.pack();
                }
                else view.invalid.setVisible(true);
            }catch(Exception exception)
            {
                exception.printStackTrace();
            }
        }
    }

    class Ok2Listener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String username = view.usernameTextField1.getText();
            String password = view.passwordField1.getText();
            registeredAdmin = new Administrator();
            registeredAdmin.setUsername(view.usernameTextField1.getText());
            registeredAdmin.setPassword(view.passwordField1.getText());
            try {
                if(registeredAdmin.verify(username,password)) {
                    view.panel4.setVisible(true);
                    view.setContentPane(view.panel4);
                    view.pack();
                }
                else view.invalid1.setVisible(true);
            }catch(Exception exception)
            {
                exception.printStackTrace();
            }
        }
    }

    class BackListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            view.panel0.setVisible (true);
            view.setContentPane(view.panel0);
            view.pack();
        }
    }

    class EmloyeeRBListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String selected = getSelectedButtonText(view.btnGroup1);
            switch (selected) {
                case "Search book by genre": {
                    JPanel myPanel = new JPanel();
                    JTextField nameField = new JTextField(10);

                    myPanel.add(new JLabel("Genre:"));
                    myPanel.add(nameField);

                    int result = JOptionPane.showConfirmDialog(null, myPanel,
                            "", JOptionPane.OK_CANCEL_OPTION);
                    if (result == JOptionPane.OK_OPTION) {
                        registeredEmployee.searchBooksByGenre(nameField.getText());
                    }
                }
                break;
                case "Search book by title": {
                    JPanel myPanel = new JPanel();
                    JTextField nameField = new JTextField(10);

                    myPanel.add(new JLabel("Title:"));
                    myPanel.add(nameField);

                    int result = JOptionPane.showConfirmDialog(null, myPanel,
                            "", JOptionPane.OK_CANCEL_OPTION);
                    if (result == JOptionPane.OK_OPTION) {
                        registeredEmployee.searchBooksByTitle(nameField.getText());
                    }
                }
                break;
                case "Search book by author": {
                    JPanel myPanel = new JPanel();
                    JTextField nameField = new JTextField(10);

                    myPanel.add(new JLabel("Author:"));
                    myPanel.add(nameField);

                    int result = JOptionPane.showConfirmDialog(null, myPanel,
                            "", JOptionPane.OK_CANCEL_OPTION);
                    if (result == JOptionPane.OK_OPTION) {
                        registeredEmployee.searchBooksByAuthor(nameField.getText());
                    }
                }
                case "Sell book": {
                    JPanel myPanel = new JPanel();
                    JTextField title = new JTextField(15);
                    JTextField author = new JTextField(15);

                    myPanel.add(new JLabel("Title:"));
                    myPanel.add(title);
                    myPanel.add(Box.createHorizontalStrut(15)); // a spacer
                    myPanel.add(new JLabel("Author:"));
                    myPanel.add(author);

                    int result = JOptionPane.showConfirmDialog(null, myPanel,
                            "", JOptionPane.OK_CANCEL_OPTION);
                    if (result == JOptionPane.OK_OPTION) {
                        registeredEmployee.sellBook(title.getText(),author.getText());
                    }
                }
            }
        }
    }

    class AdministratorRBListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String selected = getSelectedButtonText(view.btnGroup);
            switch (selected) {
                case "Add new book": {
                    JPanel myPanel = new JPanel();
                    JTextField title = new JTextField(15);
                    JTextField author = new JTextField(15);
                    JTextField quantity = new JTextField(15);
                    JTextField genre = new JTextField(15);
                    JTextField price = new JTextField(15);

                    myPanel.add(new JLabel("Title:"));
                    myPanel.add(title);
                    myPanel.add(Box.createHorizontalStrut(15)); // a spacer
                    myPanel.add(new JLabel("Author:"));
                    myPanel.add(author);
                    myPanel.add(Box.createHorizontalStrut(15)); // a spacer
                    myPanel.add(new JLabel("Quantity:"));
                    myPanel.add(quantity);
                    myPanel.add(Box.createHorizontalStrut(15)); // a spacer
                    myPanel.add(new JLabel("Genre:"));
                    myPanel.add(genre);
                    myPanel.add(Box.createHorizontalStrut(15)); // a spacer
                    myPanel.add(new JLabel("Price:"));
                    myPanel.add(price);
                    int result = JOptionPane.showConfirmDialog(null, myPanel,
                            "", JOptionPane.OK_CANCEL_OPTION);
                    if (result == JOptionPane.OK_OPTION) {
                        registeredAdmin.addBook(title.getText(),author.getText(),genre.getText(),quantity.getText(),price.getText());
                    }
                }
                break;
                case "View book information": {
                    JPanel myPanel = new JPanel();
                    JTextField id = new JTextField(15);
                    myPanel.add(new JLabel("Id:"));
                    myPanel.add(id);
                    int result = JOptionPane.showConfirmDialog(null, myPanel,
                            "", JOptionPane.OK_CANCEL_OPTION);
                    if (result == JOptionPane.OK_OPTION) {
                        registeredAdmin.viewBook(id.getText());
                    }
                    }
                break;
                case "View list of books": {
                    registeredAdmin.viewBooks();
                }
                break;
                case "Update book": {
                    JPanel myPanel = new JPanel();
                    JTextField id = new JTextField(15);
                    myPanel.add(new JLabel("Id:"));
                    myPanel.add(id);
                    int result = JOptionPane.showConfirmDialog(null, myPanel,
                            "", JOptionPane.OK_CANCEL_OPTION);
                    if (result == JOptionPane.OK_OPTION) {
                        JPanel myNewPanel = new JPanel();
                        JTextField quantity = new JTextField(15);
                        myNewPanel.add(new JLabel("New quantity:"));
                        myNewPanel.add(quantity);
                        myNewPanel.add(Box.createHorizontalStrut(15)); // a spacer
                        JTextField price = new JTextField(15);
                        myNewPanel.add(new JLabel("New price:"));
                        myNewPanel.add(price);
                        int res = JOptionPane.showConfirmDialog(null, myNewPanel,
                                "", JOptionPane.OK_CANCEL_OPTION);
                        if(res == JOptionPane.OK_OPTION && !quantity.getText().equals("") && !price.getText().equals("")){
                            registeredAdmin.updateBook(id.getText(),quantity.getText(),price.getText());
                        }
                    }
                }
                break;
                case "Remove book": {
                    JPanel myPanel = new JPanel();
                    JTextField id = new JTextField(15);
                    myPanel.add(new JLabel("Id:"));
                    myPanel.add(id);
                    int result = JOptionPane.showConfirmDialog(null, myPanel,
                            "", JOptionPane.OK_CANCEL_OPTION);
                    if (result == JOptionPane.OK_OPTION) {
                        registeredAdmin.deleteBook(id.getText());
                    }
                }
                break;
                case "Add new employee": {
                    JPanel myPanel = new JPanel();
                    JTextField name = new JTextField(15);
                    JTextField username = new JTextField(15);
                    JTextField password = new JPasswordField(15);

                    myPanel.add(new JLabel("Name:"));
                    myPanel.add(name);
                    myPanel.add(Box.createHorizontalStrut(15)); // a spacer
                    myPanel.add(new JLabel("Username:"));
                    myPanel.add(username);
                    myPanel.add(Box.createHorizontalStrut(15)); // a spacer
                    myPanel.add(new JLabel("Password:"));
                    myPanel.add(password);

                    int result = JOptionPane.showConfirmDialog(null, myPanel,
                            "", JOptionPane.OK_CANCEL_OPTION);
                    if (result == JOptionPane.OK_OPTION) {
                        registeredAdmin.addEmployee(name.getText(),username.getText(),password.getText());
                    }
                }
                break;
                case "View employee information": {
                    JPanel myPanel = new JPanel();
                    JTextField id = new JTextField(15);
                    myPanel.add(new JLabel("Id:"));
                    myPanel.add(id);
                    int result = JOptionPane.showConfirmDialog(null, myPanel,
                            "", JOptionPane.OK_CANCEL_OPTION);
                    if (result == JOptionPane.OK_OPTION) {
                        registeredAdmin.viewEmployee(id.getText());
                    }
                }
                break;
                case "View list of employees": {
                    registeredAdmin.viewEmployees();
                }
                break;
                case "Update employee": {
                    JPanel myPanel = new JPanel();
                    JTextField id = new JTextField(15);
                    myPanel.add(new JLabel("Id:"));
                    myPanel.add(id);
                    int result = JOptionPane.showConfirmDialog(null, myPanel,
                            "", JOptionPane.OK_CANCEL_OPTION);
                    if (result == JOptionPane.OK_OPTION) {
                        JPanel myNewPanel = new JPanel();
                        JTextField username = new JTextField(15);
                        myNewPanel.add(new JLabel("New username:"));
                        myNewPanel.add(username);
                        myNewPanel.add(Box.createHorizontalStrut(15)); // a spacer
                        JTextField password = new JPasswordField(15);
                        myNewPanel.add(new JLabel("New password:"));
                        myNewPanel.add(password);
                        int res = JOptionPane.showConfirmDialog(null, myNewPanel,
                                "", JOptionPane.OK_CANCEL_OPTION);
                        if(res == JOptionPane.OK_OPTION && !username.getText().equals("") && !password.getText().equals("")){
                            registeredAdmin.updateEmployee(id.getText(),username.getText(),password.getText());
                        }
                    }
                }
                break;
                case "Remove employee": {
                    JPanel myPanel = new JPanel();
                    JTextField id = new JTextField(15);
                    myPanel.add(new JLabel("Id: "));
                    myPanel.add(id);
                    int result = JOptionPane.showConfirmDialog(null, myPanel,
                            "", JOptionPane.OK_CANCEL_OPTION);
                    if (result == JOptionPane.OK_OPTION) {
                        registeredAdmin.deleteEmployee(id.getText());
                    }
                }
                break;
                case "Generate report in PDF format": {
                    registeredAdmin.generateReport("PDF","PDF Report of books out of stock");
                }
                break;
                case "Generate report in CSV format": {
                    registeredAdmin.generateReport("CSV","PDF Report of books out of stock");
                }
            }
        }
    }

    public String getSelectedButtonText(ButtonGroup buttonGroup) {
        for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();

            if (button.isSelected()) {
                return button.getText();
            }
        }

        return null;
    }

}
