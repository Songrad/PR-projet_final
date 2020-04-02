package ggf.gui.menu;

import ggf.Controller;
import ggf.gui.game.FrameGame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class MenuPanel extends JPanel
{

	private Controller ctrl;
	private FrameGame frameGame;

	public MenuPanel(Controller controleur)
	{
		this.setLayout(new GridBagLayout());
		this.ctrl = controleur;

		// Header
		JPanel header = new JPanel(new BorderLayout());
		{
			// Logo
			BufferedImage logo = null;
			try {
				logo = ImageIO.read(getClass().getResourceAsStream("/ggf/gui/img/logo.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			header.add(new JLabel(new ImageIcon(logo)), BorderLayout.NORTH);

			// Panel d'information
			JPanel infoPanel = new JPanel(new GridLayout(1, 2));
			infoPanel.add(new JLabel("Serveur: 127.0.0.1:57300"));
			infoPanel.add(new JLabel("Non connecté"));

			// Centrer les éléments de texte du panel d'information
			for (Component c : infoPanel.getComponents())
				if (c instanceof JLabel)
					((JLabel)c).setHorizontalAlignment(JLabel.CENTER);

			header.add(infoPanel);
		}

		// Corps
		JPanel body = new JPanel(new GridBagLayout());

		// Boutons
		JPanel bodyButtons = new JPanel(new GridLayout(3,1,8,8));

		// Création des boutons
		{
			JButton play = new JButton("Jouer");

			// Au clic sur Jouer
			play.addActionListener(e -> {
				FrameHome home = (FrameHome)SwingUtilities.getWindowAncestor(this);
				home.setPanel(null);
				home.setVisible(false);
				//new FrameGame(ctrl, home);
				this.frameGame = new FrameGame(ctrl, home);
			});

			JButton options = new JButton("Options");

			options.addActionListener(e -> {
				FrameHome home = (FrameHome)SwingUtilities.getWindowAncestor(this);
				home.setPanel(new OptionsPanel());
			});

			JButton best = new JButton("Meilleurs scores");

			best.addActionListener(e -> {
				FrameHome home = (FrameHome)SwingUtilities.getWindowAncestor(this);
				home.setPanel(null);
			});

			bodyButtons.add(play);
			bodyButtons.add(options);
			bodyButtons.add(best);
		}

		body.add(bodyButtons);

		this.add(header);
		this.add(body);
	}

	public void majIHM()
	{
		this.frameGame.majIHM();
	}
}
