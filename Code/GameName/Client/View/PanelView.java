package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import Model.*;

class PanelView extends JPanel{
	private Game model;
	public PanelView(Game model){
		this.model = model;
	}
	public void paintComponent(Graphics g) {

		g.setColor(Color.green);
		for (GameObject go : this.model.getlistObject()) {
			int r = go.getSize()/2;
			int x = go.getX()-(r/2);
			int y = go.getY()-(r/2);
			g.fillOval(x,y,r,r);
		}

		g.setColor(Color.red);
		for (Player p : this.model.getlistPlayer()) {
			int r = p.getSize()/2;
			int x = p.getX()-(r/2);
			int y = p.getY()-(r/2);
			g.fillOval(x,y,r,r);
			g.setColor(Color.black);
			g.drawString(p.getName(), x-(p.getName().length()/2), y);
		}
	}
}
