package ggf.gui.game;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ArrowKeyManager extends KeyAdapter
{
    public static final int LEFT = 0b1000;
    public static final int UP = 0b0100;
    public static final int RIGHT = 0b0010;
    public static final int DOWN = 0b0001;

    private boolean leftDown = false;
    private boolean upDown = false;
    private boolean rightDown = false;
    private boolean downDown = false;

    public void keyPressed(KeyEvent e)
    {
        switch (e.getKeyCode())
        {
            case KeyEvent.VK_LEFT: leftDown = true; break;
            case KeyEvent.VK_UP: upDown = true; break;
            case KeyEvent.VK_RIGHT: rightDown = true; break;
            case KeyEvent.VK_DOWN: downDown = true; break;
        }
    }

    public void keyReleased(KeyEvent e)
    {
        switch (e.getKeyCode())
        {
            case KeyEvent.VK_LEFT: leftDown = false; break;
            case KeyEvent.VK_UP: upDown = false; break;
            case KeyEvent.VK_RIGHT: rightDown = false; break;
            case KeyEvent.VK_DOWN: downDown = false; break;
        }
    }

    public int getKeys()
    {
        int keys = 0;

        if (leftDown) keys |= LEFT;
        if (upDown) keys |= UP;
        if (rightDown) keys |= RIGHT;
        if (downDown) keys |= DOWN;

        return keys;
    }
}
