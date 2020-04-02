package View.Game;

import Controler.GameStart;
import Model.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class PanelScore extends JPanel
{

    private GameStart ctrl;
    private JLabel[] bestPlayer;
    private JLabel timerLabel;
    private JLabel objRest;
    private Timer timer;
    private int TIME = 10;

    public PanelScore(GameStart controleur)
    {
        this.ctrl = controleur;

        this.setLayout(new GridLayout(10,1,10,10));

        this.timerLabel   = new JLabel("Waiting...");
        this.objRest = new JLabel("Object restant : " + controleur.getListObject().size());

        this.add(timerLabel);
        this.add(objRest);
        this.add(Box.createVerticalStrut(10));

        ArrayList<Player> lstPlayer = this.ctrl.getListPlayer();
        Collections.sort(lstPlayer);

        this.bestPlayer = new JLabel[3];

        for(int i = 0; i < lstPlayer.size(); i++)
        {
            int tmp = i + 1;
            Player p = lstPlayer.get(i);

            this.bestPlayer[i] = new JLabel("#" + tmp + " - " +p.getName() + " : " + p.getScore());
            this.add(bestPlayer[i]);
        }


        this.timerLabel.setText("Temps : " + TIME );

        timer = new Timer(1000, new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                TIME--;

                if(TIME >= 1){
                    timerLabel.setText("Temps : " + TIME);
                }
                else{
                    timer.stop();
                    timerLabel.setText("Terminé !");
                    Toolkit.getDefaultToolkit().beep();
                }

            }
        });

        timer.start();

    }


    public void majScore()
    {

        ArrayList<Player> lstPlayer = this.ctrl.getListPlayer();
        Collections.sort(lstPlayer, Collections.reverseOrder());


        for(int i = 0; i < lstPlayer.size(); i++)
        {
            int tmp = i + 1;

            Player p = lstPlayer.get(i);
            this.bestPlayer[i].setText("#" + tmp + " - " + p.getName() + " : " + p.getScore());
        }

        this.objRest.setText("Object restant : " + this.ctrl.getListObject().size());
    }

}
