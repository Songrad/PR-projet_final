package ggf.net;

import java.io.IOException;
import java.net.*;

public class UdpClient
{
    private final int PACKET_MAX_LENGTH = 4096;

    private byte[] inputBytes = new byte[PACKET_MAX_LENGTH];
    private byte[] outputBytes = new byte[0];

    private final DatagramPacket outputPacket;
    private final DatagramPacket inputPacket;

    private final DatagramSocket socket;

    public UdpClient(String ipAddr, int port) throws UnknownHostException, SocketException
    {
        this.socket = new DatagramSocket();
        this.socket.connect(InetAddress.getByName(ipAddr), port);

        this.outputPacket = new DatagramPacket(outputBytes, outputBytes.length, InetAddress.getByName(ipAddr), port);
        this.inputPacket = new DatagramPacket(inputBytes, PACKET_MAX_LENGTH);
    }

    public void send(byte[] data) throws IOException
    {
        this.outputBytes = data;

        this.outputPacket.setData(this.outputBytes);
        this.outputPacket.setLength(this.outputBytes.length);

        this.socket.send(this.outputPacket);
    }

    public byte[] receive() throws IOException
    {
        this.inputBytes = new byte[PACKET_MAX_LENGTH];
        this.inputPacket.setData(this.inputBytes);
        this.inputPacket.setLength(PACKET_MAX_LENGTH);

        this.socket.receive(this.inputPacket);

        byte[] data = new byte[this.inputPacket.getLength()];

        System.arraycopy(this.inputBytes, 0, data, 0, data.length);

        return data;
    }

    public boolean connected()
    {
        return !this.socket.isClosed();
    }
}
