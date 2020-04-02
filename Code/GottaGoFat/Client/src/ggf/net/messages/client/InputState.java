package ggf.net.messages.client;

import ggf.net.MemoryStream;
import ggf.net.messages.GameMessage;

public class InputState extends GameMessage
{
	private int keys;

	public InputState() {}

	public InputState(int keys) {
		this.keys = keys;
	}

	@Override
	public int getMessageType()
	{
		return GameMessage.INPUT_STATE;
	}

	@Override
	public byte[] getDataBytes()
	{
		MemoryStream ms = new MemoryStream();

		ms.putInt(GameMessage.INPUT_STATE);
		ms.putInt(this.keys);

		return ms.toArray();
	}

	@Override
	public GameMessage private_parseMessage(byte[] bytes)
	{
		MemoryStream ms = new MemoryStream(bytes);
		ms.seek(4);

		this.keys = ms.getInt();

		return this;
	}

	public int getInputs()
	{
		return this.keys;
	}
}
