package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class newCustomer extends JFrame implements ActionListener {
    JLabel heading, customerName, meterNumber,meterNumText,address, city,state,email,phone;
    TextField nameText,addressText, cityText,stateText,emailText,phoneText;
    JButton next, cancel;

    newCustomer(){
        super("New Customer");
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(146, 150, 20));
        add(panel);
        heading = new JLabel("New Customer");
        heading.setBounds(180, 10,200, 20);
        heading.setFont(new Font("Tahoma",Font.BOLD, 20));
        panel.add(heading);

        customerName = new JLabel("New Customer");
        customerName.setBounds(50, 80,100, 20);
        panel.add(customerName);

        nameText = new TextField();
        nameText.setBounds(180, 80, 150, 20);
        panel.add(nameText);

        meterNumber = new JLabel("Meter Number");
        meterNumber.setBounds(50, 120,100, 20);
        panel.add(meterNumber);

        meterNumText = new JLabel();
        meterNumText.setBounds(180, 120, 150, 20);
        panel.add(meterNumText);


        Random ranDom = new Random();
        long number = ranDom.nextLong() % 100000000;
        meterNumText.setText(""+ Math.abs(number));


        address = new JLabel("Address");
        address.setBounds(50, 160,100, 20);
        panel.add(address);

        addressText = new TextField();
        addressText.setBounds(180, 160, 150, 20);
        panel.add(addressText);

        city = new JLabel("City");
        city.setBounds(50, 200,100, 20);
        panel.add(city);

        cityText = new TextField();
        cityText.setBounds(180, 200, 150, 20);
        panel.add(cityText);

        state = new JLabel("State");
        state.setBounds(50, 240,100, 20);
        panel.add(state);

        stateText = new TextField();
        stateText.setBounds(180, 240, 150, 20);
        panel.add(stateText);

        email = new JLabel("Email");
        email.setBounds(50, 280,100, 20);
        panel.add(email);

        emailText = new TextField();
        emailText.setBounds(180, 280, 150, 20);
        panel.add(emailText);

        phone = new JLabel("Phone");
        phone.setBounds(50, 320,100, 20);
        panel.add(phone);

        phoneText = new TextField();
        phoneText.setBounds(180, 320, 150, 20);
        panel.add(phoneText);

        next = new JButton("Next");
        next.setBounds(120,390,100,20);
        next.setBackground(Color.BLACK);
        next.setForeground(Color.white);
        next.addActionListener(this);
        panel.add(next);

        cancel = new JButton("Cancel");
        cancel.setBounds(230,390,100,20);
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.white);
        cancel.addActionListener(this);
        panel.add(cancel);


        setLayout(new BorderLayout());
        add(panel,"Center");

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/newcustomer.png"));
        Image i2 = i1.getImage().getScaledInstance(230,200,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel imagLable = new JLabel(i3);
        add(imagLable,"West");

        setSize(700, 500);
        setLocation(400,150);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==next){
            String sname = nameText.getText();
            String smeter = meterNumText.getText();
            String saddress = addressText.getText();
            String scity = cityText.getText();
            String sstate = stateText.getText();
            String eemail = emailText.getText();
            String sphone = phoneText.getText();

            String query_customer = "insert into new_customer values('"+sname+"','"+smeter+"','"+saddress+"','"+scity+"','"+sstate+"','"+eemail+"','"+sphone+"')";
            String query_signup= "insert into Sign_Up values('"+smeter+"','','"+sname+"','','')";

            try{
                Database c = new Database();
                c.statement.executeUpdate(query_customer);
                c.statement.executeUpdate(query_signup);

                JOptionPane.showMessageDialog(null,"Customer details updated successfully");
                setVisible(false);
                new MeterInfo(smeter);

            }catch(Exception E){
                E.printStackTrace();
            }
        }else {
            setVisible(false);
        }

    }

    public static void main(String [] args){
        new newCustomer();

    }
}
