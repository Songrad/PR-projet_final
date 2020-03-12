package View;
import javax.swing.*;
import java.awt.*;
import Model.*;

public class FrameGame extends JFrame {
	private Game model;
	public FrameGame(Game model){
		this.setTitle("Game");
		PanelView pl = new PanelView(model);
		this.setContentPane(pl);
		this.setSize(600, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
}
