import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class TestBackgroundImage {

    private static final String BACKHGROUND_IMAGE_URL = "http://cache2.allpostersimages.com/p/LRG/27/2740/AEPND00Z/affiches/blue-fiber-optic-wires-against-black-background.jpg";

    protected void initUI() throws MalformedURLException {
        JFrame frame = new JFrame(TestBackgroundImage.class.getSimpleName());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        final ImageIcon backgroundImage = new ImageIcon(new URL(BACKHGROUND_IMAGE_URL));
        JPanel mainPanel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
            }

        @Override
        public Dimension getPreferredSize() {
            Dimension size = super.getPreferredSize();
            size.width = Math.max(backgroundImage.getIconWidth(), size.width);
            size.height = Math.max(backgroundImage.getIconHeight(), size.height);
            return size;
        }

        };
        mainPanel.add(new JButton("A button"), BorderLayout.WEST);
        frame.add(mainPanel);
        frame.setSize(400, 300);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                try {
                    new TestBackgroundImage().initUI();
                } catch (MalformedURLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });
    }

}