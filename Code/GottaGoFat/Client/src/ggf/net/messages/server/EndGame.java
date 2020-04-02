package ggf.net.messages.server;

import ggf.net.MemoryStream;
import ggf.net.messages.GameMessage;

public class EndGame extends GameMessage
{
	@Override
	public int getMessageType()
	{
		return GameMessage.END_GAME;
	}

	@Override
	public byte[] getDataBytes()
	{
		MemoryStream ms = new MemoryStream();

		ms.putInt(GameMessage.END_GAME);

		return ms.toArray();
	}

	@Override
	public GameMessage private_parseMessage(byte[] bytes)
	{
		return this;
	}
}
