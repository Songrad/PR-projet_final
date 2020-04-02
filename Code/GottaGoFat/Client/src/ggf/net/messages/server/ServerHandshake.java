package ggf.net.messages.server;

import ggf.net.MemoryStream;
import ggf.net.messages.GameMessage;

public class ServerHandshake extends GameMessage
{
	private int idPlayer;

	public ServerHandshake() {}

	public ServerHandshake(int idPlayer)
	{
		this.idPlayer = idPlayer;
	}

	@Override
	public int getMessageType()
	{
		return GameMessage.HANDSHAKE_SERVER;
	}

	@Override
	public byte[] getDataBytes()
	{
		MemoryStream ms = new MemoryStream();

		ms.putInt(GameMessage.HANDSHAKE_SERVER);
		ms.putInt(this.idPlayer);

		return ms.toArray();
	}

	@Override
	public GameMessage private_parseMessage(byte[] bytes)
	{
		MemoryStream ms = new MemoryStream(bytes);

		ms.seek(4);
		this.idPlayer = ms.getInt();

		return this;
	}

	public int getIdPlayer()
	{
		return idPlayer;
	}
}
