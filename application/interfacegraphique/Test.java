package application.interfacegraphique;

import java.io. *;
import application.controleur.CsvReader;
import javax.swing.*;
import java.awt.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;

public class Test {
    public static void main(String[] args) {
        JFrame framebis = new JFrame("StayShark");
        BaseFenetre.defautfenetre(framebis,400,150);

        JLabel userLabel = new JLabel();  
        userLabel.setText("Username");
        JTextField textField1 = new JTextField(15);
        JButton b1 = new JButton("Se connecter"); 
        JPanel newPanel = new JPanel(new GridLayout(2, 1));  
        newPanel.add(userLabel);    //set username label to panel  
        newPanel.add(textField1);   //set text field to panel  
        newPanel.add(b1); 
        framebis.add(newPanel, BorderLayout.CENTER); 
        b1.addActionListener(new NewAction(JTextField textField1));     //add action listener to button  
        framebis.setTitle("LOGIN FORM");  

        framebis.pack();
        framebis.setLocationRelativeTo(null);
        framebis.setVisible(true);
        // System.out.print(CsvReader.liseurCsv("./application/data/dataeleve.csv"));
    }
}

class NewAction extends AbstractAction {

    public NewAction(JTextField textField1) throws FileNotFoundException {
        String userValue = textField1.getText();
        Map<String,String> bloub = new HashMap<String,String>(CsvReader.liseurCsv("./application/data/dataeleve.csv"));
    }
 
    @Override
    public void actionPerformed(ActionEvent e) {
       if(bloub.containsKey(userValue)){
        System.out.print("kshfkjsd");
       }
    }
 }