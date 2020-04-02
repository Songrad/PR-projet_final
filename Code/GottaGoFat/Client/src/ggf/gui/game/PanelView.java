package ggf.gui.game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import ggf.Controller;
import ggf.game.*;

class PanelView extends JPanel
{
    //private Game ggf.model;
    private Controller ctrl;
    private int idP;
    private int xi, yi, rp;

    private ArrowKeyManager akm;

    public PanelView(Controller controleur)
    {
        this.ctrl = controleur;

        this.setPreferredSize(new Dimension(600,600));

        this.idP = this.ctrl.getIdPlayer(); // TODO: Paramètre
        this.setOpaque(true);
        this.setFocusable(true);
        this.akm = new ArrowKeyManager();
        this.addKeyListener(akm);
        this.addMouseListener(new MouseHandler(this));

		new Timer(16, e -> {
			int keys = akm.getKeys();
            this.ctrl.sendInputs(keys);
		}).start();
    }

    public void paintComponent(Graphics g)
    {
        ((Graphics2D)g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g.setColor(Color.white);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());

        //for (GameObject go : this.ggf.model.getlistObject())
        for(GameObject go : this.ctrl.getVisibleObjects()){
            int r = go.getRadius();
            int x = go.getX() - r;
            int y = go.getY() - r;
            g.setColor(Color.green);

            g.fillOval(x, y, r*2, r*2);
        }


        ArrayList<Player> playerList = this.ctrl.getPlayerList();
        for (int i = 0; i < playerList.size(); i++)
        {
            if (this.ctrl.isInvisible() && i != idP) continue;

            Player p = playerList.get(i);
            g.setColor(p.getColor());
            rp = p.getRadius();
            int x = p.getX() - rp;
            int y = p.getY() - rp;
            g.fillOval(x, y, rp * 2, rp * 2);

            // Débordement gauche/haut
            if (x < 0) {
                g.fillOval(x + Game.MAP_SIZE, y, rp * 2, rp * 2);
            }

            if (y < 0) {
                g.fillOval(x, y + Game.MAP_SIZE, rp * 2, rp * 2);
            }

            // Débordement bas/droite
            if (x > Game.MAP_SIZE - 2*rp) {
                g.fillOval(x - Game.MAP_SIZE, y, rp * 2, rp * 2);
            }

            if (y > Game.MAP_SIZE - 2*rp) {
                g.fillOval(x, y - Game.MAP_SIZE, rp * 2, rp * 2);
            }

            // Débordement des coins
            if (x < 0 && y < 0) { // HAUT GAUCHE
                g.fillOval(x + Game.MAP_SIZE, y + Game.MAP_SIZE, rp * 2, rp * 2);
            } else if (x < 0 && y > Game.MAP_SIZE - 2*rp) { // BAS GAUCHE
                g.fillOval(x + Game.MAP_SIZE, y - Game.MAP_SIZE, rp * 2, rp * 2);
            } else if  (x > Game.MAP_SIZE - 2*rp && y > Game.MAP_SIZE - 2*rp) { // BAS DROITE
                g.fillOval(x - Game.MAP_SIZE, y - Game.MAP_SIZE, rp * 2, rp * 2);
            } else if (x > Game.MAP_SIZE - 2*rp && y < 0) { // HAUT DROITE
                g.fillOval(x - Game.MAP_SIZE, y + Game.MAP_SIZE, rp * 2, rp * 2);
            }

            g.setColor(Color.black);
            g.drawString(p.getName(), x - (p.getName().length() / 2), y);
        }
    }
}

class MouseHandler extends MouseAdapter
{
    private final PanelView pv;

    public MouseHandler(PanelView pv) {
        this.pv = pv;
    }

    public void mouseClicked(MouseEvent e)
    {
        pv.grabFocus();
    }
}
