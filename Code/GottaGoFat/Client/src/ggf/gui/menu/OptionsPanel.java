package ggf.gui.menu;

import ggf.Controller;
import ggf.ControllerClient;

import javax.swing.*;
import java.awt.*;

public class OptionsPanel extends JPanel
{
	public OptionsPanel(Controller ctrl)
	{
		JButton enregistrer;
		KeyValuePanel server;
		KeyValuePanel playerName;
		KeyValuePanel color;

		this.add(server = new KeyValuePanel("Serveur", KeyValuePanel.TEXT));
		this.add(playerName = new KeyValuePanel("Nom du joueur", KeyValuePanel.TEXT));
		this.add(color = new KeyValuePanel("Couleur", KeyValuePanel.COLOR));
		this.add(enregistrer = new JButton("Enregistrer"));

		if (ctrl instanceof ControllerClient) {
			ControllerClient ccl = (ControllerClient) ctrl;

			server.setValue(ccl.getServerAddress());
			playerName.setValue(ccl.getPlayerName());
			color.setValue(ccl.getColor());
		}

		enregistrer.addActionListener(e -> {
			if (ctrl instanceof ControllerClient) {
				ControllerClient ccl = (ControllerClient) ctrl;

				ccl.saveOptions(
						(String)server.getValue(),
						(String)playerName.getValue(),
						(Color)color.getValue()
				);
			}
		});

		this.setLayout(new GridLayout(this.getComponentCount(), 1));
	}
}

class KeyValuePanel extends JPanel
{
	public static final int TEXT = 0;
	public static final int NUMERIC = 1;
	public static final int COLOR = 2;

	private int type;

	private Component valueInput = null;

	public KeyValuePanel(String label)
	{
		this(label, TEXT);
	}

	public KeyValuePanel(String label, int type)
	{
		this.type = type;

		this.setLayout(new GridLayout(1, 2));

		this.add(new JLabel(label));

		switch (type)
		{
			case TEXT:
				this.valueInput = new JTextField();
				break;
			case NUMERIC:
				this.valueInput = new JSpinner();
				break;
			case COLOR:
				this.valueInput = new ColorChooserButton();
				break;
		}

		this.valueInput.setPreferredSize(new Dimension(200, 32));
		this.add(valueInput);
	}

	public Object getValue() {
		switch (type)
		{
			case TEXT:
				return ((JTextField)this.valueInput).getText();
			case NUMERIC:
				return ((JSpinner)this.valueInput).getValue();
			case COLOR:
				return ((ColorChooserButton)this.valueInput).getColor();
		}

		return null;
	}

	public void setValue(Object value) {
		switch (type)
		{
			case TEXT:
				((JTextField)this.valueInput).setText((String) value); break;
			case NUMERIC:
				((JSpinner)this.valueInput).setValue(value); break;
			case COLOR:
				((ColorChooserButton)this.valueInput).setColor((Color) value); break;
		}
	}
}

class ColorChooserButton extends JPanel
{
	private Color chosenColor = Color.RED;
	private final JPanel panelPreview;

	public ColorChooserButton()
	{
		this.setLayout(new GridBagLayout());

		this.panelPreview = new JPanel();
		this.panelPreview.setBackground(chosenColor);
		this.panelPreview.setPreferredSize(new Dimension(24,24));

		JButton btn = new JButton("Choisir");

		btn.addActionListener(e -> {
			Color color = JColorChooser.showDialog(this, "Couleur du joueur", this.chosenColor);

			if (color != null)
				chosenColor = color;

			panelPreview.setBackground(chosenColor);
		});

		this.add(panelPreview);

		GridBagConstraints c = new GridBagConstraints();
		c.gridwidth = 3;
		this.add(btn, c);
	}

	public Color getColor() {
		return this.chosenColor;
	}

	public void setColor(Color value)
	{
		this.chosenColor = value;
		this.repaint();
	}
}

















