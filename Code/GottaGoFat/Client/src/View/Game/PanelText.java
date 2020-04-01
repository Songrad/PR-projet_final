package View.Game;

import Controler.GameStart;

import javax.naming.ldap.Control;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelText extends JPanel implements ActionListener
{

    private JScrollPane pane;
    private JTextArea display;
    private JTextField input;

    private GameStart ctrl;


    public PanelText(GameStart ctrl)
    {
        this.ctrl = ctrl;

        this.setLayout(new BorderLayout());

        this.display = new JTextArea("");
        this.display.setEnabled(true);
        this.display.setColumns(20);
        this.display.setRows(5);
        this.display.setLineWrap(true);
        this.display.setWrapStyleWord(true);
        this.display.setDisabledTextColor(Color.BLACK);

        this.input = new JTextField("");
        this.input.addActionListener(this);

        this.pane = new JScrollPane(this.display);
        this.add(this.pane);
        this.add(this.input,BorderLayout.SOUTH);



    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        String message = this.input.getText();
        if (message.trim().equals("")) return;

        this.ctrl.envoyerMessage(message);

        this.input.setText("");

        this.display.setText(this.ctrl.getMessagesChat());
    }

    public void majChat(){

    }
}
