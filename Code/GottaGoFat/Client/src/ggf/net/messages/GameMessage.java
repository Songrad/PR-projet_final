package ggf.net.messages;

import ggf.net.MemoryStream;
import ggf.net.messages.client.ClientHandshake;
import ggf.net.messages.client.InputState;
import ggf.net.messages.server.EndGame;
import ggf.net.messages.server.GameState;
import ggf.net.messages.server.ServerHandshake;

public abstract class GameMessage
{
	// MESSAGES SERVEUR
	public static final int HANDSHAKE_SERVER = 101;
	public static final int GAME_STATE = 111;

	// MESSAGES CLIENT
	public static final int HANDSHAKE_CLIENT = 201;
	public static final int INPUT_STATE = 211;
	public static final int END_GAME = 212;

	public abstract int getMessageType();
	public abstract byte[] getDataBytes();

	public abstract GameMessage private_parseMessage(byte[] bytes);

	public static GameMessage parseMessage(byte[] bytes) {
		int type = new MemoryStream(bytes).getInt();

		switch (type) {
			case HANDSHAKE_CLIENT:
				return new ClientHandshake().private_parseMessage(bytes);
			case INPUT_STATE:
				return new InputState().private_parseMessage(bytes);
			case HANDSHAKE_SERVER:
				return new ServerHandshake().private_parseMessage(bytes);
			case GAME_STATE:
				return new GameState().private_parseMessage(bytes);
			case END_GAME:
				return new EndGame();
		}

		return null;
	}
}
