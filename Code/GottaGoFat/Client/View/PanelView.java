package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import Model.*;

class PanelView extends JPanel implements KeyListener{
	private Game model;
	private int idP;
	private int xi,yi,rp;
	public PanelView(Game model){
		this.model = model;
		this.idP = 0; //Future paramettre
		this.setOpaque(true);
		this.setFocusable(true);
		this.addKeyListener(this);
	}
	public void paintComponent(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());

		for (GameObject go : this.model.getlistObject()) {
			int r = go.getSize()/4;
			int x = go.getX()-r;
			int y = go.getY()-r;
			if (go.getTaken()) g.setColor(Color.blue);
			else               g.setColor(Color.green);

			g.fillOval(x,y,r,r);
		}

		g.setColor(Color.red);
		for (Player p : this.model.getlistPlayer()) {
			rp = p.getSize()/2;
			int x = p.getX()-rp;
			int y = p.getY()-rp;
			g.fillOval(x,y,rp,rp);
			g.setColor(Color.black);
			g.drawString(p.getName(), x-(p.getName().length()/2), y);
		}
	}

	public void keyPressed(KeyEvent ke) {
		int key = ke.getKeyCode();
		yi = xi =0;
		switch (key) {
			case KeyEvent.VK_DOWN : yi=10;  break;
			case KeyEvent.VK_UP   : yi=-10;  break;
			case KeyEvent.VK_RIGHT: xi=10;  break;
			case KeyEvent.VK_LEFT : xi=-10;  break;

		}
		//Pour le mode Thorique
		//if (xi>this.getWidth()-rp)  xi=0;  if (xi<0) xi=this.getWidth()-rp;
		//if (yi>this.getHeight()-rp) yi=0; if  (yi<0) yi=this.getHeight()-rp;
		this.model.setCoordPlayer(xi, yi, idP);
		this.model.toucheItem(idP);
		this.revalidate();
		this.repaint();
	}
	public void keyReleased(KeyEvent ke) {}
		public void keyTyped(KeyEvent ke) {}
		}
