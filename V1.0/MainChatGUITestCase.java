import static org.junit.Assert.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * 
 */

/**
 * @author HadiGhantous
 *
 */

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import javax.swing.*;

import org.junit.*;

public class MainChatGUITestCase {

    static	MainChatGUITestCase     chatGUI;
    static JFrame      chatFrame    = new JFrame("FleetDestroyer Chat");
    static JTextArea   chatBox;
    static JTextField  messageField;
    static String username = "Hadi Tester";
    
    @Test
    public void main() {
    	MainChatGUITestCase test = new MainChatGUITestCase();
              test.display();
    }

    public void display() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        JPanel chatPanel = new JPanel();
        chatPanel.setBackground(Color.BLUE);
        chatPanel.setLayout(new GridBagLayout());

        messageField = new JTextField(25);
        messageField.requestFocusInWindow();
        messageField.addActionListener(new sendMessageListener());//hit enter to send text

        chatBox = new JTextArea();
        chatBox.setEditable(false);
        chatBox.setFont(new Font("Serif", Font.PLAIN, 15));
        chatBox.setLineWrap(true);
        
        mainPanel.add(new JScrollPane(chatBox), BorderLayout.CENTER);
        chatPanel.add(messageField);

        mainPanel.add(BorderLayout.SOUTH, chatPanel);

        chatFrame.add(mainPanel);
        chatFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        chatFrame.setSize(470, 300);
        chatFrame.setLocationRelativeTo(null);
        chatFrame.setVisible(true);
	    JOptionPane.showMessageDialog(chatFrame.getContentPane(),"Send Message: '.clear' To Erase Chat! ","SERVICE", 2);

    }

    class sendMessageListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            if (messageField.getText().length() < 1) {
            	
            } else if (messageField.getText().equals(".clear")) {
                chatBox.setText("CHAT CLEARED\n");
                messageField.setText("");
                
            } else {
            	String message = "<" + username + ">:  " + messageField.getText()+ "\n";
                chatBox.append(message);
            	messageField.setText("");
                
            }
            messageField.requestFocusInWindow();
        }
    }
}
