package ggf;

import ggf.game.GameObject;
import ggf.game.Player;

import java.util.ArrayList;

public interface Controller
{
	void sendInputs(int keys);
	int getRemainingObjectCount();
	ArrayList<GameObject> getVisibleObjects();
	ArrayList<Player> getPlayerList();
	void sendMessage(String message);
	String getMessages();
	void updateGui();
	boolean isInvisible();
	int getIdPlayer();
}
