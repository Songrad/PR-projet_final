package View;

import Model.Game;

import javax.swing.*;

public class FrameHome extends JFrame
{
    public FrameHome(Game model)
    {
        this.setLocation(100,100);
        this.setSize(600,400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        MenuPanel panel = new MenuPanel(model);

        this.add(panel);
        this.setVisible(true);
    }
}
