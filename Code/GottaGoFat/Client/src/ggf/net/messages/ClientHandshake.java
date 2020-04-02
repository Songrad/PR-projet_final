package ggf.net.messages;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class ClientHandshake extends GameMessage
{
	private final String name;

	public ClientHandshake(String name) {
		this.name = name;
	}

	@Override
	public int getMessageType()
	{
		return GameMessage.HANDSHAKE_CLIENT;
	}

	@Override
	public byte[] getDataBytes()
	{
		return this.name.getBytes(StandardCharsets.UTF_8);
	}

	@Override
	public void parseMessage(byte[] bytes)
	{
		ByteArrayInputStream bais = new ByteArrayInputStream(bytes);


	}
}
