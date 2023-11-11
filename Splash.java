package electricity.billing.system;

import javax.swing.*;
import java.awt.*;

public class Splash extends JFrame {
    Splash(){
        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource( "icon/Splash.jpg"));
        Image imageOne = imageIcon.getImage().getScaledInstance(680,382,Image.SCALE_DEFAULT);
        ImageIcon imageIcon2 = new ImageIcon(imageOne);
        JLabel imageLabel = new JLabel(imageIcon2);
        add(imageLabel);
        setSize(680,382);
        setLocation(400,250);
        setVisible(true);
        try{
            Thread.sleep(1000);
            setVisible(false);

            new Login();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        new Splash();
   }
}