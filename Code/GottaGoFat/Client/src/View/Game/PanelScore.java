package View.Game;

import Controler.GameStart;
import Model.Player;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class PanelScore extends JPanel
{

    private GameStart ctrl;
    private JLabel[] bestPlayer;

    public PanelScore(GameStart controleur)
    {
        this.ctrl = controleur;

        this.setLayout(new GridLayout(6,1));

        JLabel score   = new JLabel("temps : " + 148);
        JLabel objRest = new JLabel("Object restant : " + 15);

        this.add(score);
        this.add(objRest);

        ArrayList<Player> lstPlayer = this.ctrl.getListPlayer();
        Collections.sort(lstPlayer);

        this.bestPlayer = new JLabel[3];

        for(int i = 0; i < lstPlayer.size(); i++)
        {
            Player p = lstPlayer.get(i);
            this.bestPlayer[i] = new JLabel(p.getName() + " : " + p.getScore());
            this.add(bestPlayer[i]);
        }

    }


    public void majScore()
    {


        ArrayList<Player> lstPlayer = this.ctrl.getListPlayer();
        Collections.sort(lstPlayer, Collections.reverseOrder());


        for(int i = 0; i < lstPlayer.size(); i++)
        {
            Player p = lstPlayer.get(i);
            this.bestPlayer[i].setText(p.getName() + " : " + p.getScore());
        }
    }
}
