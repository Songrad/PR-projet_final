package IHM;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class MenuPanel extends JFrame {

    public MenuPanel() throws IOException
    {
        this.setLocation(10,10);
        this.setSize(800,600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel lstButton = new JPanel();


        // Header
        JPanel head  = new JPanel(new BorderLayout(16,16));

        BufferedImage logo = ImageIO.read(getClass().getResourceAsStream("/IHM/img/logo.png"));
        JLabel title = new JLabel(new ImageIcon(logo));

        JPanel infoPanel = new JPanel(new GridLayout(1, 2));
        infoPanel.add(new JLabel("Serveur: 127.0.0.1:1337"));
        infoPanel.add(new JLabel("Non connecté"));

        for (Component c : infoPanel.getComponents())
            if (c instanceof JLabel)
                ((JLabel)c).setHorizontalAlignment(JLabel.CENTER);

        // BODY
        JPanel body = new JPanel(new GridBagLayout());
        JPanel bodyButtons = new JPanel(new GridLayout(3,1, 8, 8));

        bodyButtons.setPreferredSize(new Dimension(200,200));

        bodyButtons.add(new JButton("Jouer"));
        bodyButtons.add(new JButton("Options"));
        bodyButtons.add(new JButton("Records"));

        body.add(bodyButtons);

        head.add(title, BorderLayout.NORTH);
        head.add(infoPanel);

        this.add(head, BorderLayout.NORTH);
        this.add(body);
        this.setVisible(true);
    }

    public static void main(String[] args) throws IOException
    {
        new MenuPanel();
    }
}
