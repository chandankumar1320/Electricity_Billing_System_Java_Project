package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MeterInfo extends JFrame implements ActionListener {
    Choice meterLocationChoice,meterTypeChoice, phaseCodeChoice,billTypeChoice;
    JButton submit;
    String meterNum;

    MeterInfo(String meterNum){
        this.meterNum=meterNum;
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(126, 137, 162, 255));
        add(panel);

        JLabel heading = new JLabel(" Meter Information");
        heading.setBounds(180, 10, 200, 20);
        heading.setFont(new Font("Tahome", Font.BOLD,20));
        panel.add(heading);

        JLabel meterNumber = new JLabel(" Meter Number");
        meterNumber.setBounds(50,80,100,20);
        panel.add(meterNumber);

        JLabel meterNumberText = new JLabel(meterNum);
        meterNumberText.setBounds(180,80,150,20);
        panel.add(meterNumberText);

        JLabel meterLocation = new JLabel(" Meter Location");
        meterLocation.setBounds(50,120,100,20);
        panel.add(meterLocation);

        meterLocationChoice = new Choice();
        meterLocationChoice.add("Outside");
        meterLocationChoice.add("Inside");
        meterLocationChoice.setBounds(180,120,150,20);
        panel.add(meterLocationChoice);

        JLabel meterType = new JLabel("Meter Type");
        meterType.setBounds(50,160,100,20);
        panel.add(meterType);

        meterTypeChoice = new Choice();
        meterTypeChoice.add("Electric Meter");
        meterTypeChoice.add("Solar Meter");
        meterTypeChoice.add("Smart Meter");
        meterTypeChoice.setBounds(180,160,150,20);
        panel.add(meterTypeChoice);

        JLabel phaseCode = new JLabel("Phase code");
        phaseCode.setBounds(50,200,100,20);
        panel.add(phaseCode);

        phaseCodeChoice = new Choice();
        phaseCodeChoice.add("011");
        phaseCodeChoice.add("022");
        phaseCodeChoice.add("033");
        phaseCodeChoice.add("044");
        phaseCodeChoice.add("055");
        phaseCodeChoice.add("066");
        phaseCodeChoice.add("077");
        phaseCodeChoice.add("088");
        phaseCodeChoice.add("099");
        phaseCodeChoice.setBounds(180,200,150,20);
        panel.add(phaseCodeChoice);

        JLabel billType = new JLabel("Bill Type");
        billType.setBounds(50,240 ,100,20);
        panel.add(billType);

        billTypeChoice = new Choice();
        billTypeChoice.add("Residential");
        billTypeChoice.add("Commercial");
        billTypeChoice.setBounds(180,240,150,20);
        panel.add(billTypeChoice);

        JLabel days = new JLabel("30 Days Billing Time/Monthly");
        days.setBounds(50,280,200,20);
        panel.add(days);

        JLabel note = new JLabel("Note: ");
        note.setBounds(50,320,150,20);
        panel.add(note);

        JLabel billNote = new JLabel("By default bill is calculated for 30 days only");
        billNote.setBounds(50,340,250,20);
        panel.add(billNote);
        submit = new JButton("Submit");
        submit.setBounds(180,390,100,20);
        submit.setBackground(Color.black);
        submit.setForeground(Color.white);
        submit.addActionListener(this);
        panel.add(submit);

        setLayout(new BorderLayout());
        add(panel,"Center");

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/details.png"));
        Image i2 = i1.getImage().getScaledInstance(230,200,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel imagLabel = new JLabel(i3);
        add(imagLabel,"East");

        setSize(700,500);
        setLocation(400,200);
        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==submit){
            String smeterNum = meterNum;
            String smeterLoc = meterLocationChoice.getSelectedItem();
            String smetertype = meterTypeChoice.getSelectedItem();
            String sbillType = billTypeChoice.getSelectedItem();
            String sphaseCode = phaseCodeChoice.getSelectedItem();
            String sday = "30";

            String quary_meterInfo = "insert into meter_Info values('"+smeterNum+"','"+smeterLoc+"','"+smetertype+"','"+sphaseCode+"','"+sbillType+"','"+sday+"')";
            try{
                Database c = new Database();
                c.statement.executeUpdate(quary_meterInfo);

                JOptionPane.showMessageDialog(null,"Meter submitted successfully");
                setVisible(false);

            }catch (Exception E){
                E.printStackTrace();

            }
        }else{
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new MeterInfo("");

    }
}
