package View.Menu;

import Controler.GameStart;
import Model.Game;
import View.Game.FrameGame;

import javax.swing.*;
import java.awt.*;

public class FrameHome extends JFrame
{
    private JPanel bottomGridBagPanel = null;
    private JPanel bottomPanel = null;
    private MenuPanel menuPanel;
    private GameStart ctrl;
    private FrameGame frameGame;

    public FrameHome(GameStart controleur)
    {
        this.ctrl = controleur;

        this.setLocation(100,100);

        this.setSize(740, 190);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBackground(Color.WHITE);
        this.setResizable(false);
        this.bottomGridBagPanel = new JPanel(new GridBagLayout());

        this.menuPanel = new MenuPanel(this.ctrl);

        this.add(this.menuPanel, BorderLayout.NORTH);
        this.add(bottomGridBagPanel);
        this.setVisible(true);
    }

    public void setPanel(JPanel panel)
    {
        if (panel == null)
        {
            this.setSize(740, 190);
            if (bottomPanel != null)
                bottomGridBagPanel.remove(bottomPanel);
        }
        else
        {
            this.setSize(740, 400);
            bottomGridBagPanel.add(panel);
        }

        this.bottomPanel = panel;
    }

    public void majIHM()
    {
        this.menuPanel.majIHM();
    }
}
