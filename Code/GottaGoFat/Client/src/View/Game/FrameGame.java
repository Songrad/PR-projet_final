package View.Game;
import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import Controler.GameStart;
import Model.*;

public class FrameGame extends JFrame {

	private GameStart ctrl;
	private PanelMain panelMain;

	public FrameGame(GameStart controleur, JFrame menuFrame){

		this.setTitle("Game");
		this.ctrl = controleur;
		this.panelMain = new PanelMain(this.ctrl);
		this.setContentPane(panelMain);
		this.setSize(600, 600);
		this.addWindowListener(new GameWindowManager(menuFrame));
		this.setVisible(true);
	}

	public void majIHM()
	{
		this.panelMain.majIHM();
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