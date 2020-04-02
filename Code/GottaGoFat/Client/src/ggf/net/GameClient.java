package ggf.net;

import ggf.ControllerClient;
import ggf.net.messages.GameMessage;
import ggf.net.messages.client.ClientHandshake;
import ggf.net.messages.client.InputState;
import ggf.net.messages.server.EndGame;
import ggf.net.messages.server.GameState;
import ggf.net.messages.server.ServerHandshake;

import java.awt.*;
import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;

public class GameClient implements Runnable
{
	private ControllerClient ctrl;
	private UdpClient client;

	public GameClient(String host, ControllerClient ctrl) throws SocketException, UnknownHostException
	{
		this.ctrl = ctrl;
		String[] parts = host.split(":");
		this.client = new UdpClient(parts[0], Integer.parseInt(parts[1]));
	}

	// LISTEN method
	public void run()
	{
		while(true) {
			try
			{
				System.out.println("Receiving");
				byte[] msg = client.receive();

				GameMessage gm = GameMessage.parseMessage(msg);

				if (gm instanceof GameState) {
					GameState gs = (GameState) gm;

					ctrl.setVisibleObjects(gs.getVisibleObjects());
					ctrl.setPlayers(gs.getPlayers());
					ctrl.setRemainingObjectCount(gs.getRemainingObjectCount());
					ctrl.setInvisible(gs.isInvisible());
					System.out.println("gamestate, update gui");
					ctrl.updateGui();
				} else if (gm instanceof ServerHandshake) {
					ServerHandshake sh = (ServerHandshake) gm;

					ctrl.setIdPlayer(sh.getIdPlayer());
				} else if (gm instanceof EndGame) {
					ctrl.gameOver();
				}
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}

	public void sendPlayer(String name, Color color ){
		try
		{
			client.send(new ClientHandshake(name, color).getDataBytes());
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public void sendInputs(int keys)
	{
		try
		{
			client.send(new InputState(keys).getDataBytes());
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
