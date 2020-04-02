package ggf.gui.game;

import ggf.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelInfo extends JPanel implements ActionListener
{

    private PanelText panelText;
    private PanelScore panelScore;
    private JButton quit;
    private Controller ctrl;

    public PanelInfo(Controller ctrl)
    {
        this.ctrl = ctrl;

        this.setLayout(new BorderLayout());

        this.panelText = new PanelText(ctrl);

        this.quit = new JButton("Quitter la partie");

        this.panelScore = new PanelScore(ctrl);

        this.add(this.panelScore,BorderLayout.NORTH);
        this.add(this.panelText);
        this.add(this.quit,BorderLayout.SOUTH);


    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == this.quit)
        {
            //TODO:Quitter
        }
    }

    public void majInfo()
    {
        this.panelScore.majScore();
    }
}
