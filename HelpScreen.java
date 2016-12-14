import javax.swing.*;
import java.awt.*;
  
public class HelpScreen {
   public static void main(String args[]) {
      JFrame frame = new JFrame("JTextField Background Demonstration");
      final ImageIcon imageIcon = new ImageIcon("C:\\Users\\Sai Yarram\\Documents\\GitHub\\battleship\\Help.JPG");
	  
	  String Help = "        	                   Welcome to Fleet Ship!\n\n- Here " +
	  "you can find the answers to those general questions you as well\nas read instrucstions"+
	  "on how to play the game!\n- To go back to the main screen just hit the X in the right corner.\n\n\n"+
	  "        	                   Game Instructions\n\n- To start a game hit the"+
	  "'Start Game'.\n- Once you have done that, a game should be set up with whoever else is\n"+
	  "online!\n- Once a game has started you will be prompted to pick your ships!\n-Once everyone"+
	  "has picked their ships then the game beings.\n- While the game is running you will be asked "+
	  "to type your coordinates into\n the text box below the fire button.\n- After this with your coordinates"+
	  "in the box click on the 'hit' button.\n- The grid on the screen will let you know if it was"+
	  "a hit or a miss\n\n- Now its your opponents turn!\n- They will go through the same processes until it is your turn again!\n"+
	  "\n\n Have fun!";
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
      frame.setResizable(false);
	  frame.setLocationRelativeTo(null);
   }
}