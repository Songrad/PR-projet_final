package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import Model.*;

class PanelView extends JPanel implements KeyListener
{
    private Game model;
    private int idP;
    private int xi, yi, rp;

    private ArrowKeyManager akm;

    public PanelView(Game model)
    {
        this.model = model;
        this.idP = 0; // TODO: Paramètre
        this.setOpaque(true);
        this.setFocusable(true);
        this.akm = new ArrowKeyManager();
        this.addKeyListener(akm);

		new Timer(16, e -> {
			int keys = akm.getKeys();

			final int STEP = 2;

			int dx = 0, dy = 0;

			if ((keys & ArrowKeyManager.LEFT)  != 0) dx -= STEP;
			if ((keys & ArrowKeyManager.RIGHT) != 0) dx += STEP;

			if ((keys & ArrowKeyManager.UP)   != 0) dy -= STEP;
			if ((keys & ArrowKeyManager.DOWN) != 0) dy += STEP;

			xi += dx; yi += dy;

			this.model.setCoordPlayer(dx, dy, idP);
			this.model.toucheItem(idP);

			this.repaint();
		}).start();
    }

    public void paintComponent(Graphics g)
    {
        g.setColor(Color.white);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());

        //for (GameObject go : this.model.getlistObject())
        for(int i = 0; i<this.model.getnbPaint();i++){
						GameObject go = this.model.getlistObject().get(i);
            int r = go.getR();
            int x = go.getX() - r;
            int y = go.getY() - r;
            g.setColor(Color.green);

            g.fillOval(x, y, r*2, r*2);
        }

        g.setColor(Color.red);
        for (Player p : this.model.getlistPlayer())
        {
            rp = p.getR();
            int x = p.getX() - rp;
            int y = p.getY() - rp;
            g.fillOval(x, y, rp*2, rp*2);
            g.setColor(Color.black);
						g.drawString(p.getName(), x - (p.getName().length() / 2), y);
            g.drawString(p.getName()+" : "+p.getScore()+" points", 100 ,100);
        }
    }

    public void keyPressed(KeyEvent ke)
    {
        int key = ke.getKeyCode();
        yi = xi = 0;
        switch (key){
            case KeyEvent.VK_DOWN:  yi =  10; break;
            case KeyEvent.VK_UP:    yi = -10; break;
            case KeyEvent.VK_RIGHT: xi =  10; break;
            case KeyEvent.VK_LEFT:  xi = -10;break;
        }
        //Pour le mode Thorique
        //if (xi>this.getWidth()-rp)  xi=0;  if (xi<0) xi=this.getWidth()-rp;
        //if (yi>this.getHeight()-rp) yi=0; if  (yi<0) yi=this.getHeight()-rp;
        this.model.setCoordPlayer(xi, yi, idP);
        this.model.toucheItem(idP);
        this.revalidate();
        this.repaint();
    }

    public void keyReleased(KeyEvent ke)
    {
    }

    public void keyTyped(KeyEvent ke)
    {
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
