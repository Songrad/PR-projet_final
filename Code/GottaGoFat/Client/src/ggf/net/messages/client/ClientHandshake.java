package ggf.net.messages.client;

import ggf.net.MemoryStream;
import ggf.net.messages.GameMessage;

import java.awt.*;
import java.io.ByteArrayInputStream;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class ClientHandshake extends GameMessage
{
	private String name;
	private Color color;

	public ClientHandshake() {}

	public ClientHandshake(String name, Color color) {
		this.name = name;
		this.color = color;
	}

	@Override
	public int getMessageType()
	{
		return GameMessage.HANDSHAKE_CLIENT;
	}

	@Override
	public byte[] getDataBytes()
	{
		MemoryStream stream = new MemoryStream();

		stream.putInt(getMessageType());
		stream.putString(name);
		stream.putInt(color.getRGB());

		return stream.toArray();
	}

	@Override
	public GameMessage private_parseMessage(byte[] bytes)
	{
		MemoryStream ms = new MemoryStream(bytes);
		ms.seek(4);

		this.name = ms.getString();
		this.color = new Color(ms.getInt());

		return this;
	}

	public String getName()
	{
		return name;
	}

	public Color getColor()
	{
		return color;
	}
}
