package View;

import Model.Game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class MenuPanel extends JPanel
{
	public MenuPanel(Game model)
	{
		this.setLayout(new BorderLayout());

		// Header
		JPanel header = new JPanel(new BorderLayout());
		{
			// Logo
			BufferedImage logo = null;
			try {
				logo = ImageIO.read(getClass().getResourceAsStream("/View/img/logo.png"));
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
		bodyButtons.setPreferredSize(new Dimension(150,150));

		// Création des boutons
		{
			JButton play = new JButton("Jouer");

			// Au clic sur Jouer
			play.addActionListener(e -> {
				// Cacher la fenêtre courante
				SwingUtilities.getWindowAncestor(this).setVisible(false);
				// Passer la fenêtre menu à la FrameJeu
				new FrameGame(model, (JFrame) SwingUtilities.getWindowAncestor(this));
			});

			JButton options = new JButton("Options");

			JButton best = new JButton("Meilleurs scores");

			bodyButtons.add(play);
			bodyButtons.add(options);
			bodyButtons.add(best);
		}

		body.add(bodyButtons);

		this.add(header, BorderLayout.NORTH);
		this.add(body);
	}
}
