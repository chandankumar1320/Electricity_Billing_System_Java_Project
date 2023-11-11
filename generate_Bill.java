package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class generate_Bill extends JFrame implements ActionListener {
    Choice searchMonthchoice;
    String meter;
    JTextArea area;
    JButton bill;
    generate_Bill(String meter){
        this.meter=meter;
        setSize(500,700);
        setLocation(500,30);
        setLayout( new BorderLayout());
        JPanel panel = new JPanel();

        JLabel heading = new JLabel("Generate Bill");

        JLabel meter_no = new JLabel(meter);

        searchMonthchoice = new Choice();
        searchMonthchoice.add("January");
        searchMonthchoice.add("February");
        searchMonthchoice.add("March");
        searchMonthchoice.add("April");
        searchMonthchoice.add("May");
        searchMonthchoice.add("June");
        searchMonthchoice.add("July");
        searchMonthchoice.add("August");
        searchMonthchoice.add("September");
        searchMonthchoice.add("October");
        searchMonthchoice.add("November");
        searchMonthchoice.add("December");

        area= new JTextArea(50,15);
        area.setText("\n \n \t ------------------- Click on the ---------------\n \t ------------------- Generate Bill");
        area.setFont(new Font("Senserif",Font.ITALIC,15));
        JScrollPane pane = new JScrollPane(area);
        bill = new JButton("Generate Bill");
        bill.addActionListener(this);

        add(pane);

        panel.add(heading);
        panel.add(meter_no);
        panel.add(searchMonthchoice);
        add(panel,"North");
        add(bill,"South");

        setVisible(true);


    }

    @Override
    public void actionPerformed(ActionEvent e) {

        try{
            Database c = new Database();
            String smonth =  searchMonthchoice.getSelectedItem();
            area.setText("\n Power Limited \n Electricity Bill For Month of "+smonth+",2023\n\n\n");
            ResultSet resultSet = c.statement.executeQuery("select * from new_customer where meter_No ='"+meter+"'");
            if (resultSet.next()){
                area.append("\n    Customer Name                        : "+resultSet.getString("name"));
                area.append("\n    Customer Meter Number          : "+resultSet.getString("meter_No"));
                area.append("\n    Customer Address                   : "+resultSet.getString("address"));
                area.append("\n    Customer City                         : "+resultSet.getString("city"));
                area.append("\n    Customer State                       : "+resultSet.getString("state"));
                area.append("\n    Customer Email                       : "+resultSet.getString("email"));
                area.append("\n    Customer Phone Number         : "+resultSet.getString("phone_No"));

            }

            resultSet = c.statement.executeQuery("select * from meter_Info where meter_number ='"+meter+"'");
            if (resultSet.next()){
                area.append("\n    Customer Meter Location        : "+resultSet.getString("meter_location"));
                area.append("\n    Customer Meter Type              : "+resultSet.getString("meter_Type"));
                area.append("\n    Customer Phase Code              : "+resultSet.getString("phase_code"));
                area.append("\n    Customer Bill Type                  : "+resultSet.getString("bill_Type"));
                area.append("\n    Customer Days                       : "+resultSet.getString("days"));


            }
            resultSet = c.statement.executeQuery("select * from tax");
            if (resultSet.next()){
                area.append("\n   Cost Per Unit                          : "+resultSet.getString("cost_per_unit"));
                area.append("\n   Meter Rent                               : "+resultSet.getString("meter_rent"));
                area.append("\n   Service Charge                      : "+resultSet.getString("service_charge"));
                area.append("\n   Service Tax                            : "+resultSet.getString("service_tax"));
                area.append("\n   Swacch Bharat                       : "+resultSet.getString("swacch_bharat"));
                area.append("\n   Fixed Tax                               : "+resultSet.getString("fixed_tax"));

            }
            resultSet = c.statement.executeQuery("select * from bill where meter_no = '"+meter+"' and month = '"+searchMonthchoice.getSelectedItem()+"'");
            if (resultSet.next()) {
                area.append("\n   Current Month                         : "+resultSet.getString("month"));
                area.append("\n   Units Consumed                      : "+resultSet.getString("unit"));
                area.append("\n   Total Charges                          : "+resultSet.getString("total_bill"));
                area.append("\n   Total Payable                            : "+resultSet.getString("total_bill"));
            }


        }catch (Exception E ){
            E.printStackTrace();
        }

    }

    public static void main(String[] args) {
        new generate_Bill("");
    }
}