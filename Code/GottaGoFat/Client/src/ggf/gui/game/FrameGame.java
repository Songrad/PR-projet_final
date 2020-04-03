package ggf.gui.game;
import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import ggf.Controller;

public class FrameGame extends JFrame {

	private Controller ctrl;
	private PanelMain panelMain;

	public FrameGame(Controller controleur){

		this.setTitle("Game");
		this.ctrl = controleur;
		this.panelMain = new PanelMain(this.ctrl);
		this.setContentPane(panelMain);
		this.setSize(600, 600);
		this.pack();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	public void majIHM()
	{
		this.panelMain.majIHM();
	}
}