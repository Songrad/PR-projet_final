package IHM;

import javax.swing.*;
import java.awt.*;

public class MenuPanel extends JFrame {

    public MenuPanel()
    {
        JPanel head  = new JPanel();
        JPanel lstButton = new JPanel();

        head.setLayout(new BorderLayout());
        lstButton.setLayout(new GridLayout(3,1));

        JLabel title = new JLabel("Gotta go fat");
        JLabel info  = new JLabel("Serveur...");

        head.add(title,BorderLayout.CENTER);
        head.add(info,BorderLayout.SOUTH);

        JButton[] boutons = new JButton[3];

        boutons[0] = new JButton("Jouer");
        boutons[1] = new JButton("Options");
        boutons[2] = new JButton("Meilleurs scores");

        for(int i=0; i<boutons.length;i++){
            lstButton.add(boutons[i]);
        }

        this.add(lstButton);
        this.add(head,BorderLayout.NORTH);


        this.setVisible(true);
    }

    public static void main(String[] args)
    {
        new MenuPanel();
    }
}
