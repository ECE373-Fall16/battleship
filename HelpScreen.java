import javax.swing.*;
import java.awt.*;
  
public class HelpScreen {
   public static void main(String args[]) {
      JFrame frame = new JFrame("JTextField Background Demonstration");
      final ImageIcon imageIcon = new ImageIcon("C:\\Users\\Sai Yarram\\Documents\\GitHub\\battleship\\Help.JPG");
	  
	  String Help = "HHIIII\n HII";
      JTextArea textField = new JTextArea(Help) {
         Image image = imageIcon.getImage();
         {
            setOpaque(false);
         }
         public void paintComponent (Graphics g) {
            g.drawImage(image, 0, 0, this);
            setForeground(Color.yellow);
            super.paintComponent(g);
         }
      };
      textField.setFont(new Font("Helvetica", Font.BOLD, 16));
  
      frame.getContentPane().add(BorderLayout.CENTER, textField);
      frame.setDefaultCloseOperation(3);
      frame.setSize(600, 600);
      frame.setVisible(true);
      frame.setResizable(true);
   }
}