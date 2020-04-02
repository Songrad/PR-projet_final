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

        this.idP = 0; // TODO: Paramètre
        this.setOpaque(true);
        this.setFocusable(true);
        this.akm = new ArrowKeyManager();
        this.addKeyListener(akm);

		new Timer(16, e -> {
			int keys = akm.getKeys();

			final short STEP = 2;

			short dx = 0, dy = 0;

			if ((keys & ArrowKeyManager.LEFT)  != 0) dx -= STEP;
			if ((keys & ArrowKeyManager.RIGHT) != 0) dx += STEP;

			if ((keys & ArrowKeyManager.UP)   != 0) dy -= STEP;
			if ((keys & ArrowKeyManager.DOWN) != 0) dy += STEP;

			xi += dx; yi += dy;


            this.ctrl.movePlayer(idP, dx,dy);

			this.repaint();
		}).start();
    }

    public void paintComponent(Graphics g)
    {
        ((Graphics2D)g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g.setColor(Color.white);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());

        //for (GameObject go : this.ggf.model.getlistObject())
        for(int i = 0; i<this.ctrl.getVisibleObjectCount();i++){
						GameObject go = this.ctrl.getObjectList().get(i);
            int r = go.getRadius();
            int x = go.getX() - r;
            int y = go.getY() - r;
            g.setColor(Color.green);

            g.fillOval(x, y, r*2, r*2);
        }


        ArrayList<Player> playerList = this.ctrl.getPlayerList();
        for (int i = 0; i < playerList.size(); i++)
        {
            Player p = playerList.get(i);
            if (i == idP)
                g.setColor(Color.red);
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

class ArrowKeyManager extends KeyAdapter
{
    public static final int LEFT = 0b1000;
    public static final int UP = 0b0100;
    public static final int RIGHT = 0b0010;
    public static final int DOWN = 0b0001;

    private boolean leftDown = false;
    private boolean upDown = false;
    private boolean rightDown = false;
    private boolean downDown = false;

    public void keyPressed(KeyEvent e)
    {
        switch (e.getKeyCode())
        {
            case KeyEvent.VK_LEFT: leftDown = true; break;
            case KeyEvent.VK_UP: upDown = true; break;
            case KeyEvent.VK_RIGHT: rightDown = true; break;
            case KeyEvent.VK_DOWN: downDown = true; break;
        }
    }

    public void keyReleased(KeyEvent e)
    {
        switch (e.getKeyCode())
        {
            case KeyEvent.VK_LEFT: leftDown = false; break;
            case KeyEvent.VK_UP: upDown = false; break;
            case KeyEvent.VK_RIGHT: rightDown = false; break;
            case KeyEvent.VK_DOWN: downDown = false; break;
        }
    }

    public int getKeys()
    {
        int keys = 0;

        if (leftDown) keys |= LEFT;
        if (upDown) keys |= UP;
        if (rightDown) keys |= RIGHT;
        if (downDown) keys |= DOWN;

        return keys;
    }
}
