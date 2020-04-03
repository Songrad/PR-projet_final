package ggf.net;

import ggf.game.Game;
import ggf.net.messages.GameMessage;
import ggf.net.messages.client.ClientHandshake;
import ggf.net.messages.client.InputState;
import ggf.net.messages.server.ServerHandshake;
import jdk.internal.util.xml.impl.Input;

import java.io.IOException;
import java.net.InetSocketAddress;

public class ClientHandler
{
	private GameServer server;
	private InetSocketAddress address;
	private Game game;
	private int playerId;

	private int inputs;

	public ClientHandler(InetSocketAddress address, Game game, GameServer gameServer)
	{
		this.server = gameServer;
		this.game = game;
		this.address = address;
		this.inputs = 0;
	}

	public InetSocketAddress getAddress()
	{
		return this.address;
	}

	public void processRequest(Message message) throws IOException
	{
		GameMessage gm = GameMessage.parseMessage(message.getData());

		if (gm instanceof ClientHandshake)
		{
			ClientHandshake ch = (ClientHandshake) gm;
			this.playerId = game.addPlayer(ch.getName(), ch.getColor());
			server.send(this.address, new ServerHandshake(playerId).getDataBytes());
		}
		else if (gm instanceof InputState)
		{
			InputState is = (InputState) gm;
			this.inputs = is.getInputs();
		}
	}


	public int getInputs()
	{
		return inputs;
	}

	public int getPlayerId()
	{
		return playerId;
	}
}
