package ggf.net;

import java.net.InetSocketAddress;

public class Message
{
	private final InetSocketAddress address;
	private final byte[] data;

	public Message(byte[] data, InetSocketAddress address)
	{
		this.data = data;
		this.address = address;
	}

	public InetSocketAddress getAddress()
	{
		return address;
	}

	public byte[] getData()
	{
		return data;
	}
}
