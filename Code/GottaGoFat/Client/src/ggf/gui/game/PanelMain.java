package ggf.gui.game;

import ggf.Controller;

import javax.swing.*;
import java.awt.*;

public class PanelMain extends JPanel
{

    private PanelView panelView;
    private PanelInfo panelInfo;
    private Controller ctrl;

    public PanelMain(Controller controleur)
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
