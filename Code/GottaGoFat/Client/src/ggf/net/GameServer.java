package ggf.net;

import ggf.game.Game;
import ggf.gui.game.ArrowKeyManager;
import ggf.net.messages.server.EndGame;
import ggf.net.messages.server.GameState;

import javax.swing.*;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.HashMap;

public class GameServer
{
	private UdpServer server;
	private Game game;

	private Timer runTimer;

	private HashMap<InetSocketAddress, ClientHandler> hmClients;

	public GameServer(int port, boolean invisible) throws IOException
	{
		this.server = new UdpServer(port);
		//this.game = new Game((int)(Math.random() * 500 + 500), invisible);
		this.game = new Game(1, invisible);

		this.hmClients = new HashMap<>();

		this.listen();
	}

	private void listen()
	{
		this.runTimer = new Timer(16, e -> run());
		this.runTimer.start();

		Message msg;
		while (true) try
		{
			msg = server.receive();

			if (!hmClients.containsKey(msg.getAddress()))
			{
				ClientHandler tmp = new ClientHandler(msg.getAddress(), this.game, this);
				hmClients.put(msg.getAddress(), tmp);
			}

			ClientHandler handler = hmClients.get(msg.getAddress());

			handler.processRequest(msg);
		}
		catch (IOException ignored)
		{
		}
	}

	public void run()
	{
		if (hmClients.size() >= 2) {
			for (ClientHandler handler : hmClients.values())
			{
				int keys = handler.getInputs();
				final short STEP = 2;

				short dx = 0, dy = 0;

				if ((keys & ArrowKeyManager.LEFT)  != 0) dx -= STEP;
				if ((keys & ArrowKeyManager.RIGHT) != 0) dx += STEP;

				if ((keys & ArrowKeyManager.UP)   != 0) dy -= STEP;
				if ((keys & ArrowKeyManager.DOWN) != 0) dy += STEP;

				this.game.movePlayer(handler.getPlayerId(), dx, dy);
			}
		}

		byte[] gs = new GameState(game).getDataBytes();
		for (ClientHandler handler : hmClients.values())
		{
			try
			{
				server.send(gs, handler.getAddress());
			}
			catch (IOException ignored){}
		}

		if (game.gameOver())
		{
			for (ClientHandler handler : hmClients.values())
			{
				try
				{
					server.send(new EndGame().getDataBytes(), handler.getAddress());
				}
				catch (IOException ignored){}
			}

			System.exit(1);
		}
	}

	public void send(InetSocketAddress address, byte[] data) throws IOException
	{
		server.send(data, address);
	}

	public static void main(String[] args) throws IOException
	{
		new GameServer(57300, false);
	}
}
