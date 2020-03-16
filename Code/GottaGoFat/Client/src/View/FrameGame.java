package View;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import Model.*;

public class FrameGame extends JFrame {
	private Game model;
	public FrameGame(Game model, JFrame menuFrame){
		this.setTitle("Game");
		PanelView pl = new PanelView(model);
		this.setContentPane(pl);
		this.setSize(600, 600);
		this.addWindowListener(new GameWindowManager(menuFrame));
		this.setVisible(true);
	}
}

class GameWindowManager extends WindowAdapter
{
	private final JFrame menuFrame;

	public GameWindowManager(JFrame menuFrame)
	{
		this.menuFrame = menuFrame;
	}

	@Override
	public void windowClosing(WindowEvent e)
	{
		super.windowClosing(e);
		menuFrame.setVisible(true);
	}
}