package View.Menu;

import javax.swing.*;
import java.awt.*;

public class OptionsPanel extends JPanel
{
	public OptionsPanel()
	{
		this.add(new KeyValuePanel("Serveur", KeyValuePanel.TEXT));
		this.add(new KeyValuePanel("Nom du joueur", KeyValuePanel.TEXT));
		this.add(new KeyValuePanel("Couleur", KeyValuePanel.COLOR));

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
	private String value = null;

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
}

class ColorChooserButton extends JPanel
{
	private Color chosenColor = Color.RED;
	private JPanel panelPreview;

	public ColorChooserButton()
	{
		this.setLayout(new GridBagLayout());

		this.panelPreview = new JPanel();
		this.panelPreview.setBackground(chosenColor);
		this.panelPreview.setPreferredSize(new Dimension(24,24));

		JButton btn = new JButton("Choisir");

		btn.addActionListener(e -> {
			Color color = JColorChooser.showDialog(this, "Couleur du joueur", null);

			if (color != null)
				chosenColor = color;

			panelPreview.setBackground(chosenColor);
		});

		this.add(panelPreview);

		GridBagConstraints c = new GridBagConstraints();
		c.gridwidth = 3;
		this.add(btn, c);
	}
}

















