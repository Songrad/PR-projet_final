package ggf.net;

import java.io.IOException;
import java.net.*;

/*
 * Classe ServeurUdp.java
 * Classe de base pour le serveur en protocole UDP
 * Cette classe permettra les intéractions avec le ClientUdp
 */

public class UdpServer
{
    private final static int MAX_PACKET_SIZE = 2048;
    private final int port;

    DatagramSocket socket;

    public UdpServer(int port) throws SocketException
    {
        this.port = port;
        this.socket = new DatagramSocket(port);
    }

    public int getPort()
    {
        return this.port;
    }

    public Message receive() throws IOException
    {
        byte[] inputBuffer = new byte[MAX_PACKET_SIZE];
        DatagramPacket packet = new DatagramPacket(inputBuffer, inputBuffer.length);
        socket.receive(packet);

        byte[] data = new byte[packet.getLength()];

        System.arraycopy(inputBuffer, 0, data, 0, data.length);

        return new Message(data, (InetSocketAddress) packet.getSocketAddress());
    }

    public void send(byte[] data, InetSocketAddress destination) throws IOException
    {
        DatagramPacket packet = new DatagramPacket(data, data.length, destination);

        socket.send(packet);
    }
}
