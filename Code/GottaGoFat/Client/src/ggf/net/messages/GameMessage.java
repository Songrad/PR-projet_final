package ggf.net.messages;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;

public abstract class GameMessage
{
	// MESSAGES SERVEUR
	public static final int HANDSHAKE_SERVER = 101;
	public static final int GAME_STATE = 111;

	// MESSAGES CLIENT
	public static final int HANDSHAKE_CLIENT = 201;
	public static final int INPUT_STATE = 211;

	public abstract int getMessageType();
	public abstract byte[] getDataBytes();

	public abstract void parseMessage(byte[] bytes);
}
