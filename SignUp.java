package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;

public class SignUp extends JFrame implements ActionListener {
    Choice loginChoice;
    TextField meterText,EmployerText,userNameText,nameText,passwordText;
    JButton create,back;
    SignUp(){
        super("Sign Up Page");
        getContentPane().setBackground(new Color(30, 143, 48));

        JLabel createAs = new JLabel("Create Account As");
        createAs.setBounds(30,50,125,20);
        add(createAs);

        loginChoice = new Choice();
        loginChoice.add("Admin");
        loginChoice.add("Customer");
        loginChoice.setBounds(170,50,120,20);
        add(loginChoice);

        JLabel meterNo = new JLabel("Meter Number");
        meterNo.setBounds(30,100,125,20);
        meterNo.setVisible(false);
        add(meterNo);

        meterText = new TextField();
        meterText.setBounds(170,100,125,20);
        meterText.setVisible(false);
        add(meterText);

        JLabel Employer = new JLabel("Employer ID");
        Employer.setBounds(30,100,125,20);
        Employer.setVisible(true);
        add(Employer);

        EmployerText = new TextField();
        EmployerText.setBounds(170,100,125,20);
        EmployerText.setVisible(true);
        add(EmployerText);

        JLabel userName = new JLabel("UserName");
        userName.setBounds(30,140,125,20);
        add(userName);

        userNameText = new TextField();
        userNameText.setBounds(170,140,125,20);
        add(userNameText);


        JLabel name = new JLabel("Name");
        name.setBounds(30,180,125,20);
        add(name);

        nameText = new TextField("");
        nameText.setBounds(170,180,125,20);
        add(nameText);

        meterText.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {
                try{
                    Database c = new Database();
                    ResultSet resultSet = c.statement.executeQuery("select * from Sign_Up  where meter_number = '"+meterText.getText()+"'");
                    if (resultSet.next()){
                        nameText.setText(resultSet.getString("name"));
                    }

                }catch (Exception E){
                    E.printStackTrace();
                }
            }
        });

        JLabel password = new JLabel("Password");
        password.setBounds(30,220,125,20);
        add(password);

        passwordText = new TextField();
        passwordText.setBounds(170,220,125,20);
        add(passwordText);


        loginChoice.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                String user = loginChoice.getSelectedItem();
                if (user.equals("Customer")){
                    Employer.setVisible(false);
                    nameText.setEditable(false);
                    EmployerText.setVisible(false);
                    meterNo.setVisible(true);
                    meterText.setVisible(true);
                }else {
                    Employer.setVisible(true);
                    EmployerText.setVisible(true);
                    meterNo.setVisible(false);
                    meterText.setVisible(false);
                }

            }
        });

        create = new JButton("Create");
        create.setBackground(new Color(91, 4, 4));
        create.setForeground(Color.white);
        create.setBounds(50,285,100,25);
        create.addActionListener(this);
        add(create);

        back = new JButton("Back");
        back.setBackground(new Color(91, 4, 4));
        back.setForeground(Color.white);
        back.setBounds(180,285,100,25);
        back.addActionListener(this);
        add(back);

        ImageIcon boyIcon = new ImageIcon(ClassLoader.getSystemResource("icon/signup.png"));
        Image boyImg = boyIcon.getImage().getScaledInstance(250,250,Image.SCALE_DEFAULT);
        ImageIcon boyIcon2 = new ImageIcon(boyImg);
        JLabel boyLabel = new JLabel(boyIcon2);
        boyLabel.setBounds(320,30,250,250);
        add(boyLabel);


        setSize(600,380);
        setLocation(500,200);
        setLayout(null);
        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()== create){
            String sloginAs = loginChoice.getSelectedItem();
            String susername = userNameText.getText();
            String sname = nameText.getText();
            String spassword = passwordText.getText();
            String smeter = meterText.getText();
            try{
                Database c = new Database();
                String query= null;
                if (loginChoice.equals("Admin")) {
                    query = "insert into Sign_Up value('" + smeter + "', '" + susername + "', '" + sname + "','" + spassword + "','" + sloginAs + "')";
                }else {
                    query = "update Sign_Up set username = '"+susername+"', password = '"+spassword+"', usertype = '"+sloginAs+"' where meter_number = '"+smeter+"'";
                }
                c.statement.executeUpdate(query);

                JOptionPane.showMessageDialog(null,"Account Created");
                setVisible(false);
                new Login();

            }catch (Exception E){
                E.printStackTrace();
            }
        } else if (e.getSource()==back) {
            setVisible(false);
            new Login();
        }
    }

    public static void main(String[] args) {
        new SignUp();
    }
}