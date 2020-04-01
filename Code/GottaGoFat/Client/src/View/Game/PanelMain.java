package View.Game;

import Controler.GameStart;
import Model.Game;

import javax.swing.*;
import java.awt.*;

public class PanelMain extends JPanel
{

    private PanelView panelView;
    private PanelInfo panelInfo;
    private GameStart ctrl;

    public PanelMain(GameStart controleur)
    {
        ctrl = controleur;

        this.setLayout(new BorderLayout());

        this.panelView = new PanelView(ctrl);
        this.add(this.panelView);

        this.panelInfo = new PanelInfo(ctrl);
        this.add(this.panelInfo,BorderLayout.EAST);

    }

    public void majIHM()
    {
        this.panelInfo.majInfo();
    }
}
