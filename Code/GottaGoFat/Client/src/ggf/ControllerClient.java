package ggf;

import ggf.game.GameObject;
import ggf.game.Player;
import ggf.gui.menu.FrameHome;
import ggf.net.GameClient;
import ggf.net.messages.server.GameState;

import java.awt.*;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class ControllerClient implements Controller
{
	private GameClient client;
	private GameState state;
	private FrameHome frame;

	private String playerName = "Glouton";
	private String serverAddress = "localhost:57300";
	private Color color = Color.RED;

	private ArrayList<GameObject> visibleObjects = new ArrayList<>();
	private ArrayList<Player> players = new ArrayList<>();
	private int remainingCount;
	private boolean invisible;
	private int idPlayer;
	private boolean gameOver;

	public ControllerClient()
	{
		this.frame = new FrameHome(this);
	}

	public void sendInputs(int keys)
	{
		if (!this.gameOver)
			client.sendInputs(keys);
	}

	@Override
	public int getRemainingObjectCount()
	{
		return this.remainingCount;
	}

	@Override
	public ArrayList<GameObject> getVisibleObjects()
	{
		return this.visibleObjects;
	}

	@Override
	public ArrayList<Player> getPlayerList()
	{
		return this.players;
	}

	@Override
	public void sendMessage(String message)
	{
		// TODO
	}

	@Override
	public String getMessages()
	{
		// TODO
		return null;
	}

	@Override
	public void updateGui()
	{
		this.frame.majIHM();
	}

	@Override
	public boolean isInvisible()
	{
		return this.invisible;
	}

	@Override
	public int getIdPlayer()
	{
		return this.idPlayer;
	}

	public void saveOptions(String host, String name, Color color)
	{
		this.serverAddress = host;
		this.playerName = name;
		this.color = color;
	}

	public String getServerAddress()
	{
		return this.serverAddress;
	}

	public String getPlayerName()
	{
		return this.playerName;
	}

	public Color getColor()
	{
		return this.color;
	}

	public static void main(String[] args)
	{
		new ControllerClient();
	}

	public void connect()
	{
		try
		{
			this.client = new GameClient(serverAddress, this);
			new Thread(this.client).start();
			this.client.sendPlayer(this.playerName, this.color);
		}
		catch (SocketException | UnknownHostException e)
		{
			e.printStackTrace();
		}
	}

	public void setVisibleObjects(ArrayList<GameObject> visibleObjects)
	{
		this.visibleObjects = visibleObjects;
	}

	public void setPlayers(ArrayList<Player> players)
	{
		this.players = players;
	}

	public void setRemainingObjectCount(int remainingCount)
	{
		this.remainingCount = remainingCount;
	}

	public void setInvisible(boolean invisible)
	{
		this.invisible = invisible;
	}

	public void setIdPlayer(int idPlayer)
	{
		this.idPlayer = idPlayer;
	}

	public void gameOver()
	{
		this.gameOver = true;
		this.frame.gameOver();
	}
}
