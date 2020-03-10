package GameName.Client.View;
import javax.swing.*;

public class FrameGame extends JFrame {
	private Game model;
	public FrameGame(Game model){
		this.setTitle("Game");
		this.model = model;
		this.setSize(300, 300);
		this.setVisible(true);
	}
	public void paint(Graphics g) {
		super.paint(g);
		for (Player p : Board.getlistPlayer()) {

		}
	}
}
