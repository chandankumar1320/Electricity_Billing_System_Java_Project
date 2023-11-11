package electricity.billing.system;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;

public class bill_Details extends JFrame {
    String meter;
    bill_Details(String meter){
        this.meter =meter;
        setSize(700,500);
        setLocation(400,150);
        setLayout(null);
        getContentPane().setBackground(Color.white);

        JTable table = new JTable();

        try{
            Database c = new Database();
            String query_bill = "select * from bill where meter_no = '"+meter+"'";
            ResultSet resultSet = c.statement.executeQuery(query_bill);
            table.setModel(DbUtils.resultSetToTableModel(resultSet));
        }catch (Exception e){
            e.printStackTrace();
        }
        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(0,0,700,650);
        add(sp);

        setVisible(true);
    }
    public static void main(String[] args) {
        new bill_Details("");
    }
}