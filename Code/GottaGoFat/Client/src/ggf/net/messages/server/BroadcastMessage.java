package ggf.net.messages.server;

import ggf.net.MemoryStream;
import ggf.net.messages.GameMessage;

public class BroadcastMessage extends GameMessage
{
	private String message;

	public BroadcastMessage(){}

	public BroadcastMessage(String message) {
		this.message = message;
	}

	@Override
	public int getMessageType()
	{
		return GameMessage.BROADCAST_MESSAGE;
	}

	@Override
	public byte[] getDataBytes()
	{
		MemoryStream ms = new MemoryStream();
		ms.putInt(GameMessage.BROADCAST_MESSAGE);

		ms.putString(message);

		return ms.toArray();
	}

	@Override
	public GameMessage private_parseMessage(byte[] bytes)
	{
		MemoryStream ms = new MemoryStream(bytes);
		ms.seek(4);

		this.message = ms.getString();

		return this;
	}

	public String getMessage()
	{
		return message;
	}
}
