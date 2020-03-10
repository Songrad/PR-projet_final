package View;
import javax.swing.*;
import java.awt.*;
import Model.*;

public class FrameGame extends JFrame {
	private Game model;
	public FrameGame(Game model){
		this.setTitle("Game");
		this.model = model;
		this.setSize(300, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	public void paintComponent(Graphics g) {
		g.setColor(Color.red);
		for (Player p : this.model.getlistPlayer()) {
			int r = p.getSize();
			int x = p.getX()-(r);
			int y = p.getY()-(r);
			g.fillOval(x,y,r,r);
		}
	}
}
