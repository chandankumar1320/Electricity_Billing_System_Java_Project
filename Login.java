package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Login extends JFrame implements ActionListener {
    JTextField userText,passwordText;
    Choice loginChoice;

    JButton loginButton,cancelButton,signupButton;

    Login(){
        super("Login");
        getContentPane().setBackground(Color.gray);
        JLabel username = new JLabel("User Name");
        username.setBounds(300,60,100,20);
        add(username);

        userText = new JTextField();
        userText.setBounds(400,60,150,20);
        add(userText);

        JLabel password = new JLabel("Password");
        password.setBounds(300,100,100,20);
        add(password);

        passwordText = new JTextField();
        passwordText.setBounds(400,100,150,20);
        add(passwordText);

        JLabel login = new JLabel("Login As");
        login.setBounds(300,140,100,20);
        add(login);

        loginChoice = new Choice();
        loginChoice.add("Admin");
        loginChoice.add("Customer");
        loginChoice.setBounds(400,140,150,20);
        add(loginChoice);

        loginButton = new JButton("Login");
        loginButton.setBounds(330,180,100,20);
        loginButton.addActionListener(this);
        add(loginButton);

        cancelButton = new JButton("Cancel");
        cancelButton.setBounds(460,180,100,20);
        cancelButton.addActionListener(this);
        add(cancelButton);

        signupButton = new JButton("Signup");
        signupButton.setBounds(400,215,100,20);
        signupButton.addActionListener(this);
        add(signupButton);

        ImageIcon profileOne =  new ImageIcon(ClassLoader.getSystemResource("icon/profile.jpg"));
        Image profileTow = profileOne.getImage().getScaledInstance(250,250,Image.SCALE_DEFAULT);
        ImageIcon fprofileOne = new ImageIcon(profileTow);
        JLabel profileLabel = new JLabel(fprofileOne);
        profileLabel.setBounds(10,3,250,250);
        add(profileLabel);



        setSize(640,300);
        setLocation(400,200);
        setLayout(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==loginButton){
            String susername = userText.getText();
            String spassword = passwordText.getText();
            String suser = loginChoice.getSelectedItem();

            try{
                Database c = new Database();
                String query = "select * from Sign_Up where username = '"+susername+"' and password = '"+spassword+"' and usertype ='"+suser+"'";
                ResultSet resultSet = c.statement.executeQuery(query);

                if (resultSet.next()){
                    String meter = resultSet.getString("meter_number");
                    setVisible(false);
                    new main_Class(suser,meter);
                }else {
                    JOptionPane.showMessageDialog(null ,"Invalid Login Credential");
                }

            }catch (Exception E){
                E.printStackTrace();
            }
        } else if (e.getSource()==cancelButton) {
            setVisible(false);
        } else if (e.getSource()==signupButton) {
            setVisible(false);
            new SignUp();
        }

    }

    public static void main(String... args) {
        new Login();
    }
}